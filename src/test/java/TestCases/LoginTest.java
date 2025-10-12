package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import PageObjects.LoginPage;
import Utils.AllureReportManager;
import Utils.DriverFactory;

@Listeners(Utils.AllureReportManager.class)
public class LoginTest extends BaseClass {

	// protected WebDriver driver;
	@Test
	public void login_test() throws Throwable {

		logger.info("**starting logintest**");
		WebDriver driver = DriverFactory.getDriver();

		LoginPage loginpage = new LoginPage(driver);
		loginpage.Login("test", "test");
	}
}
