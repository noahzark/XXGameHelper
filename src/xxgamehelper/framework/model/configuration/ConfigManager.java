package xxgamehelper.framework.model.configuration;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import xxgamehelper.framework.control.messenger.MessengerData;
import xxgamehelper.framework.utils.XmlTools;

public class ConfigManager {
	
	public static HelperConfig loadConfig(String configPath, MessengerData messenger) {
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
		
		if (!helperConfig.helperName.equals(messenger.getAPPName()))
			return null;
		
		Node modeNode = XmlTools.getChildNodeByName(
				rootElement, "ModeConfig");
		boolean debug = XmlTools.getChildNodeByName(modeNode, "DebugMode")
				.getFirstChild().getNodeValue().contains("rue");
		boolean beta = XmlTools.getChildNodeByName(modeNode, "BetaMode")
				.getFirstChild().getNodeValue().contains("rue");
		helperConfig.mode = new ModeConfig(debug, beta);
		
		Node gameNode = XmlTools.getChildNodeByName(
				rootElement, "GameConfig");
		
		//TODO Load game configs
		return helperConfig;
	}
	
}
