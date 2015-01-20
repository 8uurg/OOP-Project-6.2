package voetbalmanager.model;
import voetbalmanager.exceptions.TransferException;

/**
 * Methode voor transfers tussen teams
 * @author Jeroen
 *
 */
public class Transfer {
	
	Team verkopendTeam, kopendTeam;
	Speler speler;
	int transferbedrag;
	
	/**
	 * 
	 * @param verkopendTeam Team dat de speler verkoopt
	 * @param kopendTeam Team dat de speler aankoopt
	 * @param sp De speler die transferred wordt.
	 * @throws TransferException
	 */
	public Transfer(Team verkopendTeam, Team kopendTeam, Speler sp) throws TransferException {
		this.verkopendTeam = verkopendTeam;
		this.kopendTeam = kopendTeam;
		this.speler = sp;
		transferbedrag = sp.prijs;
		verkopendTeam.verwijderVanSelectie(sp, sp.prijs);
		try {
			kopendTeam.voegToe(sp, sp.prijs);
		} catch (TransferException e) {
			verkopendTeam.voegToe(sp, sp.prijs);
			throw e;
		}
	}
	/**
	 * String-representatie van de transfer
	 */
	public String toString() {
		String s = "";
		s += "Speler: " + speler.toString();
		s += "\nVerkoper: " + verkopendTeam.toString();
		s += "\nKoper: " + kopendTeam.toString();
		s += "\nTransferbedrag: " + transferbedrag;
		return s;
	}
	/**
	 * Equals-methode
	 */
	public boolean equals(Object other) {
		if (other instanceof Transfer) {
			Transfer that = (Transfer)other;
			if (this.verkopendTeam.equals(that.verkopendTeam) && this.kopendTeam.equals(that.kopendTeam)
				&& this.speler.equals(that.speler) && this.transferbedrag == that.transferbedrag)
				return true;
		}
		return false;
	}
}
