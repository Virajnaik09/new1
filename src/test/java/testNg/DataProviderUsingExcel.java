package testNg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import object_repository.HomePage;
import object_repository.LoginPage;
import object_repository.OrganizationLookup_Page;
import object_repository.Organization_page;
import object_repository.ValidateOrganization;
import package_utility.Excel_utility;
import package_utility.File_utility;
import package_utility.Webdriver_Utility;

public class DataProviderUsingExcel {
	
	@Test(dataProvider = "ReadData")
 public void CreateOrganization(String orgName,String EMAIL,String NUMBER) throws Throwable
 {
	 File_utility flib = new File_utility();
		String BROWSER = flib.getKeyAndValuePair("browser");
		String URL = flib.getKeyAndValuePair("url");
		String USERNAME = flib.getKeyAndValuePair("username");
		String PASSWORD = flib.getKeyAndValuePair("username");

		WebDriver driver ;
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
		home.getOrganizationButton().click();
		Organization_page org = new Organization_page(driver);
		org.getCreateOrganizationButton().click();
		
		OrganizationLookup_Page org1 = new OrganizationLookup_Page(driver);
		org1.EnterOrganizationName(orgName);
		org1.EnterEmail(EMAIL);
		org1.EnterNumber(NUMBER);
		org1.getSaveButton().click();
		ValidateOrganization validate = new ValidateOrganization(driver);
		String actData = validate.ValidateOrgDetails(driver);
		Assert.assertEquals(actData, orgName);
		home.logout(driver);
		wlib.QuitBrowser(driver);
 }
 @DataProvider
 public Object[][] ReadData() throws Throwable
 {  
	Excel_utility elib = new Excel_utility();
	Object[][] data =elib.ReadExcelDataUsingDataProvider("dataProvider");
    
	return data;
	 
 }
}
