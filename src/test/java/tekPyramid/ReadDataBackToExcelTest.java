package tekPyramid;

import java.io.FileInputStream; 
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBackToExcelTest {

	public static void main(String[] args) throws Throwable {
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Ramya Bhukya\\OneDrive\\Desktop\\testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		Row row=sh.getRow(1);
		Cell cel=row.createCell(4);
		cel.setCellType(CellType.STRING);
		cel.setCellValue("FAIL");
		
		FileOutputStream fos=new FileOutputStream("C:\\Users\\Ramya Bhukya\\OneDrive\\Desktop\\testdata.xlsx");
		wb.write(fos);
		wb.close();
		System.out.println("=====Executed====");
	}

}
