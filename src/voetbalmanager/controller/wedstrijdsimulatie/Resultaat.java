package voetbalmanager.controller.wedstrijdsimulatie;

public class Resultaat {
	private int scoreA;
	private int scoreB;
	
	public Resultaat(int scoreA, int scoreB) {
		this.scoreA = scoreA;
		this.scoreB = scoreB;
	}
	
	public int[] getScore() {
		return new int[]{scoreA, scoreB};
	}
	
	public String toString() {
		return "(" + scoreA + ", " + scoreB + ")";
	}
}
