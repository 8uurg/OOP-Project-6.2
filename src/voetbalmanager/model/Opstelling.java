package voetbalmanager.model;
import java.util.ArrayList;

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
	
	public void kanWordenOpgesteld(Speler sp) throws OpstellingException  {
		if(aanvallers.indexOf(sp) != -1 || middenvelders.indexOf(sp) != -1 || verdedigers.indexOf(sp) != -1)
			throw new OpstellingException(sp.naam + " staat al opgesteld!");
	}
	
	public void voegToeAanvaller(Speler sp) throws OpstellingException {
		kanWordenOpgesteld(sp);
		if (maxAanvallers <= aanvallers.size())
			throw new OpstellingException("Verwijder eerst een aanvaller voordat u een nieuwe opstelt!");
		aanvallers.add(sp);
	}
	
	public void voegToeMiddenvelder(Speler sp) throws OpstellingException {
		kanWordenOpgesteld(sp);
		if (maxMiddenvelders <= aanvallers.size())
			throw new OpstellingException("Verwijder eerst een middenvelder voordat u een nieuwe opstelt!");
		middenvelders.add(sp);
	}
	
	public void voegToeVerdediger(Speler sp) throws OpstellingException {
		kanWordenOpgesteld(sp);
		if (maxVerdedigers <= aanvallers.size())
			throw new OpstellingException("Verwijder eerst een verdediger voordat u een nieuwe opstelt!");
		verdedigers.add(sp);
	}
	
	public void voegToeDoelman(Speler sp) throws OpstellingException {
		kanWordenOpgesteld(sp);
		if (maxDoelmannen <= aanvallers.size())
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
			throw new OpstellingException(sp.naam + " staat niet opgesteld!");
	}
}
