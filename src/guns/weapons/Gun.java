package guns.weapons;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import guns.Action;
import guns.Timer;
import guns.weopons.data.GunData;
import guns.weopons.data.ItemData;

public class Gun {
	
	private static final int ID_SIZE = 10;
	private static final char NORMAL = '-';
	private static final char OUT_OF_AMMO = 'x';
	private static final char RELOAD = '↓';
	private static final char BURST = '~';
	
	private GunData data;
	private int ammo;
	private int burst;
	private int burst_task = -1;
	private boolean reloading;
	private boolean scoping;
	private boolean clicked_while_burst;
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
		this.ammo = data.getReloaddata().getReload_amount();
		this.item = generateItem();
		
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
		
	}
	
	/**
	 * shoot with gun
	 */
	public void shoot(Player p) {
		
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
				updateItemName(p);
				
				// if you run out of ammo play sound
				if (ammo == 0) {
					
					data.getAmmodata().getOutOfAmmoSound().play(p);
					
				}
				
			}
			// if no ammo on shoot play no ammo sound
			else {
				
				data.getAmmodata().getShootWithNoAmmoSound().play(p);
				
			}
			
		} else if (!reloading && burst > 0) {
			clicked_while_burst = true;
		}
		else if (reloading) {
			
		}
		
	}
	
	/**
	 * reload gun
	 */
	public void reload(Player p) {
		
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
		if (burst_task == -1) {
			this.burst = data.getBurstfiredata().getShotsPerBurst()-1;
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
						updateItemName(p);
					}
					// if no ammo cancel burst
					else {
						burst = 0;
						if (clicked_while_burst && data.getReloaddata().isFullyAutomatic()) {
							shoot(p);
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
		ItemMeta meta = item.getItemMeta();
		char mode = 'X';
		if (!reloading && burst == 0 && ammo > 0) mode = NORMAL;
		if (!reloading && burst > 0 && ammo > 0) mode = BURST;
		if (!reloading && burst == 0 && ammo == 0) mode = OUT_OF_AMMO;
		
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
	
}
