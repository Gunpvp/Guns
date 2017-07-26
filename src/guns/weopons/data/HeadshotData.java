package guns.weopons.data;

public class HeadshotData {
	
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

	public boolean isHeadshot() {
		return headshot;
	}

	public float getBouns_damage() {
		return bouns_damage;
	}

	public boolean isFirework() {
		return firework;
	}

	public GunSound getShooter_sound() {
		return shooter_sound;
	}

	public GunSound getVictim_sound() {
		return victim_sound;
	}
	
}
