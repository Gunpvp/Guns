package guns.weopons.data;

public class ReloadData {
	
	private boolean reload_ammo;
	private boolean reload_individual;
	private boolean fully_automatic;
	private int reload_amount;
	private int reload_duration;
	private GunSound reload_sound;
	
	public ReloadData(boolean reload_ammo, boolean reload_individual, boolean fully_automatic, int reload_amount, int reload_duration,
			GunSound reload_sound) {
		this.reload_ammo = reload_ammo;
		this.reload_individual = reload_individual;
		this.fully_automatic = fully_automatic;
		this.reload_amount = reload_amount;
		this.reload_duration = reload_duration;
		this.reload_sound = reload_sound;
	}

	public boolean isReload_ammo() {
		return reload_ammo;
	}

	public boolean isReload_individual() {
		return reload_individual;
	}
	
	public boolean isFullyAutomatic() {
		return fully_automatic;
	}

	public int getReload_amount() {
		return reload_amount;
	}

	public int getReload_duration() {
		return reload_duration;
	}

	public GunSound getReload_sound() {
		return reload_sound;
	}
	
}
