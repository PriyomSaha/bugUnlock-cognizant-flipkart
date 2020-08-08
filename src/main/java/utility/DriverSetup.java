/*
 * @author Priyom Saha
 */
package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class DriverSetup {
    public static WebDriver driver = null;

    private static String URL = "https://www.flipkart.com";

    public static WebDriver getDriver(String browserName){

        if (browserName.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("firefox")){

            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if(browserName.equalsIgnoreCase("edge")){

            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        else if(browserName.equalsIgnoreCase("opera")){

            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        }

        driver.manage().window().maximize();

        driver.get(URL);

        return driver;

    }
}
