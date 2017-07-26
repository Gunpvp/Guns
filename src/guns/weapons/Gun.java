package guns.weapons;

import guns.weopons.data.GunData;

public class Gun {
	
	
	private GunData data;

	public Gun(GunData data) {
		this.data = data;
	}

	public GunData getData() {
		return data;
	}
	
}
