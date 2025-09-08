package factory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.networknt.schema.format.DateTimeFormat;

public class BaseTest {

	public static WebDriver driver;
	public static ChromeOptions chromeOptions;
	public static EdgeOptions edgeOptions;
	public static FirefoxOptions firefoxOptions;
	public static Logger logger;
	public static Properties p;

	@BeforeClass(groups = { "sanity", "regression", "master" })
	@Parameters({ "browser", "os" })
	public void setup(String br, String os) throws IOException {

		FileReader confilgFile = new FileReader(
				System.getProperty("user.dir") + "//src//test//resources//config.properties");
		p = new Properties();
		p.load(confilgFile);
		logger = LogManager.getLogger(this.getClass());

		if (p.getProperty("execution_env").equalsIgnoreCase("reomte")) {

			DesiredCapabilities cp = new DesiredCapabilities();

			switch (os.toLowerCase()) {
			case "window":
				cp.setPlatform(Platform.WIN11);
				break;
			case "mac":
				cp.setPlatform(Platform.MAC);
				break;
			case "linux":
				cp.setPlatform(Platform.LINUX);
				break;
			default:
				System.out.println("No os matching");
				break;
			}

			switch (br.toLowerCase()) {
			case "chrome":
				cp.setBrowserName("chrome");
				break;
			case "edge":
				cp.setBrowserName("MicrosoftEdge");
				break;
			case "linux":
				cp.setBrowserName("firefox");
				break;

			default:
				System.out.println("No browser matching");
				return;
			}

			driver = new RemoteWebDriver(new URL("http://localhost:8080/wd/hub"), cp);

		} else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {

			switch (br.toLowerCase()) {
			case "chrome":
				chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--start-maximized");
				driver = new ChromeDriver(chromeOptions);
				break;
			case "edge":
				edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("--start-maximized");
				driver = new EdgeDriver(edgeOptions);
			default:
				System.out.println("Invalid browser...");
				break;
			}
		}

		driver.get(p.getProperty("appUrl"));
//		driver.get("https://tutorialsninja.com/demo/");
//		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

	}

	public String randomStringGenerator() {
		return new RandomStringGenerator.Builder().withinRange('a', 'z').get().generate(5);
	}

	public String randomNumberGanarator() {
		return new RandomStringGenerator.Builder().withinRange('0', '9').get().generate(5);
	}

	public String randomAlphaNumeric() {
		return new RandomStringGenerator.Builder().withinRange('0', 'z').get().generate(8);
	}

	public String caputureScreen(String tname) {

		String timeStamp = new SimpleDateFormat("yy.MM.dd.mm.HH.ss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "//screenshots//" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}

	@AfterClass(groups = { "sanity", "regression", "master" })
	public void tearDown() {
		driver.close();
	}
}
