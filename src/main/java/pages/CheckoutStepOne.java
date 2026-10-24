package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutStepOne {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By continueBtn=By.id("continue");
    private final By first_name=By.id("first-name");
    private final By last_name=By.id("last-name");
    private final By postal_code=By.id("postal-code");

    public CheckoutStepOne(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void enterFirstName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(first_name));
        WebElement field = driver.findElement(first_name);
        field.clear();
        field.sendKeys(name);
    }
    public void enterLastName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(last_name));
        WebElement field = driver.findElement(last_name);
        field.clear();
        field.sendKeys(name);
    }
    public void enterPostalCode(String postal) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(postal_code));
        WebElement field = driver.findElement(postal_code);
        field.clear();
        field.sendKeys(postal);
    }
    public  boolean  continueButtonDisplay(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(postal_code));
        WebElement button = driver.findElement(continueBtn);
        return  button.isDisplayed();
    }
    public void clickContinueButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueBtn));
        WebElement button = driver.findElement(continueBtn);
        button.click();
    }
    public CheckoutStepTwo redirectToContinue(String first,String last,String postal) {
        enterFirstName(first);
        enterLastName(last);
        enterPostalCode(postal);
        clickContinueButton();
        return new CheckoutStepTwo(driver);
    }
}
