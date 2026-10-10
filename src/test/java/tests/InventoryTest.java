package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTest extends BaseTest {

    private InventoryPage inventoryPage;
    private LoginPage loginPage;

    @BeforeMethod
    public void createLoginPage(){
        inventoryPage=new InventoryPage(driver);
        loginPage=new LoginPage(driver);
        loginPage.login("standard_user","secret_sauce");
    }
    @Test
    public void  pageTitle(){
        Assert.assertTrue(inventoryPage.getTitlePage().contains(WEB_TITLE),"Page title not "+WEB_TITLE);
    }

    @Test
    public void cart_icon_displayed(){
        Assert.assertTrue(inventoryPage.getCartIcon().isDisplayed(),"cart icon not displayed");
    }

    @Test
    public void products_count(){
        int product_count=inventoryPage.getProductsCount();
        Assert.assertTrue(product_count==PRODUCTS_COUNT,"products in inventory page count not "+PRODUCTS_COUNT+" is is ["+product_count+"]");
    }
}
