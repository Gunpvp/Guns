package guns.weopons.data;

import org.bukkit.Sound;

public class GunSound {
	
	private Sound sound;
	private int volume;
	private int pitch;
	
	public GunSound(Sound sound, int volume, int pitch) {
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
