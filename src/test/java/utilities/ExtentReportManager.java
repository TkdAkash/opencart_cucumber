package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter spartReporter;
	public ExtentReports reports;
	public ExtentTest test;
	
	public String reportName;
	
	public void onStart(ITestContext context) {
		
		String timeStamp = new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report-"+timeStamp+".html";
		spartReporter = new ExtentSparkReporter(".\\reports\\"+reportName);
		
		spartReporter.config().setDocumentTitle("Opencart demo cucumber");
		spartReporter.config().setReportName("Opencart report cucumber");
		spartReporter.config().setTheme(Theme.DARK);
		
		reports = new ExtentReports();
		reports.attachReporter(spartReporter);
		
	}
	
	
	public void onFinish(ITestContext contect) {
		reports.flush();
		
		String extentReportPath = System.getProperty("user.dir")+"//reports//"+reportName;
		File filePath = new File(extentReportPath);
		try {
			Desktop.getDesktop().browse(filePath.toURI());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
