package practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Demo {

	@Test
	public void productCreation() throws InterruptedException {
		System.out.println("productCreation");
	}
	
	@Test(dependsOnMethods = "productCreation")
	public void updateProduct() {
		System.out.println("updateProduct"); 
	}
	
	@Test(dependsOnMethods = {"productCreation","updateProduct"})
	public void deleteProduct() {
		System.out.println("deleteProduct");  
	}
	
	
	
	
}
