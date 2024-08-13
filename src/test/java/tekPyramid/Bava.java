package tekPyramid;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Duration;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Bava {

	public static void main(String[] args) throws Throwable {
		// Initialize WebDriver
		WebDriver driver = new ChromeDriver();

		// Navigate to the IRCTC website
		driver.get("https://www.irctc.co.in/nget/train-search");

		// Maximize the browser window
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Accept the alert if present
//		try {
//			WebElement alertButton = wait
//					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'OK')]")));
//			alertButton.click();
//		} catch (Exception e) {
//			// No alert present, continue
//		}

		// Wait until the 'From' input box is clickable and enter the source station
		WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("origin")));
		fromInput.sendKeys("KSR BENGALURU - SBC");
		fromInput.click();
		

		// Wait until the 'To' input box is clickable and enter the destination station
		WebElement toInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("destination")));
		toInput.sendKeys("HYDERABAD DECCAN - HYB");
		toInput.click();

		// Select today's date
		WebElement dateInput = driver.findElement(By.id("jDate"));
		dateInput.click();
		WebElement todayDate = driver.findElement(By.cssSelector(".ui-state-highlight"));
		todayDate.click();

		// Select 'All Classes' dropdown
		WebElement classDropdown = driver.findElement(By.id("journeyClass"));
		Select classSelect = new Select(classDropdown);
		classSelect.selectByVisibleText("SL - Sleeper Class (SL)");

		// Click on the 'Search' button
		WebElement searchButton = driver.findElement(By.cssSelector("button[type='submit']"));
		searchButton.click();

		// Wait for the results to load and scroll to the bottom
		Thread.sleep(5000);
		WebElement lastTrainElement = driver.findElement(By.cssSelector(".ng-star-inserted:last-of-type"));

		// Extract train details
		String trainName = lastTrainElement.findElement(By.cssSelector(".train-name")).getText();
		String arrivalTime = lastTrainElement.findElement(By.cssSelector(".arrival-time")).getText();
		String departureTime = lastTrainElement.findElement(By.cssSelector(".departure-time")).getText();
		String arrivalStation = lastTrainElement.findElement(By.cssSelector(".arrival-station")).getText();
		String departureStation = lastTrainElement.findElement(By.cssSelector(".departure-station")).getText();

		// Write details to Excel
		FileInputStream fis = new FileInputStream("");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = (Sheet) wb.createSheet("Train Details");
//
          //  Row headerRow =sh.getRow(0);
//            headerRow.createCell(0).setCellValue("Train Name");
//            headerRow.createCell(1).setCellValue("Arrival Time");
//            headerRow.createCell(2).setCellValue("Arrival Station");
//            headerRow.createCell(3).setCellValue("Departur");            
//            headerRow.createCell(4).setCellValue("Departure Station");
////
////            Row dataRow = sh.createRow(1);
////            dataRow.createCell(0).setCellValue(trainName);
////            dataRow.createCell(1).setCellValue(arrivalTime);
////            dataRow.createCell(2).setCellValue(arrivalStation);
////            dataRow.createCell(3).setCellValue(departureTime);
////            dataRow.createCell(4).setCellValue(departureStation);
////        
////            
////            //try  {
////            	FileOutputStream fis1 = new FileOutputStream("train_details.xlsx");
////                wb.write(fis1);
////            //}
////
////            wb.close();
//		// } catch (Exception e) {
//		// e.printStackTrace();
//		// } finally {
		driver.quit();

	}

}

// }
