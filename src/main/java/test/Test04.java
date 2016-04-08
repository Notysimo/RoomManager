package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Test04 {
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @BeforeTest
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "https://172.20.208.80:4040/admin/#/login";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void CreatesASnesResource () throws Exception {
	    driver.get(baseUrl);
	    driver.findElement(By.xpath("//input[@id='loginUsername']")).clear();
	    driver.findElement(By.xpath("//input[@id='loginUsername']")).sendKeys("Andres1");
	    driver.findElement(By.xpath("//input[@id='loginPassword']")).click();
	    driver.findElement(By.xpath("//input[@id='loginPassword']")).clear();
	    driver.findElement(By.xpath("//input[@id='loginPassword']")).sendKeys("Control123");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.linkText("Resources")).click();
	    driver.findElement(By.xpath("//div/div/button")).click();
	    driver.findElement(By.id("convert")).click();
	    driver.findElement(By.xpath("//button[@value='fa-gamepad']")).click();
	    driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
	    driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("Snes");
	    driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
	    driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("Snes");
	    driver.findElement(By.xpath("//textarea")).click();
	    driver.findElement(By.xpath("//textarea")).click();
	    driver.findElement(By.xpath("//textarea")).clear();
	    driver.findElement(By.xpath("//textarea")).sendKeys("Snes resource");
	    driver.findElement(By.xpath("//div[@id='resourcesGrid']/div[2]/div/div[3]/div[3]/div[2]/div/span")).click();
	    driver.findElement(By.linkText("Conference Rooms")).click();
	    driver.findElement(By.linkText("Resources")).click();
	    driver.findElement(By.xpath("//div[@id='resourcesGrid']/div[2]/div/div[3]/div[2]/div[2]/div")).click();
	    //WebElement snes = driver.findElement(By.(""));
	    
	    assertEquals("RoomManagerAdmin", driver.getTitle());
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
