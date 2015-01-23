package voetbalmanager.model;

import static org.junit.Assert.*;

import org.junit.Test;

import voetbalmanager.XMLLoader;

public class TestTransferMarkt {

	@Test
	public void testEquals() {
		Competitie c = XMLLoader.creeerCompetitie("HetTest");	
		assertEquals(c.getTransferMarkt(), c.getTransferMarkt());
		assertNotEquals(null, c.getTransferMarkt());
		assertNotEquals(c.getTransferMarkt(), null);
	}

}
