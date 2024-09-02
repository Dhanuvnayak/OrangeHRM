package Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;

public class AdminPage {
    WebDriver driver;
    //Locators
    private By leftSideMenu = By.className("oxd-main-menu-item-wrapper");
    private By adminoption = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]");
    private By userName = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input");
    private By searchBtn = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]");
    private By resultTable = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div");
    private By userRoleSrcBtn = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[1]");
    private By admin = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[1]");
    private By userStatus = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[1]");

    public AdminPage(WebDriver d) {
        driver = d;
    }

    public List menuList() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> list = driver.findElements(leftSideMenu);
        return list;
    }
    public void  getAdminOption() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(adminoption).click();
    }
       public void searchByUsername(String un) throws InterruptedException {
           Thread.sleep(2000);
        driver.findElement(userName).sendKeys(un);
        driver.findElement(searchBtn).submit();

}
public List getTotalRecords() throws InterruptedException {
    Thread.sleep(2000);
        List<WebElement> recordslist = driver.findElements(resultTable);
//        for(WebElement a : recordslist){
//            System.out.println(a.getText());
//        }
     return recordslist;
    }


    public void selectByUserRole() throws InterruptedException {
        Thread.sleep(2000);
        WebElement select = driver.findElement(userRoleSrcBtn);
        select.click();
        for(int i=0;i<1;i++){
            select.sendKeys(Keys.DOWN);
        }
       select.click();
        Thread.sleep(2000);
        driver.findElement(searchBtn).submit();
    }
    public void selectByStatusDisabled() throws InterruptedException {
        Thread.sleep(2000);
        WebElement select = driver.findElement(userStatus);
        select.click();
        for(int i=0;i<2;i++){
            select.sendKeys(Keys.DOWN);
        }
        select.click();
        Thread.sleep(2000);
        driver.findElement(searchBtn).submit();
    }
    public void selectByStatusEnabled() throws InterruptedException {
        Thread.sleep(2000);
        WebElement select = driver.findElement(userStatus);
        select.click();
        for(int i=0;i<1;i++){
            select.sendKeys(Keys.DOWN);
        }
        select.click();
        Thread.sleep(2000);
        driver.findElement(searchBtn).submit();
    }
    public void refreshPage() {

        driver.navigate().refresh();
    }
}
