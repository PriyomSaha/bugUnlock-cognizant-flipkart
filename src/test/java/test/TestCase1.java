package test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import utility.DriverSetup;
import utility.Locator;
import utility.TakeScreenShot;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Listeners(test.TestListner.class)
public class TestCase1 extends DriverSetup{
    private Locator locator;
    TakeScreenShot ts = new TakeScreenShot();

    @BeforeTest
    @Parameters("browserName")
    void initialize(String browserName) {
        /*get driver*/
        DriverSetup.getDriver(browserName);


        /* Maximize , find the search box, enter the value to be searched */
        DriverSetup.driver.manage().window().maximize();

        /*Creating the reference of the locator class*/
        locator = new Locator();
    }

    //For clicking the cross button from the login popup
    @Test(priority = 0)
    void clickCrossButton() throws InterruptedException {
        Thread.sleep(3000);
        WebElement crossButton = locator.setCrossButton();
        crossButton.click();

    }

    //For sending the value to search
    @Test(priority = 1)
    void search() throws InterruptedException, IOException {
        WebElement searchBox = locator.setSearchBox();
        searchBox.sendKeys("Mobile");
        searchBox.sendKeys(Keys.ENTER);
    }

    //Set the maximum price range from the dropdown in left panel
    @Test(priority = 2)
    void clickMaximumPriceDropDown() {

        locator.setMaxPriceDropDown("₹30000");
    }

    //Click the newest tab
    @Test(priority = 3)
    void clickNewestFirstTab(){
        locator.setNewestFirst().click();
    }

    @Test(priority = 4)
    void findResults() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> resultNames = locator.setResultNames();
        List<WebElement> resultRatings = locator.setResultRatings();
        List<WebElement> resultPrices = locator.setResultprice();

        /* For storing the output in excel --> create excel file and reference */
        String output_path = System.getProperty("user.dir");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("output");

        /* Creating row for the coloumn names */
        Row row = sheet.createRow(0);
        String cellValue[] = {"Sl.No.", "Name of the Mobile", "Rating of the Mobile", "Price of the Mobile"};
        int i = 0;
        for (String value : cellValue) {
            Cell cell = row.createCell(i++);
            cell.setCellValue(value);
        }
        /* Creating row for the coloumn data */
        for (i = 0; i < 5; i++) {
            row = sheet.createRow(i+1);

            String[] all_data = new String[4];
            all_data[0] = Integer.toString(i+1);
            all_data[1] = resultNames.get(i).getText();
            all_data[2] = resultRatings.get(i).getText();
            all_data[3] = resultPrices.get(i).getText();
            int j = 0;
            for (String data : all_data) {
                Cell cell = row.createCell(j++);
                cell.setCellValue(data);
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(output_path + "\\Output\\output.xlsx")) {
            workbook.write(outputStream);
            ts.snapShot("result");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.sleep(10000);

    }

    @AfterTest
    void close() {
        DriverSetup.driver.quit();
    }
}
