package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Menu {
    @FindBy(css = "[name='email']")
    private WebElement emailField;
    @FindBy(css = "[name='password']")
    private WebElement passwordField;
    @FindBy(css = "[type='submit']")
    private WebElement loginSubmit;
    @FindBy(css = "div:nth-child(1) > div > a")
    private WebElement registerBtn;
    @FindBy(css = ".alert-dismissible")
    private WebElement loginWithInvalidPasswordError;
    @FindBy(css = "#content > h2:nth-child(1)")
    private WebElement myAccount;
    @FindBy(css = "http://tutorialsninja.com/demo/index.php?route=account/account")
    private WebElement myAccountPage;
    @FindBy(css = "http://tutorialsninja.com/demo/index.php?route=account/login")
    private WebElement loginUrl;
    @FindBy(css = "div:nth-child(1) > h2")
    private WebElement isElementSelectedExists;
    @FindBy(css = "#input-email")
    private WebElement emailOfReturningCustomerField;
    @FindBy(css = "#input-password")
    private WebElement passwordOfReturningCustomerField;
    @FindBy(css = "[value='Login']")
    private WebElement loginAsReturningCustomerBtn;
    @FindBy(css = "#payment-new > div:nth-child(1) > label")
    private WebElement firstFieldInBuildingDetail;
    @FindBy(css = "#account-login > div.alert.alert-danger.alert-dismissible")
    private WebElement WarningForWrongReapingPassword;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        fillText(emailField, email);
        fillText(passwordField, password);
        click(loginSubmit);
    }

    public void loginAsReturningClient(String email, String password) {
        sleep(200);
        fillText(emailOfReturningCustomerField, email);
        fillText(passwordOfReturningCustomerField, password);
        click(loginAsReturningCustomerBtn);
    }

    public void goToRegistrationForm() {
        click(registerBtn);
    }

    public String loginOnceWithInvalidPassword() {
        return getText(loginWithInvalidPasswordError);
    }

    public String loginWithInvalidPassword() {
        int numberOfAttempts = 0;
        String validEmail = "client@gmail.com";
        String invalidPassword = "dhdd1121";
        LoginPage lp = new LoginPage(driver);
        while (numberOfAttempts < 6) {
            lp.login(validEmail, invalidPassword);
            numberOfAttempts++;
        }
        return getText(loginWithInvalidPasswordError);
    }

    public String clientTransfersToMyAccountPage() {
        return getText(myAccount);
    }

    public String getTitleForLoginPage() {
        return getText(loginUrl);
    }

    public String getTextOfFirstFieldInBillingDetail() {
        return getText(firstFieldInBuildingDetail);
    }

    public String getTextOfWarningDuoWrongPasswordForRepeatingClient() {
        return getText(WarningForWrongReapingPassword);
    }
}
