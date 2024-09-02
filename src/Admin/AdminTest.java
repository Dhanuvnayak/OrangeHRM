package Admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import Admin.LoginTest;

import java.util.List;

public class AdminTest  extends LoginTest {
    WebDriver driver;
    AdminPage adminPage;
    public AdminTest(WebDriver driver) {
        super();
        driver=driver;
    }


    @BeforeMethod
    public void setup() throws InterruptedException {
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        loginPage.loginTo("Admin", "admin123");
    
}
