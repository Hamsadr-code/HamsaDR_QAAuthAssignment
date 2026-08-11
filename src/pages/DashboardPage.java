package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class DashboardPage extends BasePage {

    private By accountHeading =
            By.xpath("//h1[contains(text(),'My account')]");

    private By accountInfo =
            By.cssSelector(".account-page");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountPageDisplayed() {

        return isDisplayed(accountHeading);
    }

    public boolean isAccountInformationDisplayed() {

        return isDisplayed(accountInfo);
    }
}