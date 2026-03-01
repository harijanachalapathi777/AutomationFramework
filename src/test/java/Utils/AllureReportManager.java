package Utils;

import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import BaseTest.BaseClass;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

public class AllureReportManager implements ITestListener {

  // WebDriver driver = DriverFactory.getDriver();
  // public static Logger logger;

  // this is to get the method name
  private static String getTestMethodName(ITestResult iTestResult) {
    return iTestResult.getMethod().getConstructorOrMethod().getName();
  }

  // this is to get screenshot on failure
  @Attachment(value = "{0} screenshots", type = "image/png")
  public static byte[] saveFailureScreenshot(WebDriver driver, String testName) {
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
  }

  // this is for the plain message
  @Attachment(value = "{0}", type = "text/plain")
  public static String saveTextLog(String message) {
    return message;
  }

  @Override
  public void onStart(ITestContext context) {
    System.out.println("start Method: " + context.getName() + " started");
  }

  @Override
  public void onFinish(ITestContext context) {
    System.out.println("finish method.." + context.getName() + "finished");
  }

  @Override
  public void onTestStart(ITestResult result) {
    System.out.println("test start method " + getTestMethodName(result) + "test started");
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    System.out.println("test success method " + getTestMethodName(result) + "test successed");
  }

  @Override
  public void onTestFailure(ITestResult result) {
    System.out.println("Test failure: " + getTestMethodName(result) + " failed");

    // get driver from test context(safe for parellel execution/ parellel runs)
    WebDriver driver = DriverFactory.getDriver();

    if (driver != null) {
      String testName = result.getName();
      SeleniumMethods.captureScreenshot(driver, testName);
      saveFailureScreenshot(driver, testName);
    }
  }

  @Override
  public void onTestSkipped(ITestResult iTestResult) {
    System.out.println("test skip method " + getTestMethodName(iTestResult) + "test skipped");
  }
}
