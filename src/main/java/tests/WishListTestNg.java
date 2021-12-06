package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.MainStorePage;
import pageObjects.Menu;
import pageObjects.ProductPage;
import utils.Utils;

public class WishListTestNg extends BaseTest {

    @Severity(SeverityLevel.MINOR)
    @Test(description = "Client will be directed to go to registration page if he will try to add to \"Wish List\" while logged out")
    public void l1_addingAProductToWishListByLoggedOutClientWillResultAnAlert() {
        driver.get(Utils.readProperty("urlMainPage"));
        MainStorePage msp = new MainStorePage(driver);
        msp.clickOnAProduct("MacBook");
        ProductPage pp = new ProductPage(driver);
        pp.clickOnAddToWishList();

        sleep(800);
        String expected = "You must login or create an account to save MacBook to your wish list!";
        String actual = pp.getTextOfWishListWarningWhileClientLoggedOut();
        Assert.assertEquals(expected, actual);
    }

    @Severity(SeverityLevel.TRIVIAL)
    @Test(dataProvider = "getDataFromExcelLoginSuccess", description = "Logged in client will get an alert confirming adding an item to \"Wish List\"")
    public void l2_loggedInClientWillGetAnAlertConfirmingAddingAnItemToWishList(String email, String password) {
        driver.get(Utils.readProperty("urlMainPage"));
        Menu menu = new Menu(driver);
        menu.goToLoginOrRegisterPageTopMenu();
        LoginPage lp = new LoginPage(driver);
        lp.login(email, password);
        menu.goToMainStorePage();
        MainStorePage msp = new MainStorePage(driver);
        msp.clickOnAProduct("MacBook");
        ProductPage pp = new ProductPage(driver);
        pp.clickOnAddToWishList();

        sleep(800);
        String expected = "Success: You have added MacBook to your wish list!";
        String actual = pp.getTextOfWishListWarningWhileClientLoggedIn();
        Assert.assertEquals(expected, actual);
    }
}
