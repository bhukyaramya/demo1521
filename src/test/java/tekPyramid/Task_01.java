package tekPyramid;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task_01 {

	public static void main(String[] args) throws Throwable {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("phones");
		driver.findElement(By.id("nav-search-submit-button")).click();
		
		java.util.List<WebElement> phones = driver
				.findElements(By.xpath("//div[@data-cy='title-recipe' and not (contains(.,'Sponsored'))]"));
		FileInputStream fis = new FileInputStream("C:\\Users\\Ramya Bhukya\\OneDrive\\Desktop\\Phone.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		int i = 0;
		for (WebElement ph : phones) {
			System.out.println(ph.getText());
			 sh.createRow(i++).createCell(0).setCellValue(ph.getText());
		}
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Ramya Bhukya\\OneDrive\\Desktop\\Phone.xlsx");
		wb.write(fos);
		wb.close();
		System.out.println("*Excecuted*");
		driver.quit();
	}

}
