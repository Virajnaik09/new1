package ganerics;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import object_repository.HomePage;
import object_repository.LoginPage;
import object_repository.ProductsLookup_Page;
import object_repository.Products_page;
import object_repository.ValidateProducts;
import package_utility.Excel_utility;
import package_utility.File_utility;
import package_utility.Java_utility;
import package_utility.Webdriver_Utility;

public class Scenario2CreateProductAndVerify {
	public static void main(String[] args) throws Throwable 
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
		home.getProductsButton().click();;
		Products_page product = new Products_page(driver);
		product.clickCreateProductsButton();
		Java_utility jlib = new Java_utility();
		int ranNum = jlib.getRandomNumber();
		Excel_utility elib = new Excel_utility();
		String productName = elib.getExcelData("product", 0, 0) +ranNum;
		ProductsLookup_Page product1 = new ProductsLookup_Page(driver);
		product1.EnterProductName(productName);
		product1.clickSaveButton();
		ValidateProducts validate = new ValidateProducts(driver);
		validate.ProductVerificationDetails(driver);
		home.logout(driver);
		wlib.QuitBrowser(driver);
	}
}

