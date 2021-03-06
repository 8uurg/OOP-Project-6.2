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
	 * verhandelbarespelers geeft alle Beschikbare Spelers op de spelersmarkt.
	 */
	private ArrayList<BeschikbareSpeler> verhandelbareSpelers;
	
	public TransferMarkt(){
//		recenteTransfers = new ArrayList<Transfer>();
		verhandelbareSpelers = new ArrayList<BeschikbareSpeler>();
	}
	
	public boolean equals(Object other) {
		if (other instanceof TransferMarkt) {
			TransferMarkt that = (TransferMarkt)other;
			return this.verhandelbareSpelers.equals(that.verhandelbareSpelers);
			
		}
		return false;
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
		verkopendTeam.verwijderVanSelectie(sp, prijs);
		try {
			kopendTeam.voegToe(sp, prijs);
		} catch (TransferException e) {
			verkopendTeam.voegToe(sp, prijs);
			throw e;
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Maak een speler uit je team verhandelbaar.
	 * @param sp De speler die het team op de spelersmarkt wil zetten.
	 */
	public void maakVerhandelbaar(Speler sp) {
		BeschikbareSpeler bsp = new BeschikbareSpeler(sp,sp.getTeam());
		if(!verhandelbareSpelers.contains(bsp)){
			verhandelbareSpelers.add(bsp);
		}
		this.setChanged();
		this.notifyObservers();
	}
	

	/**
	 * Geeft alle Spelers die op de spelermarkt staan om te verkopen.
	 * @return alle beschikbare spelers op de markt.
	 */
	public ArrayList<BeschikbareSpeler> getVerhandelbareSpelers() {
		return verhandelbareSpelers;
	}
	
	/**
	 * Koop een speler van de markt voor de gevraagde prijs.
	 * @param team Het team dat de speler gekocht heeft.
	 * @param speler De speler die verkocht is aan het team.
	 */
	public void koopSpeler(Team team,BeschikbareSpeler speler) {
		try {
			speler.getSpeler().getTeam().verwijderVanSelectie(speler.getSpeler(),speler.getSpeler().getPrijs());
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
		
		NodeList l = el.getElementsByTagName("beschikbarespeler");
		
		for(int i=0; i<l.getLength(); ++i) {
			Element beschikbarespeler = (Element) l.item(i);
			transferMarkt.verhandelbareSpelers.add(BeschikbareSpeler.laadXMLElement(beschikbarespeler, c));
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
