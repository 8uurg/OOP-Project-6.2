import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class Team {
	private ArrayList<Speler> Team;
	
	public Team()
	{
		Team = new ArrayList<Speler>();
	}
	
	public void voegToe(Speler sp){
		if(Team.indexOf(sp)==-1&&Team.size()<22)
			Team.add(sp);
	}
	
	public static Team laadXMLElement(Element el)
	{
		Team team = new Team();
		NodeList spelers = el.getElementsByTagName("player");
		
		for(int i=0; i<spelers.getLength(); i++)
		{
			Element speler = (Element) spelers.item(i);
			
			team.voegToe(Speler.laadXMLElement(speler));
		}
		
		return team;
	}
}