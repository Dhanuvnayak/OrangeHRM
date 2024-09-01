package Login;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.testng.annotations.DataProvider;

import java.io.*;

public class OHRM_Scenario1 {
        WebDriver driver;String fpath = "C:\\Users\\DHANALAKSHMI V\\Selenium_Intellije_Folder\\StarAgille_Project\\OHRMLoginData.xlsx";
        File file;FileInputStream fis;XSSFWorkbook wb;XSSFSheet sheet;
    public static void main(String[] args) throws IOException {
      OHRM_Scenario1 R = new OHRM_Scenario1();
       R.readDatafromExcel();
    }
    @DataProvider
    public Object[][] readDatafromExcel() throws IOException {
        file = new File(fpath);
        fis = new FileInputStream(file);
        wb = new XSSFWorkbook(fis);
        sheet = wb.getSheet("LoginData");
        int rows = sheet.getPhysicalNumberOfRows();
       System.out.println(rows);
        int columns = sheet.getRow(0).getPhysicalNumberOfCells();
       System.out.println(columns);
        String[][] loginData = new String[rows][columns];
        for(int i =0; i<rows;i++){
            for(int j=0; j<columns ;j++){
                loginData[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
                System.out.println( sheet.getRow(i).getCell(j).getStringCellValue());
            }
        }
        wb.close();
        fis.close();

        return loginData;

    }

}


