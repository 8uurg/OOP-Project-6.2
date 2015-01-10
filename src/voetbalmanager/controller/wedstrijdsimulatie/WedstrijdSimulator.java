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
		this.a = new WedstrijdTeam(a);
		this.b = new WedstrijdTeam(b);
	}
	
	public void reset() {
		balpositie = 3;
		rn = 0;
		
		rnd = new Random();
		
		this.a.resetScore();
		this.b.resetScore();
	}
	
	public Resultaat simuleer() {
		reset();
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
		
		//System.out.println(balpositie);
		
		boolean doelpunt = false;
		
		if(balpositie == 6) {doelpoging(a, b);}
		if(balpositie == 0) {doelpoging(b, a);}
		
		
	}
	
	public void doelpoging(WedstrijdTeam aanval, WedstrijdTeam verdedig) {
		// Is er echt een doelpoging?
		if(rnd.nextFloat()>0) {
			// Doelpoging
			float a = aanval.getTeam().getOpstelling().getWillekeurigeAanvaller(rnd).getOffensief();
			a = a * (1+rnd.nextFloat()/5);
			float b = verdedig.getKeeper().getVerdediging();
			// Haal commentaarstrepen weg om de aanvalskracht tegen de keeper te zien. (Doelpunt of niet)
			//System.out.println("A:" + a + ", B:" + b);
			if(a>b) {
				// Door keeper of in doel?
				if(true)
				{
					aanval.maakDoelpunt(rn);
					balpositie = 3;
					balA = !balA;
					return;
				}
			}
		}
		if(balpositie==6) {
			balpositie = 5;
			balA = false;
		} else if (balpositie == 0) {
			balpositie = 1;
			balA = true;
		}
	}
}
