package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import utils.Excel;

public class BaseTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @DataProvider
    public Object[][] getDataFromExcelLoginFailure() {
        String excelPath = System.getProperty("user.dir") + "\\src\\main\\java\\utils\\Util.xlsx";
        System.out.println(excelPath);
        Object[][] table = Excel.getTableArray(excelPath, "LoginFailure");
        return table;
    }

    @DataProvider
    public Object[][] getDataFromExcelLoginSuccess() {
        String excelPath = System.getProperty("user.dir") + "\\src\\main\\java\\utils\\Util.xlsx";
        System.out.println(excelPath);
        Object[][] table = Excel.getTableArray(excelPath, "LoginSuccess");
        return table;
    }

    @DataProvider
    public Object[][] getDataFromExcelRegistration() {
        String excelPath = System.getProperty("user.dir") + "\\src\\main\\java\\utils\\Util.xlsx";
        Object[][] table = Excel.getTableArray(excelPath, "registrationFormValidInput");
        return table;
    }

    @DataProvider
    public Object[][] registrationInvalidEmail() {
        String excelPath = System.getProperty("user.dir") + "\\src\\main\\java\\utils\\Util.xlsx";
        Object[][] table = Excel.getTableArray(excelPath, "registrationFormInvalidInput");
        return table;
    }

    @DataProvider
    public Object[][] registrationWithSameEmail() {
        String excelPath = System.getProperty("user.dir") + "\\src\\main\\java\\utils\\Util.xlsx";
        Object[][] table = Excel.getTableArray(excelPath, "registrationWithSameEmail");
        return table;
    }

    @DataProvider
    public Object[][] wrongPasswordForRepeatingClient() {
        String excelPath = System.getProperty("user.dir") + "\\src\\main\\java\\utils\\Util.xlsx";
        Object[][] table = Excel.getTableArray(excelPath, "wrongPasswordForRepeatingClient");
        return table;
    }

    public void goToPreviousPage() {
        driver.navigate().back();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
