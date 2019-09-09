package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AmazonCheckoutPage {
	WebDriver driver;
	
    @FindBy(xpath="//button[@id='a-autoid-9-announce']")
    WebElement productMemory;	
	
    @FindBy(xpath="//input[@id='add-to-cart-button']")
    List<WebElement> addToCartButtons;	
    
    @FindBy(xpath="//input[@id='add-to-cart-button']")
    WebElement addToCartButton;	
    
    @FindBy(xpath="//*[@id='outOfStock']/div/div[1]/span")
    WebElement outOfStock;	
    
    @FindBy(xpath="//*[@id='quantity']")
    WebElement productQuantity;	
    
    public AmazonCheckoutPage(WebDriver driver){
        this.driver = driver;
        //To create all WebElements
        PageFactory.initElements(driver, this);
    }

    public void productCheckout(String productForCheckout,String quantity)
    {
    	//String productFotCheckout = "Apple iPhone X (256GB) - Space Grey";

    	List<WebElement> searchProduct = driver.findElements(By.linkText(productForCheckout));
    	if(searchProduct.size()!=0)
    	{
    		driver.findElement(By.linkText(productForCheckout)).click();        
    		productMemory.click();
        	    		
    		//List<WebElement> productAvailability = driver.findElements(By.xpath("//input[@id='add-to-cart-button']"));
    		String unavailablity = outOfStock.getText();
        	
    		if((addToCartButtons.size()!=0) && (unavailablity.equalsIgnoreCase("Currently unavailable.")))
    		{
    			Select oSelect = new Select(productQuantity);    	    
    			oSelect.selectByValue(quantity);    	    	
    			addToCartButton.click();    	    	
    		}else {
    			System.out.println(productForCheckout + "Product is Currently unavailable.");    	    	
    		}			
    	}else {
    		System.out.println("No result available for " + productForCheckout);        	
    	}

    }
}
