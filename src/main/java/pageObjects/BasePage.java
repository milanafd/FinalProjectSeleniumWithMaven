package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    WebDriver driver;
    JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);

    }

    public void fillText(WebElement element, String text) {
        js.executeScript("arguments[0].setAttribute('style', 'background-color:yellow border: 1px solid green;');", element);
        highlightElement(element, "yellow");
        element.clear();
        element.sendKeys(text);
    }

    public void click(WebElement element) {
        js.executeScript("arguments[0].setAttribute('style', 'background-color:yellow; border: 1px solid green;');", element);
        highlightElement(element, "yellow");
        element.click();
    }

    public void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getText(WebElement element) {
        js.executeScript("arguments[0].setAttribute('style', 'background-color:cyan; border: 1px solid green;');", element);
        highlightElement(element, "yellow");
        return element.getText();
    }

    public String getPageTitle() {
        String s = driver.getTitle();
        return s;
    }

    public void alert(String text) {
        Alert promptAlert = driver.switchTo().alert();
        promptAlert.sendKeys(text);
        promptAlert.accept();
    }

    public String email(String email) {
        return email;
    }

    private void highlightElement(WebElement element, String color) {
        //keep the old style to change it back
        String originalStyle = element.getAttribute("style");
        String newStyle = "border: 1px solid " + color + ";" + originalStyle;
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Change the style
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
                element);

        // Change the style back after few milliseconds
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
                + originalStyle + "');},400);", element);
    }
}