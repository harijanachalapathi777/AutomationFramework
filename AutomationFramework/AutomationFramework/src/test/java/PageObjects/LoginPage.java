package PageObjects;
//package Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import com.example.package.Utils.SeleniumMethods;
public class LoginPage {

	private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
	
	public void Login(String username, String Password) {
	WebElement login_button = driver.findElement(By.xpath("//a[text() = 'Log in']"));
	login_button.click();
	System.out.println("Clicked on login_button");

	Utils.SeleniumMethods.setImplicitlyWait(5000);
	
	WebElement login_model = driver.findElement(By.xpath("//h5[text() = 'Log in']"));
	Utils.SeleniumMethods.waitUntilElementToVisible(driver, "//h5[text() = 'Log in']", 5000);
	
	System.out.println("time is set");
	
	String loginText = login_model.getText();
	System.out.println(loginText);
	}
	
}
