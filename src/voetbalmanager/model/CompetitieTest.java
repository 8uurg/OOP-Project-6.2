package voetbalmanager.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import voetbalmanager.XMLLoader;

public class CompetitieTest {
	Team Ajax, Feyenoord, PSV, AZ;
	Competitie eredivisie,competitie;
	
	
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
		competitie = new Competitie("competitie");
		competitie.addTeam(Feyenoord);
		assertNotEquals(competitie.getSpelerTeam(),Ajax);
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
		Competitie competitie3 = new Competitie("Eerdivisie");
		assertNotEquals(eredivisie,competitie3);
	}
	
	@Test
	public void testSorteren(){
		Competitie competitie = new Competitie("Eredivisie");
		competitie.addTeam(PSV);
		competitie.addTeam(AZ);
		competitie.addTeam(Feyenoord);
		competitie.addTeam(Ajax);
		competitie.Sorteren("Naam");
		assertEquals(competitie.getTeams().get(0),AZ);
		assertNotEquals(competitie.getTeams().get(0),PSV);
	//	System.out.println(competitie.getTeams().get(0).toString());
		Wedstrijd x = new Wedstrijd(PSV,Ajax);
		Wedstrijd y = new Wedstrijd(Feyenoord,AZ);
		x.maakUitslag(5,1);
		y.maakUitslag(3,3);
		x.kenPuntenToe();
		y.kenPuntenToe();
		x.maakUitslag(5,1);
		x.kenPuntenToe();
		competitie.Sorteren("Punten");
		assertEquals(competitie.getTeams().get(0),PSV);
		assertEquals(competitie.getTeams().get(3),Ajax);
		assertNotEquals(competitie.getTeams().get(0),Ajax);
		competitie.Sorteren("Punkten");
		assertEquals(competitie.getTeams().get(0),PSV);
		assertEquals(competitie.getTeams().get(3),Ajax);
		assertNotEquals(competitie.getTeams().get(0),Ajax);
		Wedstrijd w = new Wedstrijd(Ajax,AZ);
		w.maakUitslag(2,1);
		w.kenPuntenToe();
		competitie.Sorteren("Punten");
		assertEquals(competitie.getTeams().get(3),Feyenoord);
		w.maakUitslag(8,2);
		w.kenPuntenToe();
		competitie.Sorteren("Punten");
		assertNotEquals(competitie.getTeams().get(0),AZ);
		assertEquals(competitie.getTeams().get(0),Ajax);
	}
	
	@Test
	public void testMaakSpeelschema(){
		Speelschema a = eredivisie.maakSpeelSchema();
		
		assertTrue(a.allContainTeams(eredivisie.getTeams()));
		assertEquals(a.getSchema().size(),(eredivisie.getTeams().size()*2-2));
	}
	
	@Test
	public void testSetSpelerTeam(){
		assertTrue(Feyenoord.isComputerGestuurd());
		eredivisie.setSpelerTeam(Feyenoord);
		assertTrue(Feyenoord.isSpelerBestuurd());
		assertFalse(Feyenoord.isComputerGestuurd());
		eredivisie.setSpelerTeam(Feyenoord);
		assertFalse(Feyenoord.isComputerGestuurd());
		assertTrue(Feyenoord.isSpelerBestuurd());
		
	}
	
	@Test 
	public void testGetNaam(){
		Competitie a = new Competitie("COMP");
		assertEquals(a.getNaam(),"COMP");
		assertNotEquals(a.getNaam(),"a");
	}
	
	@Test
	public void testSetNaam(){
		assertNotEquals(eredivisie.getNaam(),"Competitie");
		eredivisie.setNaam("Competitie");
		assertEquals(eredivisie.getNaam(),"Competitie");
	}
	
	@Test
	public void testAddTeam(){
		Competitie a = new Competitie("Full");
		a.addTeam(AZ);
		a.addTeam(AZ);
		a.addTeam(AZ);
		a.addTeam(AZ);
		a.addTeam(AZ);
		a.addTeam(AZ);
		a.addTeam(AZ);
		a.addTeam(AZ);
		a.addTeam(AZ);
		a.addTeam(AZ);
		a.addTeam(AZ);
		a.addTeam(AZ);
		a.addTeam(AZ);
		a.addTeam(AZ);
		a.addTeam(AZ);
		a.addTeam(AZ);
		a.addTeam(AZ);
		a.addTeam(AZ);
		a.addTeam(Feyenoord);
		a.addTeam(PSV);
		assertNotEquals(a.getTeams().size(),20);
		assertEquals(a.getTeams().size(),19);
		assertFalse(a.getTeams().contains(PSV));
		assertTrue(a.getTeams().contains(Feyenoord));
	}
	
	@Test
	public void testGetSchema(){
		Competitie a = new Competitie("Naam");
		a.addTeam(AZ);
		a.addTeam(Feyenoord);
		a.addTeam(Ajax);
		a.addTeam(PSV);
		a.getSchema();
		assertNotEquals(a.getSchema().getSchema().size(),0);
	}
	
	@Test
	public void testZoekTeam() {
		Competitie c = XMLLoader.creeerCompetitie("Heh");
		assertNull(c.zoekTeam("Wilfried"));
		Team t = c.getTeams().get(0);
		assertEquals(t, c.zoekTeam(t.getNaam()));
	}
	
	@Test
	public void testToString() {
		Competitie c = XMLLoader.creeerCompetitie("Heh");
		assertNotNull(c.toString());
		assertTrue(c.toString().startsWith("Competitie"));
	}
	
	@Test
	public void testSpeel() {
		//De for loop runt een complete competitie 32 ronden, dus als je 25 keer een competitie wil testen moet je
		//de comments even weghalen ^^.
		for(int j=0;j<25;++j){	
			Competitie c = XMLLoader.creeerCompetitie("Heh");
			c.getSchema();
			for(int i=0; i<35; ++i){
				c.startSpeelronde();
			}
			System.out.println(c.Sorteren("Punten").toString());
		}
	}
	
	@Test
	public void testGetSpelerWedstrijd(){
		Competitie c = XMLLoader.creeerCompetitie("HA");
		c.setSpelerTeam(c.getTeams().get(0));
	//	System.out.println(c.getSpelerTeam());
		c.getSchema();
		c.startSpeelronde();
	//	System.out.println(c.getWeek());
	//	System.out.println(c.getSchema().getSchema().get(0).getWedstrijden().get(0));
	//	System.out.println(c.getSpelerWedstrijd());
		ArrayList<Wedstrijd> a = c.getSchema().getSchema().get(0).getWedstrijden();
		Wedstrijd j=null;
		for(Wedstrijd w : a){
			if(w.getSpelerWedstrijd()){
				j = w;
			}
		}
		assertEquals(c.getSpelerWedstrijd(),j);
		c.getSpelerTeam().setGebruikerTeam(false);
		assertEquals(c.getSpelerWedstrijd(),new Wedstrijd(new Team("geenSpelerWedstrijd", false),new Team("geenSpelerWedstrijd", false)));
		
	}
	
	@Test
	public void testVolgendeRonde(){
		Competitie c = XMLLoader.creeerCompetitie("Yay");
		c.getSchema();
		assertEquals(c.volgendeRonde(),c.getSchema().getSchema().get(0).getWedstrijden());
	}
	
	@Test
	public void testHuidigeResultaten(){
		Competitie c = XMLLoader.creeerCompetitie("WAUW");
		c.getSchema();
		assertEquals(c.huidigeResultaten(),c.getSchema().getSchema().get(0).getWedstrijden());
		c.startSpeelronde();
		assertEquals(c.huidigeResultaten(), c.getSchema().getSchema().get(0).getWedstrijden());
	}
}
