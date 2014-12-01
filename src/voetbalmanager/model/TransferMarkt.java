package voetbalmanager.model;
import java.util.ArrayList;
import voetbalmanager.exceptions.TransferException;


public class TransferMarkt {
	
	private ArrayList<Transfer> recenteTransfers;
	private ArrayList<Speler> verhandelbareSpelers;
	
	public TransferMarkt(){
		recenteTransfers = new ArrayList<Transfer>();
		verhandelbareSpelers = new ArrayList<Speler>();
	}
	
	// Hierbij wordt gewoon de prijs van de speler zelf gebruikt (dus speler.prijs)
	public void Transfer(Team verkopendTeam, Team kopendTeam, Speler sp) throws TransferException {
		recenteTransfers.add(new Transfer(verkopendTeam, kopendTeam, sp));
	}
	
	public void maakVerhandelbaar(Speler sp) {
		verhandelbareSpelers.add(sp);
	}
	
	// Moet prijs bij ontslag op 0 worden gezet?
	public void Ontsla(Speler sp) throws TransferException {
		sp.team.verwijderVanSelectie(sp, 0);
		maakVerhandelbaar(sp);
	}
	
	public ArrayList<Transfer> getRecenteTransfers() {
		return recenteTransfers;
	}
	
	public ArrayList<Speler> getVerhandelbareSpelers() {
		return verhandelbareSpelers;
	}

}
