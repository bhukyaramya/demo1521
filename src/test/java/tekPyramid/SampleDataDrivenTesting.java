package tekPyramid;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SampleDataDrivenTesting {

	public static void main(String[] args) throws IOException {
		// step1: Get the java representation object of the physical file using
		// FileInputStream
		FileInputStream fis = new FileInputStream("C:\\Users\\Ramya Bhukya\\OneDrive\\Desktop\\commondata1.properties");

		// step2:Create a Object of properties file and load all the keys.
		Properties pObj = new Properties();
		pObj.load(fis);

		// step3:get the value based on key
		System.out.println(pObj.getProperty("browser"));

	}

}
