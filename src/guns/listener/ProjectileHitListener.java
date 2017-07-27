package guns.listener;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import guns.weapons.GunMaster;
import guns.weopons.data.ExplosionData;
import guns.weopons.data.GunData;

public class ProjectileHitListener extends Listener{

	@EventHandler
	public void onHit(ProjectileHitEvent e) {
		
		if (e.getEntity() instanceof Snowball) {
			
			Snowball proj = (Snowball) e.getEntity();
			
			String name = proj.getName();
			String[] params = name.split(";"); 
			
			if (proj.getShooter() != null && name.startsWith(GunMaster.PROJECTILE_PREFIX)) {
				
				if (e.getHitEntity() != null && e.getHitEntity() instanceof Damageable) {
						
					Player shooter = (Player) proj.getShooter();
					float damage = Float.parseFloat(params[2]);
					float headshot = Float.parseFloat(params[3]);
					GunData data = GunMaster.getGunData(params[4]);
					float final_damage = damage;

					if (data.getExplosion().isExploding()) {
						createExplosion(data.getExplosion(), proj);
					} else {
						Damageable entity = (Damageable) e.getHitEntity();
						if (e.getHitEntity() instanceof LivingEntity) {
							
							LivingEntity lentity = (LivingEntity) e.getHitEntity();
							double difference = Math.abs((lentity.getEyeLocation().getY()) - proj.getLocation().getY());
							if (difference < 0.3 && data.getHeadshotdata().isHeadshotEnabled()) {
								
								if (data.getHeadshotdata().isFirework()) {
									Firework firework = (Firework) shooter.getWorld().spawnEntity(lentity.getEyeLocation(), EntityType.FIREWORK);
									FireworkMeta meta = firework.getFireworkMeta();
									meta.addEffect(FireworkEffect.builder().trail(true).withColor(Color.GREEN).with(Type.CREEPER).build());
									firework.setFireworkMeta(meta);
								}
								
								final_damage += headshot;
								
								data.getHeadshotdata().getShooterSound().play(shooter);
								if (e.getHitEntity() instanceof Player) {
									data.getHeadshotdata().getVictimSound().play(((Player)e.getHitEntity()));
								}
								
							}
						}
						entity.damage(final_damage);
					}
					
				}
				
				if (e.getHitBlock() != null) {
					
					GunData data = GunMaster.getGunData(params[4]);
					
					if (data.getExplosion().isExploding()) {
						createExplosion(data.getExplosion(), proj);
					}
					
				}
				
				
			}
			
		}
		
	}
	
	private void createExplosion(ExplosionData data, Snowball proj) {
		
		proj.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, proj.getLocation().getX(), proj.getLocation().getY(), proj.getLocation().getZ(), 1);
		
		for (Entity in_range : proj.getNearbyEntities(data.getRadius(), data.getRadius(), data.getRadius())) {
			
			if (in_range instanceof Damageable) {
				Damageable damageable = (Damageable) in_range;
				damageable.damage(data.getDamage());
				Vector vec = proj.getLocation().toVector().subtract(in_range.getLocation().toVector()).normalize();
				in_range.setVelocity(vec.multiply(-data.getKnockback()));
			}
			
		}
		
		for (Entity in_range : proj.getNearbyEntities(data.getRadius()*5, data.getRadius()*5, data.getRadius()*5)) {
			if (in_range instanceof Player) ((Player) in_range).playSound(in_range.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
		}
	}
	
}
