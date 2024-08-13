package tekPyramid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


import com.mysql.jdbc.Driver;

public class CreateProjectAndVerifyDataINDBEWithGUI {

	public static void main(String[] args) throws Throwable {
        
		//create project in GUI using Selenium code
		String projectName = "Snapchat_456";

		WebDriver driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://106.51.90.215:8084/");

		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		driver.findElement(By.linkText("Projects")).click();

		driver.findElement(By.xpath("//span[text()='Create Project']")).click();

		driver.findElement(By.name("projectName")).sendKeys(projectName);
		driver.findElement(By.name("createdBy")).sendKeys("ramyaa");

		Select sel = new Select(driver.findElement(By.name("status")));

		sel.selectByIndex(0);
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		driver.close();
		
		//verify the Project in BackEnd [database]using JDBC
     		boolean flag = false;
		// step 1 : load/register the database driver
				Driver driverRef = new Driver();
				DriverManager .registerDriver(driverRef);

				// step 2 : connect to database
				Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
				System.out.println("====Done====");

				// step 3 : create sql statement
				Statement stat = conn.createStatement();

				// step 4 : execute select query & get result
				ResultSet resultset = stat.executeQuery("select * from project");
				while (resultset.next()) { 
		            String actProjectName = resultset.getString(4);
		            if(projectName.equals(actProjectName)) {
		            	flag = true;
		            System.out.println(projectName+ " is available in Database==PASS");
		            }
				}
				
				if(flag==false) {
					System.out.println(projectName + " is not available in Database==FAIL");
					//if u want fail testcase u have to write assert feature if testNG
					//the testcase is going to fail.
					
				}
				// step 5 : close the connection
				conn.close();

	}

}
