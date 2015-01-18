package voetbalmanager.controller.wedstrijdsimulatie;

public class SomKrachten {
	private int aanval;
	private int verdediging;
	private int uithouding;
	
	public SomKrachten(int aanval, int verdediging, int uithouding) {
		this.aanval = aanval;
		this.verdediging = verdediging;
		this.uithouding = uithouding;
	}
	
	public int getAanval() {
		return aanval;
	}
	
	public int getVerdediging() {
		return verdediging;
	}
	
	public int getUithouding() {
		return uithouding;
	}
	
	public boolean equals(Object other) {
		if (other instanceof SomKrachten) {
			SomKrachten that = (SomKrachten)other;
			if (this.aanval == that.aanval && this.verdediging == that.verdediging && this.uithouding == that.uithouding)
				return true;
		}
		return false;
	}
}
