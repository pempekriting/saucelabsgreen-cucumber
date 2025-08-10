package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.saucelabs.context.TestContext;
import org.saucelabs.pages.*;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class OrderSteps {

    private final ProductsPage productsPage;
    private final NavigationBarPage navigationBarPage;
    private final LoginPage loginPage;
    private final CheckoutPage checkoutPage;
    private final MyCartPage myCartPage;
    List<String> listItemsInCart = new ArrayList<>();
    String productName;

    public OrderSteps(TestContext testContext) {
        productsPage = new ProductsPage(testContext);
        navigationBarPage = new NavigationBarPage(testContext);
        loginPage = new LoginPage(testContext);
        checkoutPage = new CheckoutPage(testContext);
        myCartPage = new MyCartPage(testContext);
    }

    @And("the user is on the catalog")
    public void theUserIsOnTheCatalog() {
        Assert.assertTrue(productsPage.getHdrProduct().isDisplayed());
    }

    @When("the user adds an item to the cart")
    public void theUserAddsAnItemToTheCart() {
        int quantity = 1;
        productName = "Sauce Labs Backpack";
        listItemsInCart.add(productName);
        productsPage.chooseProduct(productName)
                .chooseColor("Blue color")
                .inputQty(quantity)
                .andThenAddToCart();
        Assert.assertTrue(navigationBarPage.isCartTotalItemsDisplayed());
        Assert.assertEquals(navigationBarPage.getCartTotalItems(), quantity);
        navigationBarPage.clickCartButton();
    }

    @And("proceeds to checkout {string}")
    public void proceedsToCheckout(String conditions) {
        myCartPage.assertListItemsNameInCart(listItemsInCart);
        myCartPage.clickProceedToCheckoutButton();

        if(conditions.equalsIgnoreCase("without login")) {
            loginPage.inputEmail()
                    .inputPassword()
                    .andLogin();
        }

        checkoutPage.inputFullName()
                .inputAddressLineOne()
                .inputCityName()
                .inputZipCode()
                .inputCountryName()
                .andClickToPaymentButton();

        checkoutPage.inputFullNameCard()
                .inputCardNumber()
                .inputExpirationDate()
                .inputSecurityCode()
                .andClickReviewOrderButton();

        myCartPage.assertTotalAmountInCheckout();
        checkoutPage.clickPlaceOrderButton();
    }

    @Then("the purchase should be successful")
    public void thePurchaseShouldBeSuccessful() {
        Assert.assertTrue(checkoutPage.isCheckoutCompleteBannerDisplayed());
    }

    @And("the user fill the shipping address with {string}")
    public void theUserFillTheShippingAddressWith(String conditions) {
        myCartPage.assertListItemsNameInCart(listItemsInCart);
        myCartPage.clickProceedToCheckoutButton();

        switch (conditions) {
            case "mandatory field only":
                checkoutPage.inputFullName()
                        .inputAddressLineOne()
                        .inputCityName()
                        .inputZipCode()
                        .inputCountryName()
                        .andClickToPaymentButton();
                break;
            case "all field":
                checkoutPage.inputFullName()
                        .inputAddressLineOne()
                        .inputAddressLineTwo()
                        .inputCityName()
                        .inputStateOrRegion()
                        .inputZipCode()
                        .inputCountryName()
                        .andClickToPaymentButton();
                break;
            case "optional field only":
                checkoutPage.inputAddressLineTwo()
                        .inputStateOrRegion()
                        .andClickToPaymentButton();
                break;

            default:
                checkoutPage.andClickToPaymentButton();
        }
    }

    @Then("the user should received results conditions in checkout page based on {string} input")
    public void theUserShouldReceivedResultsConditionsInCheckoutPageBasedOnInput(String conditions) {
        switch (conditions) {
            case "mandatory field only":
            case "all field":
                Assert.assertTrue(checkoutPage.isInPaymentMethodPage());
                break;
            case "optional field only":
            default:
                checkoutPage.checkAllMandatoryFieldErrorDisplayed();
                break;
        }
    }
}
