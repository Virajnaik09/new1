package testNg;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import object_repository.CampaignLookup_Page;
import object_repository.Campaign_Page;
import object_repository.HomePage;
import object_repository.ValidateCampaign;
import package_utility.BaseClass;
import package_utility.Excel_utility;
import package_utility.Java_utility;
@Listeners(package_utility.Listeners.class)
public class CreateCampaignTest extends BaseClass {
	@Test(groups = "regressionTest",retryAnalyzer = package_utility.RetryImp.class)
	public void createCampaignTest()throws Throwable 
	{
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
		String actData = validate.ValidateCampaignDetails(driver);
		Assert.assertEquals(actData, cmpName);
		System.out.println("Campaign validated");
}
}
