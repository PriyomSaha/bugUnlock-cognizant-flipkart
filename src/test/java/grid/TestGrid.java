package grid;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestGrid {
	WebDriver driver;
	String nodeUrl;

	@Test
	void initial() throws MalformedURLException {

		WebDriverManager.chromedriver().setup();
		nodeUrl = "http://192.168.0.104:4444/wd/hub";
	//Desired capabilities
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setBrowserName("chrome");
		capabilities.setPlatform(Platform.WIN10);
	//Options
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-infobars");

		capabilities.setCapability(ChromeOptions.CAPABILITY,options);
	//Remote access
		driver = new RemoteWebDriver(new URL(nodeUrl), options);
		//driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.quit();
	}
}
