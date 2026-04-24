package web.service;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSTEMAppFunctional {

	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"/Users/akhilesh/Drivers/chromedriver-mac-arm64/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void testValidLoginGoesToQ1() throws Exception {
		driver.get("http://127.0.0.1:8085/login");

		driver.findElement(By.id("username")).sendKeys("ahsan");
		driver.findElement(By.id("passwd")).sendKeys("ahsan_pass");
		driver.findElement(By.id("dob")).sendKeys("2000-01-01");
		driver.findElement(By.cssSelector("input[type='submit']")).click();

		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Q1"));
	}

	@Test
	public void testInvalidLoginShowsError() throws Exception {
		driver.get("http://127.0.0.1:8085/login");

		driver.findElement(By.id("username")).sendKeys("wrong");
		driver.findElement(By.id("passwd")).sendKeys("wrong");
		driver.findElement(By.id("dob")).sendKeys("2000-01-01");
		driver.findElement(By.cssSelector("input[type='submit']")).click();

		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Incorrect credentials"));
	}

	@Test
	public void testQ1WrongAnswerStaysOnQ1() throws Exception {
		driver.get("http://127.0.0.1:8085/q1");

		driver.findElement(By.id("number1")).sendKeys("2");
		driver.findElement(By.id("number2")).sendKeys("3");
		driver.findElement(By.id("result")).sendKeys("10");
		driver.findElement(By.cssSelector("input[type='submit']")).click();

		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Wrong answer"));
	}

	@Test
	public void testQ1EmptyInputShowsValidationMessage() throws Exception {
		driver.get("http://127.0.0.1:8085/q1");

		driver.findElement(By.id("number1")).clear();
		driver.findElement(By.id("number2")).sendKeys("3");
		driver.findElement(By.id("result")).sendKeys("3");
		driver.findElement(By.cssSelector("input[type='submit']")).click();

		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Please enter valid numbers"));
	}

	@Test
	public void testFullQuestionFlow() throws Exception {

		driver.get("http://127.0.0.1:8085/login");

		// Login
		driver.findElement(By.id("username")).sendKeys("ahsan");
		driver.findElement(By.id("passwd")).sendKeys("ahsan_pass");
		driver.findElement(By.id("dob")).sendKeys("2000-01-01");
		driver.findElement(By.cssSelector("input[type='submit']")).click();

		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Q1"));

		// Q1
		driver.findElement(By.id("number1")).sendKeys("2");
		driver.findElement(By.id("number2")).sendKeys("3");
		driver.findElement(By.id("result")).sendKeys("5");
		driver.findElement(By.cssSelector("input[type='submit']")).click();

		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Q2"));

		// Q2
		driver.findElement(By.id("number1")).sendKeys("9");
		driver.findElement(By.id("number2")).sendKeys("4");
		driver.findElement(By.id("result")).sendKeys("5");
		driver.findElement(By.cssSelector("input[type='submit']")).click();

		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Q3"));

		// Q3
		driver.findElement(By.id("number1")).sendKeys("3");
		driver.findElement(By.id("number2")).sendKeys("4");
		driver.findElement(By.id("result")).sendKeys("12");
		driver.findElement(By.cssSelector("input[type='submit']")).click();

		Thread.sleep(1500);

		assertTrue(driver.getPageSource().contains("Welcome")
				|| driver.getPageSource().contains("Great job! You completed all 3 math questions."));
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}