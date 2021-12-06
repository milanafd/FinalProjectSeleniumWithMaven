package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ComparisonPage extends Menu {
    @FindBy(css = "#product-product > div.alert.alert-success.alert-dismissible > a:nth-child(3)")
    private WebElement firstNameField;

    public ComparisonPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductExistsInComparisonTableMoreThanOnce(String productName) {
        int occurrencesCount = 0;
        boolean isProductInComparisonTableMoreThanOnce = false;
        List<WebElement> comparisonTableList = driver.findElements(By.cssSelector("#content > table > tbody:nth-child(2) > tr:nth-child(1) > td"));
        for (WebElement we : comparisonTableList) {
            if (we.getText().equalsIgnoreCase(productName)) {
                occurrencesCount++;
                if (occurrencesCount >= 2) {
                    isProductInComparisonTableMoreThanOnce = true;
                }
            }
        }
        return isProductInComparisonTableMoreThanOnce;
    }
}
