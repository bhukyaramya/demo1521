package tekPyramid;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IRCTC_Task {

	public static void main(String[] args) throws Throwable {

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.google.com");
		// Thread.sleep(2000);

		driver.findElement(By.xpath("//textarea[@aria-label='Search']")).sendKeys("irctc");
		driver.manage().window().maximize();
		// Thread.sleep(2000);

		driver.get("https://www.irctc.co.in/nget/train-search");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		driver.findElement(By.xpath("//img[@id='disha-banner-close']")).click();

		// Wait until the 'From' input box is clickable and enter the source station
		WebElement searchFrom = driver.findElement(By.xpath("//input[@aria-autocomplete='list']"));
		searchFrom.sendKeys("KSR BENGALURU - SBC");
		searchFrom.click();

		// Wait until the 'To' input box is clickable and enter the destination station
		WebElement searchTo = driver.findElement(By.xpath("//input[@aria-controls='pr_id_2_list']"));
		searchTo.sendKeys("HYDERABAD DECAN - HYB (SECUNDERABAD)");
		searchTo.click();

		// Select today's date
		WebElement dateInput = driver.findElement(By.id("jDate"));
		dateInput.click();
		WebElement todayDate = driver.findElement(By.cssSelector(".ui-state-active"));
		todayDate.click();

		// Add an explicit wait for the dropdown to be visible and interactable
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement classDropdown = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-haspopup='listbox']")));
		classDropdown.click();

		// Scroll to the Sleeper (SL) option using JavaScript Executor
		WebElement sleeperOption = driver.findElement(By.xpath("//span[text()='Sleeper (SL)']"));
		Actions act = new Actions(driver);
		act.moveToElement(sleeperOption).perform();
		sleeperOption.click();
		Thread.sleep(2000);

		// Click on the 'Search' button
		WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
		searchButton.click();

		// Scroll to the bottom of the page to get the last train
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> trains = driver.findElements(By.cssSelector(".ng-star-inserted"));
		while (true) {
			js.executeScript("window.scrollTo(1, document.body.scrollHeight);");
			Thread.sleep(2000); // Adjust the sleep time as necessary

			// Check if we have scrolled to the last train
			List<WebElement> newTrains = driver.findElements(By.cssSelector(".ng-star-inserted"));
			if (newTrains.size() == trains.size()) {
				break;
			}
			trains = newTrains;
		}

		// Get the last train details using XPath
		WebElement lastTrain = trains.get(trains.size() - 1);
		WebElement trainName = lastTrain.findElement(By.xpath("//div[@class='col-sm-5 col-xs-11 train-heading']/strong[contains(text(),' SAMPARK KRANTI (12649)')]"));
		System.out.println(trainName.getText());

		WebElement arrivalTime = lastTrain.findElement(By.xpath("//div[@class='col-sm-5 col-xs-11 train-heading']/strong[contains(text(),' SAMPARK KRANTI (12649)')]/ancestor::div[@class='form-group no-pad col-xs-12 bull-back border-all']/descendant::div[@class='white-back no-pad col-xs-12']/descendant::span[@class='time']/strong[text()='13:50 | ']"));
		System.out.println(arrivalTime.getText());
		
		WebElement arrivalStation = lastTrain.findElement(By.xpath("//div[@class='col-sm-5 col-xs-11 train-heading']/strong[contains(text(),' SAMPARK KRANTI (12649)')]/ancestor::div[@class='form-group no-pad col-xs-12 bull-back border-all']/descendant::div[@class='white-back no-pad col-xs-12']/descendant::div[@class='col-xs-5 hidden-xs' and contains(text(),' YESVANTPUR JN | Sun, 16 Jun ')]"));
		System.out.println(arrivalStation.getText());
		
		WebElement departureTime = lastTrain.findElement(By.xpath("//div[@class='col-sm-5 col-xs-11 train-heading']/strong[contains(text(),' SAMPARK KRANTI (12649)')]/ancestor::div[@class='form-group no-pad col-xs-12 bull-back border-all']/descendant::div[@class='white-back no-pad col-xs-12']/div[@class='col-xs-7 hidden-xs']/span[@class='pull-right']/strong[text()='08:10 | ']"));
		System.out.println(departureTime.getText());
		
		WebElement departureStation = lastTrain.findElement(By.xpath("//div[@class='col-sm-5 col-xs-11 train-heading']/strong[contains(text(),' SAMPARK KRANTI (12649)')]/ancestor::div[@class='form-group no-pad col-xs-12 bull-back border-all']/descendant::div[@class='white-back no-pad col-xs-12']/div[@class='col-xs-7 hidden-xs']/span[text()=' KACHEGUDA | Mon, 17 Jun']"));
		System.out.println(departureStation.getText());
				

		// store elements in the excel sheet
		FileInputStream fis = new FileInputStream("C:\\Users\\Ramya Bhukya\\OneDrive\\Documents\\IRCTCTask.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");

		Row headerRow = sh.createRow(0);
		headerRow.createCell(0).setCellValue("Train Name");
		headerRow.createCell(1).setCellValue("Arrival Time");
		headerRow.createCell(2).setCellValue("Arrival Station");
		headerRow.createCell(3).setCellValue("Departure Time");
		headerRow.createCell(4).setCellValue("Departure Station");

		Row dataRow = sh.createRow(1);
		dataRow.createCell(0).setCellValue(trainName.getText());
		dataRow.createCell(1).setCellValue(arrivalTime.getText());
		dataRow.createCell(2).setCellValue(arrivalStation.getText());
		dataRow.createCell(3).setCellValue(departureTime.getText());
		dataRow.createCell(4).setCellValue(departureStation.getText());

		// Write the output to a file
		try {
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Ramya Bhukya\\OneDrive\\Documents\\IRCTCTask.xlsx");
			wb.write(fileOut);

			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the browser
			driver.quit();
		}
	}
}
