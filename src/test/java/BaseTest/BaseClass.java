package BaseTest;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import PageObjects.LoginPage;
import Utils.ConfigReader;
import Utils.DriverFactory;
import Utils.SeleniumMethods;

public class BaseClass {

	static WebDriver driver;
	public static Logger logger;
	// protected LoginPage loginpage;

	@BeforeMethod
	public static void setUp() throws Throwable {

		// Driver initialization
		DriverFactory.initDriver();
		try {
			String url = ConfigReader.get("url");

			driver = DriverFactory.getDriver();

			// LoginPage loginpage = new LoginPage(driver);

			driver.get(url);

			driver.manage().window().maximize();
			SeleniumMethods.setImplicitlyWait(driver, 10);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// Logging
		logger = LogManager.getLogger(BaseClass.class);
		logger.info("Logging to absolute path: " + System.getProperty("user.dir"));
		System.setProperty("log4j.configurationFile",
				"C:\\Users\\Dell\\git\\repository\\AutomationFramework\\src\\test\\resources\\log4j2.xml");
		System.out.println("Log4j2 config file: " + System.getProperty("log4j.configurationFile"));
	}

	@AfterMethod
	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@AfterSuite
	public void allureReport() {
		logger.info("loading allure report....");
		try {
			Runtime.getRuntime().exec("cmd /c start launchAllureReport.bat");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
