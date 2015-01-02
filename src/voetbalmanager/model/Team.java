package voetbalmanager.model;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import voetbalmanager.exceptions.TransferException;

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
	private boolean gebruikerTeam;
	
	/**
	 * Maakt een nieuw team aan met een bepaalde naam.
	 * @param naam			De naam van het team
	 * @param gebruikerTeam	Is de gebruiker (de speler zelf) de eigenaar van dit team?
	 */
	public Team(String naam, boolean gebruikerTeam) {
		this.naam = naam;
		this.gebruikerTeam = gebruikerTeam;
		spelers = new ArrayList<Speler>();
		opstelling = new Opstelling();
		punten = 0;
	}
	
	public boolean equals(Object other) {
		if(other instanceof Team){
			Team that = (Team)other;
			
			return	this.naam.equals(that.naam) &&
					this.spelers.equals(that.spelers)&&
					this.opstelling.equals(that.opstelling)&&
					this.budget==that.budget&&
					this.punten==that.punten;
					
		}
		return false;
	}
	
	/**
	 * Vraag de naam op van dit team.
	 * @return De naam van dit team.
	 */
	public String getNaam() {
		return this.naam;
	}
	
	/**
	 * Vraag de opstelling op van dit team.
	 * @return De opstelling van dit team.
	 */
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
			sp.wijzigTeam(new Team("Vrij beschikbaar", false));
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
		team.setAttribute("gebruikerteam", Boolean.toString(gebruikerTeam));
		
		for(Speler speler: spelers)
		{
			team.appendChild(speler.getXMLElement(doc));
		}
		
		return team;
	}
	
	/**
	 * Cree�r een team door een XML Element in te laden.
	 * @param el
	 * @return Het gecree�rde team.
	 * @throws TransferException 
	 */
	public static Team laadXMLElement(Element el)
	{
		/* TODO: De teamnaam moet nog worden ingelezen vanuit het XML-bestand
		 * TODO: Budget moet worden ingelezen uit XML-bestand (kan worden ingevoerd door getBudget()
		 * 		 of door 
		 */
		String naam = el.getAttribute("naam");
		boolean gebruikerTeam = el.getAttribute("gebruikerteam").equalsIgnoreCase("true");
		int budget = Integer.parseInt(el.getAttribute("budget"));
		
		Team team = new Team(naam, gebruikerTeam);
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
	
	/**
	 * Is een team computergestuurd?
	 * @return Boolean die aangeeft of dit team gemanaged wordt door een computer.
	 */
	public boolean isComputerGestuurd(){
		return !gebruikerTeam;
	}
	
	/**
	 * Is het team van de speler.
	 * @return Boolean die aangeeft of dit team van de speler is.
	 */
	public boolean isSpelerBestuurd(){
		return gebruikerTeam;
	}

	/**
	 * Functie om spelers toe te voegen
	 * Alleen gebruiken voor testen!
	 * @param speler
	 */
	public void overrideAdd(Speler speler) {
		spelers.add(speler);
		speler.wijzigTeam(this);
	}
	
}
