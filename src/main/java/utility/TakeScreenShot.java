/*
 * @author Priyom Saha
 */
package utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static utility.DriverSetup.driver;

public class TakeScreenShot {

    public void snapShot(String name) throws IOException {

        String output_path = System.getProperty("user.dir") + "\\Output\\";
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(output_path + name + ".png"));
    }
}