package xxgamehelper.framework.model.configuration;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import xxgamehelper.framework.utils.XmlTools;

public class ConfigManager {
	
	public static HelperConfig loadConfig(String configPath) {
		Document doc = XmlTools.initDocument(configPath);
		if (doc==null || !XmlTools.validateXml(new File("./HelperConfiguration.xsd"), new File(configPath)))
			return null;
		
		Element rootElement = doc.getDocumentElement();
		HelperConfig helperConfig = new HelperConfig(
				rootElement.getAttribute("HelperName"),
				rootElement.getAttribute("ConfigName"),
				rootElement.getAttribute("Author")
		);
		
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
