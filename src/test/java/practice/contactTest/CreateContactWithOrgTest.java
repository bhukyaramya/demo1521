package practice.contactTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateContactWithOrgTest {

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
		Row row = sh.getRow(7);
		String orgName = row.getCell(2).toString() + randomInt;
		String contactLastName = row.getCell(2).toString() + randomInt;
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
		driver.findElement(By.linkText("Organizations")).click();

		// step3 :click on "create organization module
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// step 4: enter all the details and create new Organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify header phone Number info Expected Result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " header verified==PASS");
		} else {
			System.out.println(orgName + " header is verified==FAIL");
		}

		// step 5 :navigate organization module
		driver.findElement(By.linkText("Contacts")).click();

		// step 6 :click on "create organization module
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step 7: enter all the details and create new Organization
		driver.findElement(By.name("lastname")).sendKeys("contactlastName");
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// Switch to child Window
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);

			String actUrl = driver.getCurrentUrl();
			if (actUrl.contains("module=Accounts")) {
				break;
			}

			driver.findElement(By.name("search_text")).sendKeys(orgName);
			driver.findElement(By.name("search")).click();
			// dynamic orgname is available in this variable facebook is a static data
			// basically this xpath is also getting created in runtime thats y it is dynamic
			// xpath
			driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

			// Switch to parent Window
			Set<String> set1 = driver.getWindowHandles();
			Iterator<String> it1 = set1.iterator();

			while (it1.hasNext()) {
				windowID = it1.next();
				driver.switchTo().window(windowID);

				actUrl = driver.getCurrentUrl();
				if (actUrl.contains("Contacts&action")) {
					break;
				}

				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

				// verify Header contact Information info Expected Result
				headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if (headerInfo.contains(contactLastName)) {
					System.out.println(contactLastName + "Header  verified==PASS");
				} else {
					System.out.println(contactLastName + "Header is verified==FAIL");
				}

				// Verify header orgName info Expected Result
				String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
				System.out.println(actOrgName);
				if (actOrgName.trim().equals(orgName)) {
					System.out.println(orgName + "information is created==PASS");
				} else {
					System.out.println(orgName + "information is not created==FAIL");
				}

				// step 8 : logout
				driver.quit();

			}
		}
	}
}
