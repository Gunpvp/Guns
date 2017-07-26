package guns.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import guns.weapons.Gun;
import guns.weapons.GunMaster;

public class PlayerInteractListener extends Listener {
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onInteract(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		
		if (GunMaster.hasGunInHand(p)) {
			
			e.setCancelled(true);
			
			String gun_id = GunMaster.getIDFromGun(p);
			
			if (GunMaster.isInGunTable(p)) {
				
				Gun gun = GunMaster.getGunFromPlayer(p);
				
				if (gun.checkID(gun_id)) {
					
					interactWithGun(e.getAction(),p,gun);
					
				} else {
					
					GunMaster.removeFromGunTable(p);
					Gun new_gun = GunMaster.addToGunTable(p);
					interactWithGun(e.getAction(),p,new_gun);
					
				}
				
			} else {
				
				Gun new_gun = GunMaster.addToGunTable(p);
				
				interactWithGun(e.getAction(),p,new_gun);
				
			}
			
			
			
		} else {
			
			GunMaster.removeFromGunTable(p);
			
		}
		
	}

	public void interactWithGun(Action a, Player p, Gun gun) {
		switch (a) {
		case RIGHT_CLICK_AIR:
			gun.shoot(p);
			break;
		case RIGHT_CLICK_BLOCK:
			gun.shoot(p);
			break;
		case PHYSICAL:
			break;
		case LEFT_CLICK_AIR:
			gun.scope(p);
			break;
		case LEFT_CLICK_BLOCK:
			gun.scope(p);
			break;
		}
	}
	
}
