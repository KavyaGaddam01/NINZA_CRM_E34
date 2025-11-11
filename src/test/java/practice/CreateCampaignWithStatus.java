package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericutilities.ExcelFileUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class CreateCampaignWithStatus {

	public static void main(String[] args) throws IOException {

		// Read data from properties file
		PropertyFileUtility pLib = new PropertyFileUtility();
		String BROWSER = pLib.readDataFromPropertyFile("Browser");
		String URL = pLib.readDataFromPropertyFile("URL");
		String USERNAME = pLib.readDataFromPropertyFile("Username");
		String PASSWORD = pLib.readDataFromPropertyFile("Password");

		// Read test script data from excel file	
		ExcelFileUtility eLib=new ExcelFileUtility();
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaigns", 4, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaigns", 4, 3);
		String STATUS=eLib.readDataFromExcelFile("Campaigns", 4, 4);
		
		WebDriverUtility wLib=new WebDriverUtility();

		// Launch the browser
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);

		WebDriver driver = null;
		if (BROWSER.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();
		else if (BROWSER.equalsIgnoreCase("Chrome"))
			driver = new ChromeDriver(settings);
		else if (BROWSER.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else if (BROWSER.equalsIgnoreCase("Safari"))
			driver = new SafariDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		wLib.implicitWait(driver);
		
		// Login
		LoginPage loginPage=new LoginPage(driver);
		loginPage.loginToApp(URL,USERNAME,PASSWORD);

		// Create Campaign with mandatory fields
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(CAMPAIGN_NAME);
		driver.findElement(By.name("campaignStatus")).sendKeys(STATUS);
		WebElement targetSize = driver.findElement(By.name("targetSize"));
		targetSize.clear();
		targetSize.sendKeys(TARGET_SIZE);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

		// Verify the toast msg
		HomePage homepage=new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		String msg = toastMsg.getText();
		if (msg.contains("Successfully Added"))
			System.out.println("Campaign Created");
		else
			System.out.println("Campaign Not Created");
		homepage.getCloseToastMsg().click();

		// Logout
		homepage.logout();
		

		// Close browser
		driver.quit();
	}

}
