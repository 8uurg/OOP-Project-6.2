package voetbalmanager.controller.teammanager;

import java.util.Comparator;

import voetbalmanager.model.Speler;

public class SpelerStatVergelijker implements Comparator<Speler> {

	private float amul;
	private float dmul;
	
	public SpelerStatVergelijker(float amul, float dmul) {
		this.amul = amul;
		this.dmul = dmul;
	}
	
	private float berekenGetal(Speler o) {
		return o.getOffensief()*amul + o.getDefensief()*dmul;
	}
	
	@Override
	public int compare(Speler o1, Speler o2) {
		float o1g = berekenGetal(o1);
		float o2g = berekenGetal(o2);
		
		if(o1g>o2g) return 1;
		if(o1g<o2g) return -1;
		return 0;
	}

}
