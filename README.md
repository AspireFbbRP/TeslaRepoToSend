# Tesla Stock Project

## ---------------------------------------------
## Tool : Selenium-Webdriver (Version - 4.28.1)
## Programming Language: JAVA (Version - 17) 
## Build Tool: MAVEN
## Framework: TESTNG
## Model: PageObjectModel
## ---------------------------------------------


### Information

* Navigating to finance.yahoo.com and Search for "TSLA" for Tesla Stock
* Navigating to Tesla Stock project
* Check the Tesla Stock Price is greater than $200
* Get Additional Information like Previous Close and Volume of the stock and Print it.

## SRC/MAIN/JAVA/

#### This contains two Packages as follows, 

### ORG.YAHOOFINANCE.TESLASTOCK.PAGES
In Pages Package we have HomePage Class and TeslaStockPage class
Each class will initiate Page factory elements.
All the required elements will be captured using ID, CLASS, XPATH or CSSSELECTOR
Each Page will have methods to work with the elements.

### ORG.YAHOOFINANCE.TESLASTOCK.SUPPORT
In Support Package we have classes for UTILS, BASETEST, LOG, ReadingProperties

#### UTILS
In Utils we have methods to waitForElement, takeScreenshot, ScrollToView

#### BASETEST
We have @BeforeSuite, @BeforeTest and @AfterTest methods for initialization of the suite/test and Final result.

#### LOG
We have Log file to capture the logs in the generated report. Supporting methods like, message, softAssertThat etc are available in the LOG class

## SRC/MAIN/RESOURCES/

#### data.properties
All the needed data to work with will be present in the data.properties file

#### config.properties
All the configuration property to work with will be present in the config.properties file

## SRC/TEST/JAVA/

### ORG.YAHOOFINANCE.TESLASTOCK
This Package will have all the test classes.
TeslaStockTest.java contains the test that we needed to execute

#### testNG.xml is to execute script
#### pom.xml is to store dependencies and properties

## test-output

#### Test output folder will have the reports (EmailableReport.html, Index.html) and Screenshot will be stored under screenshot folder. 