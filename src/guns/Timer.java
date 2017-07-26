package guns;

public class Timer {
	
	public static void delay(Action action, int ticks) {
		Guns.getPlugin().getServer().getScheduler().runTaskLaterAsynchronously(Guns.getPlugin(), new Runnable() {
			public void run() {
				action.perform();
			}
		}, (int)(ticks));
	}
	
	public static int sync(Action action, int ticks) {
		return Guns.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(Guns.getPlugin(), new Runnable() {
			public void run() {
				action.perform();
			}
		}, (int)(ticks));
	}
	
	public static int repeat(Action action, int offset, int ticks) {
		return Guns.getPlugin().getServer().getScheduler().scheduleSyncRepeatingTask(Guns.getPlugin(), new Runnable() {
			public void run() {
				action.perform();
			}
		}, (int)(offset), (int)(ticks));
	}
	
	public static void cancel(int task) {
		Guns.getPlugin().getServer().getScheduler().cancelTask(task);
	}
	
}
