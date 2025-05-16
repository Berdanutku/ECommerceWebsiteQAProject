package com.example;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage {

    WebDriver webDriver;
    WebDriverWait wait;

    public ProductsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait=new WebDriverWait(webDriver,Duration.ofSeconds(10));
        PageFactory.initElements(webDriver,this);
    }

    @FindBy(xpath = "//a[text()='Add to cart']")
    private WebElement addToCartButton;
    @FindBy(linkText = "View Cart")
    private WebElement viewCartButton;
    @FindBy(xpath = "//p[text()='Your product has been added to cart.']")
    private WebElement productAddedSuccessAlert;
    @FindBy(xpath = "//a[text()='View Product']")
    private WebElement viewProductButton;
    @FindBy(className = "product-information")
    private WebElement productDetailBox;
    @FindBy(id = "search_product")
    private WebElement productSearchBar;
    @FindBy(id = "submit_search")
    private WebElement productSearchButton;
    @FindBy(xpath = "//button[text()='Continue Shopping']")
    private WebElement continueShoppingButton;

    @Step("Add a product to the cart")
    public boolean addProductToTheCartSuccessfully(){
        WebElement addToCartBtn=wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        JavascriptExecutor js= (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 400);");
        addToCartBtn.click();
        WebElement productAddedSuccess=wait.until(ExpectedConditions.visibilityOf(productAddedSuccessAlert));
        takeScreenshot();
        WebElement continueShoppingBtn=wait.until(ExpectedConditions.visibilityOf(continueShoppingButton));
        continueShoppingBtn.click();
        return productAddedSuccess.isDisplayed();
    }

    @Step("View product details")
    public boolean viewProductDetailsSuccessfully(){
        WebElement viewProductBtn=wait.until(ExpectedConditions.visibilityOf(viewProductButton));
        JavascriptExecutor js= (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 400);");
        viewProductBtn.click();

        WebElement productDetail=wait.until(ExpectedConditions.visibilityOf(productDetailBox));
        takeScreenshot();
        return productDetail.isDisplayed();
    }
    @Step("Go to the cart from success alert for adding a product")
    public void viewCartFromAddProductSuccessAlert(){
        WebElement viewCartBtn=wait.until(ExpectedConditions.visibilityOf(viewCartButton));
        viewCartBtn.click();
    }
    @Step("Search product from search bar")
    public void searchProductFromSearchBar(){
        WebElement productSearch=wait.until(ExpectedConditions.visibilityOf(productSearchBar));
        WebElement productSearchBtn=wait.until(ExpectedConditions.visibilityOf(productSearchButton));

        productSearch.sendKeys("Blue Top");
        productSearchBtn.click();

        viewProductDetailsSuccessfully();
    }
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

}
