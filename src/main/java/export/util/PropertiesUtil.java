package export.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	private static Properties prop;
	
	static {
		prop = new Properties();
		
		try {
			InputStream input = new FileInputStream("config.properties");
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getDbPath() {
		return prop.getProperty("db.path");
	}
	
	public static String getSeparatorPath() {
		return prop.getProperty("separator.path");
	}
	
	public static String getCsvPath() {
		return prop.getProperty("files.csv.path");
	}
	
	public static String getImagePath() {
		return prop.getProperty("files.image.path");
	}
	
	public static String getNameFileTagsCsv() {
		return getCsvPath() + "/" + prop.getProperty("file.csv.tags");
	}
	
	public static String getNameFileCategoriesCsv() {
		return getCsvPath() + "/" + prop.getProperty("file.csv.categories");
	}
	
	public static String getNameFileBanksCsv() {
		return getCsvPath() + "/" + prop.getProperty("file.csv.banks");
	}
	
	public static String getNameFileTradesCsv() {
		return getCsvPath() + "/" + prop.getProperty("file.csv.trades");
	}
	
	public static String getNameFileCategoriesBanksCsv() {
		return getCsvPath() + "/" + prop.getProperty("file.csv.categories.banks");
	}
}
