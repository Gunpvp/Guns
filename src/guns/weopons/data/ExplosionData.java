package guns.weopons.data;

import java.io.Serializable;

public class ExplosionData implements Serializable {
	
	private boolean explosion;
	private float radius;
	private float knockback;
	private float damage;
	private GunSound shooter;
	
	public ExplosionData(boolean explosion, float radius, float knockback, float damage, GunSound shooter) {
		this.explosion = explosion;
		this.radius = radius;
		this.knockback = knockback;
		this.damage = damage;
		this.shooter = shooter;
	}

	public boolean isExploding() {
		return explosion;
	}

	public float getRadius() {
		return radius;
	}

	public float getKnockback() {
		return knockback;
	}

	public float getDamage() {
		return damage;
	}

	public GunSound getShooter() {
		return shooter;
	}
	
}
