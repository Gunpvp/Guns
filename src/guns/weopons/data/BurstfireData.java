package guns.weopons.data;

public class BurstfireData {
	
	private boolean burstfire;
	private int shots_per_burst;
	private int delay_between;
	
	public BurstfireData(boolean burstfire, int shots_per_burst, int delay_between) {
		this.burstfire = burstfire;
		this.shots_per_burst = shots_per_burst;
		this.delay_between = delay_between;
	}

	public boolean isBurstfire() {
		return burstfire;
	}

	public int getShots_per_burst() {
		return shots_per_burst;
	}

	public int getDelay_between() {
		return delay_between;
	}
	
}
