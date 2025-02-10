
package testNg;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderEx2 {
	
     @Test(dataProvider = "readData")
	public void CreateOrganization( String orgName,String NUMBER, String  EMAIL) throws Throwable
	{   WebDriver driver= new ChromeDriver();
		
		
		driver.get("http://www.Localhost:8888");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("(//a[text()=\"Organizations\"])[1]")).click();
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		
		driver.findElement(By.xpath("//input[@name=\"accountname\"]")).sendKeys(orgName);

		driver.findElement(By.id("phone")).sendKeys(NUMBER);

		driver.findElement(By.name("email1")).sendKeys(EMAIL);
		driver.findElement(By.xpath("(//input[@title=\"Save [Alt+S]\"])[1]")).click();
		driver.quit();
	}
     @DataProvider
     public Object[][] readData()
     {
    	 Random ran = new Random();
    		int ranNUM = ran.nextInt(1000);
    	Object[][] objArr = new Object[3][3];
    	objArr[0][0]="AAAAAAAA" + ranNUM;
    	objArr[0][1]="974315962" ;
    	objArr[0][2]="abcd123@gmail.com";
    	
    	objArr[1][0]="BBBBBBBB" + ranNUM;
    	objArr[1][1]="974315962";
    	objArr[1][2]="abcd123@gmail.com";
    	
    	objArr[2][0]="CCCCCCCC" + ranNUM;
    	objArr[2][1]= "974315962" ;
    	objArr[2][2]="abcd123@gmail.com";

		return objArr;
     }
}
