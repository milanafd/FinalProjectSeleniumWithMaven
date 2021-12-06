package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends Menu {
    @FindBy(css = "#input-firstname")
    private WebElement firstNameField;
    @FindBy(css = "#input-lastname")
    private WebElement lastNameField;
    @FindBy(css = "#input-email")
    private WebElement emailField;
    @FindBy(css = "#input-telephone")
    private WebElement telephoneField;
    @FindBy(css = "#input-password")
    private WebElement passwordField;
    @FindBy(css = "#input-confirm")
    private WebElement confirmPasswordField;
    @FindBy(css = "label:nth-child(2) > input[type=radio]")
    private WebElement subscribeNoCheckBox;
    @FindBy(css = "[name='agree']")
    private WebElement privacyPolicyAgreeCheckBox;
    @FindBy(css = "[type='submit']")
    private WebElement submitForm;
    @FindBy(css = ".alert-dismissible")
    private WebElement registrationWithSameEmail;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }


    public void fillRegistrationForm(String firstName, String lastName, String email, String telephone, String password, String confirmPassword) {
        sleep(200);
        fillText(firstNameField, firstName);
        fillText(lastNameField, lastName);
        fillText(emailField, email);
        fillText(telephoneField, telephone);
        fillText(passwordField, password);
        fillText(confirmPasswordField, confirmPassword);
        click(subscribeNoCheckBox);
        click(privacyPolicyAgreeCheckBox);
        sleep(400);
        click(submitForm);
    }

    public String registrationWithSameEmail() {
        return getText(registrationWithSameEmail);
    }

    public String getNewEmailStringFromTOD() {
        String newEmailStringFromTOD = "@gmail.com";
        String dateInMillis = String.valueOf(System.currentTimeMillis());
        newEmailStringFromTOD = dateInMillis + newEmailStringFromTOD;
        return newEmailStringFromTOD;
    }
}
