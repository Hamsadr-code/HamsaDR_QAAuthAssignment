package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.ConfigReader;
import utils.ScreenshotUtil;

public class LoginTests {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {

        ConfigReader.loadProperties();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));

        loginPage =
                new LoginPage(driver);
    }

    @Test
    public void testSuccessfulLogin() {

        driver.get(
                ConfigReader.get("login.url")
        );

        loginPage.login(
                ConfigReader.get("valid.email"),
                ConfigReader.get("valid.password")
        );

        Assert.assertTrue(
                loginPage.isLoggedIn(),
                "User was not logged in successfully"
        );

        ScreenshotUtil.captureScreenshot(
                driver,
                "06_Successful_Login"
        );
    }

    @Test
    public void testInvalidPassword() {

        driver.get(
                ConfigReader.get("login.url")
        );

        loginPage.login(
                ConfigReader.get("valid.email"),
                "WrongPassword123"
        );

        Assert.assertTrue(
                loginPage.isLoginErrorDisplayed(),
                "Invalid password error was not displayed"
        );

        ScreenshotUtil.captureScreenshot(
                driver,
                "07_Invalid_Password"
        );
    }

    @Test
    public void testInvalidEmail() {

        driver.get(
                ConfigReader.get("login.url")
        );

        loginPage.login(
                "invalid-email@example.com",
                ConfigReader.get("valid.password")
        );

        Assert.assertTrue(
                loginPage.isLoginErrorDisplayed(),
                "Invalid login error was not displayed"
        );

        ScreenshotUtil.captureScreenshot(
                driver,
                "08_Invalid_Email_Login"
        );
    }

    @Test
    public void testEmptyCredentials() {

        driver.get(
                ConfigReader.get("login.url")
        );

        loginPage.clickLogin();

        Assert.assertTrue(
                loginPage.isValidationMessageDisplayed(),
                "Empty credential validation was not displayed"
        );

        ScreenshotUtil.captureScreenshot(
                driver,
                "09_Empty_Login"
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}