/*
 * @author Priyom Saha
 */
package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport extends ExtentReportHelper {
	public static ExtentReports setup() {
		fileName = new SimpleDateFormat("yyyy-MM-dd HH-mm-aaa").format(new Date());
		String output_path = System.getProperty("user.dir") + "\\Output\\Reports\\report_" + fileName + ".html";

		ExtentSparkReporter sparkReport = new ExtentSparkReporter(output_path);
		sparkReport.config().setDocumentTitle("Flipkart Automation Report");
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setReportName("test_report");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparkReport);
		/* extent.setSystemInfo("HostName","Local Host"); */
		extent.setSystemInfo("OS", "WIN_10");
		extent.setSystemInfo("Tester name", "Priyom Saha");

		return extent;
	}
}
