package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationConformationPage extends Menu {
    @FindBy(css = "#content > h1")
    private WebElement registrationConformation;
    @FindBy(css = "#account-register > div.alert.alert-danger.alert-dismissible")
    private WebElement textEmailAlreadyRegistered;

    public RegistrationConformationPage(WebDriver driver) {
        super(driver);
    }

    public String MessageOfSuccessfulRegistration() {
        return getText(registrationConformation);
    }

    public String getTextOfWarningEmailIsAlreadyRegistered() {
        String warningEmailIsAlreadyRegistered = getText(textEmailAlreadyRegistered);
        return warningEmailIsAlreadyRegistered;
    }
}
