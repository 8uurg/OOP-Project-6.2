package voetbalmanager.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CompetitieTest {
	Team Ajax, Feyenoord, PSV, AZ;
	Competitie eredivisie;
	
	
	@Before
	public void voorElkeTest() {
		
		Ajax = new Team("Ajax", true);
		Feyenoord = new Team("Feyenoord", false);
		PSV = new Team("PSV", false);
		AZ = new Team("AZ", false);
		
		eredivisie = new Competitie("Eredivisie");
		eredivisie.addTeam(Ajax);
		eredivisie.addTeam(Feyenoord);
		eredivisie.addTeam(PSV);
		eredivisie.addTeam(AZ);
	}
	
	@Test
	public void testGetSpelerTeam() {
		assertEquals(eredivisie.getSpelerTeam(), Ajax);
		assertNotEquals(eredivisie.getSpelerTeam(), PSV);
	}
	
	@Test
	public void testEquals() {
		Competitie competitie2 = new Competitie("Eredivisie");
		competitie2.addTeam(Ajax);
		competitie2.addTeam(Feyenoord);
		competitie2.addTeam(PSV);
		assertNotEquals(competitie2,eredivisie);
		assertNotEquals(competitie2,("A"));
		competitie2.addTeam(AZ);
		assertEquals(competitie2,eredivisie);
	}
	
	
}
