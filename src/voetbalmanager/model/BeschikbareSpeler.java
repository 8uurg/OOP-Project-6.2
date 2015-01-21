package voetbalmanager.model;

import java.util.ArrayList;
/**
 * Spelers die beschikbaar zijn op de spelersmarkt om te kopen.
 * @author Marco
 *
 */
public class BeschikbareSpeler {
	private Speler speler;
	private Team oudTeam;
	
	/**
	 * Maakt de beschikbare speler die terecht kan komen op de spelersmarkt
	 * @param speler De speler die op de markt gezet is.
	 * @param oudTeam Het oude team van deze speler.
	 */
	public BeschikbareSpeler(Speler speler, Team oudTeam){
		this.oudTeam = oudTeam;
		this.speler = speler;
	}
	
	/**
	 * Geeft het oude team van de speler
	 * @return Team
	 */
	public Team getOudTeam(){
		return oudTeam;
	}
	
	/**
	 * Geeft alle informatie over de speler
	 * @return Speler
	 */
	public Speler getSpeler(){
		return speler;
	}
	
	/**
	 * Methode voor de AI om te bepalen of BeschikbareSpelers het waard zijn om te kopen.
	 * @param spelers De spelers van het team.
	 * @return boolean
	 */
	public boolean moetKopen(ArrayList<Speler> spelers) {
		Speler.Type a = speler.type;
		int totaalType =0;
		double totaalScore = 0;
		for(Speler inTeam: spelers){
			if(inTeam.type==a){
				totaalType++;
				totaalScore =totaalScore+inTeam.getSpelerWaarde();
			}
		}
		if(speler.getSpelerWaarde()>totaalScore/totaalType)
			return true;
		return false;
	}
	
	/**
	 * Methode voor de AI om de kans te vergroten dat ze een speler kopen
	 * @param spelers Spelers uit het team
	 * @return integer Het aantal spelers wat slechter is dan de Beschikbare Speler.
	 */
	public int besluitKoop(ArrayList<Speler> spelers){
		Speler.Type a = speler.type;
		int j=0;
		for(Speler inTeam: spelers){
			if(inTeam.type==a&&inTeam.getSpelerWaarde()<speler.getSpelerWaarde()){
				j++;
			}
		}
		return j;
	}
}
