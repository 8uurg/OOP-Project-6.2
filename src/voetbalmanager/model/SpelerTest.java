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
		assertFalse(D.equals("A"));
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

	@Test
	public void testToString() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Aanvaller;
		Speler C = new Speler("Naam",5,1,B,A,5,5,3);
		String s = "";
		Team T = new Team("Test", false);
		C.wijzigTeam(T);
		s += "---------------------\nNaam";
		s += "\n---------------------\n";
		s += "Nummer: 5\n";
		s += "Prijs: 1\n";
		s += "Status: Beschikbaar\nType: Aanvaller\n";
		s += "Offensief: 5\nDefensief: 5\nUithoudingsvermogen: 3\n";
		s += "Team: Test\n";
		
		assertEquals(C.toString(), s);
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
		assertNotEquals(z, C.getPrijs());
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
	public void testGetDefensief() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Aanvaller;
		Speler C = new Speler("Naam",5,1,B,A,6,5,3);
		assertEquals(C.getDefensief(), 5);
	}
	
	@Test
	public void testGetUithouding() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Aanvaller;
		Speler C = new Speler("Naam",5,1,B,A,6,5,3);
		assertEquals(C.getUithouding(), 3);
	}
	
	@Test
	public void testGetNaam() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Aanvaller;
		Speler C = new Speler("Naam",5,1,B,A,6,5,3);
		assertEquals(C.getNaam(), "Naam");
	}
	
	@Test
	public void testGetOffensief() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Aanvaller;
		Speler C = new Speler("Naam",5,1,B,A,6,5,3);
		assertEquals(C.getOffensief(), 6);
		assertNotEquals(C.getOffensief(), "6");
	}
	
	@Test
	public void testGetType() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Aanvaller;
		Speler C = new Speler("Naam",5,1,B,A,6,5,3);
		assertEquals(C.getType(), Speler.Type.Aanvaller);
	}
	
	@Test
	public void testGetTeam() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Aanvaller;
		Speler C = new Speler("Naam",5,1,B,A,6,5,3);
		Team T = new Team("Test", false);
		C.wijzigTeam(T);
		assertEquals(C.getTeam(), T);
		assertNotEquals(C.getTeam(), new Team("Test2", false));
	}
	
	@Test
	public void testGetSpelerWaardeAanvaller() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Aanvaller;
		Speler C = new Speler("Naam",5,1,B,A,6,5,3);
		assertEquals(C.getSpelerWaarde(), 8.8, 0.0001);
	}
	
	@Test
	public void testGetSpelerWaardeDoelman() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Doelman;
		Speler C = new Speler("Naam",5,1,B,A,6,5,3);
		assertEquals(C.getSpelerWaarde(), 9.8, 0.0001);
	}
	
	@Test
	public void testGetSpelerWaardeMiddenvelder() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Middenvelder;
		Speler C = new Speler("Naam",5,1,B,A,6,5,3);
		assertEquals(C.getSpelerWaarde(), 8.5, 0.0001);
	}
	
	@Test
	public void testGetSpelerWaardeVerdediger() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Verdediger;
		Speler C = new Speler("Naam",5,1,B,A,6,5,3);
		assertEquals(C.getSpelerWaarde(), 8.2, 0.0001);
	}

}
