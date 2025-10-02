package Utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumMethods {

	static WebDriver driver;

	public static void setImplicitlyWait(int seconds) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static WebElement waitUntilElementToVisible(WebDriver driver, String element, int timeOutInSeconds){
		String xpath = element;
		By locator = By.xpath(xpath);
      WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOutInSeconds));
      return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
     
}
