package voetbalmanager.model;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import voetbalmanager.XMLLoader;
import voetbalmanager.XMLWriter;

/**
 * Een klasse die een speler representeerdt
 */
public class Speler {
	String naam;
	int nummer;
	int prijs;
	int offensief;
	int defensief;
	int uithoudingsvermogen;
	Status status;
	Type type;
	Team team = new Team("Ongedefinieerd", false);

	public enum Status {
		Beschikbaar, Blessure, Gelekaart, Rodekaart;
	}

	public enum Type {
		Aanvaller, Middenvelder, Verdediger, Doelman;
	}

	/**
	 * Creeër een speler.
	 * 
	 * @param naam
	 * @param nummer
	 * @param prijs
	 * @param status
	 * @param type
	 * @param offensief
	 * @param defensief
	 * @param uithoudingsvermogen
	 */
	public Speler(String naam, int nummer, int prijs, Status status, Type type,
			int offensief, int defensief, int uithoudingsvermogen) {
		this.naam = naam;
		this.nummer = nummer;
		this.type = type;
		this.status = status;
		this.offensief = offensief;
		this.defensief = defensief;
		this.uithoudingsvermogen = uithoudingsvermogen;
		this.prijs = setPrijs();
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Speler) {
			Speler that = (Speler) other;

			return this.naam.equals(that.naam) 
					&& this.nummer == that.nummer
					&& this.status.equals(that.status)
					&& this.type.equals(that.type)
					&& this.offensief == that.offensief
					&& this.defensief == that.defensief
					&& this.uithoudingsvermogen == that.uithoudingsvermogen
					&& this.prijs == that.prijs;
			// this.team.equals(that.team);
		}
		return false;
	}

	public void wijzigTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("---------------------\n");
		res.append(naam);
		res.append('\n');
		res.append("---------------------\n");

		res.append("Nummer: ");
		res.append(nummer);
		res.append('\n');

		res.append("Prijs: ");
		res.append(prijs);
		res.append('\n');

		res.append("Status: ");
		res.append(status);
		res.append('\n');

		res.append("Type: ");
		res.append(type);
		res.append('\n');

		res.append("Offensief: ");
		res.append(offensief);
		res.append('\n');

		res.append("Defensief: ");
		res.append(defensief);
		res.append('\n');

		res.append("Uithoudingsvermogen: ");
		res.append(uithoudingsvermogen);
		res.append('\n');

		res.append("Team: ");
		res.append(team.getNaam());
		res.append('\n');

		return res.toString();
	}

/*	Deze methode wordt vooralsnog niet gebruikt,
 * 	wellicht later wel. Moet dan nog wel worden aangevuld
 *  met de andere statussen
 
	public void veranderStatus(Status z) {
		if (z.equals("Beschikbaar")) {
			this.status = Speler.Status.Beschikbaar;
		}
		if (z.equals("Gele kaart")) {
			this.status = Speler.Status.Gelekaart;
		}
	}
*/

	public int getRugNummer() {
		return nummer;
	}

	public int getPrijs() {
		return prijs;
	}

	public Status getStatus() {
		return status;
	}

	public Element getXMLElement(Document doc) {
		Element me = doc.createElement("speler");

		me.appendChild(XMLWriter.getElementContainingString("naam", naam, doc));
		me.appendChild(XMLWriter.getElementContainingInt("nummer", nummer, doc));
		me.appendChild(XMLWriter.getElementContainingInt("prijs", prijs, doc));
		me.appendChild(XMLWriter.getElementContainingString("status",
				status.toString(), doc));
		me.appendChild(XMLWriter.getElementContainingString("type",
				type.toString(), doc));
		me.appendChild(XMLWriter.getElementContainingInt("offensief",
				offensief, doc));
		me.appendChild(XMLWriter.getElementContainingInt("defensief",
				defensief, doc));

		// TODO Vervang door uithoudings. Dit is iets te lang.
		me.appendChild(XMLWriter.getElementContainingInt("uithoudingsvermogen",
				uithoudingsvermogen, doc));

		return me;
	}

	public static Speler laadXMLElement(Element el) {
		String naam = XMLLoader.getTaggedString("naam", el);
		int nummer = XMLLoader.getTaggedInt("nummer", el);
		int prijs = XMLLoader.getTaggedInt("prijs", el);
		Status status = Status.valueOf(XMLLoader.getTaggedString("status", el));
		Type type = Type.valueOf(XMLLoader.getTaggedString("type", el));
		int offensief = XMLLoader.getTaggedInt("offensief", el);
		int defensief = XMLLoader.getTaggedInt("defensief", el);
		int uithoudingsvermogen = XMLLoader.getTaggedInt("uithoudingsvermogen",
				el);

		// File debugging code.
		/*
		 * if(XMLLoader.flag == 1) System.out.println("Fout in speler: " + naam
		 * + ". Tag: " + XMLLoader.errtag); XMLLoader.flag = 0; XMLLoader.errtag
		 * = "";/*
		 */

		return new Speler(naam, nummer, prijs, status, type, offensief,
				defensief, uithoudingsvermogen);

	}

	public int getOffensief() {
		return offensief;
	}

	public int getDefensief() {
		return defensief;
	}

	public int getUithouding() {
		return uithoudingsvermogen;
	}

	public Team getTeam() {
		return team;
	}

	public double getSpelerWaarde() {
		double a = 0;
		switch (type) {
		case Aanvaller:
			a = offensief * 0.8 + defensief * 0.2 + uithoudingsvermogen * 1;
			break;
		case Doelman:
			a = offensief * 0.2 + defensief * 1.6 + uithoudingsvermogen * 0.2;
			break;
		case Middenvelder:
			a = offensief * 0.5 + defensief * 0.5 + uithoudingsvermogen * 1;
			break;
		case Verdediger:
			a = offensief * 0.2 + defensief * 0.8 + uithoudingsvermogen * 1;

			break;
		default:
			break;
		}
		return a;
	}
	
	public boolean transferAanbieden(ArrayList<Speler> spelers) {
		Speler.Type a = type;
		int totaalType =0;
		double totaalScore = 0;
		for(Speler inTeam: spelers){
			if(inTeam.type==a){
				totaalType++;
				totaalScore =totaalScore+inTeam.getSpelerWaarde();
			}
		}
		if(getSpelerWaarde()>totaalScore/totaalType)
			return true;
		return false;
	}
	
	
	public int setPrijs() {
		int a=(int) (getSpelerWaarde()*1500);
		return a;
	}

	public String getNaam() {
		return naam;
	}

	public Type getType() {
		return type;
	}
	
	public boolean overweegVerkopen(ArrayList<Speler> spelers) {
		Speler.Type a = type;
		int totaalType =0;
		double totaalScore = 0;
		for(Speler inTeam: spelers){
			if(inTeam.type==a){
				totaalType++;
				totaalScore =totaalScore+inTeam.getSpelerWaarde();
			}
		}
		if(checkMinimum(spelers)&&getSpelerWaarde()<totaalScore/totaalType)
			return true;
		return false;
	}
	
	public boolean checkMinimum(ArrayList<Speler> spelers){
		Speler.Type a = type;
		int min = 0;
		switch (a) {
		case Aanvaller:
			min = 5;
			break;
		case Doelman:
			min = 2;
			break;
		case Middenvelder:
			min = 5;
			break;
		case Verdediger:
			min = 5;
			break;
		default:
			break;
		}
		int i=0;
		for(Speler inTeam: spelers){
			if(inTeam.type==a){
				i++;
			}
		}
		if(i>min){
			return true;
		}
		return false;
	}
}