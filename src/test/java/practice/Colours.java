package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Colours {
public static void main(String[] args) throws Throwable {
	FileInputStream fis = new FileInputStream("C:\\Users\\viraj\\Downloads\\browser chrome.properties");
	Properties pro = new Properties();
	pro.load(fis);
	pro.setProperty("browser","edge");
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
	driver.findElement(By.xpath("//input[@id=\"twotabsearchtextbox\"]")).sendKeys("iphone",Keys.ENTER);
	List<WebElement> allColors = driver.findElements(By.xpath("//span[text()=\"Apple iPhone 13 (128GB) - Starlight\"]/../../../..//div[@class=\"a-section s-color-swatch-container s-quick-view-text-align-start\"]//a"));
for (WebElement color : allColors) 
{

	String clr = color.getAttribute("aria-label");
	System.out.println(clr);
}
}
}
