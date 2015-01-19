package voetbalmanager;
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

import voetbalmanager.exceptions.TransferException;
import voetbalmanager.model.Competitie;
import voetbalmanager.model.Speler;
import voetbalmanager.model.Team;

/**
 * Hulpklasse om een XML Bestand in te laden
 */
public class XMLWriter {
	/*
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
	*/
	/**
	 * Methode om competitie weg te schrijven naar een uitvoer.
	 * @param competitie	Competitie die weggeschreven moet worden 
	 * @param locatie		Plek waar de competitie naar weggeschreven moet worden.
	 */
	public static void saveCompetitie(Competitie competitie, StreamResult locatie) {
		Document doc = XMLWriter.getDocument();
		
		doc.appendChild(competitie.getXMLElement(doc));
		
		XMLWriter.writeDOM(doc, locatie);		
	}
	
	/**
	 * Simpele methode om competitie op te slaan.
	 * @param competitie
	 */
	public static void saveCompetitie(Competitie competitie) {
		File loc = new File("saves/" + competitie.getNaam() + ".xml");
		loc.mkdirs();
		XMLWriter.saveCompetitie(competitie, new StreamResult(loc));
	}
	
	/**
	 * Vraag een leeg document op om de gegevens in op te slaan.
	 * @return Een leeg document.
	 */
	public static Document getDocument() {
		try {
			DocumentBuilder docbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			return docbuilder.newDocument();
		} catch (ParserConfigurationException e) {
			// Configuration should be okay in any case.
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Sla een document (DOM) op in een streamresult
	 * @param doc	Document om op te slaan
	 * @param out	Uitvoer
	 */
	public static void writeDOM(Document doc, StreamResult out) {
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource doms = new DOMSource(doc);
			
			transformer.transform(doms, out);
		} catch (TransformerConfigurationException e) {
			// Configuratie zou correct moeten zijn.
			throw new RuntimeException(e);
		} catch (TransformerFactoryConfigurationError e) {
			// Configuratie van de factory zou ook correct moeten zijn.
			throw new RuntimeException(e);
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
