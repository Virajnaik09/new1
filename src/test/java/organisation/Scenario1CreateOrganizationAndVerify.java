package organisation;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Scenario1CreateOrganizationAndVerify 
{
	public static void main(String[] args) throws Throwable 
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\viraj\\Downloads\\file.properties");
		Properties pro = new Properties();
		pro.load(fis);
		String USERNAME = pro.getProperty("username");
		String PASSWORD = pro.getProperty("password");
		String BROWSER = pro.getProperty("browser");
		String URL = pro.getProperty("url");

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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("(//a[text()=\"Organizations\"])[1]")).click();
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		Random ran = new Random();
		int ranNUM = ran.nextInt(1000);
		FileInputStream fis1 = new FileInputStream("C:\\Users\\viraj\\Downloads\\Book1.xlsx");
		Workbook book = WorkbookFactory.create(fis1);
		Sheet sheet = book.getSheet("organization");
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);
		String orgName = cell.getStringCellValue()+ ranNUM;
		driver.findElement(By.xpath("//input[@name=\"accountname\"]")).sendKeys(orgName);

		Cell cell1 = sheet.getRow(1).getCell(0);
		DataFormatter data = new DataFormatter();
		String NUMBER = data.formatCellValue(cell1);
		driver.findElement(By.id("phone")).sendKeys(NUMBER);

		Cell cell2 = sheet.getRow(2).getCell(0);
		String EMAIL = data.formatCellValue(cell2);
		driver.findElement(By.name("email1")).sendKeys(EMAIL);
		driver.findElement(By.xpath("(//input[@title=\"Save [Alt+S]\"])[1]")).click();
		String actData = driver.findElement(By.xpath("//span[@id=\"dtlview_Organization Name\"]")).getText();
		//	System.out.println(orgName);
		//	System.out.println(actData);
		if(actData.contains(orgName))
		{
			System.out.println("Organisation "+orgName+" is created successfully");
		}
		else
		{
			System.out.println("Organisation "+orgName+" is not created");
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
		driver.findElement(By.xpath("//a[text()=\"Sign Out\"]")).click();
		Thread.sleep(2000);
		driver.quit();
	}
}
