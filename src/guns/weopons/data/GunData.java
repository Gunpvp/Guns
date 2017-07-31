package guns.weopons.data;

import java.io.Serializable;

public class GunData implements Serializable {
	
	private String name;
	private ItemData itemdata;
	private ShootingData shootdata;
	private ReloadData reloaddata;
	private AmmoData ammodata;
	private SneakData sneakdata;
	private ScopeData scopedata;
	private BurstfireData burstfiredata;
	private HeadshotData headshotdata;
	private ExplosionData explosion;
	private EffectData effect;
	
	public GunData(String name, ItemData itemdata, ShootingData shootdata, ReloadData reloaddata, AmmoData ammodata,
			SneakData sneakdata, ScopeData scopedata, BurstfireData burstfiredata, HeadshotData headshotdata,
			ExplosionData explosion, EffectData effect) {
		this.name = name;
		this.itemdata = itemdata;
		this.shootdata = shootdata;
		this.reloaddata = reloaddata;
		this.ammodata = ammodata;
		this.sneakdata = sneakdata;
		this.scopedata = scopedata;
		this.burstfiredata = burstfiredata;
		this.headshotdata = headshotdata;
		this.explosion = explosion;
		this.effect = effect;
	}
	
	public String getName() {
		return name;
	}

	public ItemData getItemdata() {
		return itemdata;
	}

	public ShootingData getShootdata() {
		return shootdata;
	}

	public ReloadData getReloaddata() {
		return reloaddata;
	}

	public AmmoData getAmmodata() {
		return ammodata;
	}

	public SneakData getSneakdata() {
		return sneakdata;
	}

	public ScopeData getScopedata() {
		return scopedata;
	}

	public BurstfireData getBurstfiredata() {
		return burstfiredata;
	}

	public HeadshotData getHeadshotdata() {
		return headshotdata;
	}

	public ExplosionData getExplosion() {
		return explosion;
	}

	public EffectData getEffect() {
		return effect;
	}
	
}
