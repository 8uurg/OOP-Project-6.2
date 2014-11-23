package exceptions;
@SuppressWarnings("serial")

/*	Deze excepties worden gebruikt in het opstellingscherm.
 * Voorbeelden van wanneer dit soort excepties worden gebruikt:
 * 		De gebruiker probeert een speler twee keer op te stellen
 * 		De gebruiker probeert een speler op te stellen die niet in zijn selectie zit
 * 		De gebruiker probeert een speler als doelman op te stellen
 */

public class OpstellingException extends Exception {
	
	public OpstellingException() {
		super();
	}
	
	public OpstellingException(String bericht) {
		super(bericht);
	}
	
	public OpstellingException(String bericht, Throwable error) {
		super(bericht, error);
	}
	
	public OpstellingException(Throwable error) {
		super(error);
	}
}
