package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonLogoutPage {

	WebDriver driver;

	@FindBy(xpath = "//*[@id='nav-link-accountList']/span[2]")
	WebElement accountLists;

	@FindBy(xpath = "//*[@id='nav-item-signout']/span")
	WebElement signOutBtn;

	public AmazonLogoutPage(WebDriver driver) {
		this.driver = driver;
		// To create all WebElements
		PageFactory.initElements(driver, this);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public void logoutAmazon() {
		Actions action1 = new Actions(driver);
		action1.moveToElement(accountLists).perform();
		signOutBtn.click();
	}
}
