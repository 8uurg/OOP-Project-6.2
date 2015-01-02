package voetbalmanager.controller.wedstrijdsimulatie;

import voetbalmanager.model.Team;

public class WedstrijdSimulator {
	
	private WedstrijdTeam a;
	private WedstrijdTeam b;
	
	public WedstrijdSimulator(Team a, Team b)
	{
		this.a = new WedstrijdTeam(a);
		this.b = new WedstrijdTeam(b);
	}
	
	public Resultaat simuleer() {
		simuleerHelft();
		wisselkant();
		simuleerHelft();
		return new Resultaat(a.getScore(), b.getScore());
	}
	
	private void simuleerHelft() {
		// TODO Simulatiecode hier;
		// Team a is het team dat begint.
	}
	
	private void wisselkant() {
		WedstrijdTeam temp = b;
		b = a;
		a = temp;
	}
	
}
