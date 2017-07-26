package guns.weopons.data;

public class ScopeData {
	
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
	public boolean isScope() {
		return scope;
	}
	public int getZoom_amount() {
		return zoom_amount;
	}
	public boolean isSee_in_night() {
		return see_in_night;
	}
	public float getSpread() {
		return spread;
	}
	public GunSound getToggle_sound() {
		return toggle_sound;
	}
	
}
