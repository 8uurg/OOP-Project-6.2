package voetbalmanager.controller;

import java.util.ArrayList;

import voetbalmanager.model.Competitie;
import voetbalmanager.model.Team;

/**
 * Algemene klasse voor teambeheer
 */
public class AITeamManager {
	
	public void runManagementCycle(Competitie competitie){
		ArrayList<Team> teams = competitie.getTeams();
		for(Team team:teams){
			if(team.isComputerGestuurd())
				this.manageTeam(team, competitie);
		}
	}
	
	private void manageTeam(Team team, Competitie context){
		// TODO Schrijf code voor verwerken team.
		// Afhankelijk van gekozen systeem: vereist speelschema.
	}
}
