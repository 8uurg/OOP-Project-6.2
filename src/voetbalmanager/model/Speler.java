package voetbalmanager.model;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import voetbalmanager.XMLLoader;
import voetbalmanager.XMLWriter;

/**
 * Een klasse die een speler representeerdt
 */
public class Speler {
	String	naam;
	int		nummer;
	int 	prijs;
	int		offensief;
	int		defensief;
	int		uithoudingsvermogen;
	Status	status;
	Type	type;
	Team	team = new Team("Ongedefinieerd");
	
	public enum Status {
		Beschikbaar, Blessure, Gelekaart, Rodekaart;
	}
	
	public enum Type {
		Aanvaller, Middenvelder, Verdediger, Doelman;
	}
	
	/**
	 * Cree�r een speler.
	 * @param naam
	 * @param nummer
	 * @param prijs
	 * @param status
	 * @param type
	 * @param offensief
	 * @param defensief
	 * @param uithoudingsvermogen
	 */
	public Speler(String naam,int nummer,int prijs, Status status, Type type, int offensief, 
			int defensief, int uithoudingsvermogen) {
		this.naam 					= naam;
		this.nummer					= nummer;
		this.prijs					= prijs;
		this.status					= status;
		this.type					= type;
		this.offensief				= offensief;
		this.defensief				= defensief;
		this.uithoudingsvermogen	= uithoudingsvermogen;
	}
	 
	@Override
	public boolean equals(Object other) {
		if(other instanceof Speler){
			Speler that = (Speler)other;
			
			return	this.naam.equals(that.naam) &&
					this.nummer == that.nummer &&
					this.prijs == that.prijs &&
					this.status.equals(that.status) &&
					this.type.equals(that.type) &&
					this.offensief == that.offensief &&
					this.defensief == that.defensief &&
					this.uithoudingsvermogen == that.uithoudingsvermogen;
					//this.team.equals(that.team);
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
	
	// Huh?
	public void veranderStatus(Status z){
		if(z.equals("Beschikbaar")){
			this.status=Speler.Status.Beschikbaar;
		}
		if(z.equals("Gele kaart")){
			this.status=Speler.Status.Gelekaart;
		}
	}
	
	public int getRugNummer() {
		return nummer;
	}
	
	public int getPrijs() {
		return prijs;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public Element getXMLElement(Document doc)
	{
		Element me = doc.createElement("speler");
		
		me.appendChild(XMLWriter.getElementContainingString("naam", naam, doc));
		me.appendChild(XMLWriter.getElementContainingInt("nummer", nummer, doc));
		me.appendChild(XMLWriter.getElementContainingInt("prijs", prijs, doc));
		me.appendChild(XMLWriter.getElementContainingString("status", status.toString(), doc));
		me.appendChild(XMLWriter.getElementContainingString("type", type.toString(), doc));
		me.appendChild(XMLWriter.getElementContainingInt("offensief", offensief, doc));
		me.appendChild(XMLWriter.getElementContainingInt("defensief", defensief, doc));
		
		//TODO Vervang door uithoudings. Dit is iets te lang.
		me.appendChild(XMLWriter.getElementContainingInt("uithoudingsvermogen", uithoudingsvermogen, doc));
		
		return me;
	}
	
	public static Speler laadXMLElement(Element el)
	{
		String	naam		= XMLLoader.getTaggedString("naam", el);
		int		nummer		= XMLLoader.getTaggedInt("nummer", el);
		int		prijs		= XMLLoader.getTaggedInt("prijs", el);
		Status	status		= Status.valueOf(XMLLoader.getTaggedString("status", el));
		Type	type		= Type.valueOf(XMLLoader.getTaggedString("type", el));
		int		offensief	= XMLLoader.getTaggedInt("offensief", el);
		int		defensief	= XMLLoader.getTaggedInt("defensief", el);
		int		uithoudingsvermogen = XMLLoader.getTaggedInt("uithoudingsvermogen", el);
		
		return new Speler(naam, nummer, prijs, status, type, offensief, defensief, uithoudingsvermogen);
		
	}
}