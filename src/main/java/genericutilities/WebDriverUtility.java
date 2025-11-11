package genericutilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	public void implicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitUntilElementToBeVisible(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void select(WebElement element,int index) {
		Select obj=new Select(element);
		obj.selectByIndex(index);
	}
	
	public void select(WebElement element,String value) {
		Select obj=new Select(element);  //method overloading --> no.of arguments/type of arguments/order of arguments
		obj.selectByValue(value);
	}
	
	public void select(String text,WebElement element) {
		Select obj=new Select(element);
		obj.selectByVisibleText(text);
}
	public void mouseHoverOnWebElement(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	public void clickOnWebElement(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.moveToElement(element).click().perform();
	}
}
