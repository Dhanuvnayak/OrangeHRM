package Login;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class GetLoginDataClass {
    WebDriver driver; String actUrl;
    ExtentReports report;
    ExtentTest test1;
    @BeforeTest
    public void setup() {
        // Initialize ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");
        report = new ExtentReports();
        report.attachReporter(sparkReporter);
  }
@BeforeMethod
    public void launchChrome(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test(dataProvider = "readDatafromExcel",dataProviderClass = OHRM_Scenario1.class)
    public void loginTo(String un, String ps) throws IOException, InterruptedException {
        test1 = report.createTest("Login Test for "+un);
               Thread.sleep(2000);
                driver.findElement(By.name("username")).sendKeys(un);
                Thread.sleep(2000);
                 driver.findElement(By.name("password")).sendKeys(ps);
                 Thread.sleep(2000);
                 driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).submit();
                Thread.sleep(4000);
               int a = 0;
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(file, new File("ss" +un+ ".jpeg"));
        actUrl= driver.getCurrentUrl();
        //Assert.assertEquals(actUrl, expUrl, "Invalid credentials");

        if (actUrl.contains("dashboard")) {
            System.out.println("Test case pass");
            Assert.assertTrue(actUrl.contains("dashboard"));
            driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
            driver.findElement(By.partialLinkText("Log")).click();
            test1.log(Status.PASS, MarkupHelper.createLabel("Successful login: ", ExtentColor.GREEN));

        } else {

            System.out.println("Test case fail");
            System.out.println(driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText());
            //Assert.assertTrue(actUrl.contains("login"));
            Assert.assertFalse(actUrl.contains("dashboard"));
            test1.log(Status.FAIL, MarkupHelper.createLabel(" Unsuccessful login ", ExtentColor.RED));
        }
    }
 @AfterMethod
 public void tearDown() {
     driver.close();
     report.flush();

 }

}

