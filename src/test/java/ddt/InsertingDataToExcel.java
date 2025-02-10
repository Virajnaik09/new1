package ddt;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class InsertingDataToExcel {
public static void main(String[] args) throws Throwable {
	FileInputStream fis = new FileInputStream("C:\\Users\\viraj\\Downloads\\Book1.xlsx");
	Workbook book = WorkbookFactory.create(fis);
	Sheet sheet = book.getSheet("sheet1");
	Row row = sheet.createRow(8);
	Cell cell = row.createCell(8);
	cell.setCellValue("BOSS");
	FileOutputStream fos = new FileOutputStream("C:\\Users\\viraj\\Downloads\\Book1.xlsx");
	book.write(fos);
	book.close();
	
	
}
}
