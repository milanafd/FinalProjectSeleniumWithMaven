package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainStorePage extends Menu {
    @FindBy(css = "button-cart")
    private WebElement addToCartBtn;
    @FindBy(xpath = "//*[@id=\"top-links\"]/ul/li[4]/a")
    private WebElement goToCartBtn;

    public MainStorePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnAProduct(String productName) {
        List<WebElement> list = driver.findElements(By.cssSelector("div > div.caption > h4"));
        for (WebElement element : list) {
            if (element.getText().equalsIgnoreCase(productName)) {
                int indexElement = list.indexOf(element);
                indexElement++;
                String cssString = "#content > div.row > div:nth-child(" + indexElement + ") > div > div.caption > h4 > a";
                sleep(100);
                click(driver.findElement(By.cssSelector(cssString)));
                break;
            }
        }
    }
}
