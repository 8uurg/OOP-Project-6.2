package exceptions;
@SuppressWarnings("serial")

/*	Dit soort excepties worden gebruikt bij het transferscherm
 * 	Voorbeelden van wanneer dit soort excepties worden gebruikt:
 * 		De gebruiker wilt een speler verkopen die hij niet heeft
 * 		De gebruiker wilt een speler verkopen die nu in zijn opstelling zit
 * 		De gebruiker wilt een speler kopen die al in zijn selectie zit
 */

public class TransferException extends Exception {
	
	public TransferException() {
		super();
	}
	
	public TransferException(String bericht) {
		super(bericht);
	}
	
	public TransferException(String bericht, Throwable error) {
		super(bericht, error);
	}
	
	public TransferException(Throwable error) {
		super(error);
	}
}
