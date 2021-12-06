package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCartPage extends Menu {

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductExistsInCart(String productName) {
        boolean isProductInCart = false;
        List<WebElement> shoppingCartList = driver.findElements(By.cssSelector(".table.table-bordered tr td:nth-child(2)>a"));
        for (WebElement we : shoppingCartList) {
            if (we.getText().equalsIgnoreCase(productName)) {
                isProductInCart = true;
            }
        }
        return isProductInCart;
    }
}
