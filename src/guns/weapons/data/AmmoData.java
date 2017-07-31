package guns.weapons.data;

import java.io.Serializable;

import org.bukkit.Material;

public class AmmoData implements Serializable {
	
	private Material material;
	private GunSound out_of_ammo_sound;
	private GunSound shoot_with_no_ammo_sound;
	
	public AmmoData(Material material,
			GunSound out_of_ammo_sound, GunSound shoot_with_no_ammo_sound) {
		this.material = material;
		this.out_of_ammo_sound = out_of_ammo_sound;
		this.shoot_with_no_ammo_sound = shoot_with_no_ammo_sound;
	}

	public Material getMaterial() {
		return material;
	}

	public GunSound getOutOfAmmoSound() {
		return out_of_ammo_sound;
	}

	public GunSound getShootWithNoAmmoSound() {
		return shoot_with_no_ammo_sound;
	}
	
}
