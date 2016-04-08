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


public class Test05 {
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @BeforeTest
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "https://172.20.208.80:4040/admin/#/login";
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  }

	  @Test
	  public void CustomizeTheTimeForCancelMeeting () throws Exception {
	    driver.get(baseUrl);
	    driver.findElement(By.xpath("//input[@id='loginUsername']")).clear();
	    driver.findElement(By.xpath("//input[@id='loginUsername']")).sendKeys("Andres1");
	    driver.findElement(By.xpath("//input[@id='loginPassword']")).click();
	    driver.findElement(By.xpath("//input[@id='loginPassword']")).clear();
	    driver.findElement(By.xpath("//input[@id='loginPassword']")).sendKeys("Control123");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    (new WebDriverWait(driver, 10))
	    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='navbar-brand'][text()='Room Manager']")));
	    driver.findElement(By.xpath("//a[@class='clickeable ng-binding'][text()='Settings']")).click();
	    driver.findElement(By.xpath("//input[@type='input']")).clear();
	    driver.findElement(By.xpath("//input[@type='input']")).sendKeys("10");
	    driver.findElement(By.xpath("//button[@class='info pull-right']")).click();
	    driver.get("https://172.20.208.80:4040/admin/#/admin/settings");
	    WebElement myDynamicElement = driver.findElement(By.xpath("//div[@class='toast-message']"));
	    System.out.println(myDynamicElement);
	    assertEquals(myDynamicElement.getText(), "Settings saved successfully");
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
