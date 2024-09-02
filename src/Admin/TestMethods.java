package Admin;

import graphql.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.List;
public class TestMethods {
    WebDriver driver;
    LoginPage loginPage;
    AdminPage adminPage;
    //launching chrome
    @BeforeTest
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);

    }
//Login with valid admin data
    @Test(priority = 1)
    public void testLoginWithValidCredentials() throws InterruptedException {
        loginPage.loginTo("Admin", "admin123");
        String actUrl = driver.getCurrentUrl();
        Assert.assertTrue(actUrl.contains("dashboard"));
        System.out.println("Login Successful");
    }
    // To get all left side menu options
    @Test(priority = 2)
    public void testGetAllMenuOptions() throws InterruptedException {
        List<WebElement> list = adminPage.menuList();
        int menu = list.size();
        System.out.println(menu);
        for (WebElement w : list)
            System.out.println(w.getText());
        org.testng.Assert.assertEquals(menu, 12, "The number of menu options is not correct.");
    }
    //click on Admin option and redirect to admin page
    @Test(priority = 3)
    public void clickOnAdmin() throws InterruptedException {
        adminPage.getAdminOption();
    }
// To get records by username
    @Test(priority = 4)
    public void searchEmployeeByUserName() throws InterruptedException {
        Thread.sleep(2000);
        adminPage.searchByUsername("Admin");
        List<WebElement> records = adminPage.getTotalRecords();
        int x = records.size();
        System.out.println("Total records by user name: "+x);
    }

// To get records by user role
    @Test(priority = 5)
    public void searchEmployeesByUserRole() throws InterruptedException {
        adminPage.selectByUserRole();
        List<WebElement> records = adminPage.getTotalRecords();
        int x= records.size();
        System.out.println("Total records by user role: "+x);
    }
    // To get records by employee status; Disabled
    @Test(priority = 6)
    public void searchEmployeesByStatusDisabled() throws InterruptedException {
        adminPage.selectByStatusDisabled();
        List<WebElement> records = adminPage.getTotalRecords();
        int x= records.size();
        System.out.println("Total records by user status:disabled : "+x);
    }
    // To get records by employee status; Enabled
    @Test(priority = 7)
    public void searchEmployeesByStatusEnabled() throws InterruptedException {
        adminPage.selectByStatusEnabled();
        List<WebElement> records = adminPage.getTotalRecords();
        int x= records.size();
        System.out.println("Total records by user status-enabled: "+x);

    }
    // Refreshing the page after every test
    @AfterMethod
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