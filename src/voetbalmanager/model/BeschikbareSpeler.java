package voetbalmanager.model;

import java.util.ArrayList;

public class BeschikbareSpeler {
	private Speler speler;
	private Team oudTeam;
	
	public BeschikbareSpeler(Speler speler, Team oudTeam){
		this.oudTeam = oudTeam;
		this.speler = speler;
	}
	public Team getOudTeam(){
		return oudTeam;
	}
	public Speler getSpeler(){
		return speler;
	}
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
}
