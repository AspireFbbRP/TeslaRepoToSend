package org.yahoofinance.teslastock.support;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

public class ReadingProperties {
	
	public static Properties prop = new Properties();
	
	public static FileInputStream fis;
	
	protected static int maxPageLoadWait;

	protected static int minElementWait;
	
	protected static int maxElementWait;
	
	protected static int maxImageLoadWait;
	
	public static HashMap<String, String> readFile(String fileName) throws Exception {
		String Directory = System.getProperty("user.dir");
		fis = new FileInputStream(Directory+"\\src\\main\\resources\\"+fileName+".properties");
		prop.load(fis);
		HashMap<String, String> testdata = new HashMap<String, String>();
		for (Object keys: prop.keySet()) {
			testdata.put((String) keys, prop.getProperty((String) keys));
		}
		return testdata;
	}
	
	public static void settingProperties(HashMap<String, String> config) throws Exception {
		setMaxPageLoadWait(config);
		setMinElementWait(config);
		setMaxElementWait(config);
		setMaxImageLoadWait(config);
	}
	
	public static void setMaxPageLoadWait(HashMap<String, String> config) {
		maxPageLoadWait = Integer.parseInt(config.get("maxPageLoadWait"));
	}
	
	public static void setMinElementWait(HashMap<String, String> config) {
		minElementWait = Integer.parseInt(config.get("minElementWait"));
	}

	public static void setMaxElementWait(HashMap<String, String> config) {
		maxElementWait = Integer.parseInt(config.get("maxElementWait"));
	}

	public static void setMaxImageLoadWait(HashMap<String, String> config) {
		maxImageLoadWait = Integer.parseInt(config.get("maxImageLoadWait"));
	}
	
	public static int getMinElementWait() {
		return minElementWait;
	}
	
	public static int getMaxElementWait() {
		return maxElementWait;
	}
	
	public static int getMaxImageLoadWait() {
		return maxImageLoadWait;
	}
	
	public static int getMaxPageLoadWait() {
		return maxPageLoadWait;
	}
}
