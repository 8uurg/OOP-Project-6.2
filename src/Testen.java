import exceptions.OpstellingException;


public class Testen {
	public static void main(String[] args) throws OpstellingException {
		Veldspeler xc = new Veldspeler("Marco", 12, 15000, Speler.Status.Beschikbaar, 85, 95, 32, Veldspeler.Type.Aanvaller);
		Veldspeler ad = new Veldspeler("Marco", 12, 15000, Speler.Status.Beschikbaar, 85, 95, 32, Veldspeler.Type.Aanvaller);
		System.out.println(xc.equals(ad));
		
		Team team = new Team();
		try {
			team.voegToeAanvaller(xc);
		} catch (OpstellingException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	
	
}
