
public class Wedstrijd {
	
	private Team[] teams;
	private int[] uitslag;
	
	public Wedstrijd(Team team1, Team team2, int scoreTeam1, int scoreTeam2) {
		teams = new Team[]{team1, team2};
		uitslag = new int[]{scoreTeam1, scoreTeam2};
	}
	
	public String toString() {
		String s = "";
		s += teams[0].getNaam() + " - " + teams[1].getNaam();
		s += ": " + uitslag[0] + " - "  + uitslag[1];
		return s;
	}
	
	public int[] getUitslag() {
		return uitslag;
	}
	
	public Team[] getTeams() {
		return teams;
	}

}
