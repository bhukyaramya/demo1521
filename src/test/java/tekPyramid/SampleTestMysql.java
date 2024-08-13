package tekPyramid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class SampleTestMysql {

	public static void main(String[] args) throws Throwable {
		Connection conn = null;

		try {
			// step 1 : load/register the database driver
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);

			// step 2 : connect to database
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
			System.out.println("====Done====");

			// step 3 : create sql statement
			Statement stat = conn.createStatement();

			// step 4 : execute select query & get result
			ResultSet resultset = stat.executeQuery("select * from student");
			while (resultset.next()) {
				System.out.println(
						resultset.getString(1) + "\t" + resultset.getString(2) + "\t" + resultset.getString(3));
			}

		} catch (Exception e) {
			System.out.println("handle exception");
		} finally {
			// step 5 : close the connection
			conn.close();
			System.out.println("=====Close the Connection====");
		}

	}

}
