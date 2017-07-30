package guns.weapons;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import guns.weopons.data.GunData;

public class GunMaster {
	
	public static final String GUN_ITEM_PREFIX = "§g§u§n§r";
	public static final String PROJECTILE_PREFIX = "§b§a§nl§l§r";
	public static final String EXPLOSIVE_PREFIX = "§b§a§nl§l§r";
	
	private static List<GunData> datas = new ArrayList<>();
	private static Map<Player, Gun> gun_table = new HashMap<>();
	
	/**
	 * loads all data from gun file
	 */
	public static void loadGuns() {

		File files = new File("plugins/Gunpvp/weapons/");
		
		for (File weapon_file : files.listFiles()) {
			
			GunData data = Parser.loadFromFile(weapon_file);
			datas.add(data);
			
		}
		
		Bukkit.getConsoleSender().sendMessage("§8[§eGuns§8] §7Loaded §e" + datas.size() + "§7 weapons!");
		
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
