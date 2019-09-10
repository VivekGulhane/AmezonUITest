package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonHomePage {
	WebDriver driver;
	// By currentLoggedInUser = By.xpath("//*[@id='nav-link-accountList']/span[1]");

	@FindBy(xpath = "//*[@id='nav-link-accountList']/span[1]")
	WebElement currentLoggedInUser;

	@FindBy(id = "twotabsearchtextbox")
	WebElement searchField;

	@FindBy(className = "nav-input")
	WebElement searchValueSubmit;

	// Get the title of home Page
	public AmazonHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getHomePageTitle() {
		return driver.getTitle();
	}

	public String getLoggedInUser() {
		return currentLoggedInUser.getText();
	}

	public void searchProduct(String search_text) {
		searchField.sendKeys(search_text);
		searchValueSubmit.click();
	}

}
