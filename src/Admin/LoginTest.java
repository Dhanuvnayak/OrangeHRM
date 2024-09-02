package Admin;

import graphql.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class Test {
    WebDriver driver;
    LoginPage loginPage;
    AdminPage adminPage;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);

    }

    @Test(priority = 0)
    public void testLoginWithValidCredentials() throws InterruptedException {
        loginPage.loginTo("Admin", "admin123");
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("OrangeHRM"));
        System.out.println("Login Successful");
    }

    @Test()
    public void clickOnAdmin() {
        adminPage.getAdminOption();
    }

    @Test
    public void testGetAllMenuOptions() {
        List<WebElement> list = adminPage.recordList();
        int menu = list.size();
        for (WebElement w : list)
            System.out.println(w.toString());
        org.testng.Assert.assertEquals(menu, 12, "The number of menu options is not correct.");
    }

    @Test
    public void testSearchExistingEmployee() {
        adminPage.searchByUsername("Admin");
        List<WebElement> records = adminPage.getTotalRecords();
        org.testng.Assert.assertTrue(records.contains("1"), "Total records found is not correct.");
    }

    @Test
    public void testRefreshPage() {
        adminPage.refreshPage();
        // Check if page refreshed successfully
        String title = driver.getTitle();
        org.testng.Assert.assertEquals(title, "OrangeHRM", "Page refresh failed.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}