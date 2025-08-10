package org.saucelabs.pages;

import io.appium.java_client.AppiumBy;
import org.saucelabs.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.math.BigDecimal;
import java.util.List;

public class MyCartPage extends BasePage {

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cartBt")
    private WebElement btnProceedToCheckout;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/titleTV")
    private List<WebElement> listItemNamesInCart;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/priceTV")
    private List<WebElement> listPricesInCart;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/itemNumberTV")
    private WebElement itemsTotalInCart;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/totalAmountTV")
    private WebElement totalAmount;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/amountTV")
    private WebElement shippingAmount;

    public MyCartPage(TestContext context) {
        super(context);
    }

    public void scrollToShippingAmount() {
        getDriver().findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"DHL Standard Delivery\"))"
        ));
    }

    public void assertListItemsNameInCart(List<String> listItems) {
        for (int i = 0; i < listItemNamesInCart.size(); i++) {
            Assert.assertEquals(listItemNamesInCart.get(i).getText(), listItems.get(i));
        }
    }

    public void assertTotalAmountInCheckout() {
        BigDecimal totalAmountInCart = listPricesInCart.stream()
                .map(webElement -> webElement.getText().replace('\u00A0',' ')
                        .replaceAll("[^0-9.,]", "")
                        .replace(",", "."))
                .map(BigDecimal::new)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        int qty = Integer.parseInt(itemsTotalInCart.getText().replaceAll("\\D+", ""));
        scrollToShippingAmount();
        BigDecimal shipping = new BigDecimal(shippingAmount.getText().replaceAll("[^0-9.,]", "").replace(",", "."));
        BigDecimal total = totalAmountInCart.multiply(BigDecimal.valueOf(qty)).add(shipping);
        Assert.assertEquals(total, new BigDecimal(totalAmount.getText().replaceAll("[^0-9.,]", "").replace(",", ".")));
    }

    public List<WebElement> getListItemNamesInCart() {
        return listItemNamesInCart;
    }

    public void clickProceedToCheckoutButton() {
        btnProceedToCheckout.click();
    }
}
