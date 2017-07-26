package guns;

import org.bukkit.plugin.java.JavaPlugin;

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
	}
	
	public static Guns getPlugin() {
		return instance;
	}
	
}
