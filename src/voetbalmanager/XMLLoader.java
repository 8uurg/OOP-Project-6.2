package voetbalmanager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import voetbalmanager.exceptions.TransferException;
import voetbalmanager.model.Competitie;
import voetbalmanager.model.Team;

/**
 * Hulpklasse om XML Bestanden in te laden.
 */
public class XMLLoader {
	/*
	public static void main(String[] args) 
	{
		// DEMO CODE
		
		Team inladen = laadTeam(ClassLoader.getSystemClassLoader().getResourceAsStream("test.xml"));
		
		System.out.println(inladen.toString());
	}
	
	/**
	 * Laad een team in uit een inputstream.
	 * @param in	De inputstream die ingeladen moet worden
	 * @return		Het ingeladen team
	 * @throws TransferException 
	 */
	/*// Let op. Demomethode
	private static Team laadTeam(InputStream in) {
		try {
			DocumentBuilderFactory docbuilderf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docb = docbuilderf.newDocumentBuilder();
			
			Document doc = docb.parse(in);
			doc.getDocumentElement().normalize();
			
			NodeList teams = doc.getElementsByTagName("teams");
			Element teamsel = (Element) teams.item(0);
			
			Element teamel = (Element) teamsel.getElementsByTagName("team").item(0);
			
			return Team.laadXMLElement(teamel);
			
		} catch (ParserConfigurationException e) {
			// Dit zou niet moeten gebeuren.
		} catch(IOException e) {
			// TODO
		} catch(SAXException e) {
			// ?
		}
		return null; 
	}
	*/
	
	/**
	 * Laad een competitie uit een savebestand.
	 * @param in	Het bestand om in te laden.
	 * @return Een ingeladen competitie
	 */
	public static Competitie laadCompetitie(InputSource in)
	{
		Document doc = XMLLoader.getDocument(in);
		
		return Competitie.laadXMLElement((Element) doc.getElementsByTagName("competitie").item(0));
	}
	
	/**
	 * Creeer een nieuwe competitie vanuit een beginsituatie.
	 * @param competitie	De competitie die ingeladen moet worden.
	 * @return Een nieuwe competitie
	 */
	public static Competitie creeerCompetitie(String competitie) {
		Document doc = XMLLoader.getDocument(new InputSource(XMLLoader.class.getResourceAsStream("/competities/" + competitie + ".xml")));
		
		return Competitie.laadXMLElement((Element) doc.getElementsByTagName("competitie").item(0));
	}
	
	/**
	 * Lees een XML File uit.
	 * @param in
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Document getDocument(File in) throws FileNotFoundException{
		return XMLLoader.getDocument(new InputSource(new InputStreamReader(new FileInputStream(in))));
	}
	
	/**
	 * Lees een document uit een inputsource.
	 * @param in	Invoer om gegevens uit te lezen.
	 * @return
	 */
	public static Document getDocument(InputSource in){		
		try {
			DocumentBuilderFactory docbuilderf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docb = docbuilderf.newDocumentBuilder();
			return docb.parse(in);
		} catch (SAXException e) {
			// TODO Parseprobleem... Invalide bestand.
			throw new RuntimeException(e);
		} catch (IOException e) {
			// TODO IOException, bestand bestaat niet of doet iets anders.
			throw new RuntimeException(e);
		} catch (ParserConfigurationException e) {
			// Configuratie zou moeten werken
			throw new RuntimeException(e);
		}
	}
	
	// Hulpmiddelen.
	/**
	 * Geeft de waarde van het eerste element met een bepaalde tag in een bepaald element.
	 * @param tag	De tag die opgezocht moet worden
	 * @param el	Het element waarin gezocht moet worden
	 * @return		De waarde van de eerste tag.
	 */
	public static String getTaggedString(String tag, Element el)
	{
		NodeList match = el.getElementsByTagName(tag);
		
		if(match!=null && match.getLength()>0)
			return ((Element) match.item(0)).getFirstChild().getNodeValue().trim();
		
		return ""; // TODO Temporary, throw error on missing node.
	}
	
	/**
	 * Geeft de waarde van het eerste element met een bepaalde tag in een bepaald element als een integer.
	 * @param tag	De tag die opgezocht moet worden
	 * @param el	Het element waarin gezocht moet worden
	 * @return		De waarde van de eerste tag als integer.
	 */
	public static int getTaggedInt(String tag, Element el)
	{
		return Integer.parseInt(getTaggedString(tag, el));
	}
}
