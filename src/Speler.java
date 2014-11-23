
public abstract class Speler {
	String Naam;
	int Nummer;
	int Prijs;
	Status status;

	
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
			
			return res;
		}
		return false;
	}
	
	public void veranderStatus(Status z){
		if(z.equals("Beschikbaar")){
			this.status=Speler.Status.Beschikbaar;
		}
		if(z.equals("Gele kaart")){
			this.status=Speler.Status.GeleKaart;
		}
	}
	
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