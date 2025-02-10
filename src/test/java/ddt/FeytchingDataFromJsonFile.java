package ddt;

import java.io.File;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FeytchingDataFromJsonFile {
public static void main(String[] args) throws Throwable {
	File file = new File("C:\\Users\\viraj\\Downloads\\login.json");
	ObjectMapper obj = new ObjectMapper();
	JsonNode data = obj.readTree(file);
	String BROWSER = data.get("browser").asText();
	String URL = data.get("url").asText();
	String USERNAME = data.get("username").asText();
	String PASSWORD = data.get("password").asText();
	WebDriver driver = null;
	if(BROWSER.equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
	}
	else if (BROWSER.equalsIgnoreCase("edge"))
	{
		driver = new EdgeDriver();
	}
	else if(BROWSER.equalsIgnoreCase("Firefox"))
	{
		driver = new FirefoxDriver();
	}
	else if(BROWSER.equalsIgnoreCase("Chrome"))
	{
		driver = new ChromeDriver();
	}
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	driver.get(URL);
	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	driver.findElement(By.id("submitButton")).click();

}
}
