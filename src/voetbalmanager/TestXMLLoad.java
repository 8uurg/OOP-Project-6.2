package voetbalmanager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import static org.junit.Assert.*;

import org.xml.sax.InputSource;

import voetbalmanager.model.Competitie;
import voetbalmanager.model.Team;

public class TestXMLLoad {

	// Test competitie
	@Test
	public void test() {
		Competitie oud = new Competitie("eredivisie");
		
		Team a = new Team("Ajax");
		
		Team b = new Team("Vitesse");
		
		oud.addTeam(a);
		oud.addTeam(b);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		StreamResult outs = new StreamResult(out);
		
		XMLWriter.saveCompetitie(oud, outs);
		
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		Competitie nieuw = XMLLoader.laadCompetitie(new InputSource(in));
		
		assertEquals(oud, nieuw);
		
	}

}
