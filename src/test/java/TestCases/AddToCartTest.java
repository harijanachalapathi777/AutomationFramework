package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import PageObjects.AddToCartPage;
import Utils.DriverFactory;

@Listeners(Utils.AllureReportManager.class)
public class AddToCartTest extends BaseClass {
    // static WebDriver driver;

    @Test
    public void addToCart_test() {

        WebDriver driver = DriverFactory.getDriver();

        AddToCartPage cartPage = new AddToCartPage(driver);
        cartPage.addToCart();
    }
}
