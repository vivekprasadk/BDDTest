package util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author vivekprasadk
 * Methods to work on the config files
 *
 */
public class ConfigFileReader {

	Properties configFile;
	InputStream inputStream;

	/**
	 * Method to read config file
	 */
	public ConfigFileReader() {
		try {
			configFile = new Properties();
			String propertyFile = "Config.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propertyFile);

			if (inputStream != null) {
				configFile.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propertyFile + "' not found in the classpath");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

	}

	/**
	 * Return the property value as per the key given from the config file
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return this.configFile.getProperty(key);
	}

	public String getReportConfigPath() {
		String reportConfigPath = System.getProperty("user.dir") + configFile.getProperty("reportConfigPath");
		if (reportConfigPath != null)
			return reportConfigPath;
		else
			throw new RuntimeException(
					"Report Config Path not specified in the Config.properties file for the Key:reportConfigPath");
	}

}
