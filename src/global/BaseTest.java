package global;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.CapabilityFactory;

public class BaseTest {
	public WebDriverWait wait;

	public WebDriver driver;
	public CapabilityFactory capabilityFactory = new CapabilityFactory();
	public static String screenShotPath;
	public String dateName;

	@BeforeMethod(description = "Class Level Setup!")
	@Parameters(value = { "browser" })
	public void setup(@Optional("chrome") String browser) throws MalformedURLException {

		driver = new RemoteWebDriver(new URL("http://10.69.206.138:4444/wd/hub"),
				capabilityFactory.getCapabilities(browser));
		wait = new WebDriverWait(driver, 20);
	
		dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		screenShotPath = System.getProperty("user.dir") + "\\Screenshots\\" + "SepFailed" + dateName + ".png";

	}

	public WebDriver getDriver() {
		return driver;
	}
	
	@AfterMethod(description = "Class Level Teardown!")
	public void teardown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			TakesScreenshot ts = (TakesScreenshot) getDriver();
			File source = ts.getScreenshotAs(OutputType.FILE);
			File filePath = new File(screenShotPath);
			FileUtils.copyFile(source, filePath);
		}
		getDriver().quit();
	}



}
