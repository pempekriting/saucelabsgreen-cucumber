package org.saucelabs.pages;

import org.saucelabs.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/loginBtn")
    private WebElement btnLogin;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/nameET")
    private WebElement inpUsername;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/nameErrorTV")
    private WebElement inpUserNameError;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/passwordET")
    private WebElement inpPassword;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/passwordErrorTV")
    private WebElement inpPasswordError;

    public LoginPage(TestContext context) {
        super(context);
    }

    public LoginPage inputEmail() {
        inpUsername.sendKeys("bod@example.com");
        return this;
    }

    public LoginPage inputRandomEmail() {
        inpUsername.sendKeys(getFaker().internet().emailAddress());
        return this;
    }

    public LoginPage inputRandomPassword() {
        inpPassword.sendKeys(getFaker().internet().password());
        return this;
    }

    public LoginPage inputPassword() {
        inpPassword.sendKeys("10203040");
        return this;
    }

    public void andLogin() {
        btnLogin.click();
    }

    public boolean isInputUsernameErrorDisplayed() {
        return inpUserNameError.isDisplayed();
    }

    public boolean isInputPasswordErrorDisplayed() {
        return inpPasswordError.isDisplayed();
    }
}
