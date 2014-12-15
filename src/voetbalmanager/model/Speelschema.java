package voetbalmanager.model;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Iterator;
import java.util.Random;

public class Speelschema {
	ArrayList<Team[]> wedstrijdlijst;
	ArrayList<Wedstrijd> wedstrijden;
	int aantalTeams;

	/**
	 * Creëert Speelschema voor de competitie
	 * @param wedstrijdlijst Lijst met teams die ingedeeld worden in wedstrijden
	 * @param aantalTeams Het aantal teams dat meedoet in de competitie
	 */
	public Speelschema(ArrayList<Team[]> wedstrijdlijst, int aantalTeams) {
		this.wedstrijdlijst = wedstrijdlijst;
		this.aantalTeams = aantalTeams;
		this.wedstrijden = new ArrayList<Wedstrijd>();
	}
	
	/**
	 * Creëert uit verschillende teams wedstrijden.
	 */
	public void maakWedstrijden() {
		for (int i = 0; i < wedstrijdlijst.size(); i++) {
			wedstrijden.add(new Wedstrijd(wedstrijdlijst.get(i)[0],
					wedstrijdlijst.get(i)[1]));
		}
	}
	/**
	 * Maakt verschillende speelronden voor de competitie
	 * @return ArrayList<Speelronde> Iedere speelronde in de ArrayList is een speelronde die gespeeld wordt.
	 */
	public ArrayList<Speelronde> maakRonden() {
		maakWedstrijden();
		ArrayList<Speelronde> a = new ArrayList<Speelronde>();
		ArrayList<Wedstrijd> b = wedstrijden;
		Collections.shuffle(wedstrijdlijst,new Random());
		for (int i = 0; i < aantalTeams * 2 - 2; i++) {
			Speelronde ronde = new Speelronde();
			a.add(ronde);
		}
		int y = 0;
		int z;
		for (z = 0; z < a.size(); z++) {
			a.get(z).voegToe(b.get(z));
		}
		for (z = a.size(); z < b.size(); z++) {
			while (a.get(y).getSize() < aantalTeams / 2 ) {
				if (!a.get(y).WedstrijdEquals(b.get(z))) { 
					a.get(y).voegToe(b.get(z));
				} else {
					a.get(y+1).voegToe(b.get(z));
				}z++;
			}z--;
			y++;
		}
		Collections.shuffle(a,new Random());
		return a;
	}
}
