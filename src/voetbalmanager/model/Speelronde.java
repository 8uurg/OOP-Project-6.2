package voetbalmanager.model;

/**
 * Creeert een Speelronde met wedstrijden.
 */
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Speelronde {

	ArrayList<Wedstrijd> wedstrijden;

	/**
	 * Creeert een Speelronde met wedstrijden.
	 */
	public Speelronde() {
		wedstrijden = new ArrayList<Wedstrijd>();
	}
	
	public boolean equals(Object other) {
		boolean a= false;
		if(other instanceof Speelronde) {
			Speelronde that = (Speelronde) other;
			a=true;
			for(int i=0;i<that.getSize();i++){
				a = this.wedstrijden.get(i).equals(that.wedstrijden.get(i));
				if(a==false){
					return a;
				}
			}
		}
		return a;
	}
	/**
	 * Voegt een wedstrijd toe aan de ArrayList van de speelronde
	 * 
	 * @param wedstrijd
	 */
	public void voegToe(Wedstrijd wedstrijd) {
		wedstrijden.add(wedstrijd);
	}

	/**
	 * Geeft de grootte van de wedstrijden.
	 * 
	 * @return Int grootte van de wedstrijden.
	 */
	public int getSize() {
		return wedstrijden.size();
	}

	/**
	 * Creeert een String van iedere speelronde
	 */
	public String toString() {
		String s = "";
		s += "Speelronde:\n";
		for (int i = 0; i < wedstrijden.size(); i++)
			s += wedstrijden.get(i).toString() + "\n";
		return s;
	}

	/**
	 * Geeft de wedstrijden terug in een ArrayList.
	 * 
	 * @return ArrayList<Wedstrijd> met alle wedstrijden van de speelronde.
	 */
	public ArrayList<Wedstrijd> getWedstrijden() {
		return wedstrijden;
	}

	/**
	 * Vergelijkt de wedstrijden uit een speelronde met een wedstrijd.
	 *
	 * @param w
	 *            wedstrijd waar de speelronde mee vergeleken wordt.
	 * @return Boolean die aangeeft of een wedstrijd aanwezig is in een
	 *         speelronde.
	 */
	public boolean WedstrijdEquals(Wedstrijd w) {
		for (int i = 0; i < wedstrijden.size(); i++)
			if (wedstrijden.get(i).teamEquals(w))
				return true;
		return false;
	}

	/**
	 * Om makkeljk speelrondes aan te passen
	 * 
	 * @param i
	 *            Nummer van de plaats waar de wedstrijd in de ArrayList
	 *            wedstrijden staat.
	 */
	public void verwijder(int i) {
		wedstrijden.remove(i);
	}
	
	/**
	 * Retourneert een Wedstrijd uit de speelronde.
	 * @param i de Wedstrijd op plaats i in de ArrayList.
	 * @return Wedstrijd.
	 */
	public Wedstrijd get(int i) {
		return wedstrijden.get(i);
	}
	
	/**
	 * Kijkt of een team al voorkomt in de speelronde.
	 * @param team Team dat gecheckt wordt.
	 * @return boolean.
	 */
	public boolean teamEquals(Team team) {
		for(int j=0;j<2;j++){
		for (int i = 0; i < wedstrijden.size(); i++)
			if (wedstrijden.get(i).getTeams()[j].equals(team))
				return true;
		}
		return false;
	}
	
	/**
	 * Checkt of de Speelronde alle teams bevat die Speelronde b ook bevat
	 * @param b de Speelronde die vergeleken wordt.
	 * @return boolean.
	 */
	public boolean containsAll(Speelronde b){
		if(!(getSize()==b.getSize())){
			return false;
		}
		int x = 0;
		while(x<b.getSize()){
			for(int i=0;i<2;i++){
				if(!teamEquals(b.get(x).getTeams()[i])){
					return false;
				}
			}x++;
		}
		return true;
	}
	
	/**
	 * Checkt of alle teams die in wedstrijden zijn onderverdeeld in de speelronde zitten.
	 * @param teams Alle teams die onderverdeeld zijn in wedstrijden.
	 * @return boolean.
	 */
	public boolean containsTeams(ArrayList<Team> teams){
		for(Team team:teams){
			if(!teamEquals(team)){
				return false;
			}
		}
		return true;
	}
	
	public Element getXMLElement(Document doc) {
		Element speelronde = doc.createElement("Speelronde");
		for(Wedstrijd wedstrijd: wedstrijden){
			speelronde.appendChild(wedstrijd.getXMLElement(doc));
		}
		return speelronde;
	}

	public static Speelronde laadXMLelement(Element el, Competitie competitie) {
		Speelronde ronde = new Speelronde();
		NodeList wedstrijden = el.getElementsByTagName("Wedstrijd");
		
		for(int i=0;i<wedstrijden.getLength();i++){
			Element wedstrijd = (Element) wedstrijden.item(i);
			ronde.voegToe(Wedstrijd.laadXMLElement(wedstrijd,competitie));
		}
		return ronde;
	}
}
