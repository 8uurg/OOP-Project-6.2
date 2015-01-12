package voetbalmanager.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Klasse die een Competitie representeert.
 * 
 * @author MarcoH
 * @version 30/11/2014
 */
public class Competitie {
	private String naam;
	private ArrayList<Team> teams;
	private TransferMarkt transfer;
	private Team spelerTeam;
	private Speelschema schema;

	/**
	 * Cre�ert een competitie met een naam (Bv, Eredivisie)en maakt het mogelijk
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
			return this.naam.equals(that.naam) && this.teams.equals(that.teams);
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
		ArrayList<Speelronde> A = new ArrayList<Speelronde>();
		Speelschema S = new Speelschema(C, B.size());
		while (A.size() < teams.size() * 2 - 2) {
			S = new Speelschema(C, B.size());
			A = S.maakRonden();
		}
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
			res.append(team.getPunten());
			res.append("\n");
			res.append(team.toString());
			res.append('\n');
		}

		return res.toString();
	}

	/**
	 * Cree�r een XML Element waarin de gegevens zitten
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
		return schema;
	}
	public TransferMarkt getTransferMarkt(){
		return transfer;
	}

	/**
	 * Laad een XML element in om een competitie in te laden of te cree�ren.
	 * 
	 * @param el
	 * @return
	 */
	public static Competitie laadXMLElement(Element el) {
		String naam = el.getAttribute("naam");

		Competitie competitie = new Competitie(naam);

		NodeList teamsnodes = ((Element) el.getElementsByTagName("teams").item(
				0)).getElementsByTagName("team");

		for (int i = 0; i < teamsnodes.getLength(); i++)
			competitie
					.addTeam(Team.laadXMLElement((Element) teamsnodes.item(i)));

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
			return Integer.compare(b.getPunten(), a.getPunten());
		}
	};
}
