package practice.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class CreateOrganizationWithPhoneNumberTest {

	public static void main(String[] args) throws Throwable {
		
		//read common data from properties file
		FileInputStream fis=new FileInputStream("C:\\Users\\Ramya Bhukya\\OneDrive\\Desktop\\commondata1.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		pObj.load(fis);
		
		String BROWSER = pObj.getProperty("browser");
		String URL= pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
		    // generate the Random Number
				Random random = new Random();
				int randomInt = random.nextInt(1000);

				// Read testscript data from Excel file
				FileInputStream fis1 = new FileInputStream("C:\\Users\\Ramya Bhukya\\OneDrive\\Desktop\\Utility.xlsx");
				Workbook wb = WorkbookFactory.create(fis1);
				Sheet sh = wb.getSheet("org");
				Row row = sh.getRow(7);
				String orgName = row.getCell(2).toString()+ randomInt;
				String phoneNumber = row.getCell(3).getStringCellValue();
				wb.close();

				WebDriver driver = null;
                //polymorphism concept
				if (BROWSER.equals("chrome")) {
					driver = new ChromeDriver();
				} else if (BROWSER.equals("firefox")) {
					driver = new FirefoxDriver();
				} else if (BROWSER.equals("edge")) {
					driver = new EdgeDriver();
				} else {
					driver = new ChromeDriver();
				}

				// step1: login to app
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				driver.get(URL);

				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();

				// step 2 :navigate organization module
				driver.findElement(By.linkText("Organizations")).click();

				// step3 :click on "create organization module
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

				// step 4: enter all the details and create new Organization
				driver.findElement(By.name("accountname")).sendKeys(orgName);
				driver.findElement(By.id("phone")).sendKeys(phoneNumber);
				
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				
				//Verify header msg Expected Result
				String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headerInfo.contains(orgName)) {
					System.out.println(orgName +   " header verified==PASS");
				}else {
					System.out.println(orgName +   " header is verified==FAIL");
				}
				
				//Verify header phoneNumber info Expected Result
				String actPhoneNumber=driver.findElement(By.id("dtlview_Phone")).getText();
				if(actPhoneNumber.equals(phoneNumber)) {
					System.out.println(phoneNumber +  "informatio is created==PASS");
				}else {
					System.out.println(phoneNumber +  "information is not created==FAIL");
				}
				
				
				// step5 : logout
				driver.quit();


	}

}