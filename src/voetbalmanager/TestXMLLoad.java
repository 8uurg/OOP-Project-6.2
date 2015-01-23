package voetbalmanager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.xml.transform.stream.StreamResult;

import org.junit.Test;

import static org.junit.Assert.*;

import org.xml.sax.InputSource;

import voetbalmanager.model.Competitie;
import voetbalmanager.model.Speler;
import voetbalmanager.model.Team;
import voetbalmanager.model.Wedstrijd;

public class TestXMLLoad {

	// Test competitie
	@Test
	public void testZonderSchema() {
		Competitie oud = new Competitie("eredivisie");
		
		Team a = new Team("Ajax", false);
			a.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
			a.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));
		
		Team b = new Team("Vitesse", true);
			b.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
			b.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));
	
		
		oud.addTeam(a);
		oud.addTeam(b);
		
		oud.getTransferMarkt().maakVerhandelbaar(a.getSelectie().get(0));
		oud.getTransferMarkt().maakVerhandelbaar(new Speler("James Watt", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		StreamResult outs = new StreamResult(out);
		
		XMLWriter.saveCompetitie(oud, outs);
		
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		
		System.out.print(out);
		
		Competitie nieuw = XMLLoader.laadCompetitie(new InputSource(in));
		
		assertEquals(oud, nieuw);
	}
	
	@Test
	public void testMetSchema() {
		Competitie Marcoseredivisietest = new Competitie("Marcoseredivisietest");
		
		Team A = new Team("A", false);
			A.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
			A.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));
		
		Team B = new Team("B", true);
			B.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
			B.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));

		Team C = new Team("C",true);
		C.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
		C.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));

		Team D = new Team("D",true);
		D.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
		D.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));

		Team E = new Team("E",true);
		E.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
		E.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));

		Team F = new Team("F",true);
		F.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
		F.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));

		Team G = new Team("G",true);
		G.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
		G.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));

		Team H = new Team("H",true);
		H.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
		H.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));

		Team I = new Team("I",true);
		I.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
		I.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));

		Team J = new Team("J",true);
		J.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
		J.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));

		Team K = new Team("K",true);
		K.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
		K.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));

		Team L = new Team("L",true);
		L.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
		L.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));

		Team M = new Team("M",true);
		M.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
		M.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));

		Team N = new Team("N",true);
		N.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
		N.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));

		Team O = new Team("O",true);
		O.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
		O.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));

		Team P = new Team("P",true);
		P.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
		P.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));

		Team Q = new Team("Q",true);
		Q.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
		Q.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));

		Team R = new Team("R",true);
		R.overrideAdd(new Speler("James", 12, 12, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 90, 10, 70));
		R.overrideAdd(new Speler("Patrick", 13, 2, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 60, 40, 70));

	
		Marcoseredivisietest.addTeam(A);
		Marcoseredivisietest.addTeam(B);
		Marcoseredivisietest.addTeam(C);
		Marcoseredivisietest.addTeam(D);
		Marcoseredivisietest.addTeam(E);
		Marcoseredivisietest.addTeam(F);
		Marcoseredivisietest.addTeam(G);
		Marcoseredivisietest.addTeam(H);
		Marcoseredivisietest.addTeam(I);
		Marcoseredivisietest.addTeam(J);
		Marcoseredivisietest.addTeam(K);
		Marcoseredivisietest.addTeam(L);
		Marcoseredivisietest.addTeam(M);
		Marcoseredivisietest.addTeam(N);
		Marcoseredivisietest.addTeam(O);
		Marcoseredivisietest.addTeam(P);
		Marcoseredivisietest.addTeam(Q);
		Marcoseredivisietest.addTeam(R);
		Marcoseredivisietest.maakSpeelSchema();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		StreamResult outs = new StreamResult(out);
		
		XMLWriter.saveCompetitie(Marcoseredivisietest, outs);
		
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		
	//	System.out.print(out);
		
		Competitie nieuw = XMLLoader.laadCompetitie(new InputSource(in));
		
		assertEquals(Marcoseredivisietest, nieuw);
		
	}
	
	@Test
	public void testCreatie() {
		Competitie c = XMLLoader.creeerCompetitie("eredivisie");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		StreamResult outs = new StreamResult(out);
		c.maakSpeelSchema();
		c.startCompetitie();
		XMLWriter.saveCompetitie(c, outs);
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		Competitie nieuw = XMLLoader.laadCompetitie(new InputSource(in));
		assertEquals(c.getWeek(),nieuw.getWeek());
//		assertEquals(c.getSchema().getSchema(),nieuw.getSchema().getSchema());
		assertEquals(c.getNaam(),nieuw.getNaam());
//		assertEquals(c.getTeams(),nieuw.getTeams());
		assertEquals(c.getTransferMarkt(),nieuw.getTransferMarkt());
	}

}
