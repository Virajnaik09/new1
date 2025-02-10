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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Scenario4CreateCampaignWithProductAndVerify 
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
		WebElement more = driver.findElement(By.xpath("//a[text()=\"More\"]"));
		Actions act = new Actions(driver);
		act.moveToElement(more).click().perform();
		driver.findElement(By.xpath("//a[@name=\"Campaigns\"]")).click();
		driver.findElement(By.xpath("//img[@title=\"Create Campaign...\"]")).click();
		FileInputStream fis2 = new FileInputStream("C:\\Users\\viraj\\Downloads\\Book1.xlsx");
		Workbook book1 = WorkbookFactory.create(fis2);
		Random ran1 = new Random();
		int ranNum1 = ran1.nextInt(1000);
		Sheet sheet1 = book1.getSheet("campaign");
		String name = sheet1.getRow(0).getCell(0).getStringCellValue() + ranNum1;
		driver.findElement(By.xpath("//input[@name=\"campaignname\"]")).sendKeys(name);
		driver.findElement(By.xpath("//img[@alt=\"Select\"]")).click();
		Set<String> ALLID = driver.getWindowHandles();
		Iterator<String> it = ALLID.iterator();
		while(it.hasNext())
		{
			String win = it.next();
			driver.switchTo().window(win);
			if(win.contains("Products&action"))
			{
				break;
			}
		}
		driver.findElement(By.xpath("//input[@name=\"search_text\"]")).sendKeys(productName);
		driver.findElement(By.xpath("//input[@name=\"search\"]")).click();
		driver.findElement(By.xpath("//a[text()='"+productName+"']")).click();
		Set<String> ALLID1 = driver.getWindowHandles();
		Iterator<String> it1 = ALLID1.iterator();
		while(it1.hasNext())
		{
			String win = it1.next();
			driver.switchTo().window(win);
			if(win.contains("Campaigns&action"))
			{
				break;
			}
		}
		driver.findElement(By.xpath("(//input[@title=\"Save [Alt+S]\"])[1]")).click();
		String actData = driver.findElement(By.xpath("//span[@id=\"dtlview_Campaign Name\"]")).getText();
		if(actData.contains(name))
		{
			System.out.println("Campaign created successfully");
		}
		else
		{
			System.out.println("campaign is not created");
		}
		String actPrdt = driver.findElement(By.xpath("//span[@id=\"dtlview_Product\"]")).getText();
		if(actPrdt.contains(productName))
		{
			System.out.println("product "+productName+" is created successfully in the campaign "+name);
		}
		else
		{
			System.out.println("product "+productName+" is not created in the campaign "+name);
		}
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
		driver.findElement(By.xpath("//a[text()=\"Sign Out\"]")).click();
		Thread.sleep(1000);
		driver.quit();
	}
}
