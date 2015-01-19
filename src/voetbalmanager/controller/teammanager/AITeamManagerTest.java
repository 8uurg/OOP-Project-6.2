package voetbalmanager.controller.teammanager;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import voetbalmanager.XMLLoader;
import voetbalmanager.model.Competitie;
import voetbalmanager.model.Team;

public class AITeamManagerTest {

	Competitie c;
	
	@Before
	public void setUp() {
		c = XMLLoader.creeerCompetitie("Test");
	}
	
	//@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGenereerOpstelling() {
		AITeamManager manager = new AITeamManager();
		for(Team t: c.getTeams()) {
			manager.genereerOpstelling(t);
		}
	}

}
