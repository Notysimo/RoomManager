package test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Test03a {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeTest
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://172.20.208.80:4040/tablet/#/register";
    driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
  }

  @Test
  public void SelectRoomInTabletPage() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.xpath("//input[@id='service-url-input']")).clear();
    driver.findElement(By.xpath("//input[@id='service-url-input']")).sendKeys("https://172.20.208.80:4040");
    driver.findElement(By.xpath("//input[@id='username']")).clear();
    driver.findElement(By.xpath("//input[@id='username']")).sendKeys("Andres1");
    driver.findElement(By.xpath("//input[@id='password']")).clear();
    driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Control123");
    driver.findElement(By.xpath("//div[@type='submit']")).click();
    (new WebDriverWait(driver, 10))
    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@class='control-label'][text()='Working room']")));
    //Floor1Room1
    driver.findElement(By.xpath("//div[@class='btn btn-default toggle-btn'][@type='button']")).click();
    WebElement expected = driver.findElement(By.xpath("//a[@class='list-group-item item-box ng-scope']/strong[text()='Floor1Room1']"));
    String expectedText = expected.getText();
    driver.findElement(By.xpath("//a[@class='list-group-item item-box ng-scope']/strong[text()='Floor1Room1']")).click();
    driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
    ////span[@class='room-label ng-binding']
   (new WebDriverWait(driver, 30))
    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='room-label ng-binding'][text()='Floor1Room1']")));
    System.out.println(expected);
    String actual = driver.findElement(By.xpath("//span[@class='room-label ng-binding'][text()='Floor1Room1']")).getText();
    assertEquals(expectedText, actual);
    
  }

  @AfterTest
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
