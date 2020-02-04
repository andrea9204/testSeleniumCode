package newpackage;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class AccountWithContact {
	WebDriver driver = null;
	String testUrl = "https://login.salesforce.com";
	private Map<String, Object> vars;
	
	@BeforeMethod

	//Browser open

	public void setUp(){
	System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Driver\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get(testUrl);
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
		
	public String waitForWindow(int timeout) {
		 try {
		   Thread.sleep(timeout);
		 } catch (InterruptedException e) {
		   e.printStackTrace();
		 }
		 Set<String> whNow = driver.getWindowHandles();
		 Set<String> whThen = (Set<String>) vars.get("window_handles");
		 if (whNow.size() > whThen.size()) {
		   whNow.removeAll(whThen);
		 }
		 return whNow.iterator().next();
		}

	@Test

	public void validLogin() throws InterruptedException {
		LoginPOM loginpage= new LoginPOM(driver);
		loginpage.LoginwithCookies("sforg@sf.com", "newuser@1");
		Thread.sleep(10000);
		if(driver.findElement(By.id("tryLexDialogX")).isDisplayed()){
			driver.findElement(By.id("tryLexDialogX")).click();
		}
		createAccount();
		driver.quit();
	}
		
	public void createAccount() throws InterruptedException{
		Thread.sleep(3000);
		driver.findElement(By.linkText("Accounts")).click();
		driver.findElement(By.name("new")).click();
		driver.findElement(By.id("acc2")).click();
		driver.findElement(By.id("acc2")).sendKeys("Garcia Inc");
		driver.findElement(By.id("acc6")).click();
		
		{
			   WebElement dropdown = driver.findElement(By.id("acc6"));
			   dropdown.findElement(By.xpath("//option[. = 'Investor']")).click();
			 }
			 driver.findElement(By.id("acc6")).click();
			 driver.findElement(By.name("save")).click();
			 
			 driver.findElement(By.linkText("Contacts")).click();
			 driver.findElement(By.name("new")).click();
			 driver.findElement(By.id("name_firstcon2")).click();
			 driver.findElement(By.id("name_firstcon2")).sendKeys("Amanda");
			 driver.findElement(By.id("name_lastcon2")).click();
			 driver.findElement(By.id("name_lastcon2")).sendKeys("Garcia");

			 driver.findElement(By.id("con4")).click();
			 driver.findElement(By.id("con4")).sendKeys("Garcia Inc");
			 driver.findElement(By.id("con5")).click();
			 driver.findElement(By.id("con5")).sendKeys("CEO");
			 driver.findElement(By.name("save")).click();
			 driver.findElement(By.linkText("Accounts")).click();
			 driver.findElement(By.cssSelector(".even a")).click();
			 driver.findElement(By.cssSelector(".dataCell > a")).click();
	}
}
