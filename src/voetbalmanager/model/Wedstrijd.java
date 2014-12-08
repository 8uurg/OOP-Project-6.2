package voetbalmanager.model;

public class Wedstrijd {
	
	private Team[] teams;
	private int[] uitslag;
	
	public Wedstrijd(Team team1, Team team2) {
		teams = new Team[]{team1, team2};
	}
	public boolean equals(Object other){
		if(other instanceof Wedstrijd){
			 Wedstrijd that= (Wedstrijd)other;
			 return this.teams[1].equals(that.teams[1])&&
					 this.teams[0].equals(that.teams[0]);
		}
		return false;
	}
	
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
	
	public void maakUitslag(int score1, int score2){
		this.uitslag = new int[]{score1,score2};
	}
	
	public int[] getUitslag() {
		return uitslag;
	}
	
	public Team[] getTeams() {
		return teams;
	}
	/**
	 * Puntentelling in Wedstrijd gezet omdat Wedstrijd de uitslag bevat na een game
	 */
	public void Punten(){
		if(uitslag[0]<uitslag[1])
			teams[1].geefPunten(3);
		else if(uitslag[0]==uitslag[1]){
			teams[0].geefPunten(1);
			teams[1].geefPunten(1);
		}
		else
			teams[0].geefPunten(3);
		
	}
	public String toString() {
		String s = "";
		s += teams[0].getNaam() + " - " + teams[1].getNaam();
//		s += ": " + uitslag[0] + " - "  + uitslag[1];
		return s;
	}

}
