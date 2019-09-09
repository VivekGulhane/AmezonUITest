import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class test1 {
	
	   @Test
	   public void sampleDemoTest() {
			Logger logger = Logger.getLogger("NewTest");
			WebDriver driver = null;

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

			String url = "https://www.amazon.in";
			String search_text = "Apple iPhone X";
			
			driver.get(url);
			Actions action = new Actions(driver);
			WebElement btn = driver.findElement(By.xpath("//a[@data-nav-role='signin']/span[@class='nav-line-1']"));
			action.moveToElement(btn).perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.findElement(By.xpath("//span[@class='nav-action-inner']")).click();
			
			//driver.findElement(By.xpath("//*[@class='a-aui_152852-t1 a-aui_157141-c a-aui_158613-t1 a-aui_72554-c a-aui_dropdown_187959-c a-aui_pci_risk_banner_210084-c a-aui_perf_130093-c a-aui_tnr_v2_180836-c a-aui_ux_145937-c a-meter-animate']"));
			driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("7972955471");
			driver.findElement(By.xpath("//input[@id='continue']")).click();
			driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("Vivek007");
			driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
			
	
			
			
	   }



}
