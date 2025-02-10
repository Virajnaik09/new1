package testNg;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import object_repository.HomePage;
import object_repository.OrganizationLookup_Page;
import object_repository.Organization_page;
import object_repository.ValidateOrganization;
import package_utility.BaseClass;
import package_utility.Excel_utility;
import package_utility.Java_utility;
@Listeners(package_utility.ExtentReport.class)
public class CreateOrganizationTest extends BaseClass{
	@Test(groups = "smokeTest",retryAnalyzer = package_utility.RetryImp.class)
	public void createOrganizationTest() throws Throwable 
		{
			HomePage home = new HomePage(driver);
			home.getOrganizationButton().click();
			Organization_page org = new Organization_page(driver);
			org.getCreateOrganizationButton().click();
			Java_utility jlib = new Java_utility();
			int ranNUM = jlib.getRandomNumber();
			Excel_utility elib = new Excel_utility();
			String orgName = elib.getExcelData("organization", 0, 0) +ranNUM;
			String EMAIL = elib.ReadExcelDataUsingDataFormatter("organization", 2, 0);
			String NUMBER = elib.ReadExcelDataUsingDataFormatter("organization", 1, 0);
			OrganizationLookup_Page org1 = new OrganizationLookup_Page(driver);
			org1.EnterOrganizationName(orgName);
			org1.EnterEmail(EMAIL);
			org1.EnterNumber(NUMBER);
			org1.getSaveButton().click();
			ValidateOrganization validate = new ValidateOrganization(driver);
			String actData = validate.ValidateOrgDetails(driver);
			Assert.assertEquals(actData, orgName);
			System.out.println("Organization validated");
		}
}
