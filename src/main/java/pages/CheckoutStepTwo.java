package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutStepTwo {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By subTotal= By.xpath("//div[@data-test='subtotal-label']");
    private final By finish=By.id("finish");

    public  CheckoutStepTwo(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

  public double  getTotalPrice() {
      wait.until(ExpectedConditions.visibilityOfElementLocated(subTotal));
      WebElement total = driver.findElement(subTotal);
      String strTotal = total.getText().replace("$", "");
      return Double.parseDouble(strTotal);
  }
}
