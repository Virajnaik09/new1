package ganerics;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import object_repository.ContactsLookupImg_Page;
import object_repository.Contacts_Page;
import object_repository.HomePage;
import object_repository.LoginPage;
import object_repository.OrganizationLookup_Page;
import object_repository.Organization_page;
import object_repository.ValidateContacts;
import package_utility.Excel_utility;
import package_utility.File_utility;
import package_utility.Java_utility;
import package_utility.Webdriver_Utility;

public class Scenario5CreateContactWithOrganizationAndVerify {
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
        home.getOrganizationButton().click();
		Organization_page org = new Organization_page(driver);
		org.getCreateOrganizationButton().click();
		Java_utility jlib = new Java_utility();
		int ranNum = jlib.getRandomNumber();
		Excel_utility elib = new Excel_utility();
		String orgName = elib.getExcelData("organization", 0, 0) +ranNum;
		OrganizationLookup_Page orgLook = new OrganizationLookup_Page(driver);
		orgLook.EnterOrganizationName(orgName);
		orgLook.getSaveButton().click();
		wlib.RefreshWindow(driver);
        home.getContacsButton().click();
		Contacts_Page contact = new Contacts_Page(driver);
		contact.CreateContactsButton();
		String firstname = elib.getExcelData("contacts", 0, 0 );
		String lastname = elib.getExcelData("contacts", 1, 0 ) +ranNum;
		ContactsLookupImg_Page ContactLook = new ContactsLookupImg_Page(driver);
		ContactLook.EnterFirstAndLastNameToContacts(firstname, lastname);
		ContactLook.getAddOrganizationButton().click();
		wlib.SwitchWindow(driver, "Accounts&action");
		ContactLook.AddOrgToContacts(driver, orgName);
		wlib.SwitchWindow(driver, "Contacts&action");
		ContactLook.getSaveButton().click();
		ValidateContacts validate = new ValidateContacts(driver);
		validate.VerifyContactsName(driver);
		validate.Verify_IfTheOrgNameIsAddedToContacts(driver);
		home.logout(driver);
		wlib.QuitBrowser(driver);
	}

}
