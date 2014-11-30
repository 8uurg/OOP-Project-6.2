import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import exceptions.TransferException;

/**
 * Een klasse die een team voorstelt.
 */
public class Team {
	private String naam;
	private ArrayList<Speler> spelers;
	private Opstelling opstelling;
	private int budget = -1;
	private int punten;
	
	private int maxSpelers = 22;
	
	public Team(String naam) {
		this.naam = naam;
		spelers = new ArrayList<Speler>();
		opstelling = new Opstelling();
		punten = 0;
	}
	
	public String getNaam() {
		return this.naam;
	}
	
	public Opstelling getOpstelling() {
		return this.opstelling;
	}
	
	public ArrayList<Speler> getSelectie() {
		return this.spelers;
	}
	
	public void maakBudget(int budget) {
		this.budget = budget;
	}
	
	public int getBudget() {
		return this.budget;
	}
	
	public int getPunten() {
		return this.punten;
	}
	public void geefPunten(int punten){
		this.punten = this.punten+punten;
	}
	
	public void verhoogBudget(int verhoging) throws TransferException {
		if (verhoging == 0) {}
		else if (budget != -1) 
			budget += verhoging;
		else
			throw new TransferException("Er is nog geen budget geïnitialiseerd voor dit team!");
	}
	
	public void verlaagBudget(int verlaging) throws TransferException {
		if (verlaging == 0) {}
		else if (budget == -1) 
			throw new TransferException("Er is nog geen budget geïnitialiseerd voor dit team!");
		else if (budget - verlaging < 0) 
			throw new TransferException("Er is niet voldoende budget om deze transactie te maken!");
		else
			budget -= verlaging;
	}
	
	public void voegToe(Speler sp, int transferbedrag) throws TransferException {
		if (spelers.indexOf(sp) != -1)
			throw new TransferException(sp.naam + " zit al in de selectie!");
		else if (spelers.size() >= maxSpelers)
			throw new TransferException("De selectie bevat al het maximum aantal spelers!");
		else {
			verlaagBudget(transferbedrag);
			spelers.add(sp);
			sp.wijzigTeam(this);
		}
	}
	
	public void verwijderVanSelectie(Speler sp, int transferbedrag) throws TransferException {
		if (spelers.indexOf(sp) == -1)
			throw new TransferException(sp.naam + " zit niet in de selectie!");
		else {
			verhoogBudget(transferbedrag);
			spelers.remove(sp);
			sp.wijzigTeam(new Team("Vrij beschikbaar"));
		}
	}
	
	

	@Override
	public String toString()
	{
		StringBuilder res = new StringBuilder();
		
		for(Speler speler:spelers)
		{
			res.append(speler.toString());
			res.append('\n');
		}	
		return res.toString();
	}
	
	/**
	 * Creeër een XML element waarin de gegevens zitten om dit team opnieuw te kunnen reconstrueren.
	 * @param doc
	 * @return
	 */
	public Element getXMLElement(Document doc)
	{
		Element team = doc.createElement("team");
		
		team.setAttribute("naam", naam);
		team.setAttribute("budget", Integer.toString(budget));
		
		for(Speler speler: spelers)
		{
			team.appendChild(speler.getXMLElement(doc));
		}
		
		return team;
	}
	
	/**
	 * Creeër een team door een XML Element in te laden.
	 * @param el
	 * @return Het gecreeërde team.
	 * @throws TransferException 
	 */
	public static Team laadXMLElement(Element el)
	{
		/* TODO: De teamnaam moet nog worden ingelezen vanuit het XML-bestand
		 * TODO: Budget moet worden ingelezen uit XML-bestand (kan worden ingevoerd door getBudget()
		 * 		 of door 
		 */
		String teamNaam = "Moet nog worden gemaakt";
		Team team = new Team(teamNaam);
		NodeList spelers = el.getElementsByTagName("speler");
		
		for(int i=0; i<spelers.getLength(); i++)
		{
			Element speler = (Element) spelers.item(i);
			try {
			team.voegToe(Speler.laadXMLElement(speler), 0);
			} catch (TransferException e) {
				// Ingeladen bestand bevat een invalide team, NOPE!
			}
		}
		
		return team;
	}
	

	
	
}
