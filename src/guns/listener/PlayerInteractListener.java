package guns.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;

import guns.weapons.GunMaster;
import guns.weopons.data.GunData;

public class PlayerInteractListener extends Listener {
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onInteract(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		
		if (GunMaster.hasGunInHand(p)) {
			
			GunData gun = GunMaster.getGunData(p);
			
			
			
		}
		
	}

}
