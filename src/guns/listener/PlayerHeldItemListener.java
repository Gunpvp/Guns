package guns.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import guns.weapons.Gun;
import guns.weapons.GunMaster;

public class PlayerHeldItemListener extends Listener {
	
	@EventHandler
	public void onHeldItemChange(PlayerItemHeldEvent e) {
		
		Player p = e.getPlayer();
		ItemStack item_before = p.getInventory().getItem(e.getPreviousSlot());
		
		if (item_before != null && item_before.getItemMeta() != null && item_before.getItemMeta().getDisplayName() != null) {
			String name = item_before.getItemMeta().getDisplayName();
			if (name.startsWith(GunMaster.GUN_ITEM_PREFIX)) {
				Gun gun = GunMaster.getGunFromPlayer(p);
				if (gun == null) return;
				if (gun.isScoping()) gun.scope(p);
				if (GunMaster.hasGunInHand(p)) {
					GunMaster.removeFromGunTable(p);
					GunMaster.addToGunTable(p);
				}
			}
		}
		
	}
	
}
