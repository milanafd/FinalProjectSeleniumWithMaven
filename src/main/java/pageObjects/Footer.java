package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Footer extends BasePage {
    @FindBy(css = "div:nth-child(4) > ul > li:nth-child(1) > a")
    private WebElement myAccountFooterBtn;
    @FindBy(css = "li.dropdown.open > ul > li:nth-child(2) > a")
    private WebElement logonFromDropDownTopMenu;
    @FindBy(css = ".open > ul > li:nth-child(1) > a")
    private WebElement registerFromDropDown;
    @FindBy(css = "li:nth-child(5) > a > span")
    private WebElement checkoutBtn;
    @FindBy(css = "li:nth-child(5) > a")
    private WebElement loggedOutTopMenuBtn;

    public Footer(WebDriver driver) {
        super(driver);
    }

    public void goToLoginOrRegisterPageFooter() {
        click(myAccountFooterBtn);
    }
}
