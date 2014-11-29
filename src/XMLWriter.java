import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Hulpklasse om een XML Bestand in te laden
 */
public class XMLWriter {

	public static void main(String[] args)
	{
		try {
			DocumentBuilder docbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = docbuilder.newDocument();
			
			
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
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
