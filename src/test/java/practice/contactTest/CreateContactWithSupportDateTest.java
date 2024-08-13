package practice.contactTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateContactWithSupportDateTest {

	public static void main(String[] args) throws Throwable {

		// read common data from properties file
		FileInputStream fis = new FileInputStream("C:\\Users\\Ramya Bhukya\\OneDrive\\Desktop\\commondata1.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		pObj.load(fis);

		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");

		// generate the Random Number
		Random random = new Random();
		int randomInt = random.nextInt(1000);

		// Read testscript data from Excel file
		FileInputStream fis1 = new FileInputStream("C:\\Users\\Ramya Bhukya\\OneDrive\\Desktop\\Utility.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("contact");
		Row row = sh.getRow(4);
		String lastName = row.getCell(2).toString() + randomInt;
		wb.close();

		WebDriver driver = null;
		// polymorphism concept
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
		driver.findElement(By.linkText("Contacts")).click();

		// step3 :click on "create organization module
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step 4: enter all the details and create new Organization

		Date dateObj = new Date();

		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sim.format(dateObj);

		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String endDate = sim.format(cal.getTime());

		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("support Start Date")).clear();
		driver.findElement(By.xpath("support Start Date")).sendKeys(startDate);

		driver.findElement(By.xpath("support_end_date")).clear();
		driver.findElement(By.xpath("support_end_date")).sendKeys(endDate);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify header Start Date and end Date info Expected Result
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if (actStartDate.equals(startDate)) {
			System.out.println(startDate + "information is created==PASS");
		} else {
			System.out.println(startDate + "information is created==FAIL");
		}

		String actendDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if (actendDate.equals(endDate)) {
			System.out.println(endDate + "information is created==PASS");
		} else {
			System.out.println(endDate + "information is created==FAIL");
		}

		// step5 : logout
		driver.quit();

	}

}
