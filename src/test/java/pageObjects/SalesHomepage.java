package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SalesHomepage extends Basepage {
	
	//initiate the driver constructor
	public SalesHomepage(WebDriver driver) {
		super(driver);
	}
	
	//capture the object
	
	@FindBy(xpath="//span[@title='Sales']")
	WebElement logoSales;
	
	@FindBy(xpath="(//span[normalize-space()='Home'])[1]")
	WebElement Home;
	
	//Action method
	public boolean verifySaleslogo() {
		try {
			return (logoSales.isDisplayed());
		}
		catch (Exception e){
			return (false);
		}
	}	
	
	public void clickhomeicon() {
	
		Home.click();
		
	}
}
