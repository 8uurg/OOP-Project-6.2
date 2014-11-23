import org.w3c.dom.Element;

/**
 * Een klassie die een speler representeert.
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
	
<<<<<<< HEAD
	public Speler(String naam, int nummer, int prijs, Status stat){
		Naam = naam;
		Nummer = nummer;
		Prijs = prijs;
		status = stat;
	}
	
	public enum Status {
		Beschikbaar, Blessure, GeleKaart, GelekaartTwee, RodeKaart;
	}
	
	public boolean equals(Object S){
		if (S instanceof Speler) {
			Speler that = (Speler)S;
			Boolean res = this.Naam.equals(that.Naam) && this.Nummer==that.Nummer && this.Prijs==that.Prijs && this.status.equals(that.status);
=======
	public enum Status {
		Beschikbaar, Blessure, Gelekaart, GelekaartTwee, Rodekaart;
	}
	
	public enum Type {
		Aanvaller, Middenvelder, Verdediger, Doelman;
	}
	
	/**
	 * Creeër een speler.
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
			int defensief, int uithoudingsvermogen){
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
	public boolean equals(Object other){
		if(other instanceof Speler){
			Speler that = (Speler)other;
>>>>>>> origin/master
			
			return	this.naam.equals(that.naam) &&
					this.nummer == that.nummer &&
					this.prijs == that.prijs &&
					this.status.equals(that.status) &&
					this.type.equals(that.type) &&
					this.offensief == that.offensief &&
					this.defensief == that.defensief &&
					this.uithoudingsvermogen == that.uithoudingsvermogen;
		}
		return false;
	}
	
<<<<<<< HEAD
=======
	@Override
	public String toString()
	{
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
		
		return res.toString();
	}
	
	// Huh?
>>>>>>> origin/master
	public void veranderStatus(Status z){
		if(z.equals("Beschikbaar")){
			this.status=Speler.Status.Beschikbaar;
		}
		if(z.equals("Gele kaart")){
			this.status=Speler.Status.GeleKaart;
		}
	}
	
<<<<<<< HEAD
	public String toString() {
		return Naam;
	}
	
	public int getRugNummer() {
		return Nummer;
	}
	
	public int getPrijs() {
		return Prijs;
	}
	
	public Status getStatus() {
		return status;
	}
}
=======
	/**
	 * Creeër een speler door een XML Element in te laden.
	 * @param el
	 * @return De gecreeërde speler.
	 */
	public static Speler laadXMLElement(Element el)
	{
		String	naam				= XMLLoader.getTaggedString("naam", el);
		int		nummer				= XMLLoader.getTaggedInt("nummer", el);
		int		prijs				= XMLLoader.getTaggedInt("prijs", el);
		Status	status				= Status.valueOf(XMLLoader.getTaggedString("status", el));
		Type	type				= Type.valueOf(XMLLoader.getTaggedString("type", el));
		int		offensief			= XMLLoader.getTaggedInt("offensief", el);
		int		defensief			= XMLLoader.getTaggedInt("defensief", el);
		int		uithoudingsvermogen = XMLLoader.getTaggedInt("uithoudingsvermogen", el);
		
		return new Speler(naam, nummer, prijs, status, type, offensief, defensief, uithoudingsvermogen);
		
	}
}
>>>>>>> origin/master
