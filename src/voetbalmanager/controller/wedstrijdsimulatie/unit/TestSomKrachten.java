package voetbalmanager.controller.wedstrijdsimulatie.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import voetbalmanager.controller.wedstrijdsimulatie.SomKrachten;

public class TestSomKrachten {

	@Test
	public void testEquals() {
		SomKrachten a = new SomKrachten(5, 5, 4);
		SomKrachten b = new SomKrachten(5, 5, 3);
		SomKrachten c = new SomKrachten(5, 4, 4);
		
		assertNotEquals(a, b);
		assertNotEquals(a, c);
		assertNotEquals(a, null);
	}

}
