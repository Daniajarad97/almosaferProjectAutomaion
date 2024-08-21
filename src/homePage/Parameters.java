package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameters {

	WebDriver driver = new ChromeDriver();
	String almosaferURL = "https://www.almosafer.com/en";
	String expectedDefalutLanguage = "en";
	Random rand = new Random();
	String expectedCurrency = "SAR";
	String expectedContactNumber = "+966554400000";
	boolean expectedResyltForTheLogo = true;
	String expectedTabSelected = "false";
	LocalDate date = LocalDate.now();
	int tomorrow = date.plusDays(1).getDayOfMonth();
	int theDayAfterTomorrow = date.plusDays(2).getDayOfMonth();
	String[] EnglishCities = { "Dubbai", "Jeddah", "riyadh" };
	int randomEnglishCity = rand.nextInt(EnglishCities.length);
	String[] ArabicCities = { "دبي", "جدة" };
	int randomArabicCity = rand.nextInt(ArabicCities.length);
	boolean expectedResult = true;
	boolean expectedResults = true;

	public void GeneralSetup() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(almosaferURL);
	}

	public void RandomSelectLanguage() {
		String[] URLs = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
		int randIndex = rand.nextInt(URLs.length);
		String randURLs = URLs[randIndex];
		driver.get(randURLs);
	}

	public void RandomlySelect() {
		
	}
	// By value
//	select.selectByValue("B"); 

	// by visibleText
//	if(driver.getCurrentUrl().contains("ar")) {
//		select.selectByVisibleText("1 غرفة، 1 بالغ، 0 أطفال"); 
//
//	}else {
//		select.selectByValue("1 Room, 1 Adult, 0 Children"); 
//	}
//
}
