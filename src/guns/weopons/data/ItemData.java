package guns.weopons.data;

import org.bukkit.Material;

public class ItemData {
	
	private String name;
	private Material material;
	private String lore;
	private GunSound sound;
	
	public ItemData(String name, Material material, String lore, GunSound sound) {
		this.name = name;
		this.material = material;
		this.lore = lore;
		this.sound = sound;
	}

	public String getName() {
		return name;
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
