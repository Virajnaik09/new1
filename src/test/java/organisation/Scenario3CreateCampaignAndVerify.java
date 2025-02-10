package organisation;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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

public class Scenario3CreateCampaignAndVerify 
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
		WebElement more = driver.findElement(By.xpath("//a[text()=\"More\"]"));
		Actions act = new Actions(driver);
		act.moveToElement(more).click().perform();
		driver.findElement(By.xpath("//a[@name=\"Campaigns\"]")).click();
		driver.findElement(By.xpath("//img[@title=\"Create Campaign...\"]")).click();
		FileInputStream fis1 = new FileInputStream("C:\\Users\\viraj\\Downloads\\Book1.xlsx");
		Workbook book = WorkbookFactory.create(fis1);
		Random ran = new Random();
		int ranNum = ran.nextInt(1000);
		Sheet sheet = book.getSheet("campaign");
		String name = sheet.getRow(0).getCell(0).getStringCellValue() + ranNum;
		driver.findElement(By.xpath("//input[@name=\"campaignname\"]")).sendKeys(name);
		driver.findElement(By.xpath("(//input[@title=\"Save [Alt+S]\"])[1]")).click();
		String actData = driver.findElement(By.xpath("//span[@id=\"dtlview_Campaign Name\"]")).getText();
		//System.out.println(actData);
		if(actData.equals(name))
		{
			System.out.println("Campaign "+name+" is created successfully");
		}
		else
		{
			System.out.println("campaign "+name+" is not created");
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
		driver.findElement(By.xpath("//a[text()=\"Sign Out\"]")).click();
		Thread.sleep(1000);
		driver.quit();
	}
}
