package voetbalmanager.controller.wedstrijdsimulatie.unit;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import voetbalmanager.controller.wedstrijdsimulatie.WedstrijdTeam;
import voetbalmanager.model.Team;

public class TestWedstrijdTeam {

	Team a;
	Team b;
	
	@Before
	public void setUp() {
		a = new Team("Vitesse", false);
		b = new Team("Ajax", true);
	}
	
	@Test
	public void testContructor() {
		WedstrijdTeam actual = new WedstrijdTeam(a);
		assertEquals(a, actual.getTeam());
		assertEquals(0, actual.getScore());
	}
	
	@Test
	public void testGetTeam() {
		WedstrijdTeam actual = new WedstrijdTeam(b);
		assertEquals(b, actual.getTeam());
	}
	
	@Test
	public void testGetTeamNeg() {
		WedstrijdTeam actual = new WedstrijdTeam(b);
		assertNotEquals(a, actual.getTeam());
	}
	
	@Test
	public void testGetScore() {
		WedstrijdTeam actual = new WedstrijdTeam(b);
		assertEquals(0, actual.getScore());
	}
	
	@Test
	public void testDoelpunt() {
		WedstrijdTeam actual = new WedstrijdTeam(b);
		actual.maakDoelpunt(0);
		assertEquals(1, actual.getScore());
	}
	@After
	public void tearDown() {
		a = null;
	}
}
