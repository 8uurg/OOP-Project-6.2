import java.util.ArrayList;


public class Team {
	private ArrayList<Speler> Team;
	
	public Team(){
		Team = new ArrayList<Speler>();
	}
	
	public void voegToe(Speler sp){
		if(Team.indexOf(sp)==-1&&Team.size()<22)
			Team.add(sp);
		
	}
}