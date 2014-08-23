package xxgamehelper.framework.model.configuration;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xxgamehelper.framework.utils.XmlTools;

public class ConfigManager {
	
	/***
	 * The method to load some configurations to a messenger.
	 * @param configPath The xml configuration file path
	 * @param messenger The helper messenger
	 * @return 
	 */
	public static HelperConfig loadConfig(String configPath, String appName) {
		Document doc = XmlTools.initDocument(configPath);
		if (doc==null
//TODO Use a better validate path
//				|| !XmlTools.validateXml("./HelperConfiguration.xsd", configPath)
			)
			return null;
		
		Element rootElement = doc.getDocumentElement();
		HelperConfig helperConfig = new HelperConfig(
				rootElement.getAttribute("HelperName"),
				rootElement.getAttribute("ConfigName"),
				rootElement.getAttribute("Author")
		);
		
		if (!helperConfig.helperName.equals(appName))
			return null;
		
		Node modeNode = XmlTools.getChildNodeByName(
				rootElement, "ModeConfig");
		String workFolder = XmlTools.getChildNodeByName(modeNode, "WorkFolder")
				.getFirstChild().getNodeValue();
		boolean debug = XmlTools.getChildNodeByName(modeNode, "DebugMode")
				.getFirstChild().getNodeValue().contains("rue");
		boolean beta = XmlTools.getChildNodeByName(modeNode, "BetaMode")
				.getFirstChild().getNodeValue().contains("rue");
		helperConfig.mode = new WorkConfig(workFolder, debug, beta);
		
		Node gameNode = XmlTools.getChildNodeByName(
				rootElement, "GameConfig");
		
		NodeList childNodes = gameNode.getChildNodes();
		for(int j=0;j<childNodes.getLength();j++){
			Node childNode=childNodes.item(j);
			if (childNode instanceof Element) {
				Node nodeName = XmlTools.getChildNodeByName(childNode, "Name");
				Node nodeValue = XmlTools.getChildNodeByName(childNode, "Value");
				if (nodeName!=null && nodeValue!=null) {
					String key = nodeName.getFirstChild().getNodeValue();
					String value = nodeValue.getFirstChild().getNodeValue();
					if (childNode.getNodeName().equals("StrConfig"))
						helperConfig.game.strConfig.setConfig(key, value);
					else if (childNode.getNodeName().equals("NumConfig"))
						helperConfig.game.numConfig.setConfig(key, Integer.parseInt(value));
					else if (childNode.getNodeName().equals("FloatConfig"))
						helperConfig.game.floatConfig.setConfig(key, Float.parseFloat(value));
				}
			}
		}
		return helperConfig;
	}
	
}
