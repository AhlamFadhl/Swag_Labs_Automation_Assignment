package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InventoryPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By item = By.className("inventory_item");
    private final By cart_icon=By.className("shopping_cart_link");
    private final By social_icons = By.cssSelector("li[class^='social_'] a");
    private final By add_to_cart=By.cssSelector(".btn.btn_primary.btn_small.btn_inventory");
    private final By menu=By.id("react-burger-menu-btn");
    private final By logout=By.id("logout_sidebar_link");

    By social_icon(String social) {
        return By.className("social_" + social);
    }
    By getButton_ByItemName(String item_name) {
        return By.xpath("//div[@data-test='inventory-item-name' and normalize-space()='"+item_name+"']/ancestor::div[@data-test='inventory-item']//button");
    }
    By getPrice_ByItemName(String item_name) {
        return By.xpath("//div[@data-test='inventory-item-name' and normalize-space()='"+item_name+"']/ancestor::div[@data-test='inventory-item']//div[@class='inventory_item_price']");
    }

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
    public WebElement geticonSocial(String Social_name) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(social_icon(Social_name)));
            WebElement social = driver.findElement(social_icon(Social_name));
            return social;
        } catch (TimeoutException e) {
            return null;
        }
    }

    public String iconSocial_click(String Social_name) {
        WebElement social = geticonSocial(Social_name);
        if (social != null) {

            String originalWindow=driver.getWindowHandle();
            List<String> windowTapsBefore= new ArrayList<>(driver.getWindowHandles());
            social.click();

            wait.until(ExpectedConditions.numberOfWindowsToBe(windowTapsBefore.size()+1));
            // Switch to the new tab
            List<String> windowTaps= new ArrayList<>(driver.getWindowHandles());
            for (String handle : windowTaps) {
                if (!handle.equals(originalWindow)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }
            String url = driver.getCurrentUrl();
            driver.close();
            driver.switchTo().window(originalWindow);

            return url;
        }
        return "not found icon to be click";
    }

    public void click_Add_to_cart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(add_to_cart));
        List<WebElement> buttons = driver.findElements(add_to_cart);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    public void click_Add_to_cart_or_remove(String itemName){
        By btn=getButton_ByItemName(itemName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn));
        WebElement button=driver.findElement(btn);
        button.click();
    }

    public void add_products_to_cart(List<String> items) {
        for (String itm : items) {
            click_Add_to_cart_or_remove(itm);
        }
    }

    public double  getProductPrice(String name){
       WebElement price= driver.findElement(getPrice_ByItemName(name));
      String strPrice= price.getText().split("$")[1];
      return Double.parseDouble(strPrice);
    }

    public double getProdcutsTotalPrice(List<String> items) {
        double total=0;
        for (String itm : items) {
            total+=getProductPrice(itm);
        }
        return  total;
    }

    public void iconCart_click() {
        WebElement cart = getCartIcon();
        if (cart != null) cart.click();

    }
    public CartPage redirect_to_CartPage() {
        iconCart_click();
        wait.until(ExpectedConditions.urlContains("cart.html"));
        return new CartPage(driver);
    }
    public void menuClick(){
        wait.until(ExpectedConditions.elementToBeClickable(menu));
        WebElement button=driver.findElement(menu);
        button.click();
    }
    public LoginPage logoutClick(){
        menuClick();
        wait.until(ExpectedConditions.elementToBeClickable(logout));
        WebElement button=driver.findElement(logout);
        button.click();
        return new LoginPage(driver);

    }

}
