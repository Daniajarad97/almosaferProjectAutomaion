package homePage;

import java.time.Duration;

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
}
