package voetbalmanager.model;
import static org.junit.Assert.*;
import org.junit.Test;

import voetbalmanager.controller.wedstrijdsimulatie.SomKrachten;
import voetbalmanager.exceptions.OpstellingException;

public class OpstellingTest {
	
	@Test 
	public void testSetTypeOpstelling() {
		Opstelling opstelling = new Opstelling();
		opstelling.setTypeOpstelling(4, 3, 3);
		assertEquals(opstelling.getOpstelling(), "3 - 3 - 4");
	}
	
	@Test
	public void testOpstelling() throws OpstellingException {
		Opstelling opstelling = new Opstelling();
		
		Speler A1 = new Speler("Naam",5,1,Speler.Status.Beschikbaar,Speler.Type.Aanvaller,5,5,3);
		Speler A2 = new Speler("Naam2",5,1,Speler.Status.Beschikbaar,Speler.Type.Aanvaller,5,5,3);
		Speler A3 = new Speler("Naam3",5,1,Speler.Status.Beschikbaar,Speler.Type.Aanvaller,5,5,3);
		Speler M1 = new Speler("Naam4",5,1,Speler.Status.Beschikbaar,Speler.Type.Middenvelder,5,5,3);
		Speler M2 = new Speler("Naam5",5,1,Speler.Status.Beschikbaar,Speler.Type.Middenvelder,5,5,3);
		Speler M3 = new Speler("Naam6",5,1,Speler.Status.Beschikbaar,Speler.Type.Middenvelder,5,5,3);
		Speler V1 = new Speler("Naam7",5,1,Speler.Status.Beschikbaar,Speler.Type.Verdediger,5,5,3);
		Speler V2 = new Speler("Naam8",5,1,Speler.Status.Beschikbaar,Speler.Type.Verdediger,5,5,3);
		Speler V3 = new Speler("Naam9",5,1,Speler.Status.Beschikbaar,Speler.Type.Verdediger,5,5,3);
		Speler V4 = new Speler("Naam10",5,1,Speler.Status.Beschikbaar,Speler.Type.Verdediger,5,5,3);
		Speler D1 = new Speler("Naam11",5,1,Speler.Status.Beschikbaar,Speler.Type.Doelman,5,5,3);
		
		opstelling.kanWordenOpgesteld(A1);
		opstelling.voegToeAanvaller(A1);
		opstelling.voegToeAanvaller(A2);
		opstelling.voegToeAanvaller(A3);
		opstelling.voegToeMiddenvelder(M1);
		opstelling.voegToeMiddenvelder(M2);
		opstelling.voegToeMiddenvelder(M3);
		opstelling.voegToeVerdediger(V1);
		opstelling.voegToeVerdediger(V2);
		opstelling.voegToeVerdediger(V3);
		opstelling.voegToeVerdediger(V4);
		opstelling.voegToeDoelman(D1);
		assertEquals(opstelling.geldigeOpstelling(), true);
		opstelling.verwijderSpeler(A1);
		opstelling.verwijderSpeler(V1);
		opstelling.verwijderSpeler(M1);
		opstelling.verwijderSpeler(D1);
		assertEquals(opstelling.geldigeOpstelling(), false);
	}
	
	@Test
	public void testSomKrachten() throws OpstellingException {
		Opstelling opstelling = new Opstelling();
		Speler A1 = new Speler("Naam",5,1,Speler.Status.Beschikbaar,Speler.Type.Aanvaller,5,5,3);
		Speler A2 = new Speler("Naam2",5,1,Speler.Status.Beschikbaar,Speler.Type.Aanvaller,5,5,3);
		SomKrachten som = new SomKrachten(10, 10, 6);
		SomKrachten som2 = new SomKrachten(0, 0, 0);
		opstelling.voegToeAanvaller(A1);
		assertEquals(opstelling.getMiddenveldersKrachten(), som2);
		assertNotEquals(opstelling.getVerdedigersKrachten(), som);
		assertEquals(opstelling.getKeeperKrachten(), som2);
		assertNotEquals(opstelling.getAanvallersKrachten(), som);
		opstelling.voegToeAanvaller(A2);
		assertEquals(opstelling.getAanvallersKrachten(), som);
	}
	
	@Test 
	public void testEquals() throws OpstellingException {
		Opstelling opstelling1 = new Opstelling();
		Opstelling opstelling2 = new Opstelling();
		
		assertTrue(opstelling1.equals(opstelling2));
		Speler A1 = new Speler("Naam",5,1,Speler.Status.Beschikbaar,Speler.Type.Aanvaller,5,5,3);
		opstelling1.voegToeAanvaller(A1);
		assertFalse(opstelling1.equals(opstelling2));
		assertFalse(opstelling1.equals("A"));
	}	
}
