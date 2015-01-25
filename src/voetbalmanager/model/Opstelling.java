package voetbalmanager.model;
import java.util.ArrayList;
import java.util.Random;

import voetbalmanager.controller.wedstrijdsimulatie.SomKrachten;
import voetbalmanager.exceptions.OpstellingException;

public class Opstelling {
	
	private ArrayList<Speler> aanvallers, verdedigers, middenvelders, doelmannen;
	Speler doelman;
	// Standaard opstelling is 4 - 3 - 3.
	private int maxAanvallers = 3, maxVerdedigers = 4, maxMiddenvelders = 3, maxDoelmannen = 1;
	
	public Opstelling() {
		aanvallers = new ArrayList<Speler>();
		middenvelders = new ArrayList<Speler>();
		verdedigers = new ArrayList<Speler>();
		doelmannen = new ArrayList<Speler>();
	}
	
	/**
	 * Getter voor maxAanvallers
	 * @return het maximale aantal aanvallers.
	 */
	public int getAanvallers() {
		return maxAanvallers;
	}
	
	/**
	 * Getter voor maxMiddenvelders
	 * @return het maximale aantal middenvelders.
	 */
	public int getMiddenvelders() {
		return maxMiddenvelders;
	}
	
	/**
	 * Getter voor maxVerdedigers
	 * @return het maximale aantal verdedigers.
	 */
	public int getVerdedigers() {
		return maxVerdedigers;
	}
	
	/**
	 * Getter voor maxDoelmannen
	 * @return het maximale aantal doelmannen.
	 */
	public int getDoelmannen() {
		return maxDoelmannen;
	}
	
	public boolean equals(Object other) {
		if(other instanceof Opstelling){
			Opstelling that = (Opstelling)other;
			
			return 	this.aanvallers.equals(that.aanvallers)&&
					this.verdedigers.equals(that.verdedigers)&&
					this.middenvelders.equals(that.middenvelders)&&
					this.doelmannen.equals(that.doelmannen);
		}
		return false;
	}
	
	public void setTypeOpstelling(int maxaanvallers, int maxverdedigers, int maxmiddenvelders) {
		this.maxAanvallers = maxaanvallers;
		this.maxVerdedigers = maxverdedigers;
		this.maxMiddenvelders = maxmiddenvelders;
	}
	
	public String getOpstelling() {
		String s = "";
		s += maxVerdedigers;
		s += " - ";
		s += maxMiddenvelders;
		s += " - ";
		s += maxAanvallers;
		return s;
	}
	
	public void kanWordenOpgesteld(Speler sp) throws OpstellingException  {
		if(aanvallers.indexOf(sp) != -1 || middenvelders.indexOf(sp) != -1 || verdedigers.indexOf(sp) != -1)
			throw new OpstellingException(sp.getNaam() + " staat al opgesteld!");
	}
	
	public void voegToeAanvaller(Speler sp) throws OpstellingException {
		kanWordenOpgesteld(sp);
		if (maxAanvallers <= aanvallers.size())
			throw new OpstellingException("Verwijder eerst een aanvaller voordat u een nieuwe opstelt!");
		aanvallers.add(sp);
	}
	
	public void voegToeMiddenvelder(Speler sp) throws OpstellingException {
		kanWordenOpgesteld(sp);
		if (maxMiddenvelders <= middenvelders.size())
			throw new OpstellingException("Verwijder eerst een middenvelder voordat u een nieuwe opstelt!");
		middenvelders.add(sp);
	}
	
	public void voegToeVerdediger(Speler sp) throws OpstellingException {
		kanWordenOpgesteld(sp);
		if (maxVerdedigers <= verdedigers.size())
			throw new OpstellingException("Verwijder eerst een verdediger voordat u een nieuwe opstelt!");
		verdedigers.add(sp);
	}
	
	public void voegToeDoelman(Speler sp) throws OpstellingException {
		kanWordenOpgesteld(sp);
		if (maxDoelmannen <= doelmannen.size())
			throw new OpstellingException("Verwijder eerst uw doelman voordat u een nieuwe opstelt!");
		doelmannen.add(sp);
	}
	
	public boolean geldigeOpstelling () {
		if (aanvallers.size() + middenvelders.size() + verdedigers.size() == 10 && 
				doelmannen.size() == 1)
			return true;
		return false;
	}
	
	public void verwijderSpeler(Speler sp) throws OpstellingException {
		if (aanvallers.indexOf(sp) != -1)
			aanvallers.remove(sp);
		else if (middenvelders.indexOf(sp) != -1)
			middenvelders.remove(sp);
		else if (verdedigers.indexOf(sp) != -1)
			verdedigers.remove(sp);
		else if (doelmannen.indexOf(sp) != -1)
			doelmannen.remove(sp);
		else
			throw new OpstellingException(sp.getNaam() + " staat niet opgesteld!");
	}
	
	/**
	 * Geeft de som van de krachten van alle aanvallers.
	 * @return De som van alle krachten van alle aanvallers.
	 */
	public SomKrachten getAanvallersKrachten() {
		return getKrachten(aanvallers);
	}
	
	/**
	 * Geeft de som van de krachten van alle middenvelders.
	 * @return De som van alle krachten van alle middenvelders.
	 */
	public SomKrachten getMiddenveldersKrachten() {
		return getKrachten(middenvelders);
	}
	
	/**
	 * Geeft de som van de krachten van alle verdedigers.
	 * @return De som van alle krachten van alle verdedigers.
	 */
	public SomKrachten getVerdedigersKrachten() {
		return getKrachten(verdedigers);
	}
	
	/**
	 * Geeft de som van de krachten van alle verdedigers.
	 * @return De som van alle krachten van alle verdedigers.
	 */
	public SomKrachten getKeeperKrachten() {
		// TODO Auto-generated method stub
		return getKrachten(doelmannen);
	}
	
	public Speler getWillekeurigeAanvaller(Random rnd) {
		int i = rnd.nextInt(aanvallers.size());
		return aanvallers.get(i);
	}
	
	/**
	 * Geeft de som van de krachten in een bepaalde arraylist.
	 * @param spelers De arraylist waar de krachten van opgeteld moeten worden.
	 * @return De som van alle krachten.
	 */
	private SomKrachten getKrachten(ArrayList<Speler> spelers) {
		int aanval = 0;
		int verdediging = 0;
		int uithouding = 0;
		
		for(Speler speler: spelers) {
			aanval += speler.getOffensief();
			verdediging += speler.getDefensief();
			uithouding += speler.getUithouding();
		}
		
		return new SomKrachten(aanval, verdediging, uithouding);
	}

	/**
	 * Reset de opstelling naar een lege opstelling.
	 */
	public void clear() {
		aanvallers.clear();
		middenvelders.clear();
		verdedigers.clear();
		doelmannen.clear();
	}
	
	
	
}
