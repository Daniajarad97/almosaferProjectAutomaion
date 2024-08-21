package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class homePageTesting extends Parameters {

	@BeforeTest
	public void setUp() {
		GeneralSetup();
		driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();

	}

	@Test(priority = 1, enabled = true)
	public void checkTheDefultLanguageIsEnglish() {
		String actualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(actualLanguage, expectedDefalutLanguage);
	}

	@Test(priority = 2, enabled = true)
	public void checkDefultCurrency() {
		String actualCurrency = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO")).getText();
		Assert.assertEquals(actualCurrency, expectedCurrency);
	}

	@Test(priority = 3, enabled = true)
	public void checkContactNumber() {
		String actualCntactNumber = driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(actualCntactNumber, expectedContactNumber);

	}

	@Test(priority = 4, enabled = true)
	public void checkQitafLogoIsDisplayed() {
		WebElement theFooter = driver.findElement(By.tagName("footer"));

//		WebElement classFooter = theFooter.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"));
//		boolean actualResultForTheLogo = classFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"))
//				.isDisplayed();

		boolean actualResultForTheLogo = theFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"))
				.isDisplayed();

		Assert.assertEquals(actualResultForTheLogo, expectedResyltForTheLogo);
	}

	@Test(priority = 5, enabled = true)
	public void theHotelTabIsNotSelected() {
		String actualTabSelectes = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");
		Assert.assertEquals(actualTabSelectes, expectedTabSelected);

	}

	@Test(priority = 6, enabled = true)
	public void checkFlightDeparture() {

		List<WebElement> departureAndArrivalDates = driver.findElements(By.className("LiroG"));

		String actualDepartureDate = departureAndArrivalDates.get(0).getText();
		String actualArrivalDate = departureAndArrivalDates.get(1).getText();

		int actualDepartureDateAsInt = Integer.parseInt(actualDepartureDate);
		int actualArrivalDateAsInt = Integer.parseInt(actualArrivalDate);

		Assert.assertEquals(actualDepartureDateAsInt, tomorrow);
		Assert.assertEquals(actualArrivalDateAsInt, theDayAfterTomorrow);

	}

	@Test(priority = 7, enabled = true)
	public void changeLanguage() {
		RandomSelectLanguage();
	}

	@Test(priority = 8, enabled = true)
	public void switchHotelSearchTab() {
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();

		WebElement SearchHotelInputField = driver.findElement(By.xpath("//input[@data-testid='AutoCompleteInput']"));
		String WebsiteURL = driver.getCurrentUrl();

		if (WebsiteURL.contains("ar")) {

			SearchHotelInputField.sendKeys(ArabicCities[randomArabicCity]);
		} else {
			SearchHotelInputField.sendKeys(EnglishCities[randomEnglishCity]);

		}

		WebElement ListOfLocations = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));

		WebElement firstResult = ListOfLocations.findElements(By.tagName("li")).get(1);
		firstResult.click();

	}

	@Test(priority = 9)

	public void RandomlySelectTheNumberOfVistor() {

		WebElement SelectorofTheVistor = driver
				.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));

		Select select = new Select(SelectorofTheVistor);

		// By index
		int randomIndex = rand.nextInt(2);
		select.selectByIndex(randomIndex);
		WebElement SearchHotelButton = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		SearchHotelButton.click();
	}

	@Test(priority = 10)

	public void CheckThePageFullyLoaded() throws InterruptedException {

		boolean expectedResult = true;
		Thread.sleep(10000);
		String results = driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']"))
				.getText();

		boolean finished = results.contains("وجدنا") || results.contains("found");

		Assert.assertEquals(finished, expectedResult);

	}

	@Test(priority = 11)
	public void SortItemsLowestToHighestPrice() {
		boolean expectedResults = true;
		WebElement LowestPriceSortButton = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		LowestPriceSortButton.click();

		WebElement PricesContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
		List<WebElement> AllPrices = PricesContainer.findElements(By.className("Price__Value"));

		String LowestPrice = AllPrices.get(0).getText();
		String HighestPrice = AllPrices.get(AllPrices.size() - 1).getText();
		int LowestPriceAsInt = Integer.parseInt(LowestPrice);
		int HighestPriceAsInt = Integer.parseInt(HighestPrice);
		boolean ActualResults = LowestPriceAsInt < HighestPriceAsInt;

		Assert.assertEquals(ActualResults, expectedResults);

	}
}
