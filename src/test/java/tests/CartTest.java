package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutStepOne;
import pages.InventoryPage;
import pages.LoginPage;

public class CartTest  extends BaseTest {

    private CartPage cartPage;
    @BeforeMethod
    public void createInventoryPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        cartPage = loginPage.redirect_to_InventoryPage().redirect_to_CartPage();
    }

    @Test
    public void verifyCartIsEmpty(){
      int items_count=  cartPage.get_items_cart();
        Assert.assertTrue(items_count==0);
    }

    @Test
    public void verifyCartEmptyNotAllowedToCheckout() {
        CheckoutStepOne checkout1 = cartPage.click_checkout();
        boolean isContinueButtonDisplay = checkout1.continueButtonDisplay();
        Assert.assertTrue(isContinueButtonDisplay == false);
    }
}
