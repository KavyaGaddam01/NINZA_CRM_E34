package campaigntest;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import genericutilities.BaseClass;
import objectrepository.CampaignsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.HomePage;

@Listeners(genericutilities.ListenerImplementation.class)
class CreateCampaignTest extends BaseClass {

	@Test(groups = {"smoke","regression"})
	public void createCampaignWithMandatoryFieldsTest() throws IOException {

		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaigns", 1, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaigns", 1, 3);

		// Create Campaign with mandatory fields
		CampaignsPage campaignsPage = new CampaignsPage(driver);
		campaignsPage.getAddCCreateCampaignBtn().click();
		CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getCreateCampaignBtn().click();

		// Verify the toast msg
		HomePage homepage = new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		String msg = toastMsg.getText();
		homepage.getCloseToastMsg().click();
		Assert.assertTrue(msg.contains("Successfully Added"));

		System.out.println("How are you");

		System.out.println("Hi");


	}

	@Test(groups = "regression")
	public void createCampaignWithStatusTest() throws IOException {

		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaigns", 4, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaigns", 4, 3);
		String STATUS = eLib.readDataFromExcelFile("Campaigns", 4, 4);

		// Create Campaign with mandatory fields
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(CAMPAIGN_NAME);
		driver.findElement(By.name("campaignStatus")).sendKeys(STATUS);
		WebElement targetSize = driver.findElement(By.name("targetSize"));
		targetSize.clear();
		targetSize.sendKeys(TARGET_SIZE);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

		// Verify the toast msg
		HomePage homepage = new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		String msg = toastMsg.getText();
		homepage.getCloseToastMsg().click();
		Assert.assertTrue(msg.contains("Successfully Added"));


	}

	@Test(groups = "regression")
	public void createCampaignWithExpectedCloseDateTest() throws IOException {
		
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaigns", 7, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaigns", 7, 3);

		// Create Campaign with mandatory fields
		CampaignsPage campaignsPage = new CampaignsPage(driver);
		campaignsPage.getAddCCreateCampaignBtn().click();
		CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getExpectedCloseDateTF().sendKeys(jLib.getRequiredDate(50));
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getCreateCampaignBtn().click();

		// Verify the toast msg
		HomePage homepage = new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		String msg = toastMsg.getText();
		homepage.getCloseToastMsg().click();
		Assert.assertTrue(msg.contains("Successfully Added"));
	}
}
