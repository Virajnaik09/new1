package ganerics;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import object_repository.CampaignLookup_Page;
import object_repository.Campaign_Page;
import object_repository.HomePage;
import object_repository.LoginPage;
import object_repository.ValidateCampaign;
import package_utility.Excel_utility;
import package_utility.File_utility;
import package_utility.Java_utility;
import package_utility.Webdriver_Utility;

public class Scenario3CreateCampaignAndVerify {
	public static void main(String[] args) throws Throwable 
	{
		File_utility flib = new File_utility();
		String BROWSER = flib.getKeyAndValuePair("browser");
		String URL = flib.getKeyAndValuePair("url");
		String USERNAME = flib.getKeyAndValuePair("username");
		String PASSWORD = flib.getKeyAndValuePair("username");

		WebDriver driver = null;
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			driver = new EdgeDriver();
		}
		Webdriver_Utility wlib = new Webdriver_Utility();
		wlib.MaximizeWindows(driver);
		wlib.ImplicitWait(driver);
		driver.get(URL);
		LoginPage login = new LoginPage(driver);
		login.LoginToApp(USERNAME, PASSWORD);
		HomePage home = new HomePage(driver);
		home.getMoreButton().click();
		home.getCampaignButton().click();
		Campaign_Page camp = new Campaign_Page(driver);
		camp.ClickCreateCampaignButton();
		Java_utility jlib = new Java_utility();
		int ranNum = jlib.getRandomNumber();
		Excel_utility elib = new Excel_utility();
		String cmpName = elib.getExcelData("campaign", 0, 0) +ranNum;
		CampaignLookup_Page camp1 = new CampaignLookup_Page(driver);
		camp1.EnterCampaignName(cmpName);
		camp1.ClickSaveButton();
		ValidateCampaign validate = new ValidateCampaign(driver);
		validate.ValidateCampaignDetails(driver);
		home.logout(driver);
		wlib.QuitBrowser(driver);
	}
}

