package organisation;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import package_utility.File_utility;

public class Scenario6CreateAndDeleteProduct 
{
	public static void main(String[] args) throws Throwable 
	{
//		FileInputStream fis = new FileInputStream("C:\\Users\\viraj\\Downloads\\file.properties");
//		Properties pro = new Properties();
//		pro.load(fis);
//		pro.setProperty("browser","edge");
//		String BROWSER = pro.getProperty("browser");
//		String URL = pro.getProperty("url");
//		String USERNAME = pro.getProperty("username");
//		String PASSWORD = pro.getProperty("password");
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("//a[text()=\"Products\"]")).click();
		driver.findElement(By.xpath("//img[@title=\"Create Product...\"]")).click();
		Random ran = new Random();
		int ranNum = ran.nextInt(1000);
		FileInputStream fis1 = new FileInputStream("C:\\Users\\viraj\\Downloads\\Book1.xlsx");
		Workbook book = WorkbookFactory.create(fis1);
		Sheet sheet = book.getSheet("product");
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);
		String productName = cell.getStringCellValue() + ranNum;
		driver.findElement(By.xpath("//input[@name=\"productname\"]")).sendKeys(productName);
		driver.findElement(By.xpath("(//input[@title=\"Save [Alt+S]\"])[1]")).click();
//		String prdt = driver.findElement(By.xpath("//span[@id=\"dtlview_Product Name\"]")).getText();
//		if(prdt.contains(productName))
//		{
//			System.out.println("Product "+productName+" is created succesfully");
//		}
//		else
//		{
//			System.out.println("Product "+productName+" not created");
//		}
//		driver.findElement(By.xpath("//input[@title=\"Delete [Alt+D]\"]")).click();
//		Alert alt = driver.switchTo().alert();
//		alt.accept();
//		driver.findElement(By.xpath("//input[@name=\"search_text\"]")).sendKeys(productName,Keys.ENTER);
//		String delete = driver.findElement(By.xpath("//span[@class=\"genHeaderSmall\"]")).getText();
//		if(delete.contains("No Product Found !"))
//		{
//			System.out.println("Product "+productName+" is deleted succesfully");
//		}
//		else
//		{
//			System.out.println("Product "+productName+" not deleted");
//		}
//		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
//		driver.findElement(By.xpath("//a[text()=\"Sign Out\"]")).click();
//		Thread.sleep(2000);
//		driver.quit();
	}
}
