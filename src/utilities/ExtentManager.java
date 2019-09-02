package utilities;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Platform;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {

	private static ExtentReports extent;
	private static Platform platform;
	private static String reportFile = "Sep-Test-Automaton-Report-";
	private static String macPath = System.getProperty("user.dir") + "/TestReport";
	private static String windowsPath = System.getProperty("user.dir") + "\\TestReport";
	

	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	public static ExtentReports createInstance() {
	 
		
		platform = getCurrentPlatform();
		String fileName = getReportFileLocation(platform);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		return extent;
	}

	private static String getReportFileLocation(Platform platform) {
		String reportFileLocation = null;

		switch (platform) {
		case MAC:
			reportFileLocation = getMacReportFileLoc();
			createReportPath(macPath);
			System.out.println("ExtentReport Path for MAC: " + macPath + "\n");
			break;
		case WINDOWS:
			reportFileLocation = getWinReportFileLoc();
			createReportPath(windowsPath);
			System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
			break;
		default:
			System.out.println("ExtentReport path has not been set! There is a problem!\n");
			break;
		}
		return reportFileLocation;
	}

	private static void createReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				System.out.println("Directory: " + path + " is created!");
			} else {
				System.out.println("Failed to create directory: " + path);
			}
		} else {
			System.out.println("Directory already exists: " + path);
		}
	}


	private static Platform getCurrentPlatform() {
		if (platform == null) {
			String operSys = System.getProperty("os.name").toLowerCase();
			if (operSys.contains("win")) {
				platform = Platform.WINDOWS;
			} else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
				platform = Platform.LINUX;
			} else if (operSys.contains("mac")) {
				platform = Platform.MAC;
			}
		}
		return platform;
	}
	
	public static String getReportFileName() {
		LocalDateTime myDateObj = LocalDateTime.now(); 
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss"); 
		return reportFile+myDateObj.format(myFormatObj)+".html";	
	}
	
	public static String getMacReportFileLoc() {	
		return macPath + "/" + getReportFileName();
	}
	
	public static String getWinReportFileLoc() {	
		return windowsPath + "\\" + getReportFileName();
	}

}
