package Utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumMethods {

	static  WebDriver driver;

	public static void setImplicitlyWait(WebDriver driver, int seconds) {
			System.out.println(driver);
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Explicit wait
	public static WebElement waitUntilElementToVisible(WebDriver driver, By element, int timeOutInSeconds){
		//String element = xpath;
		//WebElement ele = element;
      WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOutInSeconds));
      return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	//Screenshot
	public static String captureScreenshot(WebDriver driver, String path){
		//String className = getCallerClassName();
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(src,new File(path));
            } catch (IOException ex) {
            }
			return path;
	}
     
}
