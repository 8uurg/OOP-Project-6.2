package voetbalmanager.model;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Iterator;
import java.util.Random;

public class Speelschema {
	ArrayList<Team[]> wedstrijdlijst;
	ArrayList<Wedstrijd> wedstrijden;
	int aantalTeams;
	
	
	public Speelschema(ArrayList<Team[]> wedstrijdlijst,int aantalTeams){
		this.wedstrijdlijst = wedstrijdlijst;
		this.aantalTeams = aantalTeams;
		this.wedstrijden = new ArrayList<Wedstrijd>();
	}
	
	public void maakWedstrijden(){
		Collections.shuffle(wedstrijdlijst,new Random());
		for(int i=0;i<wedstrijdlijst.size();i++){
			wedstrijden.add(new Wedstrijd(wedstrijdlijst.get(i)[0],wedstrijdlijst.get(i)[1]));
		}
	}
	public ArrayList<Speelronde> maakRonden(){
		this.maakWedstrijden();
		ArrayList<Speelronde> a = new ArrayList<Speelronde>();
		ArrayList<Wedstrijd> b = wedstrijden;
		for(int i=0;i<aantalTeams*2-2;i++){
			Speelronde ronde = new Speelronde(i+1);
			for(int j=0;j<aantalTeams/2;j++){
				int r = 0;
				while(RondeContains(b,ronde,r)){
					r++;
				}
				ronde.voegToe(b.get(r));
				b.remove(r); //Hier gaat iets mis, waarschijnlijk mag dit niet?
				r++;
			}
			a.add(ronde);
		}
		return a;
		
	}
	
	public static boolean RondeContains(ArrayList<Wedstrijd> a,Speelronde ronde,int i){
			for(int j=0;j<ronde.getWedstrijden().size();j++){
				if(ronde.getWedstrijden().get(j).teamEquals(a.get(i)))
					return true;
			}
		return false;
	}
/*	public void callRemove(Wedstrijd a){
	//	wedstrijden.remove(i);
		Iterator<Wedstrijd> it = wedstrijden.iterator();
	    while (it.hasNext()) {
	        if (it.next().getTeams().equals(a)) {
	            it.remove();
	            break;
	        }
	    }
	}*/
}
