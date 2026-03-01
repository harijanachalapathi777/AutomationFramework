package PageObjects;

//package Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import BaseTest.BaseClass;
import Utils.DriverFactory;
import Utils.SeleniumMethods;

public class LoginPage extends BaseClass {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = DriverFactory.getDriver();
	}

	// SeleniumMethods seleniumMethods = new SeleniumMethods(driver);

	public void Login(String username, String password) {
		By login_button = By.xpath("//a[text() = 'Log in']");
		try {
			SeleniumMethods.clickElement(login_button, driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Clicked on login_button");

		logger.info(driver);

		By ele1 = By.xpath("//h5[text() = 'Log in']");
		Utils.SeleniumMethods.waitUntilElementToVisible(driver, ele1, 5);

		logger.info("explicit is working fine");
		By login_model = By.xpath("//h5[text() = 'Log in']");
		Utils.SeleniumMethods.waitUntilElementToVisible(driver, login_model, 5);

		logger.info("implicit and explicit time is set");

		String login_text = driver.findElement(login_model).getText();
		logger.info(login_text);

		Assert.assertEquals(login_text, "Log in");
		// System.out.println(" text on the login model is"+loginText);

		// xpath using parent child
		By username_locator = By.xpath("//div[@class='form-group']//child::input[@id='loginusername']");
		Utils.SeleniumMethods.waitUntilElementToVisible(driver, username_locator, 5);
		driver.findElement(username_locator).sendKeys(username);

		Utils.SeleniumMethods.setImplicitlyWait(driver, 5);

		// xpath using id
		By password_locator = By.id("loginpassword");
		Utils.SeleniumMethods.waitUntilElementToVisible(driver, password_locator, 5);
		driver.findElement(password_locator).sendKeys(password);

		Utils.SeleniumMethods.setImplicitlyWait(driver, 5);

		// xpath using descendant & text
		By login = By.xpath("//div[@id='logInModal']/descendant::button[text()='Log in']");
		Utils.SeleniumMethods.waitUntilElementToVisible(driver, login, 5);
		driver.findElement(login).click();

		logger.info("login successful");

		logger.info("setting implicit wait");
		Utils.SeleniumMethods.setImplicitlyWait(driver, 5);
		logger.info("finding element...");

		//
		By ele = By
				.xpath("//div[@id='videoModal']/following-sibling::nav/descendant::li[6]/child::a[text()='Log out']");
		Utils.SeleniumMethods.waitUntilElementToVisible(driver, ele, 5);

		By logout = By
				.xpath("//div[@id='videoModal']/following-sibling::nav/descendant::li[6]/child::a[text()='Log out']");
		// xpath using descendant,following-sibling,text
		String logout_text = driver.findElement(logout).getText();
		logger.info(logout_text);
		driver.findElement(logout).click();
		try {
			Assert.assertEquals("Log outtttt", logout_text);
		} catch (AssertionError e) {
			logger.error("Assertion failed: " + e.getMessage());
			logger.debug("debug logs...");
			throw e; // rethrow to let TestNG trigger onTestFailure
		}
	}

}
