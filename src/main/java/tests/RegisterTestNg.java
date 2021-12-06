package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import utils.Utils;

public class RegisterTestNg extends BaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "getDataFromExcelRegistration", description = "Client will be able to register successfully after registration from \"My Account\" in top menu")
    public void l1_registrationSuccessTop(String firstName, String lastName, String Email, String telephone, String password, String confirmPassword) {
        driver.get(Utils.readProperty("urlMainPage"));
        Menu menu = new Menu(driver);
        menu.goToLoginOrRegisterPageTopMenu();
        LoginPage lp = new LoginPage(driver);
        lp.goToRegistrationForm();
        RegistrationPage rp = new RegistrationPage(driver);
        String email = rp.getNewEmailStringFromTOD();
        rp.fillRegistrationForm(firstName, lastName, email, telephone, password, confirmPassword);
        RegistrationConformationPage rcp = new RegistrationConformationPage(driver);

        String expected = "Your Account Has Been Created!";
        String actual = rcp.MessageOfSuccessfulRegistration();
        Assert.assertEquals(actual, expected);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Client will be able to go to registration page successfully, after clicking on \"My Account\" in footer")
    public void l2_registrationFromFooter() {
        driver.get(Utils.readProperty("urlMainPage"));
        Footer footer = new Footer(driver);
        footer.goToLoginOrRegisterPageFooter();
        LoginPage lp = new LoginPage(driver);
        lp.goToRegistrationForm();
        RegistrationConformationPage rcp = new RegistrationConformationPage(driver);
        String expected = "Register Account";
        String actual = rcp.MessageOfSuccessfulRegistration();
        Assert.assertEquals(actual, expected);
    }

    @Severity(SeverityLevel.TRIVIAL)
    @Test(description = "Access as a \"New Customer\" registration from \"Checkout\" page by adding a product and try to purchase it when logged out")
    public void l3_clientCanRegisterFromCheckoutProcess() {
        driver.get(Utils.readProperty("urlMainPage"));
        MainStorePage msp = new MainStorePage(driver);
        msp.clickOnAProduct("MacBook");
        ProductPage pp = new ProductPage(driver);
        pp.clickOnAddToCartBtnFromProductsPage();
        Menu menu = new Menu(driver);
        menu.clickCheckout();
        CheckOutPage cop = new CheckOutPage(driver);
        String expected = "New Customer";
        String actual = cop.getRegistrationFromCheckOutText();
        Assert.assertEquals(actual, expected);
    }

    @Severity(SeverityLevel.TRIVIAL)
    @Test(description = "Client can go to registration page from \"create account\" link in the \"wish list\" alert")
    public void l4_clientCanGoToRegistrationPageFromWishListErrorMessage() {
        driver.get(Utils.readProperty("urlMainPage"));
        MainStorePage msp = new MainStorePage(driver);
        msp.clickOnAProduct("MacBook");
        ProductPage pp = new ProductPage(driver);
        pp.registerFromWishListWarning();
        RegistrationConformationPage rcp = new RegistrationConformationPage(driver);
        String expected = "Register Account";
        String actual = rcp.MessageOfSuccessfulRegistration();
        Assert.assertEquals(actual, expected);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "registrationWithSameEmail", description = "Client will not be able to register with an existing Email")
    public void l5_registrationWithSameEmail(String firstName, String lastName, String email, String telephone, String password, String confirmPassword) {
        driver.get(Utils.readProperty("urlRegistrationPage"));
        RegistrationPage rp = new RegistrationPage(driver);
        rp.fillRegistrationForm(firstName, lastName, email, telephone, password, confirmPassword);

        String expected = "Warning: E-Mail Address is already registered!";
        String actual = rp.registrationWithSameEmail();
        Assert.assertEquals(actual, expected);
    }
}
