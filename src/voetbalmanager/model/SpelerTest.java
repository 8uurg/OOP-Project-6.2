package voetbalmanager.model;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SpelerTest {
	Team T,J;
	Speler C,D,E,F,G,H,I,N;
	ArrayList<Speler> player;
	Speler.Status B;
	Speler.Type A;
	
	@Before
	public void beforeTest(){
	B = Speler.Status.Beschikbaar;
	A= Speler.Type.Aanvaller;
	T = new Team("Test", false);
	J = new Team("Ja", false);
	C = new Speler("Naam",5,1,B,A,5,5,3);
	D = new Speler("Naam",5,1,B,A,5,5,3);
	E = new Speler("Naam",5,2,B,A,6,5,3);
	F = new Speler("Naam",5,2,B,A,6,5,3);
	G = new Speler("Naam",5,1,B,A,5,5,3);
	H = new Speler("Naam",5,1,B,A,10,15,7);
	I = new Speler("Naam",5,1,B,A,2,2,2);
	N = new Speler("Naam",5,1,B,A,2,3,2);
	player = new ArrayList<Speler>();
	}
	
	@Test
	public void testSpeler() {	
		assertEquals(C,D);
		assertNotEquals(D,E);
		assertNotEquals(D,"A");
		assertEquals(C.getTeam(),D.getTeam());
		assertEquals(E,F);
		E.setPrijs(0);
		assertNotEquals(E,F);
		assertEquals(E.getPrijs(),0);
		assertNotEquals(F,G);
		assertNotEquals(H,I);
		assertNotEquals(I,N);
	}

	@Test
	public void testWijzigTeam() {
		C.wijzigTeam(T);
		D.setTeam(T);
		G.setTeam(J);
		assertEquals(D,C);
		assertNotEquals(C.getTeam(),G.getTeam());
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
		assertEquals(C.getRugNummer(),5);
		assertEquals(Rugnummerb,C.getRugNummer());
	}

	@Test
	public void testGetPrijs() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Aanvaller;
		Speler C = new Speler("Naam",5,1,B,A,5,5,3);
		C.setPrijs(5);
		int z = 4;
		assertEquals(5,C.getPrijs());
		assertEquals(5,C.getPrijs());
		assertNotEquals(z, C.getPrijs());
	}

	@Test
	public void testGetStatus() {
		Speler.Status B = Speler.Status.Beschikbaar;
		Speler.Type A= Speler.Type.Aanvaller;
		Speler C = new Speler("Naam",5,1,B,A,5,5,3);
		assertEquals(C.getStatus(),Speler.Status.Beschikbaar);
		assertNotEquals(C.getStatus(),Speler.Status.Blessure);
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
	
	@Test
	public void testTransferAanbieden(){
		player.add(C);
		player.add(D);
		player.add(E);
		assertTrue(H.transferAanbieden(player));
		assertFalse(I.transferAanbieden(player));
	}
	
	@After
	public void afterTest(){
	B = null;
	A= null;
	T = null;
	J = null;
	C = null;
	D = null;
	E = null;
	F = null;
	G = null;
	H = null;
	I = null;
	N = null;
	player = null;
	}
}
