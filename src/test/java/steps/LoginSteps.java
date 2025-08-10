package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.saucelabs.context.TestContext;
import org.saucelabs.enums.MenuName;
import org.saucelabs.pages.LoginPage;
import org.saucelabs.pages.NavigationBarPage;
import org.testng.Assert;

public class LoginSteps {

    private final LoginPage loginPage;
    private final NavigationBarPage navigationBarPage;
    private String loginCondition;

    public LoginSteps(TestContext context) {
        loginPage = new LoginPage(context);
        navigationBarPage = new NavigationBarPage(context);
    }

    @When("the user login with correct credentials")
    public void theUserLoginWithCorrectCredentials() {
        loginPage.inputEmail()
                .inputPassword()
                .andLogin();
    }

    @Then("the user successfully login should see the Logout menu in the navigation bar")
    public void theUserSuccessfullyLoginShouldSeeTheLogoutMenuInTheNavigationBar() throws InterruptedException {
        Assert.assertTrue(navigationBarPage.clickHamburgerMenu()
                .isMenuDisplayed(MenuName.LOGOUT));
    }

    @When("the user login with {string}")
    public void theUserLoginWith(String conditions) {
        loginCondition = conditions.toLowerCase().trim();
        switch (loginCondition) {
            case "blank credentials":
                loginPage.andLogin();
                break;
            case "blank username":
                loginPage.inputPassword()
                        .andLogin();
                break;
            case "blank password":
                loginPage.inputEmail()
                        .andLogin();
                break;
            case "random credentials":
                loginPage.inputRandomEmail()
                        .inputRandomPassword()
                        .andLogin();
                break;
            default:
                throw new IllegalArgumentException("Unknown condition: " + loginCondition);
        }
    }

    @Then("the user should see the error in login page")
    public void theUserShouldSeeTheErrorInLoginPage() throws InterruptedException {
        switch (loginCondition) {
            case "blank credentials":
            case "blank username":
                Assert.assertTrue(loginPage.isInputUsernameErrorDisplayed());
                break;
            case "blank password":
                Assert.assertTrue(loginPage.isInputPasswordErrorDisplayed());
                break;
            case "random credentials":
                Assert.assertFalse(navigationBarPage.clickHamburgerMenu()
                        .isMenuDisplayed(MenuName.LOGOUT));
                break;
            default:
                throw new IllegalArgumentException("Unknown condition: " + loginCondition);
        }
    }
}
