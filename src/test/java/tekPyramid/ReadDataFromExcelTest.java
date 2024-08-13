package tekPyramid;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelTest {

	public static void main(String[] args) throws Throwable, IOException {
		
		
		//step1:get the Excel path location and java object of the physical ExcelFile
		FileInputStream fis=new FileInputStream("C:\\Users\\Ramya Bhukya\\OneDrive\\Desktop\\testdata.xlsx");
		
		//step2: Open workbook in read mode
		Workbook wb=WorkbookFactory.create(fis);
		
		//step3: get the control of the "org" sheet
		 Sheet sh=wb.getSheet("sheet1");
		
		//step4: get the control of the "1st" Row
		  Row row=sh.getRow(1);
		
		//step5: get the control of the "2nd" cell and read the String Data
		 // Cell cel=row.getCell(2);
		       //String data=cel.getStringCellValue();
		      // String data=row.getCell(1).getStringCellValue();
		   //String data=row.getCell(3).getStringCellValue();
		   // Double data=row.getCell(3).getNumericCellValue();
		  String data=row.getCell(3).toString();
		       System.out.println(data);
		
		//step6: close the WorkBook
		       wb.close();
		  
		

	}

}
