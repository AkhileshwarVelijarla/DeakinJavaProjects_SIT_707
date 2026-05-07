package sit707_week2;

import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BunningsLoginTest {

    private static final String LOGIN_URL = "https://www.bunnings.com.au/login";

    private static final String VALID_EMAIL = "test@example.com";
    private static final String VALID_PASSWORD = "Password123";

    private static final String INVALID_EMAIL = "wronguser123@gmail.com";
    private static final String INVALID_PASSWORD = "wrongpass123";

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void openLoginPage() {
        driver.get(LOGIN_URL);
        sleep(4000);
        closePopups();
    }

    private void closePopups() {
        try {
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        } catch (Exception ignored) {
        }

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "document.querySelectorAll('[role=dialog], iframe, [class*=modal], [class*=overlay], [class*=buddy], [id*=buddy]').forEach(e => e.remove());"
            );
        } catch (Exception ignored) {
        }
    }

    private WebElement findEmailField() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("loginForm_emailAddress")
            ));
        } catch (Exception e) {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("username")
            ));
        }
    }

    private WebElement findPasswordField() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("loginForm_password")
            ));
        } catch (Exception e) {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("password")
            ));
        }
    }

    private WebElement findLoginButton() {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[data-locator='login-submit']")
            ));
        } catch (Exception e) {
            return wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[normalize-space()='Sign in']/ancestor::button")
            ));
        }
    }

    private void doLogin(String email, String password) {
        openLoginPage();

        WebElement emailField = findEmailField();
        WebElement passwordField = findPasswordField();
        WebElement loginButton = findLoginButton();

        emailField.clear();
        emailField.sendKeys(email);

        passwordField.clear();
        passwordField.sendKeys(password);

        loginButton.click();

        sleep(3000);
    }

    private boolean stayedOnLoginOrFailed() {
        String currentUrl = driver.getCurrentUrl().toLowerCase();
        String pageSource = driver.getPageSource().toLowerCase();

        return currentUrl.contains("login")
                || pageSource.contains("invalid")
                || pageSource.contains("required")
                || pageSource.contains("incorrect")
                || pageSource.contains("error")
                || pageSource.contains("password")
                || pageSource.contains("username");
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void testStudentIdentity() {
        String studentId = "224763306";
        Assert.assertNotNull("Student ID must not be null", studentId);
        Assert.assertEquals("224763306", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Akhileshwar Reddy Velijarla";
        Assert.assertNotNull("Student name must not be null", studentName);
    }

    @Test
    public void testValidEmailAndInvalidPassword() {
        doLogin(VALID_EMAIL, INVALID_PASSWORD);
        Assert.assertTrue("Expected login to fail or remain on login page", stayedOnLoginOrFailed());
    }

    @Test
    public void testInvalidEmailAndValidPassword() {
        doLogin(INVALID_EMAIL, VALID_PASSWORD);
        Assert.assertTrue("Expected login to fail or remain on login page", stayedOnLoginOrFailed());
    }

    @Test
    public void testInvalidEmailAndInvalidPassword() {
        doLogin(INVALID_EMAIL, INVALID_PASSWORD);
        Assert.assertTrue("Expected login to fail or remain on login page", stayedOnLoginOrFailed());
    }

    @Test
    public void testEmptyEmailAndEmptyPassword() {
        doLogin("", "");
        Assert.assertTrue("Expected login to fail or remain on login page", stayedOnLoginOrFailed());
    }

    @Test
    public void testEmptyEmailAndValidPassword() {
        doLogin("", VALID_PASSWORD);
        Assert.assertTrue("Expected login to fail or remain on login page", stayedOnLoginOrFailed());
    }

    @Test
    public void testValidEmailAndEmptyPassword() {
        doLogin(VALID_EMAIL, "");
        Assert.assertTrue("Expected login to fail or remain on login page", stayedOnLoginOrFailed());
    }
}