package org.yahoofinance.teslastock.support;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.util.HashMap;

public class BaseTest {
	
	public static HashMap<String,String> testdata;
	public static HashMap<String,String> config;
	
	@BeforeSuite(alwaysRun = true)
	public static void initialSuiteSetup() throws Exception {
		cleanUpScreeshot();
	}
	
	@BeforeTest(alwaysRun = true)
	public static void initialTestSetup() throws Exception {
			testdata = ReadingProperties.readFile("data");
			ReadingProperties.settingProperties(ReadingProperties.readFile("config"));
	}
	
	@AfterTest
	public static void endTest() {
		Log.validateTest();
	}
	
	public static void cleanUpScreeshot() throws Exception {
		String Directory = System.getProperty("user.dir");
		String screenShotFolder = Directory + "\\test-output\\screenshot\\";
		File fileDir = new File(screenShotFolder);
		File[] listOfFiles = fileDir.listFiles();
		for (File file: listOfFiles) {
			file.delete();
		}
	}
}
