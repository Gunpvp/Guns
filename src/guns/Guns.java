package guns;

import org.bukkit.plugin.java.JavaPlugin;

import guns.commands.Commands;
import guns.listener.Listeners;
import guns.weapons.GunMaster;

/**
 * 
 * @author bernh
 *
 */
public class Guns extends JavaPlugin {

	private static Guns instance;
	
	@Override
	public void onEnable() {
		super.onEnable();
		instance = this;
		
		Listeners.init();
		Commands.init();
		
		GunMaster.loadGuns();
		
	}
	
	public static Guns getPlugin() {
		return instance;
	}
	
}
