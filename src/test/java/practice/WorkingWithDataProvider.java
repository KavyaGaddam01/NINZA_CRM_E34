package practice;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericutilities.ExcelFileUtility;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class WorkingWithDataProvider {

	@Test(dataProvider = "loginDetails")
	public void login(String un,String pw) {
		
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("http://49.249.28.218:8098/",un , pw);
		
		HomePage hp=new HomePage(driver);
		hp.logout();
		driver.quit();
	}
	
	@DataProvider
	public 	Object [][] loginDetails() throws EncryptedDocumentException, IOException{
		
		ExcelFileUtility eLib=new ExcelFileUtility();
		
		Object[][] objArr=new Object[eLib.getRowCount("DataProvider")][2];  //0-3->row //0-1->col
		
//		objArr[0][0]="rmgyantra";
//		objArr[0][1]="rmgy@9999";
//		objArr[1][0]="rmgyantra";
//		objArr[1][1]="rmgy@9999";
//		objArr[2][0]="rmgyantra";
//		objArr[2][1]="rmgy@9999";
//		objArr[3][0]="rmgyantra";
//		objArr[3][1]="rmgy@9999";
		
		for(int i=1;i<=eLib.getRowCount("DataProvider");i++) {
			objArr[i-1][0]=eLib.readDataFromExcelFile("DataProvider", i, 0);
			objArr[i-1][1]=eLib.readDataFromExcelFile("DataProvider", i, 1);
		}
		
		return objArr;	
	}
	
}
