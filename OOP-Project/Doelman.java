
public class Doelman extends Speler{
	
	
	public Doelman(String naam, int nummer, int prijs, Status stat) {
		super(naam, nummer, prijs, stat);
		// TODO Auto-generated constructor stub
	}
	public boolean equals(Object DM){
		if(DM instanceof Doelman){
			Boolean res = super.equals(DM);
			
			return res;
		}
		return false;
	}
}
