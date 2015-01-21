package voetbalmanager.model;

/**
 * Creeert een Speelronde met wedstrijden.
 */
import java.util.ArrayList;

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

	public Wedstrijd get(int i) {
		return wedstrijden.get(i);
	}
	
	public boolean teamEquals(Team team) {
		for(int j=0;j<2;j++){
		for (int i = 0; i < wedstrijden.size(); i++)
			if (wedstrijden.get(i).getTeams()[j].equals(team))
				return true;
		}
		return false;
	}
	public boolean containsAny(Speelronde b){
		int x=0;
		while(x<b.getSize()){
			if(wedstrijden.contains(b.get(x))){
				return true;
			}
			x++;
		}
		return false;
	}
	
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
	public void merge(Speelronde b){
		while(b.getSize()>0){
			voegToe(b.get(0));
			b.verwijder(0);
		}
	}
}
