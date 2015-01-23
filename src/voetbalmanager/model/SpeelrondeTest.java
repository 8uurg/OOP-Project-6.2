package voetbalmanager.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SpeelrondeTest {
	Speelronde A = new Speelronde();
	Speelronde B = new Speelronde();
	Team Tone = new Team("T1", false);
	Team Ttwo = new Team("T2", false);
	Team Tthree = new Team("T3",false);
	Team Tfour = new Team("T4",false);
	Team Tfive = new Team("T5",false);
	Wedstrijd a = new Wedstrijd(Tone,Ttwo);
	Wedstrijd b = new Wedstrijd(Ttwo,Tone);
	Wedstrijd c = new Wedstrijd(Tone,Tthree);
	Wedstrijd d = new Wedstrijd(Tone,Tfour);
	Wedstrijd e = new Wedstrijd(Tthree,Tfour);
	Wedstrijd f = new Wedstrijd(Ttwo,Tfour);
	Wedstrijd g = new Wedstrijd(Ttwo,Tfive);
	
	@Test
	public void testSpeelronde() {
		assertNotEquals(A,a);
		assertEquals(A,B);
		A.voegToe(a);
		A.voegToe(b);
		a.maakUitslag(5, 5);
		B.voegToe(a);
		a.maakUitslag(5, 5);
		B.voegToe(b);
		assertEquals(A,B);
		A.voegToe(e);
		B.voegToe(d);
		assertNotEquals(A,B);
	}

	@Test
	public void testVoegToe() {
		A.voegToe(a);
		assertEquals(A.get(0),a);
	}

	@Test
	public void testGetSize() {
		A.voegToe(a);
		A.voegToe(b);
		A.voegToe(c);
		A.voegToe(d);
		assertTrue(A.getSize()==4);
		assertFalse(A.getSize()==5);
	}

/*	@Test
	public void testToString() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testGetWedstrijden() {
		A.voegToe(a);
		A.voegToe(b);
		ArrayList<Wedstrijd> a = A.getWedstrijden();
		assertTrue(a.get(0).equals(this.a));
		assertFalse(a.get(0).equals(this.b));
		assertTrue(a.get(1).equals(this.b));
	}

	@Test
	public void testWedstrijdEquals() {
		A.voegToe(a);
		A.voegToe(b);
		assertFalse(A.WedstrijdEquals(e));
		assertTrue(A.WedstrijdEquals(d));
	}

	@Test
	public void testVerwijder() {
		A.voegToe(a);
		A.voegToe(e);
		A.verwijder(0);
		assertFalse(A.get(0).equals(a));
		assertEquals(A.get(0),e);
	}

	@Test
	public void testGet() {
		A.voegToe(a);
		A.voegToe(b);
		A.voegToe(c);
		A.voegToe(d);
		assertEquals(A.get(0),a);
		assertEquals(A.get(3),d);
		assertFalse(A.get(0).equals(c));
	}
	
	@Test
	public void testContainsAll(){
		A.voegToe(a);
		A.voegToe(e);
		B.voegToe(c);
		B.voegToe(f);
		assertTrue(A.containsAll(B));
		B.voegToe(g);
		assertFalse(A.containsAll(B));
		B.verwijder(1);
		assertFalse(A.containsAll(B));
	}
	
	@Test
	public void testContainsTeams(){
		ArrayList<Team> tl = new ArrayList<Team>();
		tl.add(Tone);
		tl.add(Ttwo);
		tl.add(Tthree);
		tl.add(Tfour);
		A.voegToe(a);
		A.voegToe(e);
		assertTrue(A.containsTeams(tl));
		A.verwijder(0);
		assertFalse(A.containsTeams(tl));
	}

}
