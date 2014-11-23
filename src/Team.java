import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Een klasse die een team voorstelt.
 */
public class Team {
	private ArrayList<Speler> team;
	
	/**
	 * Maak een leeg team aan.
	 */
	public Team()
	{
		team = new ArrayList<Speler>();
	}
	
	/**
	 * Voeg een speler toe aan het team.
	 * @param sp	De speler die toegevoegd moet worden.
	 */
	public void voegToe(Speler sp){
		if(team.indexOf(sp)==-1 	// Voorkom dat hetzelfde teamlid twee keer wordt toegevoegd.
			&& team.size()<22)		// Maximale grootte team.
		{
			team.add(sp);
		}
	}
	
	public String toString()
	{
		StringBuilder res = new StringBuilder();
		
		for(Speler speler:team)
		{
			res.append(speler.toString());
			res.append('\n');
		}
		
		return res.toString();
	}
	
	/**
	 * Creeër een team door een XML Element in te laden.
	 * @param el
	 * @return Het gecreeërde team.
	 */
	public static Team laadXMLElement(Element el)
	{
		Team team = new Team();
		NodeList spelers = el.getElementsByTagName("speler");
		
		for(int i=0; i<spelers.getLength(); i++)
		{
			Element speler = (Element) spelers.item(i);
			
			team.voegToe(Speler.laadXMLElement(speler));
		}
		
		return team;
	}
}