import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class XMLLoader {
	
	public static void main(String[] args)
	{
		// DEMO CODE
	}
	
	/**
	 * Laad een team in uit een inputstream.
	 * @param in	De inputstream die ingeladen moet worden
	 * @return		Het ingeladen team
	 */
	// Let op. Demomethode
	public Team laadTeam(InputStream in){
		try {
			DocumentBuilderFactory docbuilderf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docb = docbuilderf.newDocumentBuilder();
			
			Document doc = docb.parse(in);
			doc.getDocumentElement().normalize();
			NodeList teams = doc.getElementsByTagName("teams");
			Element teamel = (Element) teams.item(0);
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
			return el.getFirstChild().getNodeValue();
		
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
