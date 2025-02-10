package ddt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class StoringMultipleDataToExcelSheet {
public static void main(String[] args) throws Throwable {
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://www.flipkart.com");
	List<WebElement> ele = driver.findElements(By.xpath("//a"));
	FileInputStream fis = new FileInputStream("C:\\Users\\viraj\\Downloads\\Book1.xlsx");
	Workbook book = WorkbookFactory.create(fis);
	Sheet sheet = book.getSheet("sheet1");
	int rowNum = 0 ;
	for (WebElement LINKS : ele) 
	{
		String name = LINKS.getAttribute("href");
		if(name != null )
		{
			sheet.createRow(rowNum++).createCell(0).setCellValue(name);
		}
	}
	FileOutputStream fos = new FileOutputStream("C:\\Users\\viraj\\Downloads\\Book1.xlsx");
	book.write(fos);
	book.close();
}
}
