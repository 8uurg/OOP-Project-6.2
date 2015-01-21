package voetbalmanager.model;

import voetbalmanager.controller.wedstrijdsimulatie.Resultaat;
import voetbalmanager.controller.wedstrijdsimulatie.WedstrijdSimulator;

public class Wedstrijd {
	
	private Team[] teams;
	private int[] uitslag;
	
	public Wedstrijd(Team team1, Team team2) {
		teams = new Team[]{team1, team2};
	}
	/**
	 * Equalsmethode voor wedstrijden.
	 */
	public boolean equals(Object other){
		if(other instanceof Wedstrijd){
			 Wedstrijd that= (Wedstrijd)other;
			 return this.teams[1].equals(that.teams[1])&&
					 this.teams[0].equals(that.teams[0]);
		}
		return false;
	}
	/**
	 * Vergelijkt 2 teams die in een wedstrijd zitten.
	 * @param other Andere wedstrijd.
	 * @return Boolean die aangeeft of 2 wedstrijden te vergelijken zijn.
	 */
	public boolean teamEquals(Object other){
		if(other instanceof Wedstrijd){
			Wedstrijd that = (Wedstrijd)other;
			if(	this.teams[1].equals(that.teams[1])|
				this.teams[0].equals(that.teams[1])|
				this.teams[1].equals(that.teams[0])|
				this.teams[0].equals(that.teams[0])){
				return true;
			}
		}
		return false;
	}
	/**
	 * Je krijgt een uitslag mee vanaf een wedstrijd en kan hier in gecreëerd worden.
	 * @param score1 Score van team 1.
	 * @param score2 Score van team 2.
	 */
	public void maakUitslag(int score1, int score2){
		this.uitslag = new int[]{score1,score2};
	}
	/**
	 * Geeft een uitslag terug in een int[].
	 * @return integer[] van de uitslagen
	 */
	public int[] getUitslag() {
		return uitslag;
	}
	/**
	 * Geeft een array terug van teams.
	 * @return Array van teams.
	 */
	public Team[] getTeams() {
		return teams;
	}
	
	/**
	 * Simuleer deze wedstrijd en sla de score op.
	 */
	public void speelWedstrijd() {
		WedstrijdSimulator sim = new WedstrijdSimulator(this.teams[0], this.teams[1]);
		Resultaat res = sim.simuleer();
		this.uitslag = res.getScore();
		kenPuntenToe();
	}
	
	/**
	 * Geeft een string terug met de wedstrijd.
	 */
	public String toString() {
		String s = "";
		s += teams[0].getNaam() + " - " + teams[1].getNaam();
//		s += ": " + uitslag[0] + " - "  + uitslag[1];
		return s;
	}
	
	public void kenPuntenToe(){
		teams[0].kenPuntenToe(uitslag[0],uitslag[1]);
		teams[1].kenPuntenToe(uitslag[1],uitslag[0]);
	}

}
