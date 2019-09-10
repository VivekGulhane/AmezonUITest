package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonLoginPage {
	WebDriver driver;
//	By uname = By.xpath("//input[@id='ap_email']");
//	By unameSubmitBtn = By.xpath("//input[@id='continue']");
//	By password = By.xpath("//input[@id='ap_password']");
//	By passwordSubmitBtn = By.xpath("//input[@id='signInSubmit']");

	@FindBy(xpath = "//input[@id='ap_email']")
	WebElement uname;

	@FindBy(xpath = "//input[@id='ap_password']")
	WebElement password;

	@FindBy(xpath = "//input[@id='continue']")
	WebElement unameSubmitBtn;

	@FindBy(xpath = "//input[@id='signInSubmit']")
	WebElement passwordSubmitBtn;

	@FindBy(xpath = "//*[@id='nav-link-accountList']/span[2]")
	WebElement accountLists;

	@FindBy(xpath = "//*[@id='nav-flyout-ya-signin']/a/span")
	WebElement signInBtn;

	public AmazonLoginPage(WebDriver driver) {
		this.driver = driver;
		// To create all WebElements
		PageFactory.initElements(driver, this);
	}

	public void setUserName(String strUserName) {
		uname.sendKeys(strUserName);
	}

	public void clickUNameBtn() {
		unameSubmitBtn.click();
	}

	public void setPassword(String strPassword) {
		password.sendKeys(strPassword);
	}

	public void clickPwdeBtn() {
		passwordSubmitBtn.click();
	}

	public void loginToAmazon(String strUserName, String strPasword) {
		Actions action = new Actions(driver);
		// WebElement btn =
		// driver.findElement(By.xpath("//*[@id='nav-link-accountList']"));
		action.moveToElement(accountLists).perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		signInBtn.click();
		// user name
		this.setUserName(strUserName);
		this.clickUNameBtn();
		// password
		this.setPassword(strPasword);
		this.clickPwdeBtn();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
