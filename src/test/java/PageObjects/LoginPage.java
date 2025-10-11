package PageObjects;

//package Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage {

	static WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public static void Login(String username, String Password) {
		WebElement login_button = driver.findElement(By.xpath("//a[text() = 'Log in']"));
		login_button.click();
		System.out.println("Clicked on login_button");

		System.out.println(driver);

		By ele1 = By.xpath("//h5[text() = 'Log in']");
		Utils.SeleniumMethods.waitUntilElementToVisible(driver, ele1, 5);

		// Utils.SeleniumMethods.setImplicitlyWait(driver, 5);

		// System.out.println("time is set");
		System.out.println("explicit is working fine");
		WebElement login_model = driver.findElement(By.xpath("//h5[text() = 'Log in']"));
		// Utils.SeleniumMethods.waitUntilElementToVisible(driver, login_model, 5);

		System.out.println("implicit and explicit time is set");

		String login_text = login_model.getText();
		System.out.println(login_text);

		Assert.assertEquals(login_text, "Log in");
		// System.out.println(" text on the login model is"+loginText);

		// xpath using parent child
		WebElement username_locator = driver
				.findElement(By.xpath("//div[@class='form-group']//child::input[@id='loginusername']"));
		username_locator.sendKeys("test");

		Utils.SeleniumMethods.setImplicitlyWait(driver, 5);

		// xpath using id
		WebElement password_locator = driver.findElement(By.id("loginpassword"));
		password_locator.sendKeys("test");

		Utils.SeleniumMethods.setImplicitlyWait(driver, 5);

		// xpath using descendant & text
		WebElement login = driver.findElement(By.xpath("//div[@id='logInModal']/descendant::button[text()='Log in']"));
		login.click();

		System.out.println("login successful");

		System.out.println("setting implicit wait");
		Utils.SeleniumMethods.setImplicitlyWait(driver, 5);
		System.out.println("finding element...");

		//
		By ele = By
				.xpath("//div[@id='videoModal']/following-sibling::nav/descendant::li[6]/child::a[text()='Log out']");
		Utils.SeleniumMethods.waitUntilElementToVisible(driver, ele, 5);

		WebElement logout = driver.findElement(By
				.xpath("//div[@id='videoModal']/following-sibling::nav/descendant::li[6]/child::a[text()='Log out']"));

		// xpath using descendant,following-sibling,text
		String logout_text = logout.getText();
		System.out.println(logout.getText());
		logout.click();
		try {
			Assert.assertEquals("Log out", logout_text);
		} catch (AssertionError e) {
			System.out.println("Assertion failed: " + e.getMessage());
			throw e; // rethrow to let TestNG trigger onTestFailure
		}
	}

}
