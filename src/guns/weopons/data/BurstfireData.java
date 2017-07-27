package guns.weopons.data;

import java.io.Serializable;

public class BurstfireData implements Serializable {
	
	private int shots_per_burst;
	private int delay_between;
	
	public BurstfireData(int shots_per_burst, int delay_between) {
		this.shots_per_burst = shots_per_burst;
		this.delay_between = delay_between;
	}


	public int getShotsPerBurst() {
		return shots_per_burst;
	}

	public int getDelay_between() {
		return delay_between;
	}
	
}
