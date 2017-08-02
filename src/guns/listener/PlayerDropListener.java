package guns.listener;

import guns.Action;
import guns.Timer;
import guns.weapons.Gun;
import guns.weapons.GunMaster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDropListener extends Listener {
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		
		Player p = e.getPlayer();
		
		if (e.getItemDrop() != null && e.getItemDrop().getItemStack() != null) {
			
			ItemStack item = e.getItemDrop().getItemStack();
			
			if (item.getItemMeta() != null && item.getItemMeta().getDisplayName() != null) {
				
				if (item.getItemMeta().getDisplayName().startsWith(GunMaster.GUN_ITEM_PREFIX)) {
					
					e.setCancelled(true);
					p.updateInventory();
					
					Timer.sync(new Action() {
						public void perform() {
							GunMaster.removeFromGunTable(p);
							GunMaster.addToGunTable(p);
							Gun gun = GunMaster.getGunFromPlayer(p);
                            if (!gun.isReloading()) gun.reload(p);
                        }
					}, 5);
					
				}
				
			}
			
		}
		
	}
	
}
