package Utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory{
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static void initDriver() throws Throwable {
		
	String browser = ConfigReader.get("browser");
	String runMode = ConfigReader.get("runMode");
	String gridUrl = ConfigReader.get("gridUrl");
	
	
	WebDriver webDriver;
	
	if(runMode.equalsIgnoreCase("remote")) {
		switch(browser.toLowerCase()) {
		   case "chrome":
		         ChromeOptions chromeOptions = new ChromeOptions();
		         chromeOptions.setCapability("browserName", "chrome");
		         webDriver  = new RemoteWebDriver(new URL(gridUrl), chromeOptions);
		         break;
		         
		         default:
		        	 throw new IllegalAccessException("unsupported browser"+ browser);
		}
	}
		else {
			switch(browser.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				webDriver = new ChromeDriver();
				break;
				
				default: 
					throw new IllegalAccessException("unsupported browser"+ browser);
			}
		}
		driver.set(webDriver);
	}
	
//	private static WebDriver createRemoteDriver(String gridUrl, org.openqa.selenium.Capabilities options) {
//        try {
//            return new RemoteWebDriver(new URL(gridUrl), options);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("Invalid Grid URL: " + gridUrl, e);
//        }
//    }
	
	public static WebDriver getDriver() {
        return driver.get();
    }
	
	public static void quitDriver() {
		if(driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
	
	
}
