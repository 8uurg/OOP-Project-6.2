package voetbalmanager.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Klasse die een Competitie representeert.
 * @author MarcoH
 * @version 30/11/2014
 */
public class Competitie{
	private String naam;
	private ArrayList<Team> teams;
	
	/**
	 * Creëert een competitie met een naam (Bv, Eredivisie)en maakt het mogelijk om teams toe te voegen aan deze competitie.
	 * @param naam
	 */
	public Competitie(String naam){
		this.naam = naam;
		this.teams = new ArrayList<Team>();
	}
	/**
	 * Voeg een team toe aan een bestaande competitie
	 * @param a Het team wat je in de competitie wil laten deelnemen.
	 * Voor Eredivisie is de grens van 18 teams opgezet.
	 */
	public void addTeam(Team a){
		if(teams.size()<19)
		teams.add(a);
	}
	/**
	 * Het speelschema van de competitie
	 * Ben er helaas niet in geslaagd om het voor nu voor elkaar te krijgen om ieder team 1 game te laten spelen en resultaten te weergeven.
	 * Op dit moment worden er 2 games gespeeld door ieder team per Speelronde.
 	 */
	public void maakSpeelSchema(){
		ArrayList<Team> B = this.teams;
		ArrayList<Team[]> C = new ArrayList<Team[]>();
		
		for(int i=0;i<(B.size()-1)*2;i++){//aantal games (teams.size()-1) omdat je jezelf moet overslaan
			int k = 0;
			while(k<=(B.size()-k-1)){
				
				Team[] a = {B.get(k),B.get(B.size()-k-1)};
				C.add(a);
				k++;
			}
			nextRound(B);
		}
		Speelschema S = new Speelschema(C,B.size());
		S.maakWedstrijden();
				
	}
	/**
	 * Verandert de indeling van de teams zodat iedere ronde tegen andere teams gespeeld wordt.
	 */
	public static void nextRound(ArrayList<Team> B){
		Team temp = B.get(1);
		B.remove(1);
		B.add(temp);
	}
	
	/**
	 * Maakt een String-representatie van de competitie.
	 * Plaats is op dit moment coded om altijd 1 2 3 te weergeven, al wordt er op bv naam gesorteerd.
	 */
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append("Competitie: ");
		res.append(naam);
		res.append("\n");
		res.append("Team");
		res.append("		");
		res.append("Punten\n");
		for(Team team:teams){
		res.append(team.getNaam());
		if(team.getNaam().length()<8)
			res.append("	");
		res.append("	");
		res.append(team.getPunten());
		res.append("\n");
		}
		return res.toString();
	}
	
	/**
	 * Creeër een XML Element waarin de gegevens zitten 
	 * @param doc Document waarin de gegevens gestopt worden.
	 * @return	Het element dat in een bepaald document gestopt kan worden.
	 */
	public Element getXMLElement(Document doc){
		Element comp = doc.createElement("competitie");
		
		comp.setAttribute("naam", this.naam);
		
		Element teamlist = doc.createElement("teams");
		
		for(Team team:teams)
			teamlist.appendChild(team.getXMLElement(doc));
		
		return comp;
	}
	
	/**
	 * Laad een XML element in om een competitie in te laden of te creeëren.
	 * @param el
	 * @return
	 */
	public static Competitie laadXMLElement(Element el) {
		String naam = el.getAttribute("naam");
		
		Competitie competitie = new Competitie(naam);
		
		NodeList teamsnodes = ((Element) el.getElementsByTagName("teams").item(0)).getElementsByTagName("team");
		
		for(int i=0; i<teamsnodes.getLength(); i++)
			competitie.addTeam(Team.laadXMLElement((Element) teamsnodes.item(i)));
		
		return competitie;
	}
	
	/**
	 * Maakt een nieuwe klasse competitie welke gesorteerd wordt en wordt teruggegeven. 
	 * @param voorwaarde Op dit moment moet de voorwaarde "Naam" of "Punten" op gegeven worden om te sorteren op naam of punten
	 * @return Geeft een nieuw object Competitie terug die gesorteerd is op de gewenste eigenschap.
	 */
	public Competitie Sorteren(String voorwaarde){
		Competitie a=new Competitie(naam);
		a.teams=this.teams;
		if(voorwaarde.equals("Naam"))
			Collections.sort(a.teams,NaamOrder);
		else if(voorwaarde.equals("Punten"))
			Collections.sort(a.teams,PuntenOrder);
		return a;
	}
	/**
	 * Sorteert met behulp van de Comparator van Java de ArrayList op Teamnaam. 
	 * Sorteert altijd van A-Z
	 */
	static Comparator<Team> NaamOrder = new Comparator<Team>() {
		public int compare(Team a, Team b){
			return a.getNaam().compareTo(b.getNaam());
		}
	};
	/**
	 * Sorteert met bheulp van de Comparator van Java de ArrayList op het aantal punten.
	 * Sorteert altijd van Hoog naar Laag. (38 staat boven 26).
	 */
	static Comparator<Team> PuntenOrder = new Comparator<Team>() {
		public int compare(Team a, Team b){
			return Integer.compare(b.getPunten(),a.getPunten());
		}
	};
}
