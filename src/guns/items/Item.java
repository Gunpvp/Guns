package guns.items;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import guns.listener.Listener;

public abstract class Item extends Listener {
	
	public static final String ITEM_PREFIX = "§i§r";
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		
		if (isItem(p.getInventory().getItemInMainHand())) {
			
			if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
				leftClick(e);
			}
			
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				rightClick(e);
			}
			
		}
		
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		
		if (e.getItemDrop()!=null) {
			ItemStack drop = e.getItemDrop().getItemStack();
			if (isItem(drop)) {
				e.setCancelled(dropItem(e.getPlayer(), drop));
			}
		}
		
	}
	
	@EventHandler
	public void onPickUp(PlayerPickupItemEvent e) {
		
		if (e.getItem()!=null) {
			ItemStack pickup = e.getItem().getItemStack();
			if (isItem(pickup)) {
				e.setCancelled(pickUpItem(e.getPlayer(), pickup));
			}
		}
		
	}
	
	protected boolean isItem(ItemStack item) {
		if (item != null
				&& item.getItemMeta() != null
				&& item.getItemMeta().getDisplayName() != null) {
			
			if (item.getItemMeta().getDisplayName().startsWith(ITEM_PREFIX) && isRightItem(item)) {
				return true;
			}
		}
		return false;
	}
	
	protected abstract boolean isRightItem(ItemStack item);
	protected abstract void leftClick(PlayerInteractEvent e);
	protected abstract void rightClick(PlayerInteractEvent e);
	protected abstract boolean dropItem(Player p, ItemStack item);
	protected abstract boolean pickUpItem(Player p, ItemStack item);
	
}
