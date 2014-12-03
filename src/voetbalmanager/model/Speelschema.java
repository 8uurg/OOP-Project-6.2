package voetbalmanager.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Speelschema {
	ArrayList<Team[]> wedstrijdlijst;
	ArrayList<Wedstrijd> wedstrijden;
	int aantalTeams;
	
	
	public Speelschema(ArrayList<Team[]> wedstrijdlijst,int aantalTeams){
		this.wedstrijdlijst = wedstrijdlijst;
		this.aantalTeams = aantalTeams;
	}
	
	public void maakWedstrijden(){
		int wedstrijdenPerWeek =aantalTeams-1;
		Collections.shuffle(wedstrijdlijst,new Random());
		for(int i=0;i<wedstrijdlijst.size();i++){
			wedstrijden.add(new Wedstrijd(wedstrijdlijst.get(i)[1],wedstrijdlijst.get(i)[2]));
		}
	}
}
