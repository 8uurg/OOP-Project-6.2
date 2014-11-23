
public class Doelman extends Speler {
	
	
	public Doelman(String naam, int nummer, int prijs, Status stat) {
		super(naam, nummer, prijs, stat);
	}
	public boolean equals(Object DM) {
		if (DM instanceof Doelman)
			return super.equals(DM);
		
		return false;
	}
}