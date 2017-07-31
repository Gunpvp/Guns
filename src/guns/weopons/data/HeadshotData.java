package guns.weopons.data;

import java.io.Serializable;

public class HeadshotData implements Serializable {
	
	private boolean headshot;
	private float bouns_damage;
	private boolean firework;
	private GunSound shooter_sound;
	private GunSound victim_sound;
	
	public HeadshotData(boolean headshot, float bouns_damage, boolean firework, GunSound shooter_sound,
			GunSound victim_sound) {
		
		this.headshot = headshot;
		this.bouns_damage = bouns_damage;
		this.firework = firework;
		this.shooter_sound = shooter_sound;
		this.victim_sound = victim_sound;
	}

	public boolean isHeadshotEnabled() {
		return headshot;
	}

	public float getBounsDamage() {
		return bouns_damage;
	}

	public boolean isFireworkEnabled() {
		return firework;
	}

	public GunSound getShooterSound() {
		return shooter_sound;
	}

	public GunSound getVictimSound() {
		return victim_sound;
	}
	
}
