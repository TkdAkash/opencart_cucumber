package factory;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {

	public static WebDriver driver;
	public static ChromeOptions chromeOptions;
	public static EdgeOptions edgeOptions;
	public static FirefoxOptions firefoxOptions;
	public static Logger logger;
	public static Properties p;

	@BeforeClass
	@Parameters({ "browser","os" })
	public void setup(String br,String os) throws IOException {

		FileReader confilgFile = new FileReader(
				System.getProperty("user.dir") + "//src//test//resources//config.properties");
		p = new Properties();
		p.load(confilgFile);
		logger = LogManager.getLogger(this.getClass());

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
		default: System.out.println("Invalid browser...");
			break;
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

	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
