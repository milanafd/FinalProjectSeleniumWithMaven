package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import utils.Utils;

public class LoginTestNg extends BaseTest {

    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "getDataFromExcelLoginSuccess", description = "Client will be able to login successfully after registration from top menu")
    public void l1_loginSuccessFromTopMenu(String email, String password) {
        driver.get(Utils.readProperty("urlMainPage"));
        Menu menu = new Menu(driver);
        menu.goToLoginOrRegisterPageTopMenu();
        LoginPage lp = new LoginPage(driver);
        lp.login(email, password);

        String expected = "My Account";
        String actual = lp.getPageTitle();
        Assert.assertEquals(actual, expected);
        driver.quit();
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "getDataFromExcelLoginFailure", description = "Client will not be able to login successfully after typing wrong password")
    public void l2_loginFailure(String email, String password) {
        driver.get(Utils.readProperty("urlMainPage"));
        Menu menu = new Menu(driver);
        menu.goToLoginOrRegisterPageTopMenu();
        LoginPage lp = new LoginPage(driver);
        lp.login(email, password);

        String expected = "Warning: No match for E-Mail Address and/or Password.";
        String actual = lp.loginOnceWithInvalidPassword();
        Assert.assertEquals(actual, expected);
    }

    @Severity(SeverityLevel.TRIVIAL)
    @Test(dataProvider = "getDataFromExcelLoginSuccess", description = "Client will be able to login successfully after clicking om My Account in footer")
    public void l3_loginFromMyAccountInFooter(String email, String password) {
        driver.get(Utils.readProperty("urlMainPage"));
        Footer footer = new Footer(driver);
        footer.goToLoginOrRegisterPageFooter();
        LoginPage lp = new LoginPage(driver);
        lp.login(email, password);

        String expected = "My Account";
        String actual = lp.getPageTitle();
        Assert.assertEquals(actual, expected);
    }

    @Severity(SeverityLevel.TRIVIAL)
    @Test(dataProvider = "getDataFromExcelLoginSuccess", description = "Logged in client in \"Checkout\" will not need to fill registration details")
    public void l4_clientEnterCartFromCheckoutBtn(String email, String password) {
        driver.get(Utils.readProperty("urlMainPage"));
        Menu menu = new Menu(driver);
        menu.goToLoginOrRegisterPageTopMenu();
        LoginPage lp = new LoginPage(driver);
        lp.login(email, password);
        menu.goToMainStorePage();
        MainStorePage msp = new MainStorePage(driver);
        msp.clickOnAProduct("MacBook");
        ProductPage pp = new ProductPage(driver);
        pp.clickOnAddToCartBtnFromProductsPage();
        menu.clickCheckout();
        CheckOutPage cop = new CheckOutPage(driver);
        cop.confirmRegistrationIsNotNeeded();
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "getDataFromExcelLoginSuccess", description = "Client can logout and login")
    public void l5_clientLogoutAndLogin(String email, String password) {
        driver.get(Utils.readProperty("urlMainPage"));
        Menu menu = new Menu(driver);
        menu.goToLoginOrRegisterPageTopMenu();
        LoginPage lp = new LoginPage(driver);
        lp.login(email, password);

        menu.goToLoggedOutTopMenu();
        String expected = "Account Logout";
        ShoppingCartPage scp = new ShoppingCartPage(driver);
        String actual = scp.getPageTitle();
        Assert.assertEquals(actual, expected);

        menu.goToLoginOrRegisterPageTopMenu();
        lp.login(email, password);

        String expected2 = "My Account";
        String actual2 = lp.getPageTitle();
        Assert.assertEquals(actual2, expected2);
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "Client can go to Login page from \"login\" link in the \"wish list\" alert")
    public void l6_clientCanGoToLoginPageFromWishListErrorMessage() {
        driver.get(Utils.readProperty("urlMainPage"));
        Menu menu = new Menu(driver);
        LoginPage lp = new LoginPage(driver);
        MainStorePage msp = new MainStorePage(driver);
        msp.clickOnAProduct("MacBook");
        ProductPage pp = new ProductPage(driver);
        pp.loginFromWishListWarning();
        String expected = "Account Login";
        String actual = lp.getPageTitle();
        Assert.assertEquals(actual, expected);
    }

    @Severity(SeverityLevel.MINOR)
    @Test(dataProvider = "getDataFromExcelLoginSuccess", description = "Client will be able to login successfully from \"My Account\" in footer")
    public void l7_LoginFromFooter(String email, String password) {
        driver.get(Utils.readProperty("urlMainPage"));
        Footer footer = new Footer(driver);
        footer.goToLoginOrRegisterPageFooter();
        LoginPage lp = new LoginPage(driver);
        lp.login(email, password);

        String expected = "My Account";
        String actual = lp.getPageTitle();
        Assert.assertEquals(actual, expected);
    }

    @Severity(SeverityLevel.MINOR)
    @Test(dataProvider = "getDataFromExcelLoginSuccess", description = "Login from \"Checkout\" page")
    public void l8_LoginFromCheckOutPage(String email, String password) {
        driver.get(Utils.readProperty("urlMainPage"));
        MainStorePage msp = new MainStorePage(driver);
        msp.clickOnAProduct("MacBook");
        ProductPage pp = new ProductPage(driver);
        pp.clickOnAddToCartBtnFromProductsPage();
        Menu menu = new Menu(driver);
        menu.clickCheckout();
        sleep(100);
        LoginPage lp = new LoginPage(driver);
        lp.loginAsReturningClient(email, password);
        sleep(900);
        String expected = "First Name";
        String actual = lp.getTextOfFirstFieldInBillingDetail();
        Assert.assertEquals(actual, expected);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Client will be blocked out for 1 hour after several failed login attempts")
    public void l9_clientTriesToLoginMultipleTimesAndGetABlockingAlert() {
        driver.get(Utils.readProperty("urlMainPage"));
        Footer footer = new Footer(driver);
        footer.goToLoginOrRegisterPageFooter();
        LoginPage lp = new LoginPage(driver);
        lp.loginWithInvalidPassword();
        String expected = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
        String actual = lp.getTextOfWarningDuoWrongPasswordForRepeatingClient();
        Assert.assertEquals(actual, expected);
    }
}
