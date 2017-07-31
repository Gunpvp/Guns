package guns.weopons.data;

import java.io.Serializable;

public class EffectData implements Serializable {
	
	private boolean effecting;
	private String effect_name;
	private int delay;
	private int amplitude;
	
	public EffectData(boolean effecting, String effect_name, int delay, int amplitude) {
		this.effecting = effecting;
		this.effect_name = effect_name;
		this.delay = delay;
		this.amplitude = amplitude;
	}

	public boolean isEffecting() {
		return effecting;
	}

	public String getEffect_name() {
		return effect_name;
	}

	public int getDelay() {
		return delay;
	}

	public int getAmplitude() {
		return amplitude;
	}
	
}
