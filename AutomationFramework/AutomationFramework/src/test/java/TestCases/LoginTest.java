package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import PageObjects.LoginPage;
import Utils.DriverFactory;

public class LoginTest extends BaseClass{
	
	//protected WebDriver driver;
	@Test
	public void login_test() throws Throwable {
		
        WebDriver driver = DriverFactory.getDriver();
     	LoginPage loginpage = new LoginPage(driver);
     	
		loginpage.Login("test", "test");
	}
}
