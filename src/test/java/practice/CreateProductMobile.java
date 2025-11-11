package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericutilities.ExcelFileUtility;
import genericutilities.JavaUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectrepository.AddProductPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;
import objectrepository.ProductsPage;

public class CreateProductMobile {

	public static void main(String[] args) throws IOException {

		// Read data from properties file
		PropertyFileUtility pLib = new PropertyFileUtility();
		String BROWSER = pLib.readDataFromPropertyFile("Browser");
		String URL = pLib.readDataFromPropertyFile("URL");
		String USERNAME = pLib.readDataFromPropertyFile("Username");
		String PASSWORD = pLib.readDataFromPropertyFile("Password");

		// Read test script data from excel file
		ExcelFileUtility eLib = new ExcelFileUtility();
		String PRODUCT_NAME = eLib.readDataFromExcelFile("Products", 1, 2);
		String CATEGORY_DD = eLib.readDataFromExcelFile("Products", 1, 3);
		String QUANTITY = eLib.readDataFromExcelFile("Products", 1, 4);
		String PRICE = eLib.readDataFromExcelFile("Products", 1, 5);
		String VENDOR_ID_DD = eLib.readDataFromExcelFile("Products", 1, 6);

		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();

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

		// Create Product Book
		HomePage homePage=new HomePage(driver);
		homePage.getProductsLink().click();
		ProductsPage productsPage=new ProductsPage(driver);
		productsPage.getAddProductBtn().click();
		AddProductPage addProductPage=new AddProductPage(driver);
		addProductPage.getProductNameTF().sendKeys(PRODUCT_NAME + jLib.generateRandomNumber());
//		driver.findElement(By.name("productName")).sendKeys(PRODUCT_NAME + jLib.generateRandomNumber());// enter product
																										// name
		WebElement categoryDD = addProductPage.getProductCategoryDD();
//		Select category = new Select(categoryDD);
//		category.selectByValue(CATEGORY_DD);// enter value-Electronics
		wLib.select(categoryDD, CATEGORY_DD);
		WebElement quantity = addProductPage.getQuantityTF();
		quantity.clear();
		quantity.sendKeys(QUANTITY);// enter quantity
		WebElement price = addProductPage.getPriceTF();
		price.clear();
		price.sendKeys(PRICE);// enter price
		WebElement vendorIdDD =addProductPage.getVendorIdDD();
//		Select vendorId = new Select(vendorIdDD);
//		vendorId.selectByValue(VENDOR_ID_DD);// enter value-VID_448
		wLib.select(vendorIdDD, VENDOR_ID_DD);
		addProductPage.getAddBtn().click();
//		driver.findElement(By.xpath("//button[text()='Add']")).click();

		// Verify the toast msg
		HomePage homepage=new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		String msg = toastMsg.getText();
		if (msg.contains("Successfully Added"))
			System.out.println("Product Created");
		else
			System.out.println("Product Not Created");
		homepage.getCloseToastMsg().click();

		// Logout
		homepage.logout();
		
		// Close browser
		driver.quit();
	}

}
