package Blazedemo.blazedemo.testcases;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Blazedemo.baseclass.BaseClass;
import Blazedemo.librarymethods.DataHandlers;
import utils.XLUtility;

@Listeners(utils.Listeners.class)
public class Booking extends BaseClass{
	
  // Find Flights	
  private static final String homePageTitle = DataHandlers.getProperty("object_repository/objectProperties.properties", "homePageTitle");
  private static final String dropDownDepartureCity = DataHandlers.getProperty("object_repository/objectProperties.properties", "dropDownDepartureCity");
  private static final String dropDownDestinationCity = DataHandlers.getProperty("object_repository/objectProperties.properties", "dropDownDestinationCity");
  private static final String findFlightsButton = DataHandlers.getProperty("object_repository/objectProperties.properties", "findFlightsButton");
  private static final String defaultChooseflight = DataHandlers.getProperty("object_repository/objectProperties.properties", "defaultChooseflight");
  
  // Fill Reservation Form
  private static final String firstName = DataHandlers.getProperty("object_repository/objectProperties.properties", "firstName");
  private static final String addressName = DataHandlers.getProperty("object_repository/objectProperties.properties", "address");
  private static final String cityName = DataHandlers.getProperty("object_repository/objectProperties.properties", "cityName");
  private static final String stateName = DataHandlers.getProperty("object_repository/objectProperties.properties", "stateName");
  private static final String zipCodeName = DataHandlers.getProperty("object_repository/objectProperties.properties", "zipCodeName");
  private static final String cardTypeId = DataHandlers.getProperty("object_repository/objectProperties.properties", "cardTypeId");
  private static final String creditCardNumberName = DataHandlers.getProperty("object_repository/objectProperties.properties", "creditCardNumberName");
  private static final String creditCardMonthId = DataHandlers.getProperty("object_repository/objectProperties.properties", "creditCardYearId");
  private static final String creditCardYearId = DataHandlers.getProperty("object_repository/objectProperties.properties", "creditCardYearId");
  private static final String nameOnCardId = DataHandlers.getProperty("object_repository/objectProperties.properties", "nameOnCardId");
  private static final String purchaseFlightXpath = DataHandlers.getProperty("object_repository/objectProperties.properties", "purchaseFlightXpath");
  
  // Capture Success Message
  private static final String successMessage = DataHandlers.getProperty("object_repository/objectProperties.properties", "successMessage");
  
  // Confirmation Id
  private static final String fetchConfirmationId = DataHandlers.getProperty("object_repository/objectProperties.properties", "fetchConfirmationId");
  
  @Test(priority = 0)
  public void verifyTitleOfThePage() throws IOException {
	  WebElement element = getElement(driver, Locators.xpath, homePageTitle);
	  String actualtitle = element.getText();
	  String expectedTitle = "Welcome to the Simple Travel Agency!";
	  Assert.assertEquals(actualtitle, expectedTitle);
  }
  
  @Test(priority = 1)
  public void submitFindFlights() throws IOException {
	  WebElement departureElement = getElement(driver, Locators.name, dropDownDepartureCity);
	  selectDropdown(departureElement, "Boston", -1 , null);
	  WebElement destinationElement = getElement(driver, Locators.name, dropDownDestinationCity);
	  selectDropdown(destinationElement, "London", -1 , null);
	  getElement(driver, Locators.xpath, findFlightsButton).click();
	  getElement(driver, Locators.xpath, defaultChooseflight).click();
  }
  
  @Test(priority = 2 , dependsOnMethods="submitFindFlights", dataProvider="UIDataProvider")
  public void fillReservationForm (String first, String address, String city, String state, String zipcode,
		  String cardType, String creditCardNumber, String creditCardMonth, String creditCardYear, String nameOnCard) throws IOException {
	  getElement(driver, Locators.name, firstName).sendKeys(first);
	  getElement(driver, Locators.name, addressName).sendKeys(address);
	  getElement(driver, Locators.name, cityName).sendKeys(city);
	  getElement(driver, Locators.name, stateName).sendKeys(state);
	  getElement(driver, Locators.name, zipCodeName).sendKeys(zipcode);
	  getElement(driver, Locators.id, cardTypeId).sendKeys(cardType);
	  getElement(driver, Locators.name, creditCardNumberName).sendKeys(creditCardNumber);
	  getElement(driver, Locators.id, creditCardMonthId).sendKeys(creditCardMonth);
	  getElement(driver, Locators.id, creditCardYearId).sendKeys(creditCardYear);
	  getElement(driver, Locators.id, nameOnCardId).sendKeys(nameOnCard);
	  getElement(driver, Locators.xpath, purchaseFlightXpath).click();
  }
  
  @Test(priority = 3, dependsOnMethods="fillReservationForm")
  public void captureSuccessMessage() throws IOException {
	  WebElement element = getElement(driver, Locators.xpath, successMessage);
	  String actualMessage = element.getText().trim();
	  String expectedMessasge = "Thank you";
	  Assert.assertTrue(actualMessage.contains(expectedMessasge));
  }
  
  @Test(priority = 4, dependsOnMethods="captureSuccessMessage")
  public void assertConfirmationId() throws IOException {
	  WebElement element = getElement(driver, Locators.xpath, fetchConfirmationId);
	  String confirmationId = element.getText().trim();
	  System.out.println("Confirmation id is generated and printed as "+confirmationId);
	  Assert.assertTrue(confirmationId != null, " Confirmation id is generated and printed as "+confirmationId);
  }
  
  @DataProvider(name="UIDataProvider")
	String [][] getData() throws IOException{
		
		// Read Data from Excel
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\externalData\\Sample-excel.xls" ;
		int rowNum = XLUtility.getRowCount(path, "Sheet1");
		int colNum = XLUtility.getCellCount(path, "Sheet1", rowNum);
		String data[][] = new String[rowNum][colNum];
		
		System.out.println("The total number of rows "+ rowNum);
		System.out.println("The total number of columns "+ colNum);
		
		for(int i =1; i <= rowNum ; i++) {
			for(int j=0; j < colNum ; j++) {
				data[i-1][j] =XLUtility.getCellData(path, "Sheet1", i, j);
			}
		}
		
		return data;
	}
  
}
