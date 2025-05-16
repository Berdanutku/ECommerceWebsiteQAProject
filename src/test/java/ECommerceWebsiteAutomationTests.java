import com.example.*;
import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ECommerceWebsiteAutomationTests {

    WebDriver webDriver;
    HomePage homePage;
    ProductsPage productsPage;
    LoginSignupPage loginSignupPage;
    CartPage cartPage;
    ContactUsPage contactUsPage;

    @BeforeClass
    void setUp() {
        WebDriverManager.chromedriver().setup();
        webDriver=new ChromeDriver();
        homePage=new HomePage(webDriver);
        productsPage=new ProductsPage(webDriver);
        loginSignupPage=new LoginSignupPage(webDriver);
        cartPage=new CartPage(webDriver);
        contactUsPage=new ContactUsPage(webDriver);
        webDriver.manage().window().maximize();
        webDriver.get("https://automationexercise.com/");
        WebDriverWait wait=new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @AfterClass
    void tearDown() {
        webDriver.quit();
    }

    @Test(priority = 3)
    public void testIsUserLoggedInSuccessfully(){
        homePage.goToTheLoginSignupPage();
        loginSignupPage.loginToTheWebsiteSuccessfully();
        Assert.isTrue(homePage.isUserLoggedOrSignIn(),"User could not log in.");
    }
    @Test(priority = 1)
    public void testIsUserSignUpSuccessfully(){
        homePage.goToTheLoginSignupPage();
        loginSignupPage.signupToTheWebsiteSuccessfully();
        Assert.isTrue(homePage.isUserLoggedOrSignIn(),"User could not sign in.");
    }
    @Test(priority = 4)
    public void testAddProductToTheCartSuccessfully(){
        homePage.goToTheProductsPage();
        productsPage.addProductToTheCartSuccessfully();
    }
    @Test(priority = 5)
    public void testViewProductDetailsSuccessfully(){
        homePage.goToTheProductsPage();
        productsPage.viewProductDetailsSuccessfully();
    }
    @Test(priority = 6)
    public void testMakePaymentSuccessfully(){
        homePage.goToTheCartPage();
        cartPage.makePaymentSuccessfully();
    }
    @Test(priority = 7)
    public void testDeleteProductFromCartSuccessfully(){
        homePage.goToTheProductsPage();
        productsPage.addProductToTheCartSuccessfully();
        homePage.goToTheCartPage();
        cartPage.deleteProductFromCartSuccessfully();
    }
    @Test(priority = 8)
    public void testSendContactMessageSuccessfully(){
        homePage.goToTheContactUsPage();
        contactUsPage.sendContactMessageSuccessfully();
    }
    @Test(priority = 9)
    public void testSearchProductFromSearchBar(){
        homePage.goToTheProductsPage();
        productsPage.searchProductFromSearchBar();
    }
    @Test(priority = 2)
    public void testLogoutAccountSuccessfully(){
        homePage.logoutAccountSuccessfully();
    }
    @Test(priority = 10)
    public void testDeleteAccountSuccessfully(){
        homePage.deleteAccountSuccessfully();
    }
}
