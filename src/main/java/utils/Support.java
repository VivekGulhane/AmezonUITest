package utils;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;

public class Support {
	WebDriver driver;
	
	public WebDriver openNewTab(WebDriver driver)
	{
		Robot rob;
		try {
			rob = new Robot();
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_T);
			rob.keyRelease(KeyEvent.VK_CONTROL);
			rob.keyRelease(KeyEvent.VK_T);
			return driver;
		} catch (AWTException e1) {
			e1.printStackTrace();
			return null;
		}
	}
	


}
