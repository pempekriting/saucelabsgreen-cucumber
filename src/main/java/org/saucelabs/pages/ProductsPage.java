package org.saucelabs.pages;

import org.openqa.selenium.By;
import org.saucelabs.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;

public class ProductsPage extends BasePage {

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/titleTV")
    private List<WebElement> listProducts;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/colorIV")
    private List<WebElement> listProductColors;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/plusIV")
    private WebElement btnPlusQty;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cartBt")
    private WebElement btnAddToCart;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/productTV")
    private WebElement hdrProduct;

    public ProductsPage(TestContext context) {
        super(context);
    }

    public List<WebElement> getListProducts() {
        return listProducts;
    }

    public ProductsPage chooseProduct(String productName) {

        By imgViaParent = By.xpath(
                "//android.widget.TextView[@content-desc='Product Title' and normalize-space(@text)='" + productName + "']" +
                        "/parent::android.view.ViewGroup/android.widget.ImageView[1]"
        );

        getDriver().findElement(imgViaParent).click();

        //Doesn't work properly, will back later
        /*       WebElement title = getListProducts().stream()
                .filter(el -> productName.equalsIgnoreCase(el.getText().trim()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product is not found: " + productName));

        title.findElement(By.xpath("./preceding-sibling::android.widget.ImageView[1]")).click();*/
        return this;
    }
    public ProductsPage chooseColor(String colorName) {
        listProductColors.stream().filter(element -> Objects.equals(getValueAttribute(element, "content-desc"), colorName)).findFirst().get().click();
        return this;
    }

    public ProductsPage inputQty(int quantity) {
        if (quantity > 1) {
            for (int i = 0; i < quantity - 1; i++) {
                btnPlusQty.click();
            }
        }
        return this;
    }

    public void andThenAddToCart() {
        btnAddToCart.click();
    }

    public WebElement getHdrProduct() {
        return hdrProduct;
    }
}