package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.RegistrationPage;
import utils.ConfigReader;
import utils.ScreenshotUtil;

public class RegistrationTests {

    private WebDriver driver;

    private RegistrationPage registrationPage;
    private LoginPage loginPage;

    private String testEmail;
    private String testPassword;

    @BeforeMethod
    public void setUp() {
        ConfigReader.loadProperties();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        testPassword = ConfigReader.get("test.password");
        testEmail = "hamsa.qa" + System.currentTimeMillis() + "@example.com";
    }
    @Test
    public void testCompleteRegistrationLoginAndDuplicateFlow() {
        driver.get(ConfigReader.get("register.url"));
        registrationPage.register(ConfigReader.get("test.gender"),ConfigReader.get("test.firstName"),ConfigReader.get("test.lastName"),testEmail,testPassword);
        Assert.assertTrue(registrationPage.isRegistrationSuccessful(),"User registration was not successful");
        ScreenshotUtil.captureScreenshot(driver,"01_Successful_Registration");
        driver.get(ConfigReader.get("login.url"));
        loginPage.login(testEmail,testPassword);
        Assert.assertTrue(loginPage.isLoggedIn(),"User could not login with registered credentials");
        ScreenshotUtil.captureScreenshot(driver,"02_Successful_Login");
        loginPage.clickLogout();
        driver.get(ConfigReader.get("register.url"));
        registrationPage.register(ConfigReader.get("test.gender"),ConfigReader.get("test.firstName"),ConfigReader.get("test.lastName"),testEmail,testPassword);
        Assert.assertTrue( registrationPage.isValidationMessageDisplayed(),"Duplicate email validation was not displayed");
        ScreenshotUtil.captureScreenshot(driver,"03_Duplicate_Email");
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}