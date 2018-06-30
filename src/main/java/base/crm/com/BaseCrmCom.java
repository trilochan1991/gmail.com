package base.crm.com;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseCrmCom {
	
	public static WebDriver driver;
	public static Properties properties;
	
	public BaseCrmCom() throws IOException{
		
	    properties = new Properties();
		FileInputStream file = new FileInputStream("");
		properties.load(file);
	}
	
	public static void Initilation(){
		
		if(properties.getProperty("brower").equals("chrome")){
			System.setProperty("driver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();			
		}
		else if(properties.getProperty("brower").equals("IE")){
			System.setProperty("driver.ie.driver", "IEDriver.exe");
			driver = new InternetExplorerDriver();
		}
		else{
			System.setProperty("driver.geco.driver", "gecodriver.exe");
			driver = new FirefoxDriver();
		}
		
	}
	
	public static void setup(){
		driver.manage().window().maximize();
		driver.get(properties.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);		
		driver.manage().deleteAllCookies();		
	}
	
	public static void Syncronisationsendkey(WebDriver driver, WebElement element, int second){
		
		WebDriverWait wait = new WebDriverWait(driver, second);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void Syncronisationclick(WebDriver driver, WebElement element, int second){
		
		WebDriverWait wait = new WebDriverWait(driver, second);
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	

}
