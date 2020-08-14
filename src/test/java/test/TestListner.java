/*
 * @author Priyom Saha
 */
package test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utility.TakeScreenShot;

import java.io.IOException;

public class TestListner extends ExtentReportHelper implements ITestListener {
    Logger logger = LogManager.getLogger(TestListner.class);
    TakeScreenShot ts = new TakeScreenShot();

    @Override
    public void onStart(ITestContext context) {
       extent =  ExtentReport.setup();
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName()+ " ");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info(result.getName() + " : Success");
        test.info(result.getName() + " : Success");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        try {
            ts.snapShot("failed__"+fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.error(result.getName() + " : Failed");
        test.error(result.getName() + " : Failed");
        test.error(result.getThrowable());
        test.info("Screen shot added by the name : failed__"+fileName);
        test.fail("SORRY! Couldn't go fwd.");
        try {
            test.addScreenCaptureFromPath( System.getProperty("user.dir")+"\\Output\\ScreenShots\\failed__"+fileName+".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.error(result.getName() + " : Skipped");
        test.error(result.getName() + " : Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}