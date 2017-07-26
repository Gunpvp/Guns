package guns.listener;

import guns.Guns;

public class Listener implements org.bukkit.event.Listener{
	
	public Listener() {
		Guns.getPlugin().getServer().getPluginManager().registerEvents(this, Guns.getPlugin());
	}
	
}
