package Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class AdminPage {
    WebDriver driver;
    //Locators
    private By leftSideMenu = By.className("oxd-main-menu-item-wrapper");
    private By adminoption = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]");
    private By userName = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input");
    private By searchBtn = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]");
    private By resultTable = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]");
    public AdminPage(WebDriver d) {
        driver = d;
    }
    public List recordList(){
        List<WebElement> list = driver.findElements(leftSideMenu);
        return list;
    }
    public void  getAdminOption(){
        driver.findElement(adminoption).click();
    }
public void searchByUsername(String un){
        driver.findElement(userName).sendKeys(un);
        driver.findElement(searchBtn).submit();

}
public List getTotalRecords() {
        List<WebElement> recordslist = driver.findElements(resultTable);
        for(WebElement a : recordslist){
            System.out.println(a.getText());
        }
     return recordslist;
    }
    public void refreshPage() {
        driver.navigate().refresh();
    }
}
