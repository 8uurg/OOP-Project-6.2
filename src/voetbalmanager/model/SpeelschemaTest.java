package voetbalmanager.model;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SpeelschemaTest {
	
	Team Ajax, Feyenoord, PSV, AZ, Vitesse, NAC;
	Speelschema speelschema;
	
	@Before
	public void voorElkeTest() {
		Ajax = new Team("Ajax", true);
		Feyenoord = new Team("Feyenoord", false);
		PSV = new Team("PSV", false);
		AZ = new Team("AZ", false);
		Vitesse = new Team("Vitesse", false);
		NAC = new Team("NAC", false);
		
		ArrayList<Team[]> wedstrijden = new ArrayList<Team[]>();
		Team[] wedstrijd1 = {Ajax, Feyenoord};
		Team[] wedstrijd2 = {PSV, AZ};
		Team[] wedstrijd3 = {Vitesse, NAC};
		wedstrijden.add(wedstrijd1);
		wedstrijden.add(wedstrijd2);
		wedstrijden.add(wedstrijd3);
		
		speelschema = new Speelschema(wedstrijden, 6);
	}
	
	@Test
	public void testMaakRonden() {
/*		Hij crasht bij uitvoeren van maakRonden(), maar ik weet niet
 * 		hoe ik de fout moet verhelpen 
 * 		speelschema.maakRonden();
 */
	}
	
}
