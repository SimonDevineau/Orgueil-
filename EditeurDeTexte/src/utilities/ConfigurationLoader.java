package utilities;

import java.io.File;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A configuration reader to read the xml configuration and put all the key
 * inside the map which is going to store them.
 * 
 * @author Pierre Reliquet
 * 
 */
public class ConfigurationLoader {

	// ////////////
	// Constants //
	// ////////////
	/**
	 * The default config filenam : App.config.xml Default Format: <config
	 * key="XXX" value="YYY" />
	 */
	public static final String[] DEFAULT_CONFIG_NAMES = { "App.config.xml",
			"App.config", "App.xml", "Settings.xml" };

	/**
	 * The default config tag <config Default Format: <config key="XXX"
	 * value="YYY" />
	 */
	public static final String DEFAULT_CONFIG_TAG = "config";

	/**
	 * The default config key attribute: Default Format: <config key="XXX"
	 * value="YYY" />
	 */
	public static final String DEFAULT_CONFIG_KEY = "key";
	/**
	 * The default config value attribute: Default Format: <config key="XXX"
	 * value="YYY" />
	 */
	public static final String DEFAULT_CONFIG_VALUE = "value";

	// ///////////////////
	// End of constants //
	// ///////////////////

	// //////////////
	// Private API //
	// //////////////

	private static void UpdateConfiguration(Map<String, String> configuration,
			String path, String tagName, String keyName, String valueName) {
		File config = new File(path);
		if (config.exists()) {
			try {
				// Get the XML document
				Document configXML = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder().parse(config);
				// Get the Xpath member to create the expression
				XPath xpath = XPathFactory.newInstance().newXPath();
				// Get the XPath expression
				XPathExpression expression = xpath.compile("//" + tagName);
				// Get all the corresponding nodes
				NodeList childs = (NodeList) expression.evaluate(configXML,
						XPathConstants.NODESET);
				// Adding the key inside the configuration map.
				// If the key already exists an exception is thrown.
				for (int i = 0; i < childs.getLength(); i++) {
					Node node = childs.item(i);
					if (node.getNodeName() == tagName && node.hasAttributes()) {
						String key = node.getAttributes().getNamedItem(keyName)
								.getNodeValue();
						String value = node.getAttributes()
								.getNamedItem(valueName).getNodeValue();
						if (configuration.containsKey(key))
							Logger.error("The key "
									+ key
									+ " exists already in the configuration files!");
						else
							configuration.put(key, value);
					}
				}
				Logger.info("Configuration loaded from " + path);

			} catch (Exception e) {
				Logger.error(
						"An error occured while reading the configuration file: "
								+ path, e);
			}
		} else {
			Logger.info(path
					+ " cannot be found; the configuration will not be modified.");
		}
	}

	// /////////////////////
	// End of private API //
	// /////////////////////

	// /////////////
	// Public API //
	// /////////////

	/**
	 * The method called if the user wants to load another configuration file
	 * with a specific format. The format should be: <configTag configKey="XX"
	 * configValue="YY" />
	 * 
	 * @param path
	 *            , the path towards the file
	 * @param configTag
	 *            , the name of the tag
	 * @param configKey
	 *            , the name of the key attribute
	 * @param configValue
	 *            , the name of the value
	 */
	public static void LoadConfigFile(Map<String, String> configuration, String path,
			String configTag, String configKey, String configValue) {
		UpdateConfiguration(configuration, path, configTag, configKey,
				configValue);
	}

	// ////////////////////
	// End of Public API //
	// ////////////////////
}