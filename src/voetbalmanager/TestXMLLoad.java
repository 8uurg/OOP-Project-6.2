package voetbalmanager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.transform.stream.StreamResult;

import org.junit.Test;

import static org.junit.Assert.*;

import org.xml.sax.InputSource;

import voetbalmanager.model.Competitie;
import voetbalmanager.model.Speler;
import voetbalmanager.model.Team;

public class TestXMLLoad {

	// Test competitie
	@Test
	public void test() {
		Competitie oud = new Competitie("eredivisie");
		
		Team a = new Team("Ajax", false);
			a.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
			a.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));
		
		Team b = new Team("Vitesse", true);
			b.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
			b.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));
	
		
		oud.addTeam(a);
		oud.addTeam(b);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		StreamResult outs = new StreamResult(out);
		
		XMLWriter.saveCompetitie(oud, outs);
		
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		
		System.out.print(out);
		
		Competitie nieuw = XMLLoader.laadCompetitie(new InputSource(in));
		
		assertEquals(oud, nieuw);
		
	}

}
