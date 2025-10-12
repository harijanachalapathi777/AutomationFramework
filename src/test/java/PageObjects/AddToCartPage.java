package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import BaseTest.BaseClass;
import Utils.SeleniumMethods;

public class AddToCartPage extends BaseClass {

    static WebDriver driver;

    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public static void addToCart() {
        SeleniumMethods.scrollToBottom(driver);
        logger.info("Scrolled to bottom to find next page");
        By next_button = By.cssSelector("#next2");
        SeleniumMethods.waitUntilElementToVisible(driver, next_button, 5);
        SeleniumMethods.clickElement(next_button, driver);
        logger.info("cliked on next button");

        // scrolling up and selecting element
        By laptop = By.xpath("//a[@href='prod.html?idp_=12']");
        SeleniumMethods.waitUntillPresenceOfElementLocated(driver, laptop, 5);
        logger.info("waited for element to locate");

        WebElement laptop_ele = driver.findElement(laptop);
        SeleniumMethods.scrollToElement(laptop_ele, driver);
        logger.info("Scrolled to desired product element");

        SeleniumMethods.setImplicitlyWait(driver, 5);
        laptop_ele.click();
        logger.info("clicked on desired product element");

        // accepting allert
        try {
            SeleniumMethods.handle_JS_alert_or_browserAlert(driver);
        } catch (Exception e) {
            logger.error("Exception is: " + e);
            logger.debug("debug log...");
        }

        // clicking on cart
        By addCart_button = By.xpath("//a[text()='Add to cart']");
        SeleniumMethods.waitUntilElementToVisible(driver, addCart_button, 10);

        SeleniumMethods.clickElement(addCart_button, driver);

        logger.info("clicking on cart button..");
        // finding element by link text
        WebElement cart_button = driver.findElement(By.linkText("Cart"));
        cart_button.click();

    }

}
