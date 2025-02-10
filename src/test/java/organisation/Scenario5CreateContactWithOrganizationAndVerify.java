package organisation;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Scenario5CreateContactWithOrganizationAndVerify 
{
	public static void main(String[] args) throws Throwable 
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\viraj\\Downloads\\file.properties");
		Properties pro = new Properties();
		pro.load(fis);
		pro.setProperty("browser","edge");
		String BROWSER = pro.getProperty("browser");
		String URL = pro.getProperty("url");
		String USERNAME = pro.getProperty("username");
		String PASSWORD = pro.getProperty("password");
		
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
		FileInputStream fis1 = new FileInputStream("C:\\Users\\viraj\\Downloads\\Book1.xlsx");
		Workbook book = WorkbookFactory.create(fis1);
		Random ran = new Random();
		int ranNum = ran.nextInt(1000);
		Sheet sheet = book.getSheet("organization");
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);
		String orgName = cell.getStringCellValue() + ranNum;
		driver.findElement(By.xpath("//input[@name=\"accountname\"]")).sendKeys(orgName);
		driver.findElement(By.xpath("(//input[@title=\"Save [Alt+S]\"])[1]")).click();
		Thread.sleep(1000);
		driver.navigate().refresh();
		driver.findElement(By.xpath("//td/a[text()=\"Contacts\"]")).click();
		driver.findElement(By.xpath("//img[@alt=\"Create Contact...\"]")).click();
		FileInputStream fis2 = new FileInputStream("C:\\Users\\viraj\\Downloads\\Book1.xlsx");
		Workbook book1 = WorkbookFactory.create(fis2);
		Sheet sheet1 = book1.getSheet("contacts");
		String firstname = sheet1.getRow(0).getCell(0).getStringCellValue();
		String lastname = sheet1.getRow(1).getCell(0).getStringCellValue() + ranNum;
		driver.findElement(By.xpath("//input[@name=\"firstname\"]")).sendKeys(firstname);
		driver.findElement(By.xpath("//input[@name=\"lastname\"]")).sendKeys(lastname);
		driver.findElement(By.xpath("//img[@title=\"Select\"]")).click();
		Set<String> ALLID = driver.getWindowHandles();
		Iterator<String> it = ALLID.iterator();
		while(it.hasNext())
		{
			String win = it.next();
			driver.switchTo().window(win);
			if(win.contains("Accounts&action"))
			{
				break;
			}
		}
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@name=\"search\"]")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		Set<String> ALLID1 = driver.getWindowHandles();
		Iterator<String> it1= ALLID1.iterator();
		while(it1.hasNext())
		{
			String win1 = it1.next();
			driver.switchTo().window(win1);
			if(win1.contains("Contacts&action"))
			{
				break;
			}
		}
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		String actlname = driver.findElement(By.xpath("//span[@id=\"dtlview_Last Name\"]")).getText();
		String actOrgName = driver.findElement(By.xpath("//td[@id=\"mouseArea_Organization Name\"]/a")).getText();	
		//	    System.out.println(orgName);
		//	    System.out.println(actOrgName);
		if(actlname.contains(lastname))
		{
			System.out.println("Contact "+lastname+" is created successfully");
		}
		else
		{
			System.out.println("Contact "+lastname+" is not created");
		}
		if(actOrgName.contains(orgName))
		{
			System.out.println("Organization "+orgName+" is added successfully in the contact " +lastname);
		}
		else
		{
			System.out.println("Organisation "+orgName+" not added in the contact "+lastname);
		}
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
		driver.findElement(By.xpath("//a[text()=\"Sign Out\"]")).click();
		Thread.sleep(2000);
		driver.quit();
	}
}
