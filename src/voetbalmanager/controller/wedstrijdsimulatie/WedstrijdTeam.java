package voetbalmanager.controller.wedstrijdsimulatie;

import voetbalmanager.model.Opstelling;
import voetbalmanager.model.Team;

/**
 * Klasse die bijhoudt welk team welke score heeft tijdens het simuleren van een wedstrijd.
 */
public class WedstrijdTeam {
	private int score = 0;
	private boolean x;
	private Team team;
	
	//Aanvalskracht aanval
	private SomKrachten aanval;
	private SomKrachten midden;
	private SomKrachten verdediging;
	
	public WedstrijdTeam(Team team, boolean x) {
		this.team = team;
		this.x = x;
		
		Opstelling o = this.team.getOpstelling();
		aanval = o.getAanvallersKrachten();
		midden = o.getMiddenveldersKrachten();
		verdediging = o.getVerdedigersKrachten();
	}
	
	public Team getTeam() {
		return team;
	}
	
	public int getScore() {
		return score;
	}
	
	public void maakDoelpunt() {
		++score;
	}
	
	public SomKrachten getAanval() {
		return aanval;
	}
	
	public SomKrachten getMiddenveld() {
		return midden;
	}
	
	public SomKrachten getVerdediging() {
		return verdediging;
	}
	
	public boolean quickEquals(WedstrijdTeam b) {
		return this.x == b.x;
	}
	
	public float getKracht(int veld, boolean aanvallend) {
		float pow = 0;
		
		switch(veld) {
		case 1:
			// Team A doelgebied
			if(aanvallend) {
				pow += verdediging.getAanval();
			} else {
				pow += verdediging.getVerdediging();
			}
			break;
		case 2:
			// Tussen midden en doelgebied A in.
			
			break;
		case 3:
			// Midden
			
			break;
		case 4:
			// Tussen midden en doelgebied B in.
			
			break;
		case 5:
			// Team B doelgebied
			
			break;
		}
		
		return pow;
	}
	
}
