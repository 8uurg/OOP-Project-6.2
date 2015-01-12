package voetbalmanager.controller;

import java.util.ArrayList;
import java.util.Random;

import voetbalmanager.model.BeschikbareSpeler;
import voetbalmanager.model.Competitie;
import voetbalmanager.model.Speler;
import voetbalmanager.model.Team;
import voetbalmanager.model.TransferMarkt;

/**
 * Algemene klasse voor teambeheer
 */
public class AITeamManager {
	private static Random rng;
	private Competitie competitie;
	private Team spelerteam;
	
	public void runManagementCycle(Competitie competitie){
		this.competitie = competitie;
		ArrayList<Team> teams = competitie.getTeams();
		for(Team team:teams){
			if(team.isComputerGestuurd())
				this.manageTeam(team, competitie);
			if(team.isSpelerBestuurd())
				this.spelerteam=team;
				
		}
	}
	
	private void manageTeam(Team team, Competitie context){
		// TODO Schrijf code voor verwerken team.
		// Afhankelijk van gekozen systeem: vereist speelschema.
		ArrayList<Speler> spelers = team.getSelectie();
		if(context.getTransferMarkt().getOudTeam(spelerteam))
		if(!(spelers.size()==Team.maxAantalSpelers())){
			spelerKopen(spelers);
		}
		else{
			spelerVerkopen(spelers);
		}
	}
	private void spelerVerkopen(ArrayList<Speler> spelers){
		double a = rng.nextDouble();
		if(a>0.5){
			for(Speler speler:spelers){
				int i = 0;
				if(speler.getSpelerWaarde()<competitie.getTransferMarkt().getMinWaarde()&&i<1){
					if(rng.nextDouble()<0.1){
						competitie.getTransferMarkt().maakVerhandelbaar(speler);
					}
				i++;
				}
			}
		}
	}
	
	private void spelerKopen(ArrayList<Speler> spelers){
		ArrayList<BeschikbareSpeler> beschikbaar = competitie.getTransferMarkt().getVerhandelbareSpelers();
		for(BeschikbareSpeler speler: beschikbaar){
			speler.moetKopen(spelers);
		}
	}
}
