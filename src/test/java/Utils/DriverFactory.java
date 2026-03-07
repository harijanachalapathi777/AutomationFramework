package Utils;

import java.net.URL;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<WebDriverWait>();

	public static void initDriver() throws Throwable {

		String browser = ConfigReader.get("browser");
		String runMode = ConfigReader.get("runMode");
		String gridUrl = ConfigReader.get("gridUrl");

		// WebDriver webDriver;

		if ("remote".equalsIgnoreCase("runMode")) {
			switch (browser.toLowerCase()) {
				case "chrome":
					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.addArguments("--headless");

					chromeOptions.setCapability("browserName", "chrome");
					driver.set(new RemoteWebDriver(new URL(gridUrl), chromeOptions));
					break;

				case "edge":
					EdgeOptions edgeOptions = new EdgeOptions();
					edgeOptions.setCapability("browserName", "edge");
					driver.set(new RemoteWebDriver(new URL(gridUrl), edgeOptions));
					break;

				case "firefox":
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					firefoxOptions.setCapability("browserName", "firefox");
					driver.set(new RemoteWebDriver(new URL(gridUrl), firefoxOptions));
					break;

				default:
					throw new IllegalAccessException("unsupported browser" + browser);
			}
		} else {
			switch (browser.toLowerCase()) {
				case "chrome":
					WebDriverManager.chromedriver().setup();
					driver.set(new ChromeDriver());
					break;

				case "edge":
					WebDriverManager.edgedriver().setup();
					driver.set(new EdgeDriver());
					break;

				case "firefox":
					WebDriverManager.firefoxdriver().setup();
					driver.set(new FirefoxDriver());
					break;

				default:
					throw new IllegalAccessException("unsupported browser" + browser);
			}
		}
		driver.set(driver.get());
	}

	// private static WebDriver createRemoteDriver(String gridUrl,
	// org.openqa.selenium.Capabilities options) {
	// try {
	// return new RemoteWebDriver(new URL(gridUrl), options);
	// } catch (MalformedURLException e) {
	// throw new RuntimeException("Invalid Grid URL: " + gridUrl, e);
	// }
	// }

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void quitDriver() {

		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
			if (wait.get() != null) {
				wait.remove();
			}
		}
	}

}
