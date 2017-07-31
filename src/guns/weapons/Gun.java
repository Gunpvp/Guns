package guns.weapons;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import guns.Action;
import guns.Timer;
import guns.weapons.data.GunData;
import guns.weapons.data.ItemData;

public class Gun {
	
	private static final int ID_SIZE = 10;
	private static final char NORMAL = '-';
	private static final char OUT_OF_AMMO = '*';
	private static final char RELOAD = '#';
	private static final char BURST = '~';
	private static final Random RANDOM = new Random();
	
	private GunData data;
	private int ammo;
	private int burst;
	private int burst_task = -1;
	private int reload_task = -1;
	private int drag_task = -1;
	private boolean reloading;
	private boolean scoping;
	private boolean clicked_while_burst;
	private boolean ready;
	private String id;
	private ItemStack item;

	/**
	 * construct Gun with raw data about gun and set default values
	 * 
	 * @param data
	 */
	public Gun(GunData data) {
		
		this.data = data;
		this.id = generateID();
		this.ammo = data.getReloaddata().getReloadAmount();
		this.item = generateItem();
		this.ready = true;
		
	}
	
	/**
	 * construct Gun of an existing item 
	 */
	public Gun(ItemStack item) {
		
		this.item = item;
		String name = item.getItemMeta().getDisplayName();
		this.id = name.substring(GunMaster.GUN_ITEM_PREFIX.length(),GunMaster.GUN_ITEM_PREFIX.length()+20);
		this.data = GunMaster.getGunData(name.substring(GunMaster.GUN_ITEM_PREFIX.length()+24).split(" ")[0]);
		this.ammo = Integer.parseInt(name.substring(GunMaster.GUN_ITEM_PREFIX.length()+24).split(" ")[2].substring(2));
		this.ready = true;
		
	}
	
	/**
	 * shoot with gun
	 */
	public void shoot(Player p) {
		
		if (!ready) return;
		
		// check if weapon is not reloading or bursting at the moment
		if (!reloading && burst == 0) {
			
			// check if weapon has ammo
			if (ammo > 0) {
				
				// decrement ammo
				ammo--;
				
				// burst with weapon
				burst(p);
				
				// add recoil to player
				recoil(p);
				
				// play sound and update item name
				data.getShootdata().getShootSound().play(p);
				for (int n = 0;n < data.getShootdata().getProjectiles();n++) shootProj(p);
				updateItemName(p);
				
				ready = false;
				Timer.delay(new Action() {
					public void perform() {
						ready = true;
					}
				}, data.getShootdata().getDelayBetweenShots());
				
				// if you run out of ammo play sound
				if (ammo == 0) {
					
					// auto reload
					if (data.getReloaddata().isFullyAutomatic()) reload(p);
					data.getAmmodata().getOutOfAmmoSound().play(p);
					
				}
				
			}
			// if no ammo on shoot play no ammo sound
			else {
				
				reload(p);
				if (!reloading) data.getAmmodata().getShootWithNoAmmoSound().play(p);
				
			}
			
		} else if (!reloading && burst > 0) {
			clicked_while_burst = true;
		}
		
	}
	
	/**
	 * reload gun
	 */
	public void reload(Player p) {
		
		if (reloading) return;
		
		if (scoping) scope(p);
		
		// check if gun does not need ammo
		if (!data.getReloaddata().isReloadingWithAmmo()) {
			
			// start reloading
			reloading = true;
			data.getReloaddata().getReloadSoundStart().play(p);
			updateItemName(p);
			
			if (data.getReloaddata().isReloadingIndividually()) {
				reload_task = Timer.repeat(new Action() {
					
					@Override
					public void perform() {
						Gun.this.ammo += 1;
						data.getReloaddata().getReloadSoundFinish().play(p);
						updateItemName(p);
						if (Gun.this.ammo >= data.getReloaddata().getReloadAmount()) {
							reloading = false;
							data.getReloaddata().getReloadSoundFinish().play(p);
							updateItemName(p);
							Timer.cancel(reload_task);
							reload_task = -1;
						}
					}
				}, data.getReloaddata().getReloadDuration(), data.getReloaddata().getReloadDuration());
			} else {
				Timer.delay(new Action() {
					
					@Override
					public void perform() {
						// set ammo to max and finish reloading
						Gun.this.ammo = Gun.this.data.getReloaddata().getReloadAmount();
						data.getReloaddata().getReloadSoundFinish().play(p);
						reloading = false;
						updateItemName(p);
					}
				}, data.getReloaddata().getReloadDuration());
			}
			
		} 
		// if gun needs ammo
		else {
			// take ammo from player and calc final ammo
			int ammo_needed = data.getReloaddata().getReloadAmount()-this.ammo;
			int ammo_found = 0;
			
			for (ItemStack item : p.getInventory()) {
				
				if (item != null && item.getType() == data.getAmmodata().getMaterial()) {
					
					if (ammo_found+item.getAmount()>=ammo_needed) {
						item.setAmount(item.getAmount()-(ammo_needed-ammo_found));
						if (item.getAmount()==0) p.getInventory().remove(item);
						p.updateInventory();
						ammo_found = ammo_needed;
						break;
					} else {
						ammo_found+=item.getAmount();
						p.getInventory().remove(item);
						p.updateInventory();
					}
					
				}
				
			}
			
			if (ammo_found > 0) {
				
				final int ammo = ammo_found;
				reloading = true;
				data.getReloaddata().getReloadSoundStart().play(p);
				updateItemName(p);
				
				if (data.getReloaddata().isReloadingIndividually()) {
					reload_task = Timer.repeat(new Action() {
						
						private int iterations = 0;
						
						@Override
						public void perform() {
							Gun.this.ammo += 1;
							iterations++;
							data.getReloaddata().getReloadSoundFinish().play(p);
							updateItemName(p);
							if (iterations >= ammo) {
								reloading = false;
								data.getReloaddata().getReloadSoundFinish().play(p);
								updateItemName(p);
								Timer.cancel(reload_task);
								reload_task = -1;
							}
						}
					}, data.getReloaddata().getReloadDuration(), data.getReloaddata().getReloadDuration());
				} else {
					Timer.delay(new Action() {
						
						@Override
						public void perform() {
							Gun.this.ammo += ammo;
							data.getReloaddata().getReloadSoundFinish().play(p);
							reloading = false;
							updateItemName(p);
						}
					}, data.getReloaddata().getReloadDuration());
				}
				
			}
		}
		
	}
	
	/**
	 * toggle scope
	 */
	public void scope(Player p) {
		// check if gun has scope
		if (data.getScopedata().isScopeable()) {
			// toggle scope
			scoping = !scoping;
			// play toggle sound
			data.getScopedata().getToggleSound().play(p);
			// give player scope effects
			if (scoping) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*1000000, 2+data.getScopedata().getZoomAmount()));
				if (data.getScopedata().isSeeingInNight()) p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20*1000000, 0));
			}
			// clear scope effects
			else {
				p.removePotionEffect(PotionEffectType.NIGHT_VISION);
				p.removePotionEffect(PotionEffectType.SLOW);
			}
		}
	}
	
	
	public void drag(Player p, Snowball proj) {
		
		if (data.getShootdata().isDraggingDown() && drag_task != -1) {
			drag_task = Timer.repeat(new Action() {
				public void perform() {
					
					if (proj != null && p != null) {
						
						double dx = p.getLocation().getX()-proj.getLocation().getX();
						double dy = p.getLocation().getY()-proj.getLocation().getY();
						double distance = Math.sqrt(dx*dx+dy*dy);
						
						if (distance > data.getShootdata().getDragDistance()) {
							
							proj.remove();
							Timer.cancel(drag_task);
							drag_task = -1;
							
						}
						
					} else {
						
						Timer.cancel(drag_task);
						drag_task = -1;
						
					}
					
				}
			}, 5, 5);
		}
		
	}
	
	/**
	 * recoil player
	 */
	public void recoil(Player p) {
		float recoil = data.getShootdata().getRecoil();
		if (data.getSneakdata().isSneakDepending() && p.isSneaking()) recoil = 0;
		p.setVelocity(p.getLocation().getDirection().normalize().multiply(recoil*-0.5f));
	}

	/**
	 * burst fire with gun 
	 */
	public void burst(Player p) {
		if (burst == 0 && burst_task == -1) {
			this.burst = data.getBurstfiredata().getShotsPerBurst()-1;
			if (burst == -1) burst = 0;
			burst_task = Timer.repeat(new Action() {
				public void perform() {
					// check ammo before shooting
					if (ammo > 0 && burst > 0) {
						
						// decrement ammo
						ammo--;
						
						burst--;
						// add recoil to player
						recoil(p);
						
						// play sound and update item name
						data.getShootdata().getShootSound().play(p);
						for (int n = 0;n < data.getShootdata().getProjectiles();n++) shootProj(p);
						updateItemName(p);
					}
					// if no ammo cancel burst
					else {
						burst = 0;
						if (clicked_while_burst && data.getReloaddata().isFullyAutomatic()) {
							shoot(p);
							// auto reload
							if (ammo == 0) reload(p);
						}
						clicked_while_burst = false;
						Timer.cancel(burst_task);
						burst_task = -1;
					}
				}
			}, data.getShootdata().getDelayBetweenShots(), data.getShootdata().getDelayBetweenShots());
		}
	}
	
	/**
	 * shoot projectile
	 */
	public void shootProj(final Player p) {
		float spread = 0.2f*(RANDOM.nextInt(3)-1);
		if (data.getSneakdata().isSneakDepending() && p.isSneaking()) spread *= data.getSneakdata().getSpread();
		if (scoping) spread *= data.getScopedata().getSpread();
		if (!p.isSneaking() && !scoping) spread *= data.getShootdata().getSpread();
		Vector vec = p.getLocation().getDirection().normalize().multiply(data.getShootdata().getSpeed()).add(Vector.getRandom().multiply(spread));
		Snowball ball = p.launchProjectile(Snowball.class, vec);
		if (data.getShootdata().isProjectilesBurn()) ball.setFireTicks(60);
		ball.setCustomName(GunMaster.PROJECTILE_PREFIX + ";" + p.getName() + ";" + data.getShootdata().getDamage() + ";"
				+ (data.getHeadshotdata().isHeadshotEnabled() ? data.getHeadshotdata().getBounsDamage() : 0) + ";" + data.getName());
		ball.setCustomNameVisible(false);
		
		drag(p, ball);
	}
	
	/**
	 * @return raw data
	 */
	public GunData getData() {
		return data;
	}
	
	/**
	 * @return itemstack from gun
	 */
	public ItemStack getItem() {
		return item;
	}

	/**
	 * generates an id with ID_SIZE chars
	 * 
	 * @return string
	 */
	public String generateID() {
		
		String full_id = "";
		int id;
		Random random = new Random();
		
		for (int n = 0;n< ID_SIZE;n++) {
			id = random.nextInt(10);
			full_id = full_id + "§" +  id;
		}
		return full_id;
	}
	
	/**
	 * @param id to check
	 * @return equality of ids
	 */
	public boolean checkID(String id) {
		return this.id.equals(id);
	}
	
	private void updateItemName(Player p) {
		p.sendMessage("Ammo: " + ammo);
		p.sendMessage("Burst: " + burst);
		p.sendMessage("Reloading: " + reloading);
		ItemMeta meta = item.getItemMeta();
		char mode = 'X';
		if (!reloading && burst == 0 && ammo > 0) mode = NORMAL;
		if (!reloading && burst > 0 && ammo > 0) mode = BURST;
		if (!reloading && ammo == 0) mode = OUT_OF_AMMO;
		if (reloading) mode = RELOAD;
		
		meta.setDisplayName(GunMaster.GUN_ITEM_PREFIX+id+"§2§l"+data.getName() + " §8§l<§7"+mode+"§8§l> §a" + this.ammo);
		
		item.setItemMeta(meta);
		p.updateInventory();
	}
	
	/**
	 * 
	 * TODO fill method
	 * 
	 * @return generated item
	 */
	public ItemStack generateItem() {
		ItemData itemdata = data.getItemdata();
		ItemStack item = new ItemStack(itemdata.getMaterial(), 1, (short) 0);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(GunMaster.GUN_ITEM_PREFIX+id+"§2§l"+data.getName() + " §8§l<§7"+NORMAL+"§8§l> §a" + this.ammo);
		meta.setLore(Arrays.asList(itemdata.getLore()));
		item.setItemMeta(meta);
		return item;
	}
	
	public boolean isScoping() {
		return scoping;
	}
	
	public boolean isReloading() {
		return reloading;
	}
	
	public boolean isOutOfAmmo() {
		return ammo==0;
	}
	
}
