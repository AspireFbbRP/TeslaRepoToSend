package org.yahoofinance.teslastock.pages;


import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.yahoofinance.teslastock.support.Log;
import org.yahoofinance.teslastock.support.Utils;

public class TeslaStockPage extends LoadableComponent<TeslaStockPage> {
	private WebDriver driver;
	
	@FindBy(xpath = "//section[contains(@class,'paddingRight')]")
	WebElement TeslaPageLoaded;
	
	@FindBy(xpath = "//section[@data-testid='quote-price']//span")
	WebElement TeslaPagePrice;
	
	@FindBy(xpath = "//div[@data-testid='quote-statistics']/ul/li//span[contains(@class,'label')]")
	List<WebElement> TeslaStatisticsLabel;
	
	@FindBy(xpath = "//div[@data-testid='quote-statistics']/ul/li//span[contains(@class,'value')]")
	List<WebElement> TeslaStatisticsValue;
	
	@FindBy(xpath = "//div[@data-testid='quote-statistics']/ul/li")
	WebElement teslaInfo;
	
	public TeslaStockPage(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}

	@Override
	protected void load() {
		
	}

	@Override
	protected void isLoaded() {
		if(!Utils.waitForElement(driver, TeslaPageLoaded)) {
			try {
				Log.fail("Tesla Page Not Loaded!!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	 /**
	  * To get Tesla stock value
	  * @return value - stock value in float
	  * @throws Exception - Exception
	  */
	public Float getTeslaStockValue() throws Exception {
		Utils.scrollToElement(TeslaPagePrice, driver);
		String stockValue = TeslaPagePrice.getText();
		return Float.parseFloat(stockValue);
	}
	
	/**
	 * To get all the tesla stock information and store in to hashmap
	 * @return HashMap<String, String> - Stock Information
	 * @throws Exception - Exception
	 */
	public HashMap<String, String> getAllTeslaStockInfo() throws Exception {
		HashMap<String, String> teslaInfo = new HashMap<String, String>();
		int teslaStat = TeslaStatisticsValue.size();
		for (int i=0; i<teslaStat; i++) {
			WebElement label = TeslaStatisticsLabel.get(i);
			String Label = label.getText();
			WebElement value = TeslaStatisticsValue.get(i);
			String Value = value.getText();
			teslaInfo.put(Label, Value);
		}
		return teslaInfo;
	}
	
	/**
	 * To Print all the stock information of Tesla stock
	 * @param teslaInfo - HashMap<String, String> information of tesla stock 
	 * @throws Exception - Exception
	 */
	public void printInfo(HashMap<String, String> teslaInfo) throws Exception {
		for (String name: teslaInfo.keySet()) {
		    String key = name.toString();
		    String value = teslaInfo.get(name).toString();
		    Log.message(key + " <<<--->>> " + value);
		}
	}
	
	/**
	 * To get desired Stock Info from Tesla Stock Page
	 * @param info - Needed Stock Info Label
	 * @return String - Stock Value 
	 * @throws Exception - Exception
	 */
	public String getDesiredTeslaStockInfo(String info) throws Exception {
		WebElement elem = teslaInfo.findElement(By.xpath("//span[text()='"+info+"']/ancestor::li/span[contains(@class,'value')]"));
		Utils.scrollToElement(elem, driver);
		String Value = elem.getText();
		return Value;
	}
	
}
