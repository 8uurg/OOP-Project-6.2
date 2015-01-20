package voetbalmanager;

import java.util.Observable;

import voetbalmanager.model.Competitie;

public class Spel extends Observable {
	
	private Competitie huidigeCompetitie;
	
	/**
	 * Vraagt de huidige competitie op.
	 * @return De huidige competitie
	 */
	public Competitie getCompetitie() {
		return huidigeCompetitie;
	}

	/**
	 * Verandert de huidige competitie en roept alle observers op om up te daten.
	 * @param competitie De competitie
	 */
	public void setCompetitie(Competitie competitie) {
		this.huidigeCompetitie = competitie;
		this.setChanged();
		this.notifyObservers();
	}
}
