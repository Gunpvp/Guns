package guns.weopons.data;

import java.io.Serializable;

public class SneakData implements Serializable {
	
	private boolean sneak_depending;
	private boolean block_recoil;
	private float spread;
	
	public SneakData(boolean sneak_depending, boolean block_recoil, float spread) {
		this.sneak_depending = sneak_depending;
		this.block_recoil = block_recoil;
		this.spread = spread;
	}

	public boolean isSneakDepending() {
		return sneak_depending;
	}

	public boolean isBlockRecoil() {
		return block_recoil;
	}

	public float getSpread() {
		return spread;
	}
	
}
