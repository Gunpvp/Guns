package guns.weapons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import guns.weopons.data.AmmoData;
import guns.weopons.data.BurstfireData;
import guns.weopons.data.ExplosionData;
import guns.weopons.data.GunData;
import guns.weopons.data.GunSound;
import guns.weopons.data.HeadshotData;
import guns.weopons.data.ItemData;
import guns.weopons.data.ReloadData;
import guns.weopons.data.ScopeData;
import guns.weopons.data.ShootingData;
import guns.weopons.data.SneakData;

public class GunMaster {
	
	public static final String GUN_ITEM_PREFIX = "�g�u�n�r";
	public static final String PROJECTILE_PREFIX = "�b�a�nl�l�r";
	public static final String EXPLOSIVE_PREFIX = "�b�a�nl�l�r";
	
	private static List<GunData> datas = new ArrayList<>();
	private static Map<Player, Gun> gun_table = new HashMap<>();
	
	/**
	 * loads all data from gun file
	 */
	public static void loadGuns() {

		GunSound aquire_sound = new GunSound(Sound.ENTITY_BAT_TAKEOFF, 1, 1);
		GunSound shoot_sound = new GunSound(Sound.ENTITY_IRONGOLEM_HURT, 1, 2).addSound(Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 1, 2);
		GunSound out_of_ammo_sound = new GunSound(Sound.BLOCK_NOTE_PLING, 1, 2);
		GunSound shoot_with_no_ammo_sound = new GunSound(Sound.BLOCK_NOTE_HAT, 1, 2);
		GunSound reload_sound_start = new GunSound(Sound.BLOCK_NOTE_HAT, 1, 1);
		GunSound reload_sound_finish = new GunSound(Sound.BLOCK_NOTE_SNARE, 1, 6);
		
		ItemData item = new ItemData(Material.STONE_SPADE, "�7Pistol", aquire_sound);
		ShootingData shoot = new ShootingData(2, 0.3f, 1, 3, 5, false, 0, 1.2f, shoot_sound);
		ReloadData reload = new ReloadData(true, false, true, 30, 30, reload_sound_start, reload_sound_finish);
		AmmoData ammo = new AmmoData(true, false, Material.SEEDS, out_of_ammo_sound, shoot_with_no_ammo_sound);
		SneakData sneak = new SneakData(true, true, 0.1f);
		ScopeData scope = new ScopeData(true, 2, true, 0.1f, aquire_sound);
		BurstfireData burst = new BurstfireData(4, 2);
		HeadshotData headshot = new HeadshotData(false, 4, true, aquire_sound, aquire_sound);
		ExplosionData explosion = new ExplosionData(false, 0, 0, 0, shoot_sound);
		
		
		datas.add(new GunData("AK74", item, shoot, reload, ammo, sneak, scope, burst, headshot, explosion));
		
	}
	
	/**
	 * get gun data via name
	 */
	public static GunData getGunData(String name) {
		for (GunData data : datas) if (data.getName().startsWith(name)) return data;
		return null;
	}
	
	/**
	 * test if player is holding gun in hand
	 */
	public static boolean hasGunInHand(Player p) {
		if (p.getInventory().getItemInMainHand() != null
				&& p.getInventory().getItemInMainHand().getItemMeta() != null
				&& p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() != null) {
			
			return p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().startsWith(GUN_ITEM_PREFIX);
			
		}
		return false;
	}
	
	/**
	 * get gun data out of players hand
	 */
	public static GunData getGunData(Player p) {
		for (GunData data : datas) {
			if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(data.getName())) {
				return data;
			}
		}
		return null;
	}
	
	/**
	 * get gun id from player hand item
	 */
	public static String getIDFromGun(Player p) {
		String gun_name = p.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
		if (gun_name.startsWith(GUN_ITEM_PREFIX)) {
			gun_name = gun_name.substring(GUN_ITEM_PREFIX.length(), GUN_ITEM_PREFIX.length()+20);
			return gun_name;
		}
		return "Error. No gun found!";
	}
	
	/**
	 * is in gun table
	 */
	public static boolean isInGunTable(Player p) {
		return gun_table.containsKey(p);
	}
	
	/**
	 * remove player from gun table
	 * @return 
	 */
	public static Gun getGunFromPlayer(Player p) {
		return gun_table.get(p);
	}
	
	/**
	 * add player to gun table
	 */
	public static Gun addToGunTable(Player p) {
		Gun gun = new Gun(p.getInventory().getItemInMainHand());
		gun_table.put(p,gun);
		return gun;
	}
	
	/**
	 * add player to gun table with bound item
	 */
	public static Gun addToGunTable(Player p, ItemStack gun_item) {
		Gun gun = new Gun(gun_item);
		gun_table.put(p,gun);
		return gun;
	}
	
	/**
	 * remove player from gun table
	 */
	public static void removeFromGunTable(Player p) {
		gun_table.remove(p);
	}
	
}
