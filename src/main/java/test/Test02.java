package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Test02 {
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
	  public void InsertAnAccountToTablet() throws Exception {
	    driver.get(baseUrl);
	    driver.findElement(By.xpath("//input[@id='service-url-input']")).clear();
	    driver.findElement(By.xpath("//input[@id='service-url-input']")).sendKeys("https://172.20.208.80:4040");
	    driver.findElement(By.xpath("//input[@id='username']")).clear();
	    driver.findElement(By.xpath("//input[@id='username']")).sendKeys("Andres1");
	    driver.findElement(By.xpath("//input[@id='password']")).clear();
	    driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Control123");
	    driver.findElement(By.xpath("//div[@type='submit']")).click();
	    WebElement expected = (new WebDriverWait(driver, 10))
	    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@class='control-label'][text()='Working room']")));
	    assertEquals(expected.getText(), "Working room");
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
