package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop;
	static {
		try {
			String path = "C:\\Users\\Dell\\git\\repository\\AutomationFramework\\src\\test\\resources\\config.properties";
			FileInputStream file = new FileInputStream(path);
			prop = new Properties();
			prop.load(file);
			file.close();
		} catch (IOException e) {
			throw new RuntimeException("failed to load proprty file", e);
		}
	}

	// to read data from pipeline first then mavn prop then finally from config file
	public static String get(String key) {
		// priority : env variable(from pipeline) -> Maven Property -> Config file
		String env = System.getenv(key);
		if (env != null)
			return env;

		String sysProp = System.getProperty(key);
		if (env != null)
			return env;

		return prop.getProperty(key);
	}

	public static boolean getBoolean(String key) {
		return Boolean.parseBoolean(prop.getProperty(key));
	}

}
