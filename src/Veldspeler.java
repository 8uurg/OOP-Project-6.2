
public class Veldspeler extends Speler {
	private Type type;
	private int OffensieveScore;
	private int DefensieveScore;
	private int Uithoudingsvermogen;
	
	public enum Type {
		Aanvaller, Middenvelder, Verdediger;
	}
	
	public Veldspeler(String naam,int nummer,int prijs, Status status, int uithvermogen, int Offscore, int defscore, Type typ){
		super(naam,nummer,prijs,status);
		type = typ;
		OffensieveScore = Offscore;
		DefensieveScore = defscore;
		Uithoudingsvermogen = uithvermogen;
		
	}
	public boolean equals(Object VS){
		if(VS instanceof Veldspeler){
			Veldspeler that = (Veldspeler)VS;
			Boolean res = super.equals(that)&&this.type.equals(that.type)&&this.OffensieveScore==that.OffensieveScore
					&&this.DefensieveScore==that.DefensieveScore
					&&this.Uithoudingsvermogen==that.Uithoudingsvermogen;
			
			return res;
		}
		return false;
	}
}
