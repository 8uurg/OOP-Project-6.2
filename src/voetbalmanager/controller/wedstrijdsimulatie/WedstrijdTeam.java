package voetbalmanager.controller.wedstrijdsimulatie;

import voetbalmanager.model.Team;

/**
 * Klasse die bijhoudt welk team welke score heeft tijdens het simuleren van een wedstrijd.
 */
public class WedstrijdTeam {
	private int score = 0;
	private Team team;
	
	public WedstrijdTeam(Team team) {
		this.team = team;
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
}
