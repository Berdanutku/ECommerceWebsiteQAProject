package com.example;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginSignupPage {

    WebDriver webDriver;
    WebDriverWait wait;

    public LoginSignupPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait=new WebDriverWait(webDriver,Duration.ofSeconds(10));
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(name = "email")
    private WebElement loginEmailField;
    @FindBy(name = "password")
    private WebElement loginPasswordField;
    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginButton;
    @FindBy(name = "name")
    private WebElement signupNameField;
    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement signupEmailField;
    @FindBy(xpath = "//button[text()='Signup']")
    private WebElement signupButton;
    @FindBy(id = "id_gender1")
    private WebElement signupGenderRadioButton;
    @FindBy(id = "password")
    private WebElement signupPasswordField;
    @FindBy(id = "first_name")
    private WebElement signupFirstNameField;
    @FindBy(id = "last_name")
    private WebElement signupLastNameField;
    @FindBy(id = "address1")
    private WebElement signupAddressField;
    @FindBy(id = "state")
    private WebElement signupStateField;
    @FindBy(id = "city")
    private WebElement signupCityField;
    @FindBy(id = "zipcode")
    private WebElement signupZipcodeField;
    @FindBy(id = "mobile_number")
    private WebElement signupMobileNumberField;
    @FindBy(xpath = "//button[text()='Create Account']")
    private WebElement signupCreateAccountButton;
    @FindBy(xpath = "//a[text()='Continue']")
    private WebElement signupSuccessAlertContinueButton;

    @Step("Login to the website")
    public void loginToTheWebsiteSuccessfully() {
        WebElement loginEmail = wait.until(ExpectedConditions.visibilityOf(loginEmailField));
        WebElement loginPassword = wait.until(ExpectedConditions.visibilityOf(loginPasswordField));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));

        loginEmail.sendKeys("utku@gmail.com");
        loginPassword.sendKeys("utku123456789");
        loginBtn.click();

    }
    @Step("Sign up to the website")
    public void signupToTheWebsiteSuccessfully(){
        WebElement signupName=wait.until(ExpectedConditions.visibilityOf(signupNameField));
        WebElement signupEmail=wait.until(ExpectedConditions.visibilityOf(signupEmailField));
        WebElement signupBtn=wait.until(ExpectedConditions.elementToBeClickable(signupButton));

        signupName.sendKeys("utku123");
        signupEmail.sendKeys("utku@gmail.com");
        signupBtn.click();

        WebElement signupGenderBtn=wait.until(ExpectedConditions.visibilityOf(signupGenderRadioButton));
        WebElement signupPassword=wait.until(ExpectedConditions.visibilityOf(signupPasswordField));
        WebElement signupFirstName=wait.until(ExpectedConditions.visibilityOf(signupFirstNameField));
        WebElement signupLastName=wait.until(ExpectedConditions.visibilityOf(signupLastNameField));
        WebElement signupAddress=wait.until(ExpectedConditions.visibilityOf(signupAddressField));
        WebElement signupState=wait.until(ExpectedConditions.visibilityOf(signupStateField));
        WebElement signupCity=wait.until(ExpectedConditions.visibilityOf(signupCityField));
        WebElement signupZipcode=wait.until(ExpectedConditions.visibilityOf(signupZipcodeField));
        WebElement signupMobileNumber=wait.until(ExpectedConditions.visibilityOf(signupMobileNumberField));
        WebElement signupCreateAccountBtn=wait.until(ExpectedConditions.visibilityOf(signupCreateAccountButton));

        signupGenderBtn.click();
        signupPassword.sendKeys("utku123456789");
        signupFirstName.sendKeys("Utku");
        signupLastName.sendKeys("Turk");
        signupAddress.sendKeys("Karsiyaka/Izmir");
        signupState.sendKeys("Karsiyaka");
        signupCity.sendKeys("Izmir");
        signupZipcode.sendKeys("35555");
        signupMobileNumber.sendKeys("5555555555");

        JavascriptExecutor js= (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 400);");
        signupCreateAccountBtn.click();

        WebElement signupSuccessContinueBtn=wait.until(ExpectedConditions.visibilityOf(signupSuccessAlertContinueButton));
        signupSuccessContinueBtn.click();
        takeScreenshot();
    }
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

}