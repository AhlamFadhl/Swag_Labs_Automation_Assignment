package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By cart_list_items=By.className("cart_list");
    private final By cart_item=By.className("cart_item");
    private final By checkoutBtn=By.id("checkout");


    private final By complete_header=By.xpath("data-test='complete-header'");
    private final By back_to_products=By.id("back-to-products");

    public CartPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public int get_items_cart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cart_list_items));
        List<WebElement> items = driver.findElements(cart_item);
        return items.size();
    }

    public CheckoutStepOne click_checkout(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn));
        WebElement checkout=driver.findElement(checkoutBtn);
        checkout.click();
        return new CheckoutStepOne(driver);
    }



}
