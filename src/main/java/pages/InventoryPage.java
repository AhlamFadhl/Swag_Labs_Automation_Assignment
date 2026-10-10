package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By item = By.className("inventory_item");
    private final By cart_icon=By.className("shopping_cart_link");

   public InventoryPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String getTitlePage(){
        if(driver!=null)
            return driver.getTitle();
        else return null;
    }

   public int getProductsCount(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(item));
        List<WebElement> items = driver.findElements(item);
        return items.size();
    }

   public WebElement getCartIcon(){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(cart_icon));
            WebElement cart = driver.findElement(cart_icon);
            return cart;
        }
        catch (TimeoutException e){
            return  null;
        }
    }

}
