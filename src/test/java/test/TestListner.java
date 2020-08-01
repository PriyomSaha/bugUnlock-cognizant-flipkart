package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListner implements ITestListener {
    Logger logger = LogManager.getLogger(TestListner.class);

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info(result.getName()+" : Success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error(result.getName()+" : Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn(result.getName()+" : Skipped");
    }
}