package org.saucelabs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.saucelabs.context.TestContext;
import org.saucelabs.enums.MenuName;

import java.util.List;
import java.util.NoSuchElementException;

public class NavigationBarPage extends BasePage{

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/menuIV")
    private WebElement hamburgerMenu;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cartIV")
    private WebElement cartButton;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cartTV")
    private WebElement cartTotalItems;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/itemTV")
    private List<WebElement> listOfMenu;

    public NavigationBarPage(TestContext context) {
        super(context);
    }

    public NavigationBarPage chooseMenu(MenuName menuName) {
        listOfMenu.stream().filter(webElement -> webElement.getText().equals(menuName.getCatalog()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Menu not found: " + menuName.getCatalog()))
                .click();
        return this;
    }

    public boolean isMenuDisplayed(MenuName menuName) {
        return listOfMenu.stream().filter(webElement -> webElement.getText().equals(menuName.getCatalog()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Menu not found: " + menuName.getCatalog()))
                .isDisplayed();
    }

    public boolean isCartButtonDisplayed() {
        return cartButton.isDisplayed();
    }

    public boolean isCartTotalItemsDisplayed() {
        return cartTotalItems.isDisplayed();
    }

    public int getCartTotalItems() {
        return Integer.parseInt(cartTotalItems.getText());
    }

    public NavigationBarPage clickHamburgerMenu() {
        hamburgerMenu.click();
        return this;
    }

    public void clickCartButton() {
        cartButton.click();
    }
}
