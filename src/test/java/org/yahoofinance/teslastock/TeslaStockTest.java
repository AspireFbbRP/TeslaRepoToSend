package org.yahoofinance.teslastock;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.yahoofinance.teslastock.pages.HomePage;
import org.yahoofinance.teslastock.pages.TeslaStockPage;
import org.yahoofinance.teslastock.support.BaseTest;
import org.yahoofinance.teslastock.support.Log;

public class TeslaStockTest extends BaseTest{
	  @Test
	  public void TeslaStock() throws Exception {
		  int i=0;
		  WebDriver driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  String url = testdata.get("URL");
		  String DataToSearch = testdata.get("DataToSearch");
		  Float StockPrice = Float.parseFloat(testdata.get("StockPrice"));
		  try {
			  HomePage homePage = new HomePage(driver, url).get();
			  homePage.enterValueInSearch(DataToSearch);
			  Log.message(++i + ". Enter TSLA in search box", driver);
			  
			  TeslaStockPage teslaPage = homePage.selectSearchListItemByIndex(0);
			  Log.message(++i + ". Selected Tesla in the list and Navigated to Tesla Stock page!", driver);
			  
			  Float teslaStockPrice = teslaPage.getTeslaStockValue();
			  Log.message(++i + ". Got Tesla Stock value! : " +teslaStockPrice, driver);
			  
			  Log.softAssertThat(teslaStockPrice > StockPrice,
					  "Stock Price should be greater than 200!",
					  "Stock Price is greater than 200.00 : " + teslaStockPrice, driver);
			  
			  //HashMap<String, String> teslaStockInfo = teslaPage.getAllTeslaStockInfo();
			  
			  //teslaPage.printInfo(teslaStockInfo);
			  
			  String prevClose = teslaPage.getDesiredTeslaStockInfo("Previous Close");
			  String volume = teslaPage.getDesiredTeslaStockInfo("Volume");
			  
			  Log.message(++i + ". Tesla Stock Info for Previous Close : "+prevClose, driver);
			  
			  Log.message(++i + ". Tesla Stock Info for Volume : "+volume, driver);
			  
		  } catch (Exception e) {
			  e.printStackTrace();
		  } finally {
			  driver.quit();
		  }
	  }
}
