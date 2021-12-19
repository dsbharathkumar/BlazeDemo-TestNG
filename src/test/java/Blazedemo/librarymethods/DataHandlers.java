package Blazedemo.librarymethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DataHandlers {

	public static String getProperty(String fileName, String key) throws IOException {
		File f = new File(fileName);
		Properties prop = null;
		String value = null;
		try {
			FileInputStream fis = new FileInputStream(f);
			prop = new Properties();
			prop.load(fis);
			value = prop.getProperty(key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return value;
	}
	
}
