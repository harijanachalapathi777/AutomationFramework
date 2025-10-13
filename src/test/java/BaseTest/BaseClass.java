package BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.beust.jcommander.Parameter;

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

	@DataProvider(name = "login_data", indices = { 0 })
	public static Object[][] login_Data() {
		Object data[][] = {
				{ "test", "test" },
				{ "test1", "test1" },
				{ "test2", "test2" },
				{ "test3", "test3" },
				{ "test4", "test4" },
		};
		return data;
	}

}
