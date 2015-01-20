package voetbalmanager.model;
import java.util.ArrayList;
import voetbalmanager.exceptions.TransferException;


public class TransferMarkt {
	
	private ArrayList<Transfer> recenteTransfers;
	private ArrayList<BeschikbareSpeler> verhandelbareSpelers;
	
	public TransferMarkt(){
		recenteTransfers = new ArrayList<Transfer>();
		verhandelbareSpelers = new ArrayList<BeschikbareSpeler>();
	}
	
	// Hierbij wordt gewoon de prijs van de speler zelf gebruikt (dus speler.prijs)
	public void Transfer(Team verkopendTeam, Team kopendTeam, Speler sp, int prijs) throws TransferException {
		sp.prijs=prijs;
		recenteTransfers.add(new Transfer(verkopendTeam, kopendTeam, sp));
	}
	
	public void maakVerhandelbaar(Speler sp) {
		BeschikbareSpeler bsp = new BeschikbareSpeler(sp,sp.getTeam());
		verhandelbareSpelers.add(bsp);
	}
	
	// Moet prijs bij ontslag op 0 worden gezet?
	public void Ontsla(Speler sp) throws TransferException {
		sp.team.verwijderVanSelectie(sp, 0);
		maakVerhandelbaar(sp);
	}
	
	public ArrayList<Transfer> getRecenteTransfers() {
		return recenteTransfers;
	}
	
	public ArrayList<BeschikbareSpeler> getVerhandelbareSpelers() {
		return verhandelbareSpelers;
	}
	public boolean getOudTeam(Team team){
		for(BeschikbareSpeler a:verhandelbareSpelers){
			if(a.getOudTeam().equals(team)){
				return true;
			}
		}
		return false; 
	}

	public double getMinWaarde() {
		double minSpelerwaarde = 5*10^6;
		for(BeschikbareSpeler speler:verhandelbareSpelers){
			if(speler.getSpeler().getSpelerWaarde()<minSpelerwaarde){
				minSpelerwaarde=speler.getSpeler().getSpelerWaarde();
			}
		}
		return 0;
	}

	public void koopSpeler(Team team,BeschikbareSpeler speler) {
		try {
			team.voegToe(speler.getSpeler(),(int) (speler.getSpeler().getPrijs()*1.1));
			
		} catch (TransferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verkocht(speler);
	}

	private void verkocht(BeschikbareSpeler speler){
		verhandelbareSpelers.remove(verhandelbareSpelers.indexOf(speler));		
	}
	
	public ArrayList<BeschikbareSpeler> interesse(ArrayList<Speler> spelers){
		ArrayList<BeschikbareSpeler> potentieel = new ArrayList<BeschikbareSpeler>();
		for(BeschikbareSpeler speler: verhandelbareSpelers){
			if(speler.moetKopen(spelers)){
				potentieel.add(speler);
			}
		}
		return potentieel;
	}
	
	

}
