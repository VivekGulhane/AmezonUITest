package tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.AmazonCheckoutPage;
import pages.AmazonHomePage;
import pages.AmazonLoginPage;
import pages.AmazonLogoutPage;
import utils.Support;

public class AmazonTest extends Support {
	WebDriver driver;

	AmazonLoginPage objAmzLoginPg;
	AmazonHomePage objAmzHomePg;
	AmazonCheckoutPage objAmzCheckoutPg;
	AmazonLogoutPage objAmzLogoutPg;


	@BeforeTest
	@Parameters({"browserName","platform"})
	public void setup(String browserName, String platform) {
		String url = "https://www.amazon.in";
		String search_text = "Apple iPhone X";
		String driverPath = System.getProperty("user.dir") + "\\drivers";

		if (platform.toLowerCase().contains("win")) {
			if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.firefox.marionette", driverPath + "\\geckodriver.exe");
				driver = new FirefoxDriver();

			} else if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", driverPath + "\\chromedriver.exe");
				driver = new ChromeDriver();
			} else {
				System.out.println("Please provide correct browser name");
			}

		} else if (platform.toLowerCase().contains("linux")) {
			if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.firefox.marionette", driverPath + "\\geckodriver");
				driver = new FirefoxDriver();

			} else if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", driverPath + "\\chromedriver");
				driver = new ChromeDriver();
			} else {
				System.out.println("Please provide correct browser name");
			}

		} else {
			System.out.println("Please provide correct os name");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);

	}

	@Test(priority = 0)
	public void Open_Home_Page() {
		// Create Page object
		objAmzHomePg = new AmazonHomePage(driver);

		// Verify home page title
		String homePageTitle = objAmzHomePg.getHomePageTitle();
		Assert.assertEquals(homePageTitle,
				"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in",
				"Page Title is not matching with expected");
	}

	@Test(priority = 1)
	public void Loginnn() {
		objAmzLoginPg = new AmazonLoginPage(driver);

		// login to application
		objAmzLoginPg.loginToAmazon("7972955471", "Vivek007");// Can parameterized this to validate positive negative
																// test cases
		String userName = objAmzHomePg.getLoggedInUser();
		Assert.assertEquals(userName, "Hello, Vivek", "Logged in user in not correct");
	}

	@Test(priority = 2)
	public void ProductSearch() {
		objAmzHomePg = new AmazonHomePage(driver);
		objAmzCheckoutPg = new AmazonCheckoutPage(driver);

		// Search Product
		objAmzHomePg.searchProduct("Apple iPhone X");
		String currentUrl = driver.getCurrentUrl();

		// open new tab
		openNewTab(driver);

		// handle windows
		String base = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		driver.switchTo().window((String) set.toArray()[1]);
		driver.get(currentUrl);

		// Check product availability and checkout
		objAmzCheckoutPg.productCheckout("Apple iPhone X (256GB) - Space Grey", "2");
	}

	@Test(priority = 3)
	public void Logout() {
		// log out
		objAmzLogoutPg = new AmazonLogoutPage(driver);
		objAmzLogoutPg.logoutAmazon();
		Assert.assertTrue(objAmzLogoutPg.getPageTitle().contains("Amazon Sign In"),
				"Either Page title is incorrect after logout or not logout successifully");
	}

	@AfterTest
	public void afterClass() {
		driver.quit();
	}
}
