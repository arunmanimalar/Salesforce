package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Setuppage extends Basepage {
	
	//initiated the driver instance 
	public Setuppage(WebDriver driver) {
		super(driver);
	}
	
	//capture the objects
	@FindBy(xpath="//span[@title='Setup']")
	WebElement logosetup;
	
	@FindBy(xpath="//button[@class='slds-button slds-icon-waffle_container slds-context-bar__button slds-button forceHeaderButton salesforceIdentityAppLauncherHeader']")
	WebElement btnapplauncher;
	
	@FindBy(xpath="//span[normalize-space()='App Launcher']")
	WebElement btnapplauncher1;
	
	@FindBy(xpath="//input[@placeholder='Search apps and items...']")
	WebElement txtsearchbox;
	
	@FindBy(xpath="//a[@data-label='Sales']//b[contains(text(),'Sales')]")
	WebElement btnsales;
	
	//Action methods
	public boolean verifysetuppage() {
		try {
		return (logosetup.isDisplayed());
		}
		catch(Exception e) {
			return (false);
		}
	}
	
	public void clickapplauncher() {
		btnapplauncher.click();
	}
	
	public void Setsearchterm(String searchtext) {
		txtsearchbox.sendKeys(searchtext);
	}
	
	public void clicksalesfromsuggestion() {
		btnsales.click();
	}

}
