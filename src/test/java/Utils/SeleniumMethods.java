package Utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumMethods {

	static WebDriver driver;

	public static void setImplicitlyWait(WebDriver driver, int seconds) {
		System.out.println(driver);
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Explicit wait
	public static WebElement waitUntilElementToVisible(WebDriver driver, By ele, int timeOutInSeconds) {
		// String element = xpath;
		// WebElement ele = element;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
	}

	/*
	 * Explicit wait for a driver to wait untill an element presence is acknowledged
	 * in DOM
	 */
	public static WebElement waitUntillPresenceOfElementLocated(WebDriver driver, By ele, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		return wait.until(ExpectedConditions.presenceOfElementLocated(ele));
	}

	// Screenshot
	public static String captureScreenshot(WebDriver driver, String path) {
		// String className = getCallerClassName();
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(path));
		} catch (IOException ex) {
		}
		return path;
	}

	// Javascript executor to scroll tot he bottom/end (x-> represents
	// horizontal(width), y -> vertical(height))
	public static void scrollToBottom(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}

	//
	public static void scrollToElement(WebElement laptop, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", laptop);
	}

	public static void scrollToPixel(int x, int y, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(x,y);");
	}

	// handle JavaScript Alert or browser alert
	public static void handle_JS_alert_or_browserAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
	}

	/*
	 * click element -> It takes by element first then converts to WebElement and
	 * then clicks on element using selenium built in mechanism
	 */
	public static void clickElement(By locator, WebDriver driver) {
		driver.findElement(locator).click();
	}

}
