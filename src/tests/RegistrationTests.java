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

        // Load configuration
        ConfigReader.loadProperties();

        // Launch Chrome
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));

        // Initialize Page Objects
        registrationPage =
                new RegistrationPage(driver);

        loginPage =
                new LoginPage(driver);

        // Get password from configuration
        testPassword =
                ConfigReader.get("test.password");

        // Generate a unique email
        testEmail =
                "hamsa.qa"
                + System.currentTimeMillis()
                + "@example.com";
    }

    // ============================================================
    // REG-001
    // Complete Registration → Login → Logout → Duplicate Email
    // ============================================================

    @Test
    public void testCompleteRegistrationLoginAndDuplicateFlow() {

        // --------------------------------------------------------
        // STEP 1: Open Demo Web Shop Registration Page
        // --------------------------------------------------------

        driver.get(
                ConfigReader.get("register.url")
        );

        // --------------------------------------------------------
        // STEP 2: Register a New User
        // --------------------------------------------------------

        registrationPage.register(
                ConfigReader.get("test.gender"),
                ConfigReader.get("test.firstName"),
                ConfigReader.get("test.lastName"),
                testEmail,
                testPassword
        );

        // --------------------------------------------------------
        // STEP 3: Verify Registration Was Successful
        // --------------------------------------------------------

        Assert.assertTrue(
                registrationPage.isRegistrationSuccessful(),
                "User registration was not successful"
        );

        ScreenshotUtil.captureScreenshot(
                driver,
                "01_Successful_Registration"
        );

        // --------------------------------------------------------
        // STEP 4: Open Login Page
        // --------------------------------------------------------

        driver.get(
                ConfigReader.get("login.url")
        );

        // --------------------------------------------------------
        // STEP 5: Login Using Newly Registered Credentials
        // --------------------------------------------------------

        loginPage.login(
                testEmail,
                testPassword
        );

        // --------------------------------------------------------
        // STEP 6: Verify Successful Login
        // --------------------------------------------------------

        Assert.assertTrue(
                loginPage.isLoggedIn(),
                "User could not login with registered credentials"
        );

        ScreenshotUtil.captureScreenshot(
                driver,
                "02_Successful_Login"
        );

        // --------------------------------------------------------
        // STEP 7: Logout
        // --------------------------------------------------------

        loginPage.clickLogout();

        // --------------------------------------------------------
        // STEP 8: Open Registration Page Again
        // --------------------------------------------------------

        driver.get(
                ConfigReader.get("register.url")
        );

        // --------------------------------------------------------
        // STEP 9: Try Registering With SAME EMAIL
        // --------------------------------------------------------

        registrationPage.register(
                ConfigReader.get("test.gender"),
                ConfigReader.get("test.firstName"),
                ConfigReader.get("test.lastName"),
                testEmail,
                testPassword
        );

        // --------------------------------------------------------
        // STEP 10: Verify Duplicate Email Validation
        // --------------------------------------------------------

        Assert.assertTrue(
                registrationPage.isValidationMessageDisplayed(),
                "Duplicate email validation was not displayed"
        );

        ScreenshotUtil.captureScreenshot(
                driver,
                "03_Duplicate_Email"
        );
    }

    // ============================================================
    // CLEANUP
    // ============================================================

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}