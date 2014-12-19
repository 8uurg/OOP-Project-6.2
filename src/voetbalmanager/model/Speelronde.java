package voetbalmanager.model;

/**
 * Creeert een Speelronde met wedstrijden.
 */
import java.util.ArrayList;

public class Speelronde {

	int ronde;
	ArrayList<Wedstrijd> wedstrijden;

	/**
	 * Creeert een Speelronde met wedstrijden.
	 */
	public Speelronde() {
		this.ronde = ronde;
		wedstrijden = new ArrayList<Wedstrijd>();
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

	public Wedstrijd get(int i) {
		return wedstrijden.get(i);
	}
}
