package org.yahoofinance.teslastock.support;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.io.Files;

public class Utils {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean waitForElement(WebDriver driver, WebElement elem) {
		int minWait = ReadingProperties.getMinElementWait();
		try {
			FluentWait wait = new FluentWait(driver);
			wait.withTimeout(Duration.ofSeconds(minWait));
			wait.pollingEvery(Duration.ofMillis(500));
			wait.ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.elementToBeClickable(elem));
			
			if(elem.isDisplayed()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean waitForPageLoad(WebDriver driver) {
		int pageWait = ReadingProperties.getMaxPageLoadWait();
		FluentWait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(pageWait)).pollingEvery(Duration.ofMillis(500))
				.ignoring(StaleElementReferenceException.class)
				.withMessage("Page Load Timed Out");
				
		String title = driver.getTitle();
		String url = driver.getCurrentUrl();
		
		if ("the page cannot be found".equalsIgnoreCase(title)
				|| title.contains("is not available")
				|| url.contains("/error/")
				|| url.toLowerCase().contains("/errorpage/")) {
			Assert.fail("Site is down. [Title: " + title + ", URL:" + url + "]");
			return false;
		} else {
			return true;
		}
	}
	
	public static String takeScreenShot(WebDriver driver) throws Exception {
		File f;
		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String formattedDate = sdf.format(currentDate).replaceAll("[^a-zA-Z0-9]", "");
		String Directory = System.getProperty("user.dir");
		String screenShotPath = Directory + "\\test-output\\screenshot\\"+formattedDate+".png";
		f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File newFile = new File(screenShotPath);
		Files.copy(f, newFile);
		return screenShotPath;
	}
	
}
