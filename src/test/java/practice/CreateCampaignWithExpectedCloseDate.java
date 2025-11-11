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
import genericutilities.JavaUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectrepository.CampaignsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class CreateCampaignWithExpectedCloseDate {

	public static void main(String[] args) throws IOException {
		// Read data from properties file
		PropertyFileUtility pLib = new PropertyFileUtility();
		String BROWSER = pLib.readDataFromPropertyFile("Browser");
		String URL = pLib.readDataFromPropertyFile("URL");
		String USERNAME = pLib.readDataFromPropertyFile("Username");
		String PASSWORD = pLib.readDataFromPropertyFile("Password");

		// Read test script data from excel file
		ExcelFileUtility eLib=new ExcelFileUtility();
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaigns", 7, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaigns", 7, 3);
		
		JavaUtility jLib=new JavaUtility();
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
		wLib.implicitWait(driver);
		// Login
		LoginPage loginPage=new LoginPage(driver);
		loginPage.loginToApp(URL,USERNAME,PASSWORD);

		// Create Campaign with mandatory fields
		CampaignsPage campaignsPage = new CampaignsPage(driver);
		campaignsPage.getAddCCreateCampaignBtn().click();
		CreateCampaignPage createCampaignPage=new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getExpectedCloseDateTF().sendKeys(jLib.getRequiredDate(50));
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getCreateCampaignBtn().click();
		
//		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
//		driver.findElement(By.name("campaignName")).sendKeys(CAMPAIGN_NAME);
//		WebElement targetSize = driver.findElement(By.name("targetSize"));
//		targetSize.clear();
//		targetSize.sendKeys(TARGET_SIZE);
//		driver.findElement(By.name("expectedCloseDate")).sendKeys(jLib.getRequiredDate(50));
//		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

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
