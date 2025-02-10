package testNg;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderex1 {
	
	@Test(dataProvider = "getData")
	public void BookTickets(int num,String src ,String dst)
	{
		System.out.println("Book "+num+ " Tickets From " +src+ " To " + dst );
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] objArr = new Object[3][3];
		objArr[0][0]= 2;
		objArr[0][1]= "Banglore";
		objArr[0][2]= "Manglore";
		
		objArr[1][0]= 3;
		objArr[1][1]= "Banglore";
		objArr[1][2]= "Mysore";

		objArr[2][0]= 4;
		objArr[2][1]= "Banglore";
		objArr[2][2]= "Hydrabad";


		return objArr;
		
	}
}
