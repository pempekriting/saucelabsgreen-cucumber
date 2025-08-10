package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.saucelabs.context.TestContext;
import org.saucelabs.enums.MenuName;
import org.saucelabs.pages.LoginPage;
import org.saucelabs.pages.NavigationBarPage;
import org.testng.Assert;

public class GeneralSteps {

    private final NavigationBarPage navigationBarPage;
    private final LoginPage loginPage;

    public GeneralSteps(TestContext testContext) {
        navigationBarPage = new NavigationBarPage(testContext);
        loginPage = new LoginPage(testContext);
    }

    @Given("the app is launched")
    public void theAppIsLaunched() {
        Assert.assertTrue(navigationBarPage.isCartButtonDisplayed());
    }

    @And("navigate to login page")
    public void navigateToLoginPage() throws InterruptedException {
        Assert.assertTrue(navigationBarPage.clickHamburgerMenu()
                .isMenuDisplayed(MenuName.LOGIN));
        navigationBarPage.chooseMenu(MenuName.LOGIN);
    }

    @Given("the user is {string}")
    public void theUserIs(String conditions) throws InterruptedException {
        if(conditions.equalsIgnoreCase("with login")) {
            Assert.assertTrue(navigationBarPage.clickHamburgerMenu()
                    .isMenuDisplayed(MenuName.LOGIN));
            navigationBarPage.chooseMenu(MenuName.LOGIN);
            loginPage.inputEmail()
                    .inputPassword()
                    .andLogin();
        }
    }
}
