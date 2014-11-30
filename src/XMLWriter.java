import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import exceptions.TransferException;


/**
 * Hulpklasse om een XML Bestand in te laden
 */
public class XMLWriter {

	public static void main(String[] args)
	{
		try {
			DocumentBuilder docbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = docbuilder.newDocument();
			
			// Start DEMO Code.
			ArrayList<Team> teams = new ArrayList<Team>();
			
			try {
				Team a = new Team("FF");
				a.voegToe(new Speler("John Doe", 12, 120000, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 70, 20, 60), 0);
				a.voegToe(new Speler("James Doe", 12, 120000, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 70, 20, 60), 0);
				
				Team b = new Team("VV");
				b.voegToe(new Speler("Jeffrey Dest", 12, 120000, Speler.Status.Beschikbaar, Speler.Type.Aanvaller, 70, 20, 60), 0);
				b.voegToe(new Speler("John Dös", 12, 120000, Speler.Status.Beschikbaar, Speler.Type.Verdediger, 30, 70, 60), 0);
				
				teams.add(a);
				teams.add(b);
			} catch(TransferException e) {
			}
			
			
			Element teamstag = doc.createElement("teams");
			
			for(Team team: teams)
				teamstag.appendChild(team.getXMLElement(doc));
			
			doc.appendChild(teamstag);
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource doms = new DOMSource(doc);
			
			// TODO vervang out.xml
			transformer.transform(doms, new StreamResult(new File("./out.xml")));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Creeërt een element met een tagname, een bepaalde waarde (content).
	 * @param tagName	Naam van het element.
	 * @param content	Inhoud van het element.
	 * @param doc		Document object om een Element mee te maken.
	 * @return	Een element met een specifieke naam en inhoud.
	 */
	public static Element getElementContainingInt(String tagName, int content, Document doc)
	{
		return getElementContainingString(tagName, Integer.toString(content), doc);
	}
	
	/**
	 * Creeërt een element met een tagname, een bepaalde string (content).
	 * @param tagName	Naam van het element.
	 * @param content	Inhoud van het element.
	 * @param doc		Document object om een Element mee te maken.
	 * @return	Een element met een specifieke naam en inhoud.
	 */
	public static Element getElementContainingString(String tagName, String content, Document doc)
	{
		Element el = doc.createElement(tagName);
		
		el.appendChild(doc.createTextNode(content));
		
		return el;
	}
}
