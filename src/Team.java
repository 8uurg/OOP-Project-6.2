import java.util.ArrayList;

import exceptions.OpstellingException;
import exceptions.TransferException;

public class Team {
	private ArrayList<Speler> spelers, aanvallers, verdedigers, middenvelders;			// eventueel nog reserves?
	private ArrayList<Doelman> doelmannen;
	int maxSpelers = 22, maxAanvallers = 3, maxVerdedigers = 4, maxMiddenvelders = 3, maxDoelmannen = 1;
	
	public Team() {
		spelers = new ArrayList<Speler>();
		aanvallers = new ArrayList<Speler>();
		middenvelders = new ArrayList<Speler>();
		verdedigers = new ArrayList<Speler>();
		doelmannen = new ArrayList<Doelman>();
	}
	
	public void voegToeSelectie(Speler sp) throws TransferException {
		if (spelers.indexOf(sp) != -1)
			throw new TransferException(sp + " zit al in de selectie!");
		else if (spelers.size() >= maxSpelers)
			throw new TransferException("De selectie bevat al het maximum aantal spelers!");
		else
			spelers.add(sp);
	}
	
	public void verwijderVanSelectie(Speler sp) throws TransferException {
		if (spelers.indexOf(sp) == -1)
			throw new TransferException(sp + " zit niet in de selectie!");
	}
	
	public void kanWordenOpgesteld(Speler sp) throws OpstellingException  {
		if(aanvallers.indexOf(sp) != -1 || middenvelders.indexOf(sp) != -1 || verdedigers.indexOf(sp) != -1)
			throw new OpstellingException(sp + " staat al opgesteld!");
		
		if (spelers.indexOf(sp) == -1) 
			throw new OpstellingException(sp + " zit niet in de selectie!");
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
		if (sp instanceof Doelman)
			doelmannen.add((Doelman)sp);
		else
			throw new OpstellingException(sp + " is geen doelman!");
	}
	
	public boolean geldigeOpstelling () {
		if (aanvallers.size() + middenvelders.size() + verdedigers.size() + doelmannen.size() == 11 && 
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
			throw new OpstellingException(sp + " staat niet opgesteld!");
	}
	
	
}