package org.yahoofinance.teslastock.support;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

public class Log {
	static String HTML_BEGIN = "<div class=\\\"test-message\\\">&emsp;"; 
	static String HTML_END = "</div>"; 
	static Logger logs = LogManager.getLogger(Log.class);
	
	static SoftAssert softAssert = new SoftAssert();
	
	public static void message(String description, WebDriver driver) throws Exception{
		String screenShotPath = Utils.takeScreenShot(driver);
		logs.info(description);
		Reporter.log(description + " --> <a target=\"_blank\" href="+screenShotPath+">Screenshot</a>");
	}
	
	public static void message(String description) throws Exception{
		logs.info(description);
		Reporter.log(description);
	}
	
	public static void validateTest() {
		softAssert.assertAll();
	}
	
	public static void softAssertThat(boolean condition, String Expected, String Actual, WebDriver driver) throws Exception {
		if(condition) {
			message("Expected Result : " + Expected);
			message("Actual Result : " + Actual, driver);
		} else {
			message("Expected Result : " + Expected);
			failSoft("Actual Result : " + Actual, driver);
		}
	}
	
	public static void failSoft(String description, WebDriver driver) throws Exception {
		String screenShotPath = Utils.takeScreenShot(driver);
		softAssert.fail(description);
		Reporter.log(description + " --> <a target=\"_blank\" href="+screenShotPath+">Screenshot</a>");
	}
	
	public static void fail(String description) throws Exception {
		Assert.fail(description);
	}
}
