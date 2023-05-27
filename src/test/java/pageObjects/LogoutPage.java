package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends Basepage{

	public LogoutPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="(//span[@class='uiImage'])[1]")
	WebElement profileIcon;
	
	@FindBy(xpath="//a[normalize-space()='Log Out']")
	WebElement logoutlabel;
	
	//Action class
	//Click on the Profile icon in home page
	public void clickprofileicon() {
		profileIcon.click();
	}
	
	//Click on logout
	public void clicklogout() {
		logoutlabel.click();
	}
	
}
