package guns.weapons;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

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
	
	public static final String GUN_ITEM_PREFIX = "§g§u§n§r";
	
	private static List<GunData> datas = new ArrayList<>();
	
	/**
	 * loads all data from gun file
	 */
	public static void loadGuns() {

		GunSound aquire_sound = new GunSound(Sound.ENTITY_BAT_TAKEOFF, 1, 1);
		GunSound shoot_sound = new GunSound(Sound.ENTITY_IRONGOLEM_HURT, 1, 2).addSound(Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 1, 2);
		GunSound out_of_ammo_sound = new GunSound(Sound.BLOCK_NOTE_PLING, 1, 2);
		GunSound shoot_with_no_ammo_sound = new GunSound(Sound.BLOCK_NOTE_HAT, 1, 2);
		GunSound reload_sound = new GunSound(Sound.BLOCK_NOTE_HAT, 1, 1).addSound(Sound.BLOCK_NOTE_SNARE, 1, 6);
		
		ItemData item = new ItemData("§2§lColt45", Material.STONE_SPADE, "§7Pistol", aquire_sound);
		ShootingData shoot = new ShootingData(true, true, 2, 0, 1, 5, "SNOWBALL", 40, false, 0, 0.3f, shoot_sound);
		ReloadData reload = new ReloadData(true, false, 6, 35, reload_sound);
		AmmoData ammo = new AmmoData(true, false, Material.SEEDS, out_of_ammo_sound, shoot_with_no_ammo_sound);
		SneakData sneak = new SneakData(false, true, 0);
		ScopeData scope = new ScopeData(false, 0, true, 0, null);
		BurstfireData burst = new BurstfireData(false, 0, 0);
		HeadshotData headshot = new HeadshotData(false, 0, false, null, null);
		ExplosionData explosion = new ExplosionData(false, 0, 0, 0, null);
		
		
		datas.add(new GunData("Colt45", item, shoot, reload, ammo, sneak, scope, burst, headshot, explosion));
		
	}
	
	/**
	 * get gun data via name
	 */
	public static GunData getGunData(String name) {
		for (GunData data : datas) if (data.getName().equals(name)) return data;
		return null;
	}
	
	/**
	 * test if player is holding gun in hand
	 */
	public static boolean hasGunInHand(Player p) {
		return ((p.getInventory().getItemInMainHand() != null) && (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().startsWith(GUN_ITEM_PREFIX)));
	}
	
	/**
	 * get gun out of players hand
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
	 * shoot with weapon
	 */
	public static void shoot(Player p, GunData gun) {
		
	}
	
}
