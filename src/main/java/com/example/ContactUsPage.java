package com.example;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactUsPage {

    WebDriver webDriver;
    WebDriverWait wait;

    public ContactUsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait=new WebDriverWait(webDriver,Duration.ofSeconds(10));
        PageFactory.initElements(webDriver,this);
    }

    @FindBy(name = "name")
    WebElement contactNameField;
    @FindBy(name = "email")
    WebElement contactEmailField;
    @FindBy(name = "subject")
    WebElement contactSubjectField;
    @FindBy(name = "message")
    WebElement contactMessageField;
    @FindBy(name = "submit")
    WebElement contactSubmitButton;
    @FindBy(xpath = "//div[text()='Success! Your details have been submitted successfully.']")
    WebElement contactSuccessAlert;

    @Step("Send a contact message")
    public boolean sendContactMessageSuccessfully(){
        WebElement contactName=wait.until(ExpectedConditions.visibilityOf(contactNameField));
        WebElement contactEmail=wait.until(ExpectedConditions.visibilityOf(contactEmailField));
        WebElement contactSubject=wait.until(ExpectedConditions.visibilityOf(contactSubjectField));
        WebElement contactMessage=wait.until(ExpectedConditions.visibilityOf(contactMessageField));
        WebElement contactSubmitBtn=wait.until(ExpectedConditions.visibilityOf(contactSubmitButton));

        contactName.sendKeys("utku");
        contactEmail.sendKeys("utku@gmail.com");
        contactSubject.sendKeys("Subject 1");
        contactMessage.sendKeys("Message 1");
        JavascriptExecutor js= (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 400);");
        contactSubmitBtn.click();

        webDriver.switchTo().alert().accept();

        WebElement contactSuccess=wait.until(ExpectedConditions.visibilityOf(contactSuccessAlert));
        return contactSuccess.isDisplayed();
    }
}
