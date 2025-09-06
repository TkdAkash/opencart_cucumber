package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import factory.BaseTest;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter spartReporter;
	public ExtentReports reports;
	public ExtentTest test;

	public String reportName;

	public void onStart(ITestContext testContext) {

		String timeStamp = new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report-" + timeStamp + ".html";
		spartReporter = new ExtentSparkReporter(".\\reports\\" + reportName);

		spartReporter.config().setDocumentTitle("Opencart demo cucumber");
		spartReporter.config().setReportName("Opencart report cucumber");
		spartReporter.config().setTheme(Theme.DARK);

		reports = new ExtentReports();
		reports.attachReporter(spartReporter);
		reports.setSystemInfo("Application", "opencart");
		reports.setSystemInfo("Module", "Admin");
		reports.setSystemInfo("Sub Module", "Customers");
		reports.setSystemInfo("User Name", System.getProperty("user.name"));
		reports.setSystemInfo("Environemnt", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		reports.setSystemInfo("Operating system ", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		reports.setSystemInfo("Browser ", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			reports.setSystemInfo("Groups ", includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult testResult) {
		test = reports.createTest(testResult.getTestClass().getName());
		test.assignCategory(testResult.getMethod().getGroups());
		test.log(Status.PASS, testResult.getName() + " got Passed");

		try {
			String imagePath = new BaseTest().caputureScreen(testResult.getName());
			test.addScreenCaptureFromPath(imagePath);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void onTestFailure(ITestResult testResult) {
		test = reports.createTest(testResult.getTestClass().getName());
		test.assignCategory(testResult.getMethod().getGroups());
		test.log(Status.FAIL, testResult.getName() + " got Failed");
		test.log(Status.INFO, testResult.getThrowable().getMessage());

		try {
			String imagePath = new BaseTest().caputureScreen(testResult.getName());
			test.addScreenCaptureFromPath(imagePath);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult testResult) {
		test = reports.createTest(testResult.getTestClass().getName());
		test.assignCategory(testResult.getMethod().getGroups());
		test.log(Status.SKIP, testResult.getName() + " got Skipped");
		test.log(Status.INFO, testResult.getThrowable().getMessage());

		try {
			String imagePath = new BaseTest().caputureScreen(testResult.getName());
			test.addScreenCaptureFromPath(imagePath);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void onFinish(ITestContext contect) {
		reports.flush();

		String extentReportPath = System.getProperty("user.dir") + "//reports//" + reportName;
		File filePath = new File(extentReportPath);
		try {
			Desktop.getDesktop().browse(filePath.toURI());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
