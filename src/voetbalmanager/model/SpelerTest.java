package voetbalmanager.model;
import static org.junit.Assert.*;

import org.junit.Test;


public class SpelerTest {

	@Test
	public void testSpeler() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Aanvaller;
		Speler C = new Speler("Naam",5,1,B,A,5,5,3);
		Speler D = new Speler("Naam",5,1,B,A,5,5,3);
		Speler E = new Speler("Naam",5,2,B,A,5,5,3);
		assertTrue(C.equals(D));
		assertFalse(D.equals(E));
		assertTrue(C.team.equals(D.team));
		
	}

	@Test
	public void testWijzigTeam() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Aanvaller;
		Team T = new Team("Test", false);
		Team J = new Team("Ja", false);
		Speler C = new Speler("Naam",5,1,B,A,5,5,3);
		Speler D = new Speler("Naam",5,1,B,A,5,5,3);
		Speler E = new Speler("Naam",5,1,B,A,5,5,3);
		C.wijzigTeam(T);
		D.team=T;
		E.team=J;
		assertEquals(D,C);
		assertFalse(C.team.equals(E.team));
	}

/*	@Test
	public void testVeranderStatus() {
		fail("Not yet implemented");
	} */

	@Test
	public void testGetRugNummer() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Aanvaller;
		int Rugnummer = 5;
		int Rugnummerb = 5;
		Speler C = new Speler("Naam",Rugnummer,1,B,A,5,5,3);
		assertEquals(C.getRugNummer(),C.nummer);
		assertEquals(Rugnummerb,C.getRugNummer());
	}

	@Test
	public void testGetPrijs() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Aanvaller;
		Speler C = new Speler("Naam",5,1,B,A,5,5,3);
		C.prijs=5;
		int z = 4;
		assertEquals(C.prijs,C.getPrijs());
		assertEquals(5,C.getPrijs());
		assertFalse(z==C.getPrijs());
	}

	@Test
	public void testGetStatus() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Aanvaller;
		Speler C = new Speler("Naam",5,1,B,A,5,5,3);
		assertEquals(C.getStatus(),Speler.Status.Beschikbaar);
		assertFalse(C.getStatus().equals(Speler.Status.Blessure));
	}

	@Test
	public void testGetXMLElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testLaadXMLElement() {
		fail("Not yet implemented");
	}

}
