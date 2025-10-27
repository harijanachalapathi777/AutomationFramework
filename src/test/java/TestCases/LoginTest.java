package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import PageObjects.LoginPage;
import Utils.DriverFactory;
import Utils.ExcelDataProvider;
import Utils.JsonDataProvider;

@Listeners(Utils.AllureReportManager.class)
public class LoginTest extends BaseClass {

	// @Parameters()
	@Test(dataProvider = "JsonData", dataProviderClass = JsonDataProvider.class)
	// @Test(dataProvider = "loginDataExcel", dataProviderClass =
	// ExcelDataProvider.class)
	public void login_test(String email, String pw) throws Throwable {

		logger.info("**starting logintest**");
		WebDriver driver = DriverFactory.getDriver();

		LoginPage loginpage = new LoginPage(driver);
		loginpage.Login(email, pw);
	}
}
