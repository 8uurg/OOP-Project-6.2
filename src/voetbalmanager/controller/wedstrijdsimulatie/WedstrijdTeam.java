package voetbalmanager.controller.wedstrijdsimulatie;

import voetbalmanager.model.Opstelling;
import voetbalmanager.model.Team;

/**
 * Klasse die bijhoudt welk team welke score heeft tijdens het simuleren van een wedstrijd.
 */
public class WedstrijdTeam {
	private int score = 0;
	private Team team;
	
	//Aanvalskracht aanval
	private SomKrachten aanval;
	private SomKrachten midden;
	private SomKrachten verdediging;
	private SomKrachten keeper;
	
	public WedstrijdTeam(Team team) {
		this.team = team;
		
		Opstelling o = this.team.getOpstelling();
		aanval = o.getAanvallersKrachten();
		midden = o.getMiddenveldersKrachten();
		verdediging = o.getVerdedigersKrachten();
		keeper = o.getKeeperKrachten();
	}
	
	public void resetScore() {
		score = 0;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public int getScore() {
		return score;
	}
	
	public void maakDoelpunt(int ronde) {
		// Voeg eventueel statestiek toe.
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
	
	public SomKrachten getKeeper() {
		return keeper;
	}
	
	public float getKracht(int veld, boolean aanvallend) {
		float pow = 0;
		
		switch(veld) {
		case 1:
			// Team A doelgebied
			if(aanvallend) {
				pow += verdediging.getAanval();
				pow += midden.getAanval();
			} else {
				pow += verdediging.getVerdediging();
				pow += midden.getVerdediging();
			}
			break;
		case 2:
			// Tussen midden en doelgebied A in.
			if(aanvallend) {
				pow += verdediging.getAanval() * 0.6;
				pow += midden.getAanval();
				pow += aanval.getAanval() * 0.7;
			} else {
				pow += verdediging.getVerdediging();
				pow += midden.getVerdediging() * 0.9;
				pow += aanval.getVerdediging() * 0.1;
			}
			break;
		case 3:
			// Midden
			if(aanvallend) {
				pow += verdediging.getAanval() * 0.2;
				pow += midden.getAanval();
				pow += aanval.getAanval();
			} else {
				//pow += verdediging.getVerdediging();
				pow += midden.getVerdediging();
				pow += aanval.getVerdediging() * 0.2;
			}
			break;
		case 4:
			// Tussen midden en doelgebied B in.
			if(aanvallend) {
				pow += midden.getAanval() * 0.9;
				pow += aanval.getAanval();
			} else {
				//pow += midden.getVerdediging();
				pow += aanval.getVerdediging();
			}
			break;
		case 5:
			// Team B doelgebied
			if(aanvallend) {
				pow += midden.getAanval();
				pow += aanval.getAanval();
			} else {
				//pow += midden.getVerdediging();
				pow += aanval.getVerdediging();
			}
			break;
		}
		
		return pow;
	}

	
}
