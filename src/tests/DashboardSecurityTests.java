package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ScreenshotUtil;

public class DashboardSecurityTests {

    private WebDriver driver;

    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeMethod
    public void setUp() {

        ConfigReader.loadProperties();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));

        loginPage =
                new LoginPage(driver);

        dashboardPage =
                new DashboardPage(driver);
    }

    @Test
    public void testAccountAccessAfterLogin() {

        driver.get(
                ConfigReader.get("login.url")
        );

        loginPage.login(
                ConfigReader.get("valid.email"),
                ConfigReader.get("valid.password")
        );

        driver.get(
                ConfigReader.get("account.url")
        );

        Assert.assertTrue(
        		dashboardPage.isAccountPageDisplayed(),
                "My Account page was not displayed"
        );

        ScreenshotUtil.captureScreenshot(
                driver,
                "10_Account_Access_After_Login"
        );
    }

    @Test
    public void testAccountWithoutLogin() {

        driver.get(
                ConfigReader.get("account.url")
        );

        Assert.assertFalse(
        		dashboardPage.isAccountPageDisplayed(),
                "Unauthenticated user accessed My Account"
        );

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("/login"),
                "User was not redirected to login"
        );

        ScreenshotUtil.captureScreenshot(
                driver,
                "11_Account_Without_Login"
        );
    }

    @Test
    public void testAccountAfterLogout() {

        driver.get(
                ConfigReader.get("login.url")
        );

        loginPage.login(
                ConfigReader.get("valid.email"),
                ConfigReader.get("valid.password")
        );

        Assert.assertTrue(
                loginPage.isLoggedIn(),
                "Login was unsuccessful"
        );

        loginPage.clickLogout();

        driver.get(
                ConfigReader.get("account.url")
        );

        Assert.assertFalse(
                dashboardPage.isAccountPageDisplayed(),
                "Account page is accessible after logout"
        );

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("/login"),
                "User was not redirected to login after logout"
        );

        ScreenshotUtil.captureScreenshot(
                driver,
                "12_Account_After_Logout"
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}