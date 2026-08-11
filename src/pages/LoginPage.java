package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class LoginPage extends BasePage {

    private By emailField =
            By.id("Email");

    private By passwordField =
            By.id("Password");

    private By rememberMeCheckbox =
            By.id("RememberMe");

    private By loginButton =
            By.cssSelector("input[value='Log in']");

    private By validationSummary =
            By.cssSelector(".validation-summary-errors");

    private By loginError =
            By.cssSelector(".message-error");

    private By myAccountLink =
            By.cssSelector("a.account");

    private By logoutLink =
            By.cssSelector("a.ico-logout");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void selectRememberMe() {

        if (!driver.findElement(rememberMeCheckbox).isSelected()) {
            click(rememberMeCheckbox);
        }
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void login(String email, String password) {

        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public void loginWithRememberMe(
            String email,
            String password) {

        enterEmail(email);
        enterPassword(password);
        selectRememberMe();
        clickLogin();
    }

    public boolean isValidationMessageDisplayed() {
        return isDisplayed(validationSummary);
    }

    public boolean isLoginErrorDisplayed() {
        return isDisplayed(loginError);
    }

    public boolean isLoggedIn() {
        return isDisplayed(myAccountLink);
    }

    public void clickLogout() {
        click(logoutLink);
    }
}