package guns.weapons.data;

import java.io.Serializable;

public class ShootingData implements Serializable {
	
	private int delay_between_shots;
	private float recoil;
	private int projectiles;
	private float damage;
	private int speed;
	private boolean drag_down;
	private int drag_distance;
	private float spread;
	private GunSound shoot_sound;
	private boolean projectiles_burn;
	
	public ShootingData(int delay_between_shots, float recoil,
			int projectiles, float damage, int speed, boolean drag_down, int drag_distance,
			float spread, GunSound shoot_sound, boolean projectiles_burn) {
		
		this.delay_between_shots = delay_between_shots;
		this.recoil = recoil;
		this.projectiles = projectiles;
		this.damage = damage;
		this.speed = speed;
		this.drag_down = drag_down;
		this.drag_distance = drag_distance;
		this.spread = spread;
		this.shoot_sound = shoot_sound;
		this.projectiles_burn = projectiles_burn;
		
	}

	public int getDelayBetweenShots() {
		return delay_between_shots;
	}

	public float getRecoil() {
		return recoil;
	}

	public int getProjectiles() {
		return projectiles;
	}

	public float getDamage() {
		return damage;
	}

	public int getSpeed() {
		return speed;
	}

	public boolean isDraggingDown() {
		return drag_down;
	}

	public int getDragDistance() {
		return drag_distance;
	}

	public float getSpread() {
		return spread;
	}

	public GunSound getShootSound() {
		return shoot_sound;
	}
	
	public boolean isProjectilesBurn() {
		return projectiles_burn;
	}
	
}
