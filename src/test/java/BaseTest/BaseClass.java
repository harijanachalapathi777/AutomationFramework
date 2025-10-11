package BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import PageObjects.LoginPage;
import Utils.ConfigReader;
import Utils.DriverFactory;
import Utils.SeleniumMethods;

public class BaseClass {

	static WebDriver driver;
	// protected LoginPage loginpage;

	@BeforeMethod
	public static void setUp() throws Throwable {
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
	}

	@AfterMethod
	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
