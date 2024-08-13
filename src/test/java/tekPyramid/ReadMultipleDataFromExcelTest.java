package tekPyramid;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDataFromExcelTest {

	public static void main(String[] args) throws Throwable, IOException {

		FileInputStream fis = new FileInputStream("C:\\Users\\Ramya Bhukya\\OneDrive\\Desktop\\testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("sheet2");
		
		int rowCount=sh.getLastRowNum();

		// Row row = sh.getRow(1);

		for (int i = 1; i <rowCount; i++) { 

			Row row = sh.getRow(i);
			String column1Data = row.getCell(0).toString();
			String column2Data = row.getCell(1).toString();

			// System.out.println(column1Data);
			// System.out.println(column2Data);
			System.out.println(column1Data + "/t" + column2Data);
		}
		wb.close();
	}

}
