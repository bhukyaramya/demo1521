package tekPyramid;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Empty {

	public static void main(String[] args) throws IOException {
		
		FileInputStream fis=new FileInputStream("C:\\Users\\Ramya Bhukya\\OneDrive\\Desktop\\commondata1.properties");
		Properties pObj=new Properties();
		pObj.load(fis);                                                                                                
		
		String BROWSER=pObj.getProperty("browser");
		String URL=pObj.getProperty("url");
		String USERNAME=pObj.getProperty("username");
		String PASSWORD=pObj.getProperty("password");
		
		//generate the Random Number
		Random random = new Random();
		int randomInt = random.nextInt(1000);
		
		
		Scanner s=new Scanner(System.in);
		System.out.println("enter the Browser");
		String browser=s.next();
		System.out.println(browser);
		
		WebDriver driver  = null;
		if(browser.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(browser.equals("edge")) {
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}
				
				
		 //driver=new ChromeDriver();
		driver.get(URL);
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//driver.findElement(By.linkText("organizations")).click();
		driver.quit();
	}

}
