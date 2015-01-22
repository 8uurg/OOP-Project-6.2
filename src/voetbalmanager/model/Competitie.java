package voetbalmanager.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import voetbalmanager.controller.teammanager.AITeamManager;

/**
 * Klasse die een Competitie representeert.
 * 
 * @author MarcoH
 * @version 30/11/2014
 */
public class Competitie extends Observable {
	private String naam;
	private ArrayList<Team> teams;
	private TransferMarkt transfer;
	private Team spelerTeam;
	private Speelschema schema;
	private int week;

	/**
	 * Creëert een competitie met een naam (Bv, Eredivisie)en maakt het mogelijk
	 * om teams toe te voegen aan deze competitie.
	 * 
	 * @param naam
	 */
	public Competitie(String naam) {
		this.naam = naam;
		this.teams = new ArrayList<Team>();
		this.transfer = new TransferMarkt();
	}

	/**
	 * Equals-methode voor verschillende competities.
	 */
	public boolean equals(Object other) {
		if (other instanceof Competitie) {
			Competitie that = (Competitie) other;
			return this.naam.equals(that.naam) && 
					this.teams.equals(that.teams);
		}

		return false;
	}

	/**
	 * Voeg een team toe aan een bestaande competitie
	 * 
	 * @param a
	 *            Het team wat je in de competitie wil laten deelnemen. Voor
	 *            Eredivisie is de grens van 18 teams opgezet.
	 */
	public void addTeam(Team a) {
		if (teams.size() < 19)
			teams.add(a);
	}

	/**
	 * Geeft een lijstje van de teams in de huidige competitie.
	 * 
	 * @return
	 */
	public ArrayList<Team> getTeams() {
		return teams;
	}

	/**
	 * Vraagt het team op dat door de speler bestuurd wordt.
	 * 
	 * @return Het team van de speler als dat bestaat. Anders null.
	 */
	public Team getSpelerTeam() {
		if (spelerTeam != null)
			return spelerTeam;

		for (Team team : teams)
			if (team.isSpelerBestuurd())
				return (spelerTeam = team);

		return null;
	}

	/**
	 * Het speelschema van de competitie Ben er helaas niet in geslaagd om het
	 * voor nu voor elkaar te krijgen om ieder team 1 game te laten spelen en
	 * resultaten te weergeven. Op dit moment worden er 2 games gespeeld door
	 * ieder team per Speelronde.
	 */
	public Speelschema maakSpeelSchema() {
		ArrayList<Team> B = this.teams;
		ArrayList<Team[]> C = new ArrayList<Team[]>();
		ArrayList<Team[]> D = new ArrayList<Team[]>();

		for (int i = 0; i < (B.size() - 1); i++) {// aantal games
													// (teams.size()-1) omdat je
													// jezelf moet overslaan
			int k = 0;
			while (k <= (B.size() - k - 1)) {
				Team[] a = { B.get(k), B.get(B.size() - k - 1) };
				Team[] b = { B.get(B.size() - k - 1), B.get(k) };
				C.add(a);
				D.add(b);
				k++;
			}
			nextRound(B);
		}
		C.addAll(D);
		Speelschema S = new Speelschema(C, B.size());
		S.maakRonden();
		schema = S;
		return S;
	}

	/**
	 * Verandert de indeling van de teams zodat iedere ronde tegen andere teams
	 * gespeeld wordt.
	 */
	public static void nextRound(ArrayList<Team> B) {
		Team temp = B.get(1);
		B.remove(1);
		B.add(temp);
	}
	
	

	/**
	 * Maakt een String-representatie van de competitie.
	 */
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("Competitie: ");
		res.append(naam);
		res.append("\n");
		res.append("Team");
		res.append("		");
		res.append("Punten\n");

		for (Team team : teams) {
			res.append(team.getNaam());
			if (team.getNaam().length() < 8)
				res.append("	");
			res.append("	");
			res.append(team.toString());
			res.append("	");
			res.append(team.getPuntenTotaal());
			res.append('\n');
		}

		return res.toString();
	}

	/**
	 * Creeër een XML Element waarin de gegevens zitten
	 * 
	 * @param doc
	 *            Document waarin de gegevens gestopt worden.
	 * @return Het element dat in een bepaald document gestopt kan worden.
	 */
	public Element getXMLElement(Document doc) {
		Element comp = doc.createElement("competitie");
		
		comp.setAttribute("naam", this.naam);

		Element teamlist = doc.createElement("teams");

		for (Team team : teams)
			teamlist.appendChild(team.getXMLElement(doc));

		comp.appendChild(teamlist);

		return comp;
	}
	
	public Speelschema getSchema(){
		if(schema==null){
			maakSpeelSchema();
		}
		return schema;
	}
	
	public TransferMarkt getTransferMarkt(){
		return transfer;
	}

	/**
	 * Laad een XML element in om een competitie in te laden of te creeëren.
	 * 
	 * @param el
	 * @return
	 */
	public static Competitie laadXMLElement(Element el) {
		String naam = el.getAttribute("naam");

		Competitie competitie = new Competitie(naam);

		NodeList teamsnodes = ((Element) el.getElementsByTagName("teams").item(0)).getElementsByTagName("team");

		for (int i = 0; i < teamsnodes.getLength(); i++){
			Team nieuwteam = Team.laadXMLElement((Element) teamsnodes.item(i));
			competitie.addTeam(nieuwteam);
			if(nieuwteam.isSpelerBestuurd()) competitie.setSpelerTeam(nieuwteam);
		}

		return competitie;
	}

	/**
	 * Maakt een nieuwe klasse competitie welke gesorteerd wordt en wordt
	 * teruggegeven.
	 * 
	 * @param voorwaarde
	 *            Op dit moment moet de voorwaarde "Naam" of "Punten" op gegeven
	 *            worden om te sorteren op naam of punten
	 * @return Geeft een nieuw object Competitie terug die gesorteerd is op de
	 *         gewenste eigenschap.
	 */
	public Competitie Sorteren(String voorwaarde) {
		Competitie a = new Competitie(naam);
		a.teams = this.teams;
		if (voorwaarde.equals("Naam"))
			Collections.sort(a.teams, NaamOrder);
		else if (voorwaarde.equals("Punten"))
			Collections.sort(a.teams, PuntenOrder);
		return a;
	}
	
	/**
	 * Setter voor naam
	 * @param naam Nieuwe naam voor deze competitie
	 */
	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	/**
	 * Getter voor naam
	 * @return De naam van deze competitie
	 */
	public String getNaam() {
		return this.naam;
	}
	

	/**
	 * Sorteert met behulp van de Comparator van Java de ArrayList op Teamnaam.
	 * Sorteert altijd van A-Z
	 */
	static Comparator<Team> NaamOrder = new Comparator<Team>() {
		public int compare(Team a, Team b) {
			return a.getNaam().compareTo(b.getNaam());
		}
	};
	/**
	 * Sorteert met behulp van de Comparator van Java de ArrayList op het aantal
	 * punten. Sorteert altijd van Hoog naar Laag. (38 staat boven 26).
	 */
	static Comparator<Team> PuntenOrder = new Comparator<Team>() {
		public int compare(Team a, Team b) {
			int puntena = a.getPuntenTotaal();
			int puntenb = b.getPuntenTotaal();
			if(puntena<puntenb) return 1;
			if(puntenb<puntena) return -1;
			// Zelfde aantal punten.
			puntena = a.getDoelsaldo();
			puntenb = b.getDoelsaldo();
			if(puntena<puntenb) return 1;
			if(puntenb<puntena) return -1;
			return 0;
			
		}
	};
	
	
	/**
	 * Setter voor gebruikerTeam
	 */
	public void setSpelerTeam(Team team) {
		if(spelerTeam!=null) {
			spelerTeam.setGebruikerTeam(false);
		}
		spelerTeam = team;
		spelerTeam.setGebruikerTeam(true);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void startCompetitie(){
		int week=this.week;
		if(week<schema.getSchema().size()){
			schema.getSchema().get(week);
			if(week+1<schema.getSchema().size()){
				schema.getSchema().get(week+1);
			}
			else{
				System.out.println("Dit is de laatste speelronde, hierna volgen geen rondes meer");
			}
			AITeamManager a = new AITeamManager();
			a.runManagementCycle(this);
			week++;
		}
		else{
			System.out.println("Deze competitie is afgelopen, er kan geen nieuwe ronde meer gespeeld worden");
		}
		
	}
}
