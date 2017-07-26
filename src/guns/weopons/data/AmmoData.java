package guns.weopons.data;

import org.bukkit.Material;

public class AmmoData {
	
	private boolean take_ammo_per_shot;
	private boolean take_single_on_reload;
	private Material material;
	private GunSound out_of_ammo_sound;
	private GunSound shoot_with_no_ammo_sound;
	
	public AmmoData(boolean take_ammo_per_shot, boolean take_single_on_reload, Material material,
			GunSound out_of_ammo_sound, GunSound shoot_with_no_ammo_sound) {
		this.take_ammo_per_shot = take_ammo_per_shot;
		this.take_single_on_reload = take_single_on_reload;
		this.material = material;
		this.out_of_ammo_sound = out_of_ammo_sound;
		this.shoot_with_no_ammo_sound = shoot_with_no_ammo_sound;
	}

	public boolean isTake_ammo_per_shot() {
		return take_ammo_per_shot;
	}

	public boolean isTake_single_on_reload() {
		return take_single_on_reload;
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
