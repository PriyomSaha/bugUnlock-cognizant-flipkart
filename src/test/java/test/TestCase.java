/*
 * @author Priyom Saha
 */
package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import utility.DriverSetup;
import utility.Locator;
import utility.TakeScreenShot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Listeners(TestListner.class)
public class TestCase extends ExtentReportHelper {
	Logger logger = LogManager.getLogger(TestCase.class);

	private Locator locator;
	String output_path = System.getProperty("user.dir");
	String browser = "";
	TakeScreenShot ts = new TakeScreenShot();

	@BeforeTest
	@Parameters("browserName")
	void initialize(String browserName) {
		logger.info("In browser ' " + browserName + " '");
		browser = browserName;
		/* get driver */
		DriverSetup.getDriver(browserName);

		/* Maximize , find the search box, enter the value to be searched */
		DriverSetup.driver.manage().window().maximize();

		/* Creating the reference of the locator class */
		locator = new Locator();
	}

	// For clicking the cross button from the login popup
	@Test(priority = 0)
	void clickCrossButton() throws InterruptedException {
		Thread.sleep(3000);
		WebElement crossButton = locator.setCrossButton();
		crossButton.click();
		test.info("To close the login popup modal");
	}

	// For sending the value to search
	@Test(priority = 1)
	@Parameters("query")
	void search(String query) throws IOException {
		logger.info("With search query ' " + query + " '");

		test.info("In browser ' " + browser + " '");
		test.info("With search query ' " + query + " '");

		/* Getting the data from excel sheet */
		File file = new File(output_path + "\\src\\main\\resources\\data.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		Workbook data = new XSSFWorkbook(inputStream);
		Sheet sheet = data.getSheet("data");

		/* test case with 'Mobile' in search query(+ve) and 'Numbers / Spcl char' */
		Row row;
		if (query.equalsIgnoreCase("mobile")) {
			row = sheet.getRow(0);
		} else if (query.equalsIgnoreCase("number")) {
			row = sheet.getRow(1);
		} else {
			row = sheet.getRow(2);
		}
		/* Locatintg the searchbox from the locator clasa */
		WebElement searchBox = locator.setSearchBox();

		row.getCell(0).setCellType(CellType.STRING);
		searchBox.sendKeys(row.getCell(0).getStringCellValue());
		searchBox.sendKeys(Keys.ENTER);

	}

	// Set the maximum price range from the dropdown in left panel
	@Test(priority = 2)
	void clickMaximumPriceDropDown() {
		Assert.assertFalse(locator == null);

		locator.setMaxPriceDropDown("₹30000");
		test.info("To set the maximum price range to ₹30000");
	}

	// Click the newest tab
	@Test(priority = 3, dependsOnMethods = "clickMaximumPriceDropDown")
	void clickNewestFirstTab() throws InterruptedException {
		Thread.sleep(3000);
		locator.setNewestFirst().click();
		test.info("To show the newest mobiles");
	}

	@Test(priority = 4, dependsOnMethods = "clickMaximumPriceDropDown")
	void findResults() throws InterruptedException, IOException {
		Thread.sleep(2000);
		List<WebElement> resultNames = locator.setResultNames();
		List<WebElement> resultRatings = locator.setResultRatings();
		List<WebElement> resultPrices = locator.setResultprice();

		test.info("Store the data in excell sheet and capture a screen shot");
		/* For storing the output in excel --> create excel file and reference */
		File file = new File(output_path + "\\Output\\output.xlsx");
		XSSFWorkbook workbook;

		if (file.exists()) {
			FileInputStream inputStream = new FileInputStream(file);
			workbook = new XSSFWorkbook(inputStream);
		} else {
			file.createNewFile();
			workbook = new XSSFWorkbook();
		}

		XSSFSheet sheet = workbook.createSheet(fileName);

		CellStyle style = workbook.createCellStyle();

		/* Creating row for the coloumn names */
		Row row = sheet.createRow(0);
		String cellValue[] = { "Sl.No.", "Name of the Mobile", "Rating of the Mobile", "Price of the Mobile" };
		int i = 0;
		style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		for (String value : cellValue) {
			Cell cell = row.createCell(i++);
			cell.setCellValue(value);
			cell.setCellStyle(style);
		}

		/* Creating row for the coloumn data */
		for (i = 0; i < 5; i++) {
			row = sheet.createRow(i + 1);

			String[] all_data = new String[5];

			/* sl no */
			all_data[0] = Integer.toString(i + 1);
			/* Mobile name */
			all_data[1] = resultNames.get(i).getText();
			/* Rating */
			if (resultRatings.size() == resultPrices.size()) {
				all_data[2] = resultRatings.get(i).getText();
			} else {
				all_data[2] = "ratings not available";
			}
			/* Price */
			all_data[3] = resultPrices.get(i).getText();

			// 1 Karbonn KX4 ratings not available ₹899

			/* DATA */
			int j = 0;
			for (String data : all_data) {
				Cell cell = row.createCell(j++);
				cell.setCellValue(data);
			}
		}

		try (FileOutputStream outputStream = new FileOutputStream(file)) {

			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
			ts.snapShot(fileName);
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
