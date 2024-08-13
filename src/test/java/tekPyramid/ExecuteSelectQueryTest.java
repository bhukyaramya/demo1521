package tekPyramid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExecuteSelectQueryTest {

	public static void main(String[] args) throws Throwable {
		//Connection conn=null;
		
		
		// step 1 : load/register the database driver
				Driver driverRef = new Driver();
				DriverManager.registerDriver(driverRef);

				// step 2 : connect to database
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
				System.out.println("====Done====");

				// step 3 : create sql statement
				Statement stat = conn.createStatement();

				// step 4 : execute select query & get result
				         int result = stat.executeUpdate("insert into student values('103','Ram','CSE');");
				         System.out.println(result);
				 
		
				// step 5 : close the connection
				conn.close();

	}

}
