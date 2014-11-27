import java.util.ArrayList;

public class Speelronde {
	
	int ronde;	
	ArrayList<Wedstrijd> wedstrijden;
	
	public Speelronde(int ronde) {
		this.ronde = ronde;
		wedstrijden = new ArrayList<Wedstrijd>();
	}
	
	public void voegToe(Wedstrijd wedstrijd) {
		wedstrijden.add(wedstrijd);
	}
	
	public String toString() {
		String s = "";
		s += "Speelronde " + ronde + ":\n";
		for (int i = 0; i < wedstrijden.size(); i++)
			s += wedstrijden.get(i).toString() + "\n";
		return s;
	}
}
