package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private final By nameInput = By.name("user-name");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.name("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void enterUserName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        WebElement field = driver.findElement(nameInput);
        field.clear();
        field.sendKeys(name);
    }

    public void enterUserPass(String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        WebElement field = driver.findElement(passwordInput);
        field.clear();
        field.sendKeys(pass);
    }

    public void clickLoginButton() {
        WebElement button = driver.findElement(loginButton);
        button.click();
    }

    public boolean isRedirectToInventory(){
        try {
            return wait.until(ExpectedConditions.urlContains("/inventory.html"));
        }
        catch (TimeoutException e) {
            return false;
        }
    }
    public void login(String name, String pass) {
        enterUserName(name);
        enterUserPass(pass);
        clickLoginButton();
    }

    public WebElement getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            WebElement alert = driver.findElement(errorMessage);
            return alert;
        } catch (TimeoutException e) {
            return null;
        }
    }

    public boolean messageError_iscorrect(WebElement alert, String Expected_msg) {
        return alert.getText().contains(Expected_msg);
    }

}
