package tekPyramid;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.reporters.jq.Main;

public class CreateOrgTest {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		
		//read common data from JSONFile
		JSONParser parser= new JSONParser();
		
	     Object obj = parser.parse(new FileReader("C:\\Users\\Ramya Bhukya\\OneDrive\\Desktop\\data\\appCommonData.json"));
	    JSONObject map = (JSONObject)obj;
	    
	    String BROWSER = map.get("browser").toString(); 
		String URL = map.get("url").toString(); 
		String USERNAME = map.get("username").toString(); 
		String PASSWORD = map.get("password").toString(); 

		// generate the Random Number
		Random random = new Random();
		int randomInt = random.nextInt(1000);

		// Read testscript data from Excel file
		FileInputStream fis1 = new FileInputStream("C:\\Users\\Ramya Bhukya\\OneDrive\\Desktop\\testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Sheet1");
		Row row = sh.getRow(1);
		String orgName = row.getCell(2).toString()+ randomInt;
		wb.close();

		WebDriver driver = null;

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
		driver.findElement(By.linkText("organizations")).click();

		// step3 :click on "create organization module
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// step 4: enter all the details and create new Organization
		driver.findElement(By.name("accountname")).sendKeys("facebook1");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// step5 : logout
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/layerPopupBg.gif']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		driver.quit();

	}

}
