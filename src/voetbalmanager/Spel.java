package voetbalmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import voetbalmanager.model.Competitie;

public class Spel {
	
	public static Spel huidigSpel;
	
	public Competitie huidigeCompetitie;
	public String spelnaam;
	
	private Spel(Competitie competitie, String naam)
	{
		this.huidigeCompetitie = competitie;
		this.spelnaam = naam;
	}
	
	public static void main(String[] args) {
		// TODO Start spel op.
	}
	
	public static void laadSpel(Spel spel)
	{
		// TODO Laad een spel in.
		Spel.huidigSpel = spel;
	}
	
	public static void saveHuidigSpel()
	{
		// TODO Sla huidig spel op
	}
	
	public static List<Spel> getSpellenLijst()
	{
		File[] saves = new File("./saves/").listFiles();
		// TODO Controle of de folder bestaat.
		ArrayList<Spel> res = new ArrayList<Spel>();
		
		for(File save: saves) {
			try {
				Document doc = XMLLoader.getDocument(save);
				
				res.add(Spel.uitXML((Element) doc.getElementsByTagName("spel").item(0)));
				// TODO Laad spel.
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		// TODO geef een lijst met savefiles.
		return res;
	}
	
	public static Spel uitXML(Element el){
		String naam = el.getAttribute("naam");
		Competitie competitie = Competitie.laadXMLElement(((Element) el.getElementsByTagName("competitie").item(0)));
		
		return new Spel(competitie, naam);
	}
}
