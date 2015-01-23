package voetbalmanager.model;

import static org.junit.Assert.*;

import org.junit.Test;

import voetbalmanager.XMLLoader;

public class TestBeschikbareSpeler {

	@Test
	public void testBesluitKoop() {
		Competitie c = XMLLoader.creeerCompetitie("Meep");
		Team t = c.getTeams().get(0);
		Speler jeff = t.getSelectie().get(2);
		
		BeschikbareSpeler s = new BeschikbareSpeler(jeff, t);
		
		System.out.println(s.besluitKoop(c.getTeams().get(1).getSelectie()));
	}
	
	@Test
	public void testMoetKopen() {
		Competitie c = XMLLoader.creeerCompetitie("Meep");
		Team t = c.getTeams().get(0);
		Speler jeff = t.getSelectie().get(2);
		
		BeschikbareSpeler s = new BeschikbareSpeler(jeff, t);
		
		System.out.println(s.moetKopen(c.getTeams().get(1).getSelectie()));
		System.out.println(s.moetKopen(c.getTeams().get(4).getSelectie()));
	}

}
