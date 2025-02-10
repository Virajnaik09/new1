package testNg;

import org.testng.annotations.Test;

public class SampleTest {
//    @Test(priority = 1)
//	public void createContact()
//	{
//		System.out.println("Contact created");
//	}
//    @Test(priority = -1)
//	public void ModifyContact()
//	{
//		System.out.println("Contact Modified");
//	}
//    @Test(priority = -2)
//	public void DeleteContact()
//	{
//		System.out.println("Contact Deleted");
//	}
	 @Test(priority = 2)
		public void createContact()
		{ 
			System.out.println("Contact created");
		}
	    @Test
		public void ModifyContact()
		{
			System.out.println("Contact Modified");
		}
	    @Test(dependsOnMethods = { "ModifyContact","createContact"})
		public void DeleteContact()
		{
			System.out.println("Contact Deleted");
		}
}
