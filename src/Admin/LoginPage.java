package Admin;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
public class LoginPage {
    private WebDriver driver;
    //Page Factory
    //Locators
    private By userNameField = By.name("username");
    private By passWordField = By.name("password");
    private By loginButton = By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button");
    public LoginPage(WebDriver d) {
        driver = d;
    }
    public void loginTo(String un, String ps) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(userNameField).sendKeys(un);
        driver.findElement(passWordField).sendKeys(ps);
        Thread.sleep(2000);
        driver.findElement(loginButton).submit();
    }
}