package guns.weopons.data;

public class ShootingData {
	
	private boolean cancel_left;
	private boolean cancel_right;
	private int delay_between_shots;
	private float recoil;
	private int projectiles;
	private float damage;
	private String projectile;
	private int speed;
	private boolean drag_down;
	private int drag_distance;
	private float spread;
	private GunSound shoot_sound;
	
	public ShootingData(boolean cancel_left, boolean cancel_right, int delay_between_shots, float recoil,
			int projectiles, float damage, String projectile, int speed, boolean drag_down, int drag_distance,
			float spread, GunSound shoot_sound) {
		
		this.cancel_left = cancel_left;
		this.cancel_right = cancel_right;
		this.delay_between_shots = delay_between_shots;
		this.recoil = recoil;
		this.projectiles = projectiles;
		this.damage = damage;
		this.projectile = projectile;
		this.speed = speed;
		this.drag_down = drag_down;
		this.drag_distance = drag_distance;
		this.spread = spread;
		this.shoot_sound = shoot_sound;
		
	}

	public boolean isCancel_left() {
		return cancel_left;
	}

	public boolean isCancel_right() {
		return cancel_right;
	}

	public int getDelay_between_shots() {
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

	public String getProjectile() {
		return projectile;
	}

	public int getSpeed() {
		return speed;
	}

	public boolean isDrag_down() {
		return drag_down;
	}

	public int getDrag_distance() {
		return drag_distance;
	}

	public float getSpread() {
		return spread;
	}

	public GunSound getShoot_sound() {
		return shoot_sound;
	}
	
}
