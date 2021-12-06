package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import pageObjects.*;
import utils.Utils;

import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class ProductTestNg extends BaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Add a product to cart from product page")
    public void l1_addAProductToCartFromProductPage() {
        driver.get(Utils.readProperty("urlMainPage"));
        MainStorePage msp = new MainStorePage(driver);
        msp.clickOnAProduct("MacBook");
        ProductPage pp = new ProductPage(driver);
        pp.clickOnAddToCartBtnFromProductsPage();
        pp.goToShoppingCart();
        ShoppingCartPage scp = new ShoppingCartPage(driver);
        boolean isProductExistsInCart = scp.isProductExistsInCart("MacBook");

        assertTrue(isProductExistsInCart);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Client will not be able to add to product comparison table the same product twice")
    public void l2_clientWillNotBeAbleToAddToProductComparisonTableTheSameProductTwice() {
        driver.get(Utils.readProperty("urlMainPage"));
        MainStorePage msp = new MainStorePage(driver);
        msp.clickOnAProduct("MacBook");
        ProductPage pp = new ProductPage(driver);
        pp.addAProductToComparison();
        goToPreviousPage();
        msp.clickOnAProduct("iPhone");
        pp.addAProductToComparison();
        goToPreviousPage();
        msp.clickOnAProduct("MacBook");
        pp.addAProductToComparison();
        sleep(400);
        pp.navigateToComparisonPage();

        ComparisonPage cp = new ComparisonPage(driver);
        boolean canClientCompareTheSameProductMoreThanOnce = cp.isProductExistsInComparisonTableMoreThanOnce("MacBook");
        assertFalse(canClientCompareTheSameProductMoreThanOnce);
    }
}
