import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewTest {
	Logger logger = Logger.getLogger("NewTest");
	WebDriver driver;


	@BeforeClass
	public void beforeClass() {
	String browser ="chrome";
	String os = "Windows 10";
	String driverPath = System.getProperty("user.dir") + "\\drivers";
	System.out.println("driverPath  "   + driverPath);
		
		if(os.toLowerCase().contains("win"))
		{
			if(browser.equalsIgnoreCase("Firefox"))
			{
				System.setProperty("webdriver.firefox.marionette",driverPath+"\\geckodriver.exe");
				driver = new FirefoxDriver();
				
			}else if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver",driverPath+"\\chromedriver.exe");
				driver = new ChromeDriver();		
			}
			
		}else if(os.toLowerCase().contains("linux")) {
			if(browser.equalsIgnoreCase("Firefox"))
			{
				System.setProperty("webdriver.firefox.marionette",driverPath+"\\geckodriver");
				driver = new FirefoxDriver();
				
			}else if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", driverPath+"\\chromedriver");
				driver = new ChromeDriver();		
			}
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	@Test
	public void verifySearchButton() throws InterruptedException {

		String url = "https://www.amazon.in";
		String search_text = "Apple iPhone X";
		
		driver.get(url);
		Actions action = new Actions(driver);
//		WebElement btn = driver.findElement(By.xpath("//a[@data-nav-role='signin']/span[@class='nav-line-1']"));
		
		WebElement btn = driver.findElement(By.xpath("//*[@id='nav-link-accountList']"));
		action.moveToElement(btn).perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		driver.findElement(By.xpath("//span[@class='nav-action-inner']")).click();
		driver.findElement(By.xpath("//*[@id='nav-flyout-ya-signin']/a/span")).click();
		
		//driver.findElement(By.xpath("//*[@class='a-aui_152852-t1 a-aui_157141-c a-aui_158613-t1 a-aui_72554-c a-aui_dropdown_187959-c a-aui_pci_risk_banner_210084-c a-aui_perf_130093-c a-aui_tnr_v2_180836-c a-aui_ux_145937-c a-meter-animate']"));
		driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("7972955471");
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("Vivek007");
		driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(search_text);
		driver.findElement(By.className("nav-input")).click();

		String currentUrl = driver.getCurrentUrl();
		WebElement link = driver.findElement(By.linkText("Apple iPhone X (256GB) - Space Grey"));

		Robot rob;
		try {
			rob = new Robot();
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_T);
			rob.keyRelease(KeyEvent.VK_CONTROL);
			rob.keyRelease(KeyEvent.VK_T);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}

		// handle windows
		String base = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		driver.switchTo().window((String) set.toArray()[1]);
		driver.get(currentUrl);
		
		String product = "Apple iPhone X (256GB) - Space Grey";

		List<WebElement> searchProduct = driver.findElements(By.linkText(product));
		if(searchProduct.size()!=0)
		{
			driver.findElement(By.linkText(product)).click();
			driver.findElement(By.xpath("//button[@id='a-autoid-9-announce']")).click();
			
			List<WebElement> productAvailability = driver.findElements(By.xpath("//input[@id='add-to-cart-button']"));
			String unavailablity = driver.findElement(By.xpath("//*[@id='outOfStock']/div/div[1]/span")).getText();
			if((productAvailability.size()!=0) && (unavailablity.equalsIgnoreCase("Currently unavailable.")))
			{
				Select oSelect = new Select(driver.findElement(By.xpath("//*[@id='quantity']")));				 
				oSelect.selectByValue("3");
				driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
			}else {
				System.out.println(product + "Product is Currently unavailable.");
			}			
		}else {
			System.out.println("No result available for " + product);
		}
		Actions action1 = new Actions(driver);
		WebElement btn1 = driver.findElement(By.xpath("//*[@id='nav-link-accountList']/span[2]"));
		action1.moveToElement(btn1).perform();
		driver.findElement(By.xpath("//*[@id='nav-item-signout']/span")).click();		
	}
}
