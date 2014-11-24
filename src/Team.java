import java.util.ArrayList;

import exceptions.OpstellingException;
import exceptions.TransferException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Een klasse die een team voorstelt.
 */
public class Team {
	private ArrayList<Speler> spelers, aanvallers, verdedigers, middenvelders, doelmannen;			// eventueel nog reserves?
	int maxSpelers = 22, maxAanvallers = 3, maxVerdedigers = 4, maxMiddenvelders = 3, maxDoelmannen = 1;
	int budget = -1;
	
	public Team() {
		spelers = new ArrayList<Speler>();
		aanvallers = new ArrayList<Speler>();
		middenvelders = new ArrayList<Speler>();
		verdedigers = new ArrayList<Speler>();
		doelmannen = new ArrayList<Speler>();
	}
	
	public void maakBudget(int budget) {
		this.budget = budget;
	}
	
	public int getBudget() {
		return this.budget;
	}
	
	public void verhoogBudget(int verhoging) throws TransferException {
		if (verhoging == 0) {}
		else if (budget != -1) 
			budget += verhoging;
		else
			throw new TransferException("Er is nog geen budget geïnitialiseerd voor dit team!");
	}
	
	public void verlaagBudget(int verlaging) throws TransferException {
		if (verlaging == 0) {}
		else if (budget == -1) 
			throw new TransferException("Er is nog geen budget geïnitialiseerd voor dit team!");
		else if (budget - verlaging < 0) 
			throw new TransferException("Er is niet voldoende budget om deze transactie te maken!");
		else
			budget -= verlaging;
			
	}
	
	public void voegToeSelectie(Speler sp, int transferbedrag) throws TransferException {
		if (spelers.indexOf(sp) != -1)
			throw new TransferException(sp.naam + " zit al in de selectie!");
		else if (spelers.size() >= maxSpelers)
			throw new TransferException("De selectie bevat al het maximum aantal spelers!");
		else {
			verlaagBudget(transferbedrag);
			spelers.add(sp);
		}
			
	}
	
	public void verwijderVanSelectie(Speler sp, int transferbedrag) throws TransferException {
		if (spelers.indexOf(sp) == -1)
			throw new TransferException(sp.naam + " zit niet in de selectie!");
		else {
			verhoogBudget(transferbedrag);
			spelers.remove(sp);
		}
	}
	
	public void kanWordenOpgesteld(Speler sp) throws OpstellingException  {
		if(aanvallers.indexOf(sp) != -1 || middenvelders.indexOf(sp) != -1 || verdedigers.indexOf(sp) != -1)
			throw new OpstellingException(sp.naam + " staat al opgesteld!");
		
		if (spelers.indexOf(sp) == -1) 
			throw new OpstellingException(sp.naam + " zit niet in de selectie!");
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
	 * Creeër een team door een XML Element in te laden.
	 * @param el
	 * @return Het gecreeërde team.
	 * @throws TransferException 
	 */
	public static Team laadXMLElement(Element el)
	{
		Team team = new Team();
		NodeList spelers = el.getElementsByTagName("speler");
		
		for(int i=0; i<spelers.getLength(); i++)
		{
			Element speler = (Element) spelers.item(i);
			try {
			team.voegToeSelectie(Speler.laadXMLElement(speler), 0);
			} catch (TransferException e) {
				// Ingeladen bestand bevat een invalide team, NOPE!
			}
		}
		
		return team;
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