package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.DataDriven;

import java.util.List;

public class InventoryTest extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeMethod
    public void createInventoryPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage = loginPage.redirect_to_InventoryPage();
    }

    @Test
    public void pageTitle() {
        Assert.assertTrue(inventoryPage.getTitlePage().contains(WEB_TITLE), "Page title not " + WEB_TITLE);
    }

    @Test
    public void cart_icon_displayed() {
        Assert.assertTrue(inventoryPage.getCartIcon().isDisplayed(), "cart icon not displayed");
    }

    @Test
    public void products_count() {
        int product_count = inventoryPage.getProductsCount();
        Assert.assertTrue(product_count == PRODUCTS_COUNT, "products in inventory page count not " + PRODUCTS_COUNT + " is is [" + product_count + "]");
    }

    @Test
    public void verifyFacebookLink() {
        String url = inventoryPage.iconSocial_click("facebook");
        Assert.assertTrue(url.contains("facebook.com"), "Unexpected URL: " + url);
    }

    @Test
    public void verifyLinkedinLink() {
        String url = inventoryPage.iconSocial_click("linkedin");
        Assert.assertTrue(url.contains("linkedin.com"), "Unexpected URL: " + url);
    }

    @Test
    public void verifyXLink() {
        String url = inventoryPage.iconSocial_click("x");
        Assert.assertTrue(url.contains("x.com"), "Unexpected URL: " + url);
    }

    @Test(dataProvider = "getCartItems", dataProviderClass = DataDriven.class)
    public void verifyAddSpecificProductsToCart(List<String> items) {
        inventoryPage.add_products_to_cart(items);
        CartPage cartPage = inventoryPage.redirect_to_CartPage();
        int items_in_cart = cartPage.get_items_cart();
        Assert.assertTrue(items.size() == items_in_cart);
    }

    @Test(dataProvider = "getCartItems", dataProviderClass = DataDriven.class)
    public void verifyRemoveOneProduct(List<String> items) {
        String toRemove = DataDriven.getString("itemToRemove");
        inventoryPage.add_products_to_cart(items);
        inventoryPage.click_Add_to_cart_or_remove(toRemove);
        CartPage cartPage = inventoryPage.redirect_to_CartPage();
        int items_in_cart = cartPage.get_items_cart();
        Assert.assertTrue(items.size() - 1 == items_in_cart);
    }

    @Test(dataProvider = "getCartItems", dataProviderClass = DataDriven.class)
    public void verifyCartTotalPrice(List<String> items) {
        inventoryPage.add_products_to_cart(items);
        double expectedTotal = 0;
        expectedTotal = inventoryPage.getProdcutsTotalPrice(items);
        CartPage cartPage = inventoryPage.redirect_to_CartPage();
        CheckoutStepOne checkout1 = cartPage.click_checkout();
        CheckoutStepTwo checkout2 = checkout1.redirectToContinue("Ahlam", "Almuflehi", "0000");
        double total = checkout2.getTotalPrice();
        System.out.println(expectedTotal + "expectedTotal***");
        System.out.println(total + " total***");
        Assert.assertTrue(expectedTotal == total);
    }

    @Test
    public void verifyCartStateAfterLogout() {
        inventoryPage.click_Add_to_cart();
        inventoryPage.click_Add_to_cart();
        LoginPage login = inventoryPage.logoutClick();
        login.login("standard_user", "secret_sauce");
        inventoryPage = login.redirect_to_InventoryPage();
        CartPage cartPage = inventoryPage.redirect_to_CartPage();
        int items_count = cartPage.get_items_cart();
        Assert.assertTrue(items_count == 2);
    }
}
