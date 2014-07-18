package xxgamehelper.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/***
 * Some tools to validate, initialize and getChildNode from a xml file.
 * @author LongFangzhou
 *
 */
public class XmlTools {
	
	/***
	 * The method to validate xml files using a schema.
	 * @param xsdPath Schema file path
	 * @param xmlPath XML file path
	 * @return True if the xml file passed validation, otherwise false.
	 */
	public static boolean validateXml(String xsdPath,String xmlPath) {
		return XmlTools.validateXml(new File(xsdPath), new File(xmlPath));
	}
	
	/***
	 * The method to validate xml files using a schema.
	 * @param xsdSchema Schema file
	 * @param xmlData Xml file
	 * @return True if the xml file passed validation, otherwise false.
	 */
	public static boolean validateXml(File xsdSchema,File xmlData) {
		SchemaFactory schemaFactory = SchemaFactory
                .newInstance("http://www.w3.org/2001/XMLSchema");
		Schema schema = null;
        try {
            schema = schemaFactory.newSchema(xsdSchema);
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        }
        
        Validator validator = schema.newValidator();
 
        try {
            Source source = new StreamSource(new FileInputStream(xmlData));
            validator.validate(source);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
        return true;
	}
	
	/***
	 * The method to inialize a xml file.
	 * @param configPath XML file path
	 * @return The document of xml file
	 */
	public static Document initDocument(String configPath) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = factory.newDocumentBuilder();
			return db.parse(new File(configPath));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/***
	 * Search a child node with a name key.
	 * @param fatherNode Target node 
	 * @param name Search key
	 * @return the child node if found, otherwise null.
	 */
	public static Node getChildNodeByName(Node fatherNode, String name) {
		NodeList nodes = fatherNode.getChildNodes();
		for (int i=0;i<nodes.getLength();i++){
			if (nodes.item(i) instanceof Element)
				if (nodes.item(i).getNodeName().equals(name))
					return nodes.item(i);
		}
		return null;
	}
	
}
