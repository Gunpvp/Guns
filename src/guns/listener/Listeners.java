package guns.listener;

public class Listeners {
	
	public static void init() {
		
		new PlayerInteractListener();
		new ProjectileHitListener();
		
	}
	
}
