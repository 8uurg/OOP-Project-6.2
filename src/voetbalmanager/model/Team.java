package voetbalmanager.model;
import java.util.ArrayList;
import java.util.Observable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import voetbalmanager.exceptions.OpstellingException;
import voetbalmanager.exceptions.TransferException;

/**
 * Een klasse die een team voorstelt.
 */
public class Team extends Observable {
	private String naam;
	private ArrayList<Speler> spelers;
	private Opstelling opstelling;
	private int budget = -1;
	public static final int startBudget = 100000;
	
	private int gewonnen = 0;
	private int gelijk = 0;
	private int verloren = 0;
	private int doelpunten = 0;
	private int tegenpunten = 0;
	
	private static int maxSpeler = 30;
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
	}
	
	public boolean equals(Object other) {
		if(other instanceof Team){
			Team that = (Team)other;
			
			return	this.naam.equals(that.naam) &&
					this.spelers.equals(that.spelers)&&
					this.opstelling.equals(that.opstelling)&&
					this.budget==that.budget&&
					this.gewonnen==that.gewonnen&&
					this.verloren==that.verloren&&
					this.gelijk==that.gelijk&&
					this.doelpunten==that.doelpunten&&
					this.tegenpunten==that.tegenpunten;
					
		}
		return false;
	}
	
	/**
	 * Berekend het huidige puntensaldo van dit team op.
	 * @return Het huidige puntensaldo.
	 */
	public int getPuntenTotaal() {
		return gewonnen * 3 + gelijk;
	}
	
	/**
	 * Vraag het aantal doelpunten van dit team op.
	 * @return Het totale aantal doelpunten van dit team.
	 */
	public int getDoelsaldo() {
		return doelpunten;
	}
	
	/**
	 * Vraag het aantal doelpunten wat dit team tegen heeft gekregen op.
	 * @return Het totale aantal tegendoelpunten van dit team.
	 */
	public int getTegendoelpunten() {
		return tegenpunten;
	}
	
	/**
	 * Vraag het aantal gewonnen wedstrijden op.
	 * @return Aantal gewonnen wedstrijden.
	 */
	public int getGewonnen() {
		return gewonnen;
	}
	
	/**
	 * Vraag het aantal gelijkgespeelde wedstrijden op.
	 * @return Aantal gelijkgespeelde wedstrijden.
	 */
	public int getGelijk() {
		return gelijk;
	}
	
	/**
	 * Vraag het aantal verloren wedstrijden op.
	 * @return Aantal verloren wedstrijden.
	 */
	public int getVerloren() {
		return verloren;
	}
	
	/**
	 * Vraag de naam op van dit team.
	 * @return De naam van dit team.
	 */
	public String getNaam() {
		return naam;
	}
	/**geeft een Lijstje van speler namen
	 * 
	 * @return
	 */
	public String getSpelerNamen(){
		String res="";
		for(int i=0;i<spelers.size();i++){
			res= res + spelers.get(i).getNaam() + "\n";
		}
		return res;
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
		this.setChanged();
		this.notifyObservers();
	}
	
	public int getBudget() {
		return this.budget;
	}
	
	public static int maxAantalSpelers(){
		return maxSpeler;
	}
	
	public void verhoogBudget(int verhoging) throws TransferException {
		if (verhoging == 0) {}
		else if (budget != -1) 
			budget += verhoging;
		else
			throw new TransferException("Er is nog geen budget geÃ¯nitialiseerd voor dit team!");
	}
	
	public void verlaagBudget(int verlaging) throws TransferException {
		if (verlaging == 0) {}
		else if (budget == -1) 
			throw new TransferException("Er is nog geen budget geÃ¯nitialiseerd voor dit team!");
		else if (budget - verlaging < 0) 
			throw new TransferException("Er is niet voldoende budget om deze transactie te maken!");
		else
			budget -= verlaging;
	}
	
	public void voegToe(Speler sp, int transferbedrag) throws TransferException {
		if (spelers.indexOf(sp) != -1)
			throw new TransferException(sp.naam + " zit al in de selectie!");
		else if (spelers.size() >= maxSpeler)
			throw new TransferException("De selectie bevat al het maximum aantal spelers!");
		else {
			verlaagBudget(transferbedrag);
			spelers.add(sp);
			sp.wijzigTeam(this);
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	public void verwijderVanSelectie(Speler sp, int transferbedrag) throws TransferException {
		if (spelers.indexOf(sp) == -1)
			throw new TransferException(sp.naam + " zit niet in de selectie!");
		else {
			verhoogBudget(transferbedrag);
			spelers.remove(sp);
			sp.wijzigTeam(new Team("Vrij beschikbaar", false));
			this.setChanged();
			this.notifyObservers();
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
	 * CreeÃ«r een XML element waarin de gegevens zitten om dit team opnieuw te kunnen reconstrueren.
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
	 * Creeër een team door een XML Element in te laden.
	 * @param el
	 * @return Het gecreeërde team.
	 * @throws TransferException 
	 */
	public static Team laadXMLElement(Element el)
	{
		String naam = el.getAttribute("naam");
		boolean gebruikerTeam = el.getAttribute("gebruikerteam").equalsIgnoreCase("true");
		
		String bg = el.getAttribute("budget"); 
		int budget = bg==""?startBudget:Integer.parseInt(bg);
		
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
		
		team.maakBudget(budget);
		
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
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Functie die ook direct een selectiespeler aan de opstelling toevoegt.
	 * Voegt spelers toe aan een team, zonder selectieoverhead.
	 * Alleen gebruiken voor testen!
	 *
	 * @param speler
	 * @param selectie
	 */
	//Misschien slim om zo'n zelfde systeem te gebruiken voor inlezen opstelling.
	public void overrideAdd(Speler speler, boolean selectie) {
		overrideAdd(speler);
		
		if(selectie) {
		try {
			switch(speler.type) {
			case Aanvaller:
				opstelling.voegToeAanvaller(speler);
				break;
			case Doelman:
				opstelling.voegToeDoelman(speler);
				break;
			case Middenvelder:
				opstelling.voegToeMiddenvelder(speler);
				break;
			case Verdediger:
				opstelling.voegToeVerdediger(speler);
				break;
			default:
				break;
			}
			} catch(OpstellingException e) {
				// Zal in testsituaties niet gebeuren, weer een block erbij die niet getest wordt...
			}
		}
	}
	
	public void setGebruikerTeam(boolean computer) {
		gebruikerTeam = computer;
	}

	public int doeBod(Speler sp) {
		int a =(int) (sp.getPrijs()*1.25);
		return a;
	}
	
	public void kenPuntenToe(int uitslag0,int uitslag1){
		doelpunten=doelpunten+uitslag0;
		tegenpunten=tegenpunten+uitslag1;
		if(uitslag0>uitslag1){
			gewonnen=gewonnen+1;
		}
		else if(uitslag0==uitslag1){
			gelijk = gelijk+1;
		}
		else if(uitslag0<uitslag1){
			verloren = verloren+1;
		}
		this.setChanged();
		this.notifyObservers();
	}
}
