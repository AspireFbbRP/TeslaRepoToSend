package org.yahoofinance.teslastock.support;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.util.HashMap;

public class BaseTest {
	
	public static HashMap<String,String> testdata;
	public static HashMap<String,String> config;
	
	@BeforeTest(alwaysRun = true)
	public static void initialSetup() throws Exception {
			testdata = ReadingProperties.readFile("data");
			ReadingProperties.settingProperties(ReadingProperties.readFile("config"));
	}
	
	@AfterTest
	public static void endTest() {
		Log.validateTest();
	}
}
