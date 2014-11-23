import org.w3c.dom.Element;


public class Speler {
	String	naam;
	int		nummer;
	int 	prijs;
	int		offensief;
	int		defensief;
	int		uithoudingsvermogen;
	Status	status;
	Type	type;
	
	public enum Status {
		Beschikbaar, Blessure, Gelekaart, gelekaarttwee, Rodekaart;
	}
	
	public enum Type {
		Aanvaller, Middenvelder, Verdediger, Doelman;
	}
	
	public Speler(String naam,int nummer,int prijs, Status status, Type type, int offensief, 
			int defensief, int uithoudingsvermogen){
		this.naam 	= naam;
		this.nummer	= nummer;
		this.prijs	= prijs;
		this.status	= status;
		this.type = type;
		this.offensief = offensief;
		this.defensief = defensief;
		this.uithoudingsvermogen = uithoudingsvermogen;
	}
	 
	public boolean equals(Object other){
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
		}
		return false;
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
