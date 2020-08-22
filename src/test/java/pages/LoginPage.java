package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{

    @FindBy(id = "_input-1")
    WebElement inputEmail;
    @FindBy(id = "_input-2")
    WebElement inputPassword;
    @FindBy(css = "[data-testid='auth-continue-button']")
    WebElement continueButton;
    @FindBy(css = "[data-testid='auth-login-button']")
    WebElement loginButton;
    @FindBy(css = "[data-testid='community-onboarding-continue']")
    WebElement onboardingButton;
    @FindBy(xpath = "//span[contains(text(), 'You’ve been signed in, welcome back!')]")
    WebElement successMessage;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(inputEmail));
        return null;
    }

    @Override
    public LoginPage openPage() {
        driver.get("https://my.whisk-dev.com/");
        isPageOpened();
        return this;
    }

    public LoginPage provideEmailAndContinue(String email) {
        inputEmail.sendKeys(email);
        continueButton.click();
        return this;
    }

    public LoginPage providePasswordAndLogin(String password) {
        wait.until(ExpectedConditions.visibilityOf(inputPassword));
        inputPassword.sendKeys(password);
        loginButton.click();
        return this;
    }

    public ShoppingPage onboardingAndVerifySuccess() {
        wait.until(ExpectedConditions.visibilityOf(onboardingButton));
        onboardingButton.click();
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return new ShoppingPage(driver);
    }


}
