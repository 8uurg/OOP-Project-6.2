import java.util.ArrayList;


public final class Sorteer {
	private Sorteer() {
		
	}
	
	public static ArrayList<Speler> sorteerOpPrijsAflopend(ArrayList<Speler> spelers) {
		ArrayList<Speler> antwoord = new ArrayList<Speler>();
		ArrayList<Speler> kopie = new ArrayList<Speler>(spelers);
		int maxWaarde, indexGrootGetal;
		for (int i = 0; i < spelers.size(); i++) {
			maxWaarde = kopie.get(0).getPrijs();
			indexGrootGetal = 0;
			for (int b = 0; b < kopie.size(); b++) {
				if (kopie.get(b).getPrijs() > maxWaarde) {
					indexGrootGetal = b;
					maxWaarde = kopie.get(b).getPrijs();
				}
			}
			antwoord.add(kopie.get(indexGrootGetal));
			kopie.remove(indexGrootGetal);
		}
		
		return antwoord;
	}
	
}
