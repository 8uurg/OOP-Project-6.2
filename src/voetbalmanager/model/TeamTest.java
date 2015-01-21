package voetbalmanager.model;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import voetbalmanager.exceptions.OpstellingException;
import voetbalmanager.exceptions.TransferException;

public class TeamTest {
	Speler A, B, C, D;
	Team Ajax, Feyenoord;
	
	
	@Before
	public void voorElkeTest() throws TransferException {
		A = new Speler("SpelerA",1,10,Speler.Status.Beschikbaar,Speler.Type.Aanvaller,1,2,3);
		B = new Speler("SpelerB",2,10,Speler.Status.Beschikbaar,Speler.Type.Verdediger,4,5,6);
		C = new Speler("SpelerC",3,10,Speler.Status.Beschikbaar,Speler.Type.Doelman,7,8,9);
		D = new Speler("SpelerD",4,10,Speler.Status.Beschikbaar,Speler.Type.Middenvelder,10,11,12);
		
		Ajax = new Team("Ajax", false);
		Feyenoord = new Team("Feyenoord", false);
		
		Ajax.overrideAdd(A);
		Feyenoord.overrideAdd(B);
		Ajax.voegToe(C, 0);
		Feyenoord.voegToe(D, 0);
	}
	
	@Test
	public void testToString() {
		String s = "";
		s += A.toString();
		s += "\n";
		s += C.toString();
		s += "\n";
		assertEquals(Ajax.toString(), s);
	}
	
	@Test
	public void testTeam() {
		
		Team Ajax2 = new Team("Ajax", false);
		assertNotEquals(Ajax2, Ajax);
		assertNotEquals(Ajax, Feyenoord);
		assertNotEquals(Ajax2, "A");
		Ajax2.overrideAdd(A);
		Ajax2.overrideAdd(C);
		assertEquals(Ajax2, Ajax);
	}
	
	@Test
	public void testGetSpelerNamen() {
		assertEquals(Ajax.getSpelerNamen(), "SpelerA\nSpelerC\n");
	}
	
	@Test
	public void testBudget() throws TransferException {
		try {
			Ajax.verlaagBudget(10);
			fail("Moet exception geven!");
		} catch (TransferException e) {
			assertTrue(e.getMessage().equals("Er is nog geen budget geïnitialiseerd voor dit team!"));
		}
		try {
			Ajax.verhoogBudget(10);
			fail("Moet exception geven!");
		} catch (TransferException e) {
			assertTrue(e.getMessage().equals("Er is nog geen budget geïnitialiseerd voor dit team!"));
		}
		Ajax.maakBudget(100);
		assertEquals(Ajax.getBudget(), 100);
		assertNotEquals(Ajax.getBudget(), -1);
		Ajax.verhoogBudget(100);
		assertEquals(Ajax.getBudget(), 200);
		try {
			Ajax.verlaagBudget(300);
			fail("Moet exception geven ivm te laag budget!");
		} catch (TransferException e) {
			assertTrue(e.getMessage().equals("Er is niet voldoende budget om deze transactie te maken!"));
		}
		Ajax.verlaagBudget(100);
		Ajax.verhoogBudget(0);
		assertEquals(Ajax.getBudget(), 100);
	}
	
	@Test
	public void testVerwijderVanSelectie() throws TransferException {
		Ajax.maakBudget(50);
		Ajax.verwijderVanSelectie(A, 500);
		String s = "";
		s += C.toString();
		s += "\n";
		assertEquals(Ajax.toString(), s);
	}
	
	@Test
	public void testVoegToe() throws TransferException {
		Ajax.maakBudget(200);
		Ajax.voegToe(B, 100);
		String s = A.toString();
		s += "\n";
		s += C.toString();
		s += "\n";
		s += B.toString();
		s += "\n";
		assertEquals(Ajax.toString(), s);
	}
	
	@Test
	public void testOverrideAdd() throws OpstellingException {
		Team PSV = new Team("PSV", false);
		PSV.overrideAdd(A, true);
		PSV.overrideAdd(B, true);
		PSV.overrideAdd(C, true);
		PSV.overrideAdd(D, true);
		
		Opstelling opstelling = new Opstelling();
		opstelling.voegToeAanvaller(A);
		opstelling.voegToeVerdediger(B);
		opstelling.voegToeDoelman(C);
		opstelling.voegToeMiddenvelder(D);
		
		assertEquals(PSV.getOpstelling(), opstelling);
	}
	
	

}
