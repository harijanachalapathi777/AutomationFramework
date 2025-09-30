package Utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class SeleniumMethods {

	static WebDriver driver;
	
	public static void setImplicitlyWait(int seconds) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
