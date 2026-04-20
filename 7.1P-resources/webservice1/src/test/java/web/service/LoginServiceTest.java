package web.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginServiceTest {

	private WebDriver driver;

	@Before
	public void setUp() {
		String chromeDriverPath = System.getProperty("webdriver.chrome.driver");
		if (chromeDriverPath == null || chromeDriverPath.trim().isEmpty()) {
			throw new IllegalStateException(
					"Set -Dwebdriver.chrome.driver=<your-driver-path> before running Selenium tests.");
		}

		String loginPage = System.getProperty("login.page");
		if (loginPage == null || loginPage.trim().isEmpty()) {
			throw new IllegalStateException(
					"Set -Dlogin.page=file:///absolute/path/to/pages/login.html before running Selenium tests.");
		}

		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.navigate().to(loginPage);
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	private void submitLogin(String username, String password, String dob) {
		WebElement element = driver.findElement(By.id("username"));
		element.clear();
		element.sendKeys(username);

		element = driver.findElement(By.id("passwd"));
		element.clear();
		element.sendKeys(password);

		element = driver.findElement(By.id("dob"));
		element.clear();
		element.sendKeys(dob);

		element = driver.findElement(By.cssSelector("input[type='submit']"));
		element.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Interrupted while waiting for login response.", e);
		}
	}

	@Test
	public void testLoginSuccess() {
		submitLogin("ahsan", "ahsan_pass", "2000-01-15");
		Assert.assertEquals("success", driver.getTitle());
	}

	@Test
	public void testLoginFailsForWrongPassword() {
		submitLogin("ahsan", "wrong_pass", "2000-01-15");
		Assert.assertEquals("fail", driver.getTitle());
	}

	@Test
	public void testLoginFailsForWrongDob() {
		submitLogin("ahsan", "ahsan_pass", "2000-01-16");
		Assert.assertEquals("fail", driver.getTitle());
	}

	@Test
	public void testLoginFailsForBlankUsername() {
		submitLogin("", "ahsan_pass", "2000-01-15");
		Assert.assertEquals("fail", driver.getTitle());
	}

	@Test
	public void testLoginFailsForInvalidDobFormat() {
		submitLogin("ahsan", "ahsan_pass", "15-01-2000");
		Assert.assertEquals("fail", driver.getTitle());
	}
}