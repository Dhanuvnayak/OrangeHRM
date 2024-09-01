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
        adminPage.getAdminOption();
    }

    @Test
    public void testGetAllMenuOptions() {
     List<WebElement> list= adminPage.recordList();
     int menu = list.size();
        for(WebElement w : list)
            System.out.println(w.toString());
     Assert.assertEquals(menu, 12, "The number of menu options is not correct.");
    }

    @Test
    public void testSearchExistingEmployee() {
        adminPage.searchByUsername("Admin");
        List<WebElement> records = adminPage.getTotalRecords();
        Assert.assertTrue(records.contains("1"), "Total records found is not correct.");
    }

    @Test
    public void testRefreshPage() {
        adminPage.refreshPage();
        // Check if page refreshed successfully
        String title = driver.getTitle();
        Assert.assertEquals(title, "OrangeHRM", "Page refresh failed.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
