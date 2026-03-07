package Utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {

    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<WebDriverWait>();

    static {
        wait.set(new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(30)));
    }

    public static void waitForElement() {
        wait.get().until(ExpectedConditions.visibilityOfElementLocated(By.id("elementId")));
    }
}
