package guns.weapons;

import guns.weopons.data.GunData;

public class Gun {
	
	public static final String GUN_ITEM_PREFIX = "§g§u§n";
	
	private GunData data;

	public Gun(GunData data) {
		this.data = data;
	}

	public GunData getData() {
		return data;
	}
	
}
