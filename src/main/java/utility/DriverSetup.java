/*
 * @author Priyom Saha
 */
package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class DriverSetup {
    public static WebDriver driver = null;

    private static String URL = "https://www.flipkart.com";

    public static WebDriver getDriver(String browserName) {

        if (browserName.equalsIgnoreCase("chrome")) {
            String completePath = System.getProperty("user.dir") + "/driver/chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", completePath);

            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            String completePath = System.getProperty("user.dir") + "/driver/geckodriver.exe";
            System.setProperty("webdriver.gecko.driver", completePath);

            driver = new FirefoxDriver();
        }
        else if(browserName.equalsIgnoreCase("edge")){
            String completePath = System.getProperty("user.dir") + "/driver/edgedriver.exe";
            System.setProperty("webdriver.edge.driver", completePath);

            driver = new EdgeDriver();
        }
        else if(browserName.equalsIgnoreCase("opera")){
            String completePath = System.getProperty("user.dir") + "/driver/operadriver.exe";
            System.setProperty("webdriver.opera.driver", completePath);

            driver = new OperaDriver();
        }
        driver.manage().window().maximize();

        driver.get(URL);

        return driver;

    }
}
