package guns.items;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public abstract class ThrowableItem extends Item {

	private float speed;
	private int timeout;
	
	public ThrowableItem(float speed, int timeout) {
		
		this.speed = speed;
		this.timeout = timeout;
		
	}
	
	@Override
	protected void leftClick(PlayerInteractEvent e) { e.setCancelled(true); }

	@Override
	protected void rightClick(PlayerInteractEvent e) {
		ItemStack grenades = e.getItem();
		int amount = grenades.getAmount()-1;
		if (amount == 0) {
			e.getPlayer().getInventory().remove(grenades);
			e.getPlayer().updateInventory();
		} else {
			grenades.setAmount(amount);
			e.getPlayer().updateInventory();
		}
		
		ItemStack throwableitem = new ItemStack(grenades);
		throwableitem.setAmount(1);
		org.bukkit.entity.Item item = e.getPlayer().getWorld().dropItem(e.getPlayer().getEyeLocation(), throwableitem);
		item.setVelocity(e.getPlayer().getLocation().getDirection().normalize().multiply(speed));
		
		
	}
	
	@Override
	protected boolean pickUpItem(Player p, ItemStack item) {
		return true;
	}
	
	protected abstract void trigger();
	
}
