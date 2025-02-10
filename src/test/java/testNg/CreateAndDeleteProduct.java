package testNg;

import org.testng.Assert;
import org.testng.annotations.Test;

import object_repository.HomePage;
import object_repository.ProductsLookup_Page;
import object_repository.Products_page;
import object_repository.ValidateProducts;
import package_utility.BaseClass;
import package_utility.Excel_utility;
import package_utility.Java_utility;
import package_utility.Webdriver_Utility;

public class CreateAndDeleteProduct extends BaseClass {

	@Test(groups = {"smokeTest","regression"})
	public void createAndDeleteProduct() throws Throwable 
	{
		Webdriver_Utility wlib = new Webdriver_Utility();
		HomePage home = new HomePage(driver);
		home.getProductsButton().click();
		Products_page prdct = new Products_page(driver);
		prdct.clickCreateProductsButton();
		Java_utility jlib = new Java_utility();
		int ranNum = jlib.getRandomNumber();
		Excel_utility elib = new Excel_utility();
		String productName = elib.getExcelData("product", 0, 0) + ranNum;
		ProductsLookup_Page prdctLook = new ProductsLookup_Page(driver);
		prdctLook.EnterProductName(productName);
		prdctLook.clickSaveButton();
		ValidateProducts validate = new ValidateProducts(driver);
		String actPrdct = validate.ProductVerificationDetails(driver);
		Assert.assertEquals(actPrdct, productName);
		System.out.println("Product validated");
		home.getProductsButton().click();
		prdct.DeleteProduct(driver, productName);
		wlib.AcceptAlert(driver);
		jlib.JavaSleep(2000);
		wlib.RefreshWindow(driver);
		validate.Verify_IfTheProductIsDeleted(driver, productName);
	}
}
