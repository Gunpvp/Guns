package guns.weopons.data;

import java.io.Serializable;

import org.bukkit.Material;

public class ItemData implements Serializable {
	
	private Material material;
	private String lore;
	private GunSound sound;
	
	public ItemData(Material material, String lore, GunSound sound) {
		this.material = material;
		this.lore = lore;
		this.sound = sound;
	}

	public Material getMaterial() {
		return material;
	}

	public String getLore() {
		return lore;
	}

	public GunSound getSound() {
		return sound;
	}
	
}
