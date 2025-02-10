package testNg;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import object_repository.HomePage;
import object_repository.ProductsLookup_Page;
import object_repository.Products_page;
import object_repository.ValidateProducts;
import package_utility.BaseClass;
import package_utility.Excel_utility;
import package_utility.Java_utility;
@Listeners(package_utility.ExtentReport.class)
public class CreateProductTest extends BaseClass{
	@Test(groups = "smokeTest",retryAnalyzer = package_utility.RetryImp.class)
	public void createProductTest()throws Throwable 
	{   		// I AM VIRAJ
		         // VIRAJ NAIK
		        // viraj
		//viraj
		//
		//
		//
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
		String actprdt = validate.ProductVerificationDetails(driver);
		Assert.assertEquals(actprdt, productName);
		System.out.println("Product validated");
	}
	//viraj
}
