/*
 * @author Priyom Saha
 */
package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static utility.DriverSetup.driver;

public class Locator {
    WebElement locator = null;

    public WebElement setCrossButton(){
        String path = "//button[@class='_2AkmmA _29YdH8']";
        /*Wait until element is not visible*/
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));

        locator = driver.findElement(By.xpath(path));
        return locator;
    }

    public WebElement setSearchBox() {
        locator = driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']"));
        return locator;
    }

    public void setMaxPriceDropDown(String value) {
        String path = "//div[@class='_1YoBfV']//select[@class='fPjUPw']";

        /*Wait until element is not visible*/
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));

        Select drpCountry = new Select(driver.findElement(By.xpath(path)));
        drpCountry.selectByVisibleText(value);
    }

    public WebElement setNewestFirst() {
        locator = driver.findElement(By.xpath("//div[contains(text(),'Newest First')]"));
        return locator;
    }

    public List<WebElement> setResultNames() {
        String path = "//div[@class='_1-2Iqu row']/div[1]/div[1]";
        /*Wait until element is not visible*/
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));

        List<WebElement> list = driver.findElements(By.xpath(path));
        return list;
    }

    public List<WebElement> setResultRatings() {
        String path ="//div[@class='_1-2Iqu row']/div[1]/div[2]/span[1]/div";

        List<WebElement> list = driver.findElements(By.xpath(path));
        return list;
    }

    public List<WebElement> setResultprice() {
        String path = "//div[@class='_1-2Iqu row']/div[2]/div[1]/div/div[1]";

        List<WebElement> list = driver.findElements(By.xpath(path));
        return list;
    }
}
