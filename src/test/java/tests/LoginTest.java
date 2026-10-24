package tests;

import base.BaseTest;
import io.qameta.allure.*;
import model.UserData;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataDriven;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    @BeforeMethod
    public void createLoginPage(){
        loginPage=new LoginPage(driver);
    }

    @Test (dataProvider = "getValidJsonData" ,dataProviderClass = DataDriven.class)
    public void validLogin(UserData user){
        loginPage.login(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.isRedirectToInventory(),"valid user [username: "+user.getUsername()+" with password: "+user.getPassword()+"] has not login");
    }

    @Test (dataProvider = "getInValidJsonData" ,dataProviderClass = DataDriven.class)
    public void invalidLogin(UserData user){
        loginPage.login(user.getUsername(),user.getPassword());
        Assert.assertFalse(loginPage.isRedirectToInventory(),"inavelid user [username: "+user.getUsername()+" with password: "+user.getPassword()+"] has login");
    }

    @Test
    public void loginWithout_pass() {
        loginPage.login("admin", "");
        WebElement msg = loginPage.getErrorMessage();
        Assert.assertTrue(msg.isDisplayed(),"message error not displayed");
        Assert.assertTrue(loginPage.messageError_iscorrect(msg, "Password is required"),"message error not correct");
    }
}
