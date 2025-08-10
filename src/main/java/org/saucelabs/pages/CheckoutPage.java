package org.saucelabs.pages;

import org.saucelabs.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CheckoutPage extends BasePage {

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/fullNameET")
    private WebElement inpFullName;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/fullNameErrorTV")
    private WebElement inpFullNameError;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/address1ET")
    private WebElement inpAddressLineOne;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/address1ErrorTV")
    private WebElement inpAddressLineOneError;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/address2ET")
    private WebElement inpAddressLineTwo;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cityET")
    private WebElement inpCity;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cityErrorTV")
    private WebElement inpCityError;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/stateET")
    private WebElement inpStateOrRegion;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/zipET")
    private WebElement inpZipCode;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/zipErrorTV")
    private WebElement inpZipCodeError;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/countryET")
    private WebElement inpCountry;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/countryErrorTV")
    private WebElement inpCountryError;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/paymentBtn")
    private WebElement btnToPayment;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/nameET")
    private WebElement inpFullNameCard;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cardNumberET")
    private WebElement inpCardNumber;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/expirationDateET")
    private WebElement inpExpirationDate;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/securityCodeET")
    private WebElement inpSecurityCode;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/paymentBtn")
    private WebElement btnReviewOrder;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/completeTV")
    private WebElement checkoutCompleteBanner;
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/enterPaymentMethodTV")
    private WebElement hdrEnterPaymentMethod;

    public CheckoutPage(TestContext context) {
        super(context);
    }

    public CheckoutPage inputFullName() {
        inpFullName.sendKeys(getFaker().name().fullName());
        return this;
    }

    public CheckoutPage inputAddressLineOne() {
        inpAddressLineOne.sendKeys(getFaker().address().fullAddress());
        return this;
    }

    public CheckoutPage inputAddressLineTwo() {
        inpAddressLineTwo.sendKeys(getFaker().address().secondaryAddress());
        return this;
    }

    public CheckoutPage inputCityName() {
        inpCity.sendKeys(getFaker().address().cityName());
        return this;
    }

    public CheckoutPage inputStateOrRegion() {
        inpStateOrRegion.sendKeys(getFaker().address().state());
        return this;
    }

    public CheckoutPage inputZipCode() {
        inpZipCode.sendKeys(getFaker().address().zipCode());
        return this;
    }

    public CheckoutPage inputCountryName() {
        inpCountry.sendKeys(getFaker().address().country());
        return this;
    }

    public CheckoutPage inputFullNameCard() {
        inpFullNameCard.sendKeys(getFaker().name().fullName());
        return this;
    }

    public boolean isInPaymentMethodPage() {
        return hdrEnterPaymentMethod.isDisplayed();
    }

    public CheckoutPage inputCardNumber() {
        inpCardNumber.sendKeys(getFaker().finance().creditCard());
        return this;
    }

    public CheckoutPage inputExpirationDate() {
        inpExpirationDate.sendKeys("1130");
        return this;
    }

    public CheckoutPage inputSecurityCode() {
        inpSecurityCode.sendKeys(getFaker().number().digits(3));
        return this;
    }

    public void andClickReviewOrderButton() {
        btnReviewOrder.click();
    }

    public void andClickToPaymentButton() {
        btnToPayment.click();
    }

    public void clickPlaceOrderButton() {
        btnReviewOrder.click();
    }

    public boolean isCheckoutCompleteBannerDisplayed() {
        return checkoutCompleteBanner.isDisplayed();
    }

    public void checkAllMandatoryFieldErrorDisplayed() {
        Assert.assertTrue(inpFullNameError.isDisplayed());
        Assert.assertTrue(inpAddressLineOneError.isDisplayed());
        Assert.assertTrue(inpCityError.isDisplayed());
        Assert.assertTrue(inpZipCodeError.isDisplayed());
        Assert.assertTrue(inpCountryError.isDisplayed());
    }
}
