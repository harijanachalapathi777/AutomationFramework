package BaseTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import PageObjects.LoginPage;
import Utils.ConfigReader;
import Utils.DriverFactory;

public class BaseClass {

	static WebDriver driver;
	//protected LoginPage loginpage;
	
	@BeforeTest
	public static void setUp() throws Throwable {
		DriverFactory.initDriver();
		try {
				String url = ConfigReader.get("url");
				
				driver = DriverFactory.getDriver();
				
				//LoginPage loginpage = new LoginPage(driver);
				
				driver.get(url);
				
				driver.manage().window().maximize();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@AfterTest
	public static void tearDown() {
		if (driver != null) {
            driver.quit();
        }
	}
}
