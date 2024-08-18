package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class homePageTesting {

	WebDriver driver = new ChromeDriver();
	String almosaferURL = "https://www.almosafer.com/en";
	String expectedDefalutLanguage = "en";
	Random rand = new Random();
	String randomCurrentLang = "";

	@BeforeTest
	public void setUp() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(almosaferURL);
		driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();

	}

	@Test(priority = 1, enabled = true)
	public void checkTheDefultLanguageIsEnglish() {
		String actualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		System.out.println(actualLanguage);

		Assert.assertEquals(actualLanguage, expectedDefalutLanguage);
	}

	@Test(priority = 2, enabled = true)
	public void checkDefultCurrency() {
		String expectedCurrency = "SAR";
		WebElement currency = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO"));
		String actualCurrency = currency.getText();
		Assert.assertEquals(actualCurrency, expectedCurrency);
	}

	@Test(priority = 3, enabled = true)
	public void checkContactNumber() {
		String expectedContactNumber = "+966554400000";
		String actualCntactNumber = driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(actualCntactNumber, expectedContactNumber);

	}

	@Test(priority = 4, enabled = true)
	public void checkQitafLogoIsDisplayed() {
		boolean expectedResyltForTheLogo = true;
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
		String expectedTabSelected = "false";
		WebElement tabSelected = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String actualTabSelectes = tabSelected.getAttribute("aria-selected");

		Assert.assertEquals(actualTabSelectes, expectedTabSelected);

	}

	@Test(priority = 6, enabled = true)
	public void checkFlightDeparture() {
		LocalDate date = LocalDate.now();
		int tomorrow = date.plusDays(1).getDayOfMonth();
		int theDayAfterTomorrow = date.plusDays(2).getDayOfMonth();

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
		String[] URLs = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
		int randIndex = rand.nextInt(URLs.length);
		String randURLs = URLs[randIndex];
		driver.get(randURLs);
		randomCurrentLang = randURLs;

	}

	@Test(priority = 8, enabled = true)
	public void switchToHotelSearchTab() {

		WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		hotelTab.click();
		WebElement searchTab = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
		String actualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

		String[] locationTabIsEn = { "Dubai", "Jeddah", "Riyadh" };
		String[] locationTabIsAr = { "دبي", "جدة" };

		int randIndexByEn = rand.nextInt(locationTabIsEn.length);
		int randIndexByAr = rand.nextInt(locationTabIsAr.length);

		if ("en".equalsIgnoreCase(actualLanguage)) {

			searchTab.sendKeys(locationTabIsEn[randIndexByEn]);
			WebElement firstResult = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
			firstResult.getAttribute("data-testid");
			firstResult.click();
		} else {
			searchTab.sendKeys(locationTabIsAr[randIndexByAr]);
			searchTab.sendKeys(locationTabIsEn[randIndexByEn]);
			WebElement firstResult = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
			firstResult.getAttribute("data-testid");
			firstResult.click();

		}

	}
}
