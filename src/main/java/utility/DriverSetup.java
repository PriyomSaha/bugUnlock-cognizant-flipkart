/*
 * @author Priyom Saha
 */
package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverSetup {
    public static WebDriver driver = null;

    private static String URL = "https://www.flipkart.com";

    public static WebDriver getDriver(String browserName) throws MalformedURLException {

        String nodeUrl = "http://192.168.0.104:4444/wd/hub";

        if (browserName.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();


            //Desired capabilities
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setBrowserName("chrome");
            capabilities.setPlatform(Platform.WIN10);
            //Options
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-infobars");
            //options.merge(capabilities);
            capabilities.setCapability(ChromeOptions.CAPABILITY,options);
            //Remote access
            driver = new RemoteWebDriver(new URL(nodeUrl), options);
        }

        else if(browserName.equalsIgnoreCase("firefox")){

            WebDriverManager.firefoxdriver().setup();
            //Desired capabilities
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setBrowserName("firefox");
            capabilities.setPlatform(Platform.WIN10);
            //Options
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-infobars");
            //options.merge(capabilities);
            capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS,options);
            //Remote access
            driver = new RemoteWebDriver(new URL(nodeUrl), options);

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
