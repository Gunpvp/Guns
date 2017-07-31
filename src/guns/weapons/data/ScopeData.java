package guns.weapons.data;

import java.io.Serializable;

public class ScopeData implements Serializable {
	
	private boolean scope;
	private int zoom_amount;
	private boolean see_in_night;
	private float spread;
	private GunSound toggle_sound;
	
	public ScopeData(boolean scope, int zoom_amount, boolean see_in_night, float spread, GunSound toggle_sound) {
		this.scope = scope;
		this.zoom_amount = zoom_amount;
		this.see_in_night = see_in_night;
		this.spread = spread;
		this.toggle_sound = toggle_sound;
	}
	public boolean isScopeable() {
		return scope;
	}
	public int getZoomAmount() {
		return zoom_amount;
	}
	public boolean isSeeingInNight() {
		return see_in_night;
	}
	public float getSpread() {
		return spread;
	}
	public GunSound getToggleSound() {
		return toggle_sound;
	}
	
}
