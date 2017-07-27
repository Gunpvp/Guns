package guns.weopons.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GunSound implements Serializable {
	
	private List<CustomSound> sounds = new ArrayList<>();
	
	public GunSound(Sound sound, int volume, int pitch) {
		sounds.add(new CustomSound(sound, volume, pitch));
	}
	
	public GunSound addSound(Sound sound, int volume, int pitch) {
		sounds.add(new CustomSound(sound, volume, pitch));
		return this;
	}
	
	public List<CustomSound> getSounds() {
		return sounds;
	}
	
	public void play(Player p) {
		for (CustomSound sound : sounds) p.playSound(p.getLocation(), sound.getSound(), sound.getVolume(), sound.getPitch());
	}
	
	public class CustomSound implements Serializable {
		
		private Sound sound;
		private int volume;
		private int pitch;
		
		public CustomSound(Sound sound, int volume, int pitch) {
			this.sound = sound;
			this.volume = volume;
			this.pitch = pitch;
		}
		
		public Sound getSound() {
			return sound;
		}
		public int getVolume() {
			return volume;
		}
		public int getPitch() {
			return pitch;
		}
		
	}
	
}
