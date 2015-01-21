package voetbalmanager.model;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Iterator;
import java.util.Random;

public class Speelschema {
	ArrayList<Speelronde> schema;
	ArrayList<Team[]> wedstrijdlijst;
	ArrayList<Wedstrijd> wedstrijden;
	int aantalTeams;

	/**
	 * Creëert Speelschema voor de competitie
	 * 
	 * @param wedstrijdlijst
	 *            Lijst met teams die ingedeeld worden in wedstrijden
	 * @param aantalTeams
	 *            Het aantal teams dat meedoet in de competitie
	 */
	public Speelschema(ArrayList<Team[]> wedstrijdlijst, int aantalTeams) {
		this.wedstrijdlijst = wedstrijdlijst;
		this.aantalTeams = aantalTeams;
		this.wedstrijden = new ArrayList<Wedstrijd>();
		this.schema = new ArrayList<Speelronde>();
	}
	public ArrayList<Speelronde> getSchema(){
		return schema;
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
	 * 
	 * @return ArrayList<Speelronde> Iedere speelronde in de ArrayList is een
	 *         speelronde die gespeeld wordt.
	 */
	public ArrayList<Speelronde> maakRonden() {
		maakWedstrijden();
//		System.out.println(wedstrijden.size());
		while(wedstrijden.size()>aantalTeams){
			recursieRonden();
		}
		laatsteRonden();
		Collections.shuffle(schema, new Random());
		return schema;
		}
	
	/**
	 * Bouwt het grootste deel van de ronden op
	 */
	public void recursieRonden(){
		ArrayList<Speelronde> a = new ArrayList<Speelronde>();
		Collections.shuffle(wedstrijden, new Random());
		for (int i = 0; i < aantalTeams * 3; i++) {
			Speelronde ronde = new Speelronde();
			a.add(ronde);
		}
		int z;
		for (z = 0; z < aantalTeams - 2; z++) {
			a.get(z).voegToe(wedstrijden.get(z));
		}

		for (z = aantalTeams - 2; z < wedstrijden.size(); z++) {
			int j = 0;
			if (!a.get(j).WedstrijdEquals(wedstrijden.get(z))) {
				a.get(j).voegToe(wedstrijden.get(z));
			} else {
				while (a.get(j).WedstrijdEquals(wedstrijden.get(z))) {
					j++;
				}
				if(!a.get(j).WedstrijdEquals(wedstrijden.get(z))){
				a.get(j).voegToe(wedstrijden.get(z));
				}
			}
		}
		ArrayList<Wedstrijd> mismatch = new ArrayList<Wedstrijd>();
		ArrayList<Speelronde> temp = new ArrayList<Speelronde>();
		for (int o = 0; o < a.size(); o++) {
			if (a.get(o).getSize() < aantalTeams/2) {
				mismatch.addAll(a.get(o).getWedstrijden());
			} else {
				temp.add(a.get(o));
			}
		}
		wedstrijden = mismatch;
		schema.addAll(temp);
	}
	
	/**
	 * Is gefocust op de laatste 2 ronden die nogal vervelend kunnen doen.
	 */
	public void laatsteRonden(){
		Speelronde a = new Speelronde();
		Speelronde b = new Speelronde();
		
		a.voegToe(wedstrijden.get(0));
		wedstrijden.remove(0);
		for(Wedstrijd wedstrijd:wedstrijden){
			if(!b.WedstrijdEquals(wedstrijd)){
				if(a.teamEquals(wedstrijd.getTeams()[0])&&a.teamEquals(wedstrijd.getTeams()[1])){
					b.voegToe(wedstrijd);
					wedstrijden.remove(wedstrijden.indexOf(wedstrijd));
					break;
				}
				else if(!a.teamEquals(wedstrijd.getTeams()[0])&&a.teamEquals(wedstrijd.getTeams()[1])){
					b.voegToe(wedstrijd);
					wedstrijden.remove(wedstrijden.indexOf(wedstrijd));
					break;
				}
				else if(!a.teamEquals(wedstrijd.getTeams()[1])&&a.teamEquals(wedstrijd.getTeams()[0])){
					b.voegToe(wedstrijd);
					wedstrijden.remove(wedstrijden.indexOf(wedstrijd));
					break;
				}
			}
		}
		int i=0;
		while(!a.containsAll(b)&i<1000){
			for(Wedstrijd wedstrijd:wedstrijden){
				if(!a.WedstrijdEquals(wedstrijd)){
					if(b.teamEquals(wedstrijd.getTeams()[0])&&b.teamEquals(wedstrijd.getTeams()[1])){
						a.voegToe(wedstrijd);
						wedstrijden.remove(wedstrijden.indexOf(wedstrijd));
						break;
					}
				else if(!b.teamEquals(wedstrijd.getTeams()[0])&&b.teamEquals(wedstrijd.getTeams()[1])){
					a.voegToe(wedstrijd);
					wedstrijden.remove(wedstrijden.indexOf(wedstrijd));
					break;
					}
				else if(!b.teamEquals(wedstrijd.getTeams()[1])&&b.teamEquals(wedstrijd.getTeams()[0])){
					a.voegToe(wedstrijd);
					wedstrijden.remove(wedstrijden.indexOf(wedstrijd));
					break;
					}
				}
			}
			for(Wedstrijd wedstrijd:wedstrijden){
				if(!b.WedstrijdEquals(wedstrijd)){
					if(a.teamEquals(wedstrijd.getTeams()[0])&&a.teamEquals(wedstrijd.getTeams()[1])){
						b.voegToe(wedstrijd);
						wedstrijden.remove(wedstrijden.indexOf(wedstrijd));
						break;
				}
					else if(!a.teamEquals(wedstrijd.getTeams()[0])&&a.teamEquals(wedstrijd.getTeams()[1])){
						b.voegToe(wedstrijd);
						wedstrijden.remove(wedstrijden.indexOf(wedstrijd));
						break;
					}
					else if(!a.teamEquals(wedstrijd.getTeams()[1])&&a.teamEquals(wedstrijd.getTeams()[0])){
						b.voegToe(wedstrijd);
						wedstrijden.remove(wedstrijden.indexOf(wedstrijd));
						break;
					}
				}
			}
			i++;
			if(i>aantalTeams){
				i=1000;
			}
		}
		if(a.getSize()==aantalTeams/2&&b.getSize()==aantalTeams/2){
			schema.add(a);
			schema.add(b);
		}
		else{
			while(a.getSize()>0){
				wedstrijden.add(a.get(0));
				a.verwijder(0);
			}
			while(b.getSize()>0){
				wedstrijden.add(b.get(0));
				b.verwijder(0);
			}
			while(schema.size()>0){
				while(schema.get(0).getSize()>0){
					wedstrijden.add(schema.get(0).get(0));
					schema.get(0).verwijder(0);
				}
				schema.remove(0);
			}
			while(wedstrijden.size()>aantalTeams){
				recursieRonden();
			}
			laatsteRonden();
		}
	}
	
	/**
	 * Stringmethode, let op! Op dit moment wordt alleen het schema weergeven.
	 */
	public String toString(){
		String res = schema.get(0).toString();
		for(int i=1;i<schema.size();i++){
			res = res + schema.get(i).toString();	
		}
		return res;
	}
}