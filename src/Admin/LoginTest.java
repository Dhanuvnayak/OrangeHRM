package Admin;

import graphql.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import Admin.AdminTest;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;
    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);

    }
    @Test(priority = 0)
    public void testLoginWithValidCredentials() throws InterruptedException {
        loginPage.loginTo("Admin","admin123");
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("OrangeHRM"));
        System.out.println("Login Successful");
    }
@Test(priority = 1)
public void methodName() {
        AdminTest a = new AdminTest(driver);
        a.getClass();
}
}
