package voetbalmanager.controller.wedstrijdsimulatie;

import java.util.Random;

import voetbalmanager.model.Team;

public class WedstrijdSimulator {
	// Aantal rondes per helft
	private final int aantalrondes = 45;
	
	// De teams die meedoen aan het spel
	private WedstrijdTeam a;
	private WedstrijdTeam b;
	
	// Heeft A de bal?
	private boolean balA;
	/**
	 * 1: Team A doelgebied
	 * 2: Tussen midden en doelgebied A in.
	 * 3: Midden
	 * 4: Tussen midden en doelgebied B in.
	 * 5: Team B doelgebied
	 */
	private int balpositie = 3;
	private int rn = 0;
	
	private Random rnd;
	
	public WedstrijdSimulator(Team a, Team b)
	{
		this.a = new WedstrijdTeam(a, true);
		this.b = new WedstrijdTeam(b, false);
		
		rnd = new Random();
	}
	
	public Resultaat simuleer() {
		this.balA = true;
		simuleerHelft();
		this.balA = false;
		simuleerHelft();
		return new Resultaat(a.getScore(), b.getScore());
	}
	
	private void simuleerHelft() {
		this.balpositie = 3;
		
		for(rn = 0; rn < aantalrondes; ++rn) {
			simuleerRonde();
		}
	}
	
	private void simuleerRonde() {
		float amod = 1+((rnd.nextInt(10))-5)/50;
		float bmod = 1+((rnd.nextInt(10))-5)/50;
		
		float powa = a.getKracht(balpositie, balA);
		float powb = b.getKracht(6-balpositie, !balA);
		
		if(amod*powa > bmod*powb) {
			if(balA){
				++balpositie;
			}
			else
			{
				balA = true;
			}
		} else {
			if(!balA) {
				--balpositie;
			} else {
				balA = false;
			}
		}
		
		if(rnd.nextFloat()>0.9) {
			// Doelpoging
		}
		
	}
	
}
