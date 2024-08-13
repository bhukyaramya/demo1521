package tekPyramid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class SampleUnitTestCheckProjectInBackEnd {

	@Test
	public void projectCheckTest() throws Throwable {
		
		String expectedStudentName = "sita";
		boolean flag = false;
		// step 1 : load/register the database driver
		Driver driverRef = new Driver();
		DriverManager .registerDriver(driverRef);

		// step 2 : connect to database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
		System.out.println("====Done====");

		// step 3 : create sql statement
		Statement stat = conn.createStatement();

		// step 4 : execute select query & get result
		ResultSet resultset = stat.executeQuery("select * from student");
		while (resultset.next()) { 
            String actStudentName = resultset.getString(2);
            if(expectedStudentName.equals(actStudentName)) {
            	flag = true;
            System.out.println(expectedStudentName + " is available==PASS");
            }
		}
		
		if(flag==false) {
			System.out.println(expectedStudentName + " is not available==FAIL");
			//if u want fail testcase u have to write assert feature if testNG
			//the testcase is going to fail.
			Assert.fail();
		}
		// step 5 : close the connection
		conn.close();

	}
}
