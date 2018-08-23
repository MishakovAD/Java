package Property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class Property {
	private static Properties props;

	static {
		props = new Properties();
		try {
			System.out.println("Start get property");
			Property util = new Property();
			//props = util.getProperties("C:/niikp/server-config.properties"); //server
			//props = util.getProperties("E:/JavaProjectDocs/local-config.properties");
			props = util.getProperties("C:/niikp/config-for-diff-export.properties");
			System.out.println("db.url " + props.getProperty("db.url"));
			System.out.println("db.login " + props.getProperty("db.login"));
			System.out.println("db.password " + props.getProperty("db.password"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private Property() {
	}

	public static String getProperty(String key) {
		return props.getProperty(key);
	}

	public static Set<Object> getkeys() {
		return props.keySet();
	}

	public static Properties getProperties() {
		return props;
	}

	private Properties getPropertiesFromClasspath(String propFileName) throws IOException {
		Properties props = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = this.getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream == null) {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			props.load(inputStream);
		} finally {
			inputStream.close();
		}
		return props;
	}
	
	private Properties getProperties(String propFileName) throws IOException {
		Properties props = new Properties();
		FileInputStream fis = null;
			fis = new FileInputStream(propFileName);
			props.load(fis);

		return props;
	}
}
