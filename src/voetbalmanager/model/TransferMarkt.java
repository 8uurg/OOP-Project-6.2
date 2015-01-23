package voetbalmanager.model;
import java.util.ArrayList;
import java.util.Observable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import voetbalmanager.exceptions.TransferException;

/**
 * De transfermarkt, bedoeld om spelers te verhandelen.
 * @author Jeroen, Marco
 * 
 */
public class TransferMarkt extends Observable {
	
	/**
	 * recenteTransfers geeft alle transfers terug die tussen teams plaatsvonden.
	 * verhandelbarespelers geeft alle Beschikbare Spelers op de spelersmarkt.
	 */
	private ArrayList<Transfer> recenteTransfers;
	private ArrayList<BeschikbareSpeler> verhandelbareSpelers;
	
	public TransferMarkt(){
		recenteTransfers = new ArrayList<Transfer>();
		verhandelbareSpelers = new ArrayList<BeschikbareSpeler>();
		//BeschikbareSpeler dummie = new BeschikbareSpeler(new Speler("a", 0, 0, null, null, 0, 0, 0),null);
		//maakVerhandelbaar(new Speler("a", 0, 0, null, null, 0, 0, 0));
	}
	
	// Hierbij wordt gewoon de prijs van de speler zelf gebruikt (dus speler.prijs)
	/**
	 * Maakt transfers mogelijk tussen twee verschillende teams.
	 * @param verkopendTeam Team dat een aanbod gekregen heeft.
	 * @param kopendTeam Team dat een aanbod gedaan heeft op een speler.
	 * @param sp De speler die transferred wordt tussen de teams.
	 * @param prijs De prijs die voor de speler betaald wordt.
	 * @throws TransferException
	 */
	public void Transfer(Team verkopendTeam, Team kopendTeam, Speler sp, int prijs) throws TransferException {
		sp.prijs=prijs;
		recenteTransfers.add(new Transfer(verkopendTeam, kopendTeam, sp));
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Maak een speler uit je team verhandelbaar.
	 * @param sp De speler die het team op de spelersmarkt wil zetten.
	 */
	public void maakVerhandelbaar(Speler sp) {
		BeschikbareSpeler bsp = new BeschikbareSpeler(sp,sp.getTeam());
		verhandelbareSpelers.add(bsp);
		this.setChanged();
		this.notifyObservers();
	}
	
	// Moet prijs bij ontslag op 0 worden gezet?
	//Wordt op dit moment nog niet geimplementeerd.
	public void Ontsla(Speler sp) throws TransferException {
		sp.team.verwijderVanSelectie(sp, 0);
		maakVerhandelbaar(sp);
	}
	
	/**
	 * Laat alle transfers zien tussen teams die er hebben plaatsgevonden.
	 * @return alle recente transfers.
	 */
	public ArrayList<Transfer> getRecenteTransfers() {
		return recenteTransfers;
	}
	
	/**
	 * Geeft alle Spelers die op de spelermarkt staan om te verkopen.
	 * @return alle beschikbare spelers op de markt.
	 */
	public ArrayList<BeschikbareSpeler> getVerhandelbareSpelers() {
		return verhandelbareSpelers;
	}
	
	/**
	 * Vergelijkt de oude teams van de beschikbare spelers met 1 team. Dit is voor de AI om te checken of de speler de markt gebruikt heeft.
	 * @param team Het team waarvan gecheckt moet worden of er een speler op de markt staat.
	 * @return boolean.
	 */
	public boolean getOudTeam(Team team){
		for(BeschikbareSpeler a:verhandelbareSpelers){
			if(a.getOudTeam().equals(team)){
				return true;
			}
		}
		return false; 
	}
	/**
	 * Geeft de score terug van de slechtste speler op de spelersmarkt.
	 * @return Score.
	 */
	public double getMinWaarde() {
		double minSpelerwaarde = 5*10^6;
		for(BeschikbareSpeler speler:verhandelbareSpelers){
			if(speler.getSpeler().getSpelerWaarde()<minSpelerwaarde){
				minSpelerwaarde=speler.getSpeler().getSpelerWaarde();
			}
		}
		return 0;
	}
	/**
	 * Koop een speler van de markt voor de gevraagde prijs.
	 * @param team Het team dat de speler gekocht heeft.
	 * @param speler De speler die verkocht is aan het team.
	 */
	public void koopSpeler(Team team,BeschikbareSpeler speler) {
		try {
			team.voegToe(speler.getSpeler(),speler.getSpeler().getPrijs());
			
		} catch (TransferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verkocht(speler);
	}
	/**
	 * Als een speler gekocht is wordt hier het bedrag overgemaakt naar het oude team dat is betaald voor de speler.
	 * ook wordt de speler van de lijst beschikbare spelers afgehaald.
	 * @param speler De speler die verkocht is.
	 */
	private void verkocht(BeschikbareSpeler speler){
		try {
			speler.getOudTeam().verhoogBudget(speler.getSpeler().getPrijs());
		} catch (TransferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verhandelbareSpelers.remove(verhandelbareSpelers.indexOf(speler));	
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Retourneert aan de AI een aantal spelers die voordelig zijn om het team te versterken.
	 * @param spelers De spelers waarmee de beschikbare spelers vergeleken worden.
	 * @return een lijst met BeschikbareSpelers die het team versterken als ze aan de selectie worden toegevoegd.
	 */
	public ArrayList<BeschikbareSpeler> interesse(ArrayList<Speler> spelers){
		ArrayList<BeschikbareSpeler> potentieel = new ArrayList<BeschikbareSpeler>();
		for(BeschikbareSpeler speler: verhandelbareSpelers){
			if(speler.moetKopen(spelers)){
				potentieel.add(speler);
			}
		}
		return potentieel;
	}
	
	/**
	 * Laad een TransferMarkt uit een XML Element.
	 * @param el	Het element waaruit de transfermarkt moet worden ingeladen.
	 * @param c		De competitie waaruit het element komt.
	 * @return De ingeladen transfermarkt.
	 */
	public static TransferMarkt laadXMLElement(Element el, Competitie c) {
		TransferMarkt transferMarkt = new TransferMarkt();
		
		if(el!=null) {
			NodeList l = el.getElementsByTagName("beschikbarespeler");
			
			for(int i=0; i<l.getLength(); ++i) {
				transferMarkt.verhandelbareSpelers.add(BeschikbareSpeler.laadXMLElement(el, c));
			}
		}
		
		return transferMarkt;
	}
	
	/**
	 * Genereer een XML Element voor opslaan.
	 * @param doc Het document waarin het element terecht komt
	 * @return Een nieuw element dat deze instantie van deze klasse weergeeft.
	 */
	public Element getXMLElement(Document doc) {
		Element e = doc.createElement("transfermarkt");
		
		for(BeschikbareSpeler s: verhandelbareSpelers) {
			e.appendChild(s.getXMLElement(doc));
		}
		
		return e;
	}
	

}
