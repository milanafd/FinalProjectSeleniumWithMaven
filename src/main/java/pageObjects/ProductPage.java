package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends Menu {
    @FindBy(css = "#content > div > div.col-sm-4 > div.btn-group > button:nth-child(2)")
    private WebElement addProductToComparison;
    @FindBy(css = "#product-product > div.alert.alert-success.alert-dismissible > a:nth-child(3)")
    private WebElement wishListRegisterBtn;
    @FindBy(css = "[data-original-title='Add to Wish List']")
    private WebElement addToWishListBtn;
    @FindBy(css = "#product-product > div.alert.alert-success.alert-dismissible")
    private WebElement wishListWarningMessage;
    @FindBy(css = "#product-product > div.alert.alert-success.alert-dismissible > a:nth-child(2)")
    private WebElement wishListLoginBtn;
    @FindBy(css = "[onclick=\"wishlist.add('43');\"]")
    private WebElement wishListBtn;
    @FindBy(css = "[id='button-cart']")
    private WebElement AddToCartFromProductPageBtn;
    @FindBy(css = "#product-product > div.alert.alert-success.alert-dismissible > a:nth-child(3)")
    private WebElement goToComparisonBtn;


    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnAddToWishList() {
        click(addToWishListBtn);
    }

    public void loginFromWishListWarning() {
        click(wishListBtn);
        sleep(800);
        click(wishListLoginBtn);
    }

    public void registerFromWishListWarning() {
        click(wishListBtn);
        sleep(800);
        click(wishListRegisterBtn);
    }

    public void clickOnAddToCartBtnFromProductsPage() {
        click(AddToCartFromProductPageBtn);
    }

    public void goToWishList() {
        click(wishListBtn);
    }

    public boolean isWishListWarningDisplayed() {
        sleep(900);
        Boolean isWishListWarningDisplayed = wishListWarningMessage.isDisplayed();
        return isWishListWarningDisplayed;
    }

    public String getTextOfWishListWarningWhileClientLoggedOut() {
        String WarningMessageNeedsWithOutNewLineWhileClientLoggedOut = getText(wishListWarningMessage);
        String textOfWishListWarningMessageWithXWhileClientLoggedOut = WarningMessageNeedsWithOutNewLineWhileClientLoggedOut.replaceAll("[\n]", "");
        String textOfWishListWarningMessageWhileClientLoggedOut = textOfWishListWarningMessageWithXWhileClientLoggedOut.substring(0, 71 - 1);
        return textOfWishListWarningMessageWhileClientLoggedOut;
    }

    public String getTextOfWishListWarningWhileClientLoggedIn() {
        String WarningMessageNeedsWithOutNewLineWhileClientLoggedIn = getText(wishListWarningMessage);
        String textOfWishListWarningMessageWithXWhileClientLoggedIn = WarningMessageNeedsWithOutNewLineWhileClientLoggedIn.replaceAll("[\n]", "");
        String textOfWishListWarningMessageWhileClientLoggedIn = textOfWishListWarningMessageWithXWhileClientLoggedIn.substring(0, 51 - 1);
        return textOfWishListWarningMessageWhileClientLoggedIn;
    }

    public void addAProductToComparison() {
        click(addProductToComparison);
    }

    public void navigateToComparisonPage() {
        click(goToComparisonBtn);
    }
}
