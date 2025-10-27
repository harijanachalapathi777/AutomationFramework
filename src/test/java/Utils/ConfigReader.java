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

	public static String get(String key) {
		return prop.getProperty(key);
	}

	public static boolean getBoolean(String key) {
		return Boolean.parseBoolean(prop.getProperty(key));
	}

}
