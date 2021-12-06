package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Menu extends BasePage {
    @FindBy(css = "[title='My Account']")
    private WebElement dropdownMyAccountTopMenu;
    @FindBy(css = "li.dropdown.open > ul > li:nth-child(2) > a")
    private WebElement logonFromDropDownTopMenu;
    @FindBy(css = ".open > ul > li:nth-child(1) > a")
    private WebElement registerFromDropDown;
    @FindBy(css = "[title='Checkout']")
    private WebElement checkoutBtn;
    @FindBy(css = "li:nth-child(5) > a")
    private WebElement loggedOutTopMenuBtn;
    @FindBy(css = "#logo > h1 > a")
    private WebElement logoOfMainStorePage;
    @FindBy(css = "#top-links > ul > li:nth-child(4) > a > span")
    private WebElement shoppingCartBtn;

    public Menu(WebDriver driver) {
        super(driver);
    }

    public void goToLoginOrRegisterPageTopMenu() {
        click(dropdownMyAccountTopMenu);
        click(logonFromDropDownTopMenu);
    }

    public void goToLoggedOutTopMenu() {
        click(dropdownMyAccountTopMenu);
        click(loggedOutTopMenuBtn);
    }

    public void clickCheckOut() {
        click(checkoutBtn);
    }

    public void goToMainStorePage() {
        click(logoOfMainStorePage);
    }

    public void goToRegistrationFromDropDown() {
        click(registerFromDropDown);
    }

    public void clickCheckout() {
        click(checkoutBtn);
    }

    public void LoggedOutTopMenu() {
        click(loggedOutTopMenuBtn);
    }

    public void goToShoppingCart() {
        click(shoppingCartBtn);
    }
}
