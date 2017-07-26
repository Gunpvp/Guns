package guns;

import org.bukkit.plugin.java.JavaPlugin;

import guns.listener.Listeners;

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
		
	}
	
	public static Guns getPlugin() {
		return instance;
	}
	
}
