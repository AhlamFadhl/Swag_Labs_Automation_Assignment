package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.sql.Driver;

public class BaseTest {

protected WebDriver driver;
protected final String BASE_URL="https://www.saucedemo.com";
protected final String WEB_TITLE="Swag Labs";
protected final int PRODUCTS_COUNT=6;

    @BeforeMethod
    public void startBrowser(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
}

    @AfterMethod (alwaysRun = true)
    public void quitBrowser(){
        if(driver!=null)
        driver.quit();
    }



}
