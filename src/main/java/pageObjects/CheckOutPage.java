package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.fail;

public class CheckOutPage extends Menu {
    @FindBy(css = "div:nth-child(1) > h2")
    private WebElement registrationText;

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public String getRegistrationFromCheckOutText() {
        sleep(200);
        return getText(registrationText);
    }

    public void confirmRegistrationIsNotNeeded() {
        sleep(200);
        try {
            registrationText.isDisplayed();
            fail("Element should not have been displayed but it was!");
        } catch (NoSuchElementException e) {
        }
    }
}
