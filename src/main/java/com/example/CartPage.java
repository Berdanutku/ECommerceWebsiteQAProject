package com.example;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    WebDriver webDriver;
    WebDriverWait wait;

    public CartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait=new WebDriverWait(webDriver,Duration.ofSeconds(10));
        PageFactory.initElements(webDriver,this);
    }

    @FindBy(xpath = "//a[text()='Proceed To Checkout']")
    private WebElement proceedToCheckoutButton;
    @FindBy(className = "cart_quantity_delete")
    private WebElement cartDeleteProductButton;
    @FindBy(id = "empty_cart")
    private WebElement cartIsEmptyAlert;
    @FindBy(xpath = "//a[text()='Place Order']")
    private WebElement placeOrderButton;
    @FindBy(name = "name_on_card")
    private WebElement nameOnCardField;
    @FindBy(name = "card_number")
    private WebElement cardNumberField;
    @FindBy(name = "cvc")
    private WebElement cvcField;
    @FindBy(name = "expiry_month")
    private WebElement expirationMonthDateField;
    @FindBy(name = "expiry_year")
    private WebElement expirationYearDateField;
    @FindBy(xpath = "//button[text()='Pay and Confirm Order']")
    private WebElement payAndConfirmOrderButton;
    @FindBy(xpath = "//p[text()='Congratulations! Your order has been confirmed!']")
    private WebElement paymentSuccessAlert;

    @Step("Make a product payment and order")
    public boolean makePaymentSuccessfully(){
        WebElement proceedToCheckoutBtn=wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutButton));
        proceedToCheckoutBtn.click();

        WebElement placeOrderBtn=wait.until(ExpectedConditions.visibilityOf(placeOrderButton));
        JavascriptExecutor js= (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 600);");
        placeOrderBtn.click();

        WebElement nameOnCard=wait.until(ExpectedConditions.visibilityOf(nameOnCardField));
        WebElement cardNumber=wait.until(ExpectedConditions.visibilityOf(cardNumberField));
        WebElement cvc=wait.until(ExpectedConditions.visibilityOf(cvcField));
        WebElement expirationMonth=wait.until(ExpectedConditions.visibilityOf(expirationMonthDateField));
        WebElement expirationYear=wait.until(ExpectedConditions.visibilityOf(expirationYearDateField));
        WebElement payAndConfirmOrderBtn=wait.until(ExpectedConditions.visibilityOf(payAndConfirmOrderButton));

        nameOnCard.sendKeys("Utku Turk");
        cardNumber.sendKeys("154745385246525");
        cvc.sendKeys("585");
        expirationMonth.sendKeys("05");
        expirationYear.sendKeys("29");

        js.executeScript("window.scrollBy(0, 200);");
        payAndConfirmOrderBtn.click();

        WebElement paymentSuccess=wait.until(ExpectedConditions.visibilityOf(paymentSuccessAlert));
        takeScreenshot();
        return paymentSuccess.isDisplayed();
    }

    @Step("Remove a product from cart")
    public boolean deleteProductFromCartSuccessfully(){
        WebElement cartDeleteProductBtn=wait.until(ExpectedConditions.visibilityOf(cartDeleteProductButton));
        cartDeleteProductBtn.click();

        WebElement cartIsEmpty=wait.until(ExpectedConditions.visibilityOf(cartIsEmptyAlert));
        takeScreenshot();
        return cartIsEmpty.isDisplayed();
    }
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

}
