package guns.weopons.data;

import java.io.Serializable;

public class ReloadData implements Serializable {
	
	private boolean reload_ammo;
	private boolean reload_individual;
	private boolean fully_automatic;
	private int reload_amount;
	private int reload_duration;
	private GunSound reload_sound_start;
	private GunSound reload_sound_finish;
	
	public ReloadData(boolean reload_ammo, boolean reload_individual, boolean fully_automatic, int reload_amount, int reload_duration,
			GunSound reload_sound_start, GunSound reload_sound_finish) {
		this.reload_ammo = reload_ammo;
		this.reload_individual = reload_individual;
		this.fully_automatic = fully_automatic;
		this.reload_amount = reload_amount;
		this.reload_duration = reload_duration;
		this.reload_sound_start = reload_sound_start;
		this.reload_sound_finish = reload_sound_finish;
	}

	public boolean isReloadingWithAmmo() {
		return reload_ammo;
	}

	public boolean isReloadingIndividually() {
		return reload_individual;
	}
	
	public boolean isFullyAutomatic() {
		return fully_automatic;
	}

	public int getReloadAmount() {
		return reload_amount;
	}

	public int getReloadDuration() {
		return reload_duration;
	}

	public GunSound getReloadSoundStart() {
		return reload_sound_start;
	}
	
	public GunSound getReloadSoundFinish() {
		return reload_sound_finish;
	}
	
}
