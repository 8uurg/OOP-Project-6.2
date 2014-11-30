import java.util.ArrayList;
import java.util.*;

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
	 * Ben op dit moment bezig om een systeem te maken wat het mogelijk maakt dat ieder team 1x speelt per week.
	 */
	public void SpeelSchema(){
		for(int i=0;i<(2*teams.size()-2);i++){//aantal games (teams.size()-2) omdat je 2x jezelf moet overslaan
			for(int k=0;k<teams.size();k++){//hier wordt nog aan gewerkt.
			//WIP		
			}	
		}
				
	}
	
	/**
	 * Maakt een String-representatie van de competitie.
	 * Plaats is op dit moment coded om altijd 1 2 3 te weergeven, al wordt er op bv naam gesorteerd.
	 */
	@Override
	public String toString(){
		int i = 0;
		StringBuilder res = new StringBuilder();
		res.append("Competitie: ");
		res.append(naam);
		res.append("\n");
		res.append("Plaats");
		res.append("	");
		res.append("Team");
		res.append("		");
		res.append("Punten\n");
		for(Team team:teams){
		i=i+1;
		res.append(i);
		res.append("	");
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
