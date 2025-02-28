package org.yahoofinance.teslastock.pages;

import org.testng.Assert;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.yahoofinance.teslastock.support.Utils;
import org.openqa.selenium.support.ui.LoadableComponent;


public class HomePage extends LoadableComponent<HomePage> {
	
	private WebDriver driver;
	public boolean isLoadedProperly;
	
	@FindBy(css = "#ybar-search")
	WebElement loadingElem;
	
	@FindBy(css = "input.finsrch-inpt.inpt-placeholder-secondary")
	WebElement searchBox;
	
	@FindBy(xpath = "//div[@id='header-quote-lookup'] //h3[contains(text(),'Trending Tickers')]")
	WebElement TrendingNowSection;
	
	@FindBy(xpath = "//div[@id='header-quote-lookup'] //h3[contains(text(),'Symbols')]")
	WebElement SearchSection;
	
	@FindBy(xpath = "//ul[contains(@class, 'modules-module_list_')]/li")
	List<WebElement> listSelectableItems;
	
	public HomePage(WebDriver driver, String url) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
		driver.get(url);
		//Utils.waitForPageLoad(driver);
	}
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		isLoadedProperly = true;
		if(!(Utils.waitForElement(driver, loadingElem))) {
			isLoadedProperly = false;
			Assert.fail("Home page is not loaded properly!!");
		} else {
			System.out.println("Inside else --> Home page loaded");
			Assert.assertTrue(isLoadedProperly, "Home page is loaded properly!!");
		}
	}
	
	/**
	 * To Enter value in the stock search text box
	 * @param value - value to be entered
	 * @throws Exception - Exception
	 */
	public void enterValueInSearch(String value) throws Exception {
		searchBox.click();
		Utils.waitForElement(driver, TrendingNowSection);
		CharSequence ch1 = value;
		searchBox.sendKeys(Keys.chord(ch1));
		Utils.waitForElement(driver, SearchSection);
	}
	
	/**
	 * To click on desired element based on index from the list of suggestion based on our input
	 * @param index - Index value needed to be selected
	 * @return TeslaStockPage - TeslaStock Page 
	 * @throws Exception - Exception
	 */
	public TeslaStockPage selectSearchListItemByIndex(int index) throws Exception {
		Utils.waitForElement(driver, SearchSection);
		listSelectableItems.get(index).click();
		Utils.waitForPageLoad(driver);
		return new TeslaStockPage(driver).get();
	}
}
