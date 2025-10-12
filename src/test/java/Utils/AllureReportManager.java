package Utils;

import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import BaseTest.BaseClass;
import io.qameta.allure.Attachment;

public class AllureReportManager implements ITestListener {

  private WebDriver driver;
  public static Logger logger;

  // this is to get the method name
  private static String getTestMethodName(ITestResult iTestResult) {
    return iTestResult.getMethod().getConstructorOrMethod().getName();
  }

  // this is to get screenshot on failure
  @Attachment(value = "Screenshot on failure", type = "image/png")
  public static byte[] saveFailureScreenshot(WebDriver driver) {
    try {
      return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    } catch (Exception e) {
      logger.error("ss capture failed " + e);
      return null;
    }
  }

  // this is for the plain message
  @Attachment(value = "{0}", type = "text/plain")
  public static String saveTextLog(String message) {
    return message;
  }

  @Override
  public void onStart(ITestContext iTestContext) {
    System.out.println("start method.." + iTestContext.getName() + "started");
    if (driver != null) {
      iTestContext.setAttribute("Webdriver", driver);
    }
  }

  @Override
  public void onFinish(ITestContext iTestContext) {
    System.out.println("finish method.." + iTestContext.getName() + "finished");
    if (driver != null) {
      iTestContext.setAttribute("Webdriver", driver);
    }
  }

  @Override
  public void onTestStart(ITestResult iTestResult) {
    System.out.println("test start method " + getTestMethodName(iTestResult) + "test started");
  }

  @Override
  public void onTestSuccess(ITestResult iTestResult) {
    System.out.println("test success method " + getTestMethodName(iTestResult) + "test successed");
  }

  @Override
  public void onTestFailure(ITestResult iTestResult) {
    System.out.println("test failure method " + getTestMethodName(iTestResult) + "test failed");

    Object testClass = iTestResult.getInstance();
    WebDriver driver = DriverFactory.getDriver();
    System.out.println(driver);
    if (driver != null) {
      System.out.println("screenshot captured for test case " + getTestMethodName(iTestResult));
      saveFailureScreenshot(driver);
    }
    saveTextLog(getTestMethodName(iTestResult) + "failed screenshot is taken!");
  }

  @Override
  public void onTestSkipped(ITestResult iTestResult) {
    System.out.println("test skip method " + getTestMethodName(iTestResult) + "test skipped");
  }
}
