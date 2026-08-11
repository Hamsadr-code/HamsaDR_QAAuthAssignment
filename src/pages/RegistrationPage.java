package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class RegistrationPage extends BasePage {

    private By maleRadioButton = By.id("gender-male");
    private By femaleRadioButton = By.id("gender-female");
    private By firstNameField = By.id("FirstName");
    private By lastNameField = By.id("LastName");
    private By emailField = By.id("Email");
    private By passwordField = By.id("Password");
    private By confirmPasswordField = By.id("ConfirmPassword");
    private By registerButton = By.id("register-button");
    private By registrationResult = By.cssSelector(".result");
    private By validationSummary = By.cssSelector(".validation-summary-errors");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }
    public void selectMale() {
        click(maleRadioButton);
    }
    public void selectFemale() {
        click(femaleRadioButton);
    }
    public void enterFirstName(String firstName) {
        type(firstNameField, firstName);
    }
    public void enterLastName(String lastName) {
        type(lastNameField, lastName);
    }
    public void enterEmail(String email) {
        type(emailField, email);
    }
    public void enterPassword(String password) {
        type(passwordField, password);
    }
    public void enterConfirmPassword(String password) {
        type(confirmPasswordField, password);
    }
    public void clickRegister() {
        click(registerButton);
    }

    public void register(
            String gender,
            String firstName,
            String lastName,
            String email,
            String password) {

        if (gender.equalsIgnoreCase("male")) {
            selectMale();
        } else if (gender.equalsIgnoreCase("female")) {
            selectFemale();
        } else {
            throw new IllegalArgumentException("Gender must be Male or Female");
        }

        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        clickRegister();
    }

    public boolean isRegistrationSuccessful() {
        return isDisplayed(registrationResult);
    }
    public boolean isValidationMessageDisplayed() {
        return isDisplayed(validationSummary);
    }
    public String getRegistrationResult() {
        return getText(registrationResult);
    }
    public String getValidationMessage() {
        return getText(validationSummary);
    }
}