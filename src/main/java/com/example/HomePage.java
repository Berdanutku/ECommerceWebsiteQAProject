package com.example;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    WebDriver webDriver;
    WebDriverWait wait;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait=new WebDriverWait(webDriver,Duration.ofSeconds(10));
        PageFactory.initElements(webDriver,this);
    }
    @FindBy(xpath = "//a[text()=' Logged in as ']")
    private WebElement loggedIn;
    @FindBy(xpath = "//a[text()=' Products']")
    private WebElement productsButton;
    @FindBy(xpath = "//a[text()=' Signup / Login']")
    private WebElement loginSignupButton;
    @FindBy(xpath = "//a[text()=' Cart']")
    private WebElement cartButton;
    @FindBy(xpath = "//a[text()=' Contact us']")
    private WebElement contactUsButton;
    @FindBy(xpath = "//a[text()=' Logout']")
    private WebElement logoutButton;
    @FindBy(xpath = "//a[text()=' Delete Account']")
    private WebElement deleteAccountButton;
    @FindBy(xpath = "//p[text()='Your account has been permanently deleted!']")
    private WebElement deleteAccountSuccessAlert;

    @Step("Go to the Login / Signup Page")
    public void goToTheLoginSignupPage(){
        WebElement loginSignupBtn=wait.until(ExpectedConditions.visibilityOf(loginSignupButton));
        loginSignupBtn.click();
    }
    @Step("Go to the Products Page")
    public void goToTheProductsPage(){
        WebElement productsBtn=wait.until(ExpectedConditions.visibilityOf(productsButton));
        productsBtn.click();
    }
    @Step("Go to the Cart Page")
    public void goToTheCartPage(){
        WebElement cartBtn=wait.until(ExpectedConditions.visibilityOf(cartButton));
        cartBtn.click();
    }
    @Step("Go to the Contact Us Page")
    public void goToTheContactUsPage(){
        WebElement contactUsBtn=wait.until(ExpectedConditions.visibilityOf(contactUsButton));
        contactUsBtn.click();
    }
    @Step("Check user logged or signed in to the website")
    public boolean isUserLoggedOrSignIn(){
        WebElement loggedInIcon=wait.until(ExpectedConditions.visibilityOf(loggedIn));
        takeScreenshot();
        return loggedInIcon.isDisplayed();
    }
    @Step("Logout from the website")
    public void logoutAccountSuccessfully(){
        WebElement logoutBtn=wait.until(ExpectedConditions.visibilityOf(logoutButton));
        logoutBtn.click();
    }
    @Step("Delete the account")
    public boolean deleteAccountSuccessfully(){
        WebElement deleteAccountBtn=wait.until(ExpectedConditions.visibilityOf(deleteAccountButton));
        deleteAccountBtn.click();

        WebElement deleteAccountSuccess=wait.until(ExpectedConditions.visibilityOf(deleteAccountSuccessAlert));
        takeScreenshot();
        return deleteAccountSuccess.isDisplayed();
    }
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

}
