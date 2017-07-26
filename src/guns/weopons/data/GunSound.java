package guns.weopons.data;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Sound;

public class GunSound {
	
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
	
	public class CustomSound {
		
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
