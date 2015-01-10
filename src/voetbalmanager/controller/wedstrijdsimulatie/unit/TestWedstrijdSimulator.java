package voetbalmanager.controller.wedstrijdsimulatie.unit;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import voetbalmanager.model.Speler;
import voetbalmanager.model.Team;

public class TestWedstrijdSimulator {

	private Team a;
	private Team b;
	
	@Before
	public void setUp() {
		a = new Team("NAC Breda", false);
		a.overrideAdd(new Speler("Jelle ten Rouwelaar", 1, 230, Speler.Status.Beschikbaar, Speler.Type.Doelman, 46, 78, 65), true);
		a.overrideAdd(new Speler("Sepp de Roover", 2, 200, Speler.Status.Beschikbaar, Speler.Type.Verdediger, 70, 74, 75), true);
		a.overrideAdd(new Speler("Henrico Drost", 3, 230, Speler.Status.Beschikbaar, Speler.Type.Verdediger, 56, 68, 70), true);
		a.overrideAdd(new Speler("Aleksandar Damcevski", 4, 271, Speler.Status.Beschikbaar, Speler.Type.Verdediger, 47, 67, 78), true);
		a.overrideAdd(new Speler("Romy Amieux ", 5, 125, Speler.Status.Beschikbaar, Speler.Type.Verdediger, 58, 70, 78), true);
		a.overrideAdd(new Speler("Joeri de Kamps", 6, 265, Speler.Status.Beschikbaar, Speler.Type.Middenvelder, 63, 63, 73), true);
		a.overrideAdd(new Speler("Elson Hooi", 7, 325, Speler.Status.Beschikbaar, Speler.Type.Middenvelder, 89, 28, 47), true);
		a.overrideAdd(new Speler("Jeffrey Sarpong", 8, 195, Speler.Status.Beschikbaar, Speler.Type.Middenvelder, 78, 29, 54), true);
		a.overrideAdd(new Speler("Kingsley Boateng", 9, 340, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 82, 29, 69), true);
		a.overrideAdd(new Speler("Uros Matic", 10, 200, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 71, 52, 73), true);
		a.overrideAdd(new Speler("Adnane Tighadouini", 11, 190, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 77, 28, 76), true);
		a.overrideAdd(new Speler("Andries Noppert", 12, 150, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 75, 43, 65));
		a.overrideAdd(new Speler("Joey Suk", 14, 245, Speler.Status.Beschikbaar, Speler.Type.Middenvelder, 45, 67, 89));
		a.overrideAdd(new Speler("Erik Falkenburg", 17, 250, Speler.Status.Beschikbaar, Speler.Type.Middenvelder, 66, 66, 68));
		a.overrideAdd(new Speler("Mounir el Allouchi", 19, 165, Speler.Status.Beschikbaar, Speler.Type.Middenvelder, 67, 66, 56));
		a.overrideAdd(new Speler("Mats Seuntjens", 20, 200, Speler.Status.Beschikbaar, Speler.Type.Verdediger, 57, 76, 76));
		a.overrideAdd(new Speler("Gabor Babos", 21, 200, Speler.Status.Beschikbaar, Speler.Type.Verdediger, 28, 56, 66));
		a.overrideAdd(new Speler("Gill Swerts", 22, 190, Speler.Status.Beschikbaar, Speler.Type.Verdediger, 54, 55, 65));
		a.overrideAdd(new Speler("Kenny van der Weg", 28, 160, Speler.Status.Beschikbaar, Speler.Type.Verdediger, 64, 64, 65));
		a.overrideAdd(new Speler("Stipe Perica", 13, 210, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 67, 68, 79));
		a.overrideAdd(new Speler("Isak Ssewankambo", 16, 200, Speler.Status.Beschikbaar, Speler.Type.Verdediger, 47, 68, 58));
		a.overrideAdd(new Speler("Dilson Gabbay", 15, 200, Speler.Status.Beschikbaar, Speler.Type.Middenvelder, 77, 56, 56));
		
		b = new Team("Vitesse", false);
		b.overrideAdd(new Speler("Eloy Room", 1, 200, Speler.Status.Beschikbaar, Speler.Type.Doelman, 37, 67, 67), true);
		b.overrideAdd(new Speler("Jeroen Houwen", 48, 200, Speler.Status.Beschikbaar, Speler.Type.Doelman, 34, 56, 66));
		b.overrideAdd(new Speler("Rochdi Achenteh", 35, 230, Speler.Status.Beschikbaar, Speler.Type.Verdediger, 56, 66, 67), true);
		b.overrideAdd(new Speler("Kevin Diks", 40, 160, Speler.Status.Beschikbaar, Speler.Type.Verdediger, 44, 56, 66), true);
		b.overrideAdd(new Speler("Dan Mori", 2, 175, Speler.Status.Beschikbaar, Speler.Type.Verdediger, 56, 77, 64), true);
		b.overrideAdd(new Speler("Wallace Cardo", 3, 150, Speler.Status.Beschikbaar, Speler.Type.Verdediger, 33, 56, 66), true);
		b.overrideAdd(new Speler("Guram Kashia", 37, 140, Speler.Status.Beschikbaar, Speler.Type.Verdediger, 44, 67, 65));
		b.overrideAdd(new Speler("Arnold Kruiswijk", 15, 190, Speler.Status.Beschikbaar, Speler.Type.Verdediger, 67, 77, 71));
		b.overrideAdd(new Speler("Jan-Arie van der Heijden", 23, 130, Speler.Status.Beschikbaar, Speler.Type.Verdediger, 49, 69, 60));
		b.overrideAdd(new Speler("Kelvin Leerdam", 5, 165, Speler.Status.Beschikbaar, Speler.Type.Middenvelder, 66, 71, 78), true);
		b.overrideAdd(new Speler("Josh McEachran", 6, 140, Speler.Status.Beschikbaar, Speler.Type.Middenvelder, 75, 77, 56), true);
		b.overrideAdd(new Speler("Marko Vejinovi", 7, 180, Speler.Status.Beschikbaar, Speler.Type.Middenvelder, 43, 77, 67), true);
		b.overrideAdd(new Speler("Gino Bosz", 25, 175, Speler.Status.Beschikbaar, Speler.Type.Middenvelder, 73, 77, 78));
		b.overrideAdd(new Speler("Zakaria Labyad", 20, 160, Speler.Status.Beschikbaar, Speler.Type.Middenvelder, 49, 74, 19));
		b.overrideAdd(new Speler("Marvelous Nakamba", 18, 110, Speler.Status.Beschikbaar, Speler.Type.Middenvelder, 77, 43, 65));
		b.overrideAdd(new Speler("Davy Propper", 10, 125, Speler.Status.Beschikbaar, Speler.Type.Middenvelder, 59, 69, 72));
		b.overrideAdd(new Speler("Uroi Aurrevii", 9, 160, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 69, 64, 67), true);
		b.overrideAdd(new Speler("Valeri Qazaishvili", 8, 170, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 89, 24, 76), true);
		b.overrideAdd(new Speler("Denys Oliynyk", 11, 230, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 77, 44, 77), true);
		b.overrideAdd(new Speler("Abiola Dauda", 14, 160, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 69, 28, 69));
		b.overrideAdd(new Speler("Renato Ibarra", 30, 170, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 77, 37, 67));
		b.overrideAdd(new Speler("Bertrand Traori", 27, 125, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 67, 27, 77));
		
		
	}
	
	@Test
	public void testSimulatie() {
		fail("Not yet implemented!");
	}

}
