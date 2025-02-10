package ddt;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FectingDataFromExcel {
public static void main(String[] args) throws Throwable {
	// I Am Viraj
	FileInputStream fis = new FileInputStream("C:\\Users\\viraj\\Downloads\\Book1.xlsx");
	//FileInputStream fis = new FileInputStream("./src/test/resources/Book1.xlsx");
	Workbook book = WorkbookFactory.create(fis);
//	Sheet SHEET = book.getSheet("sheet1");
//	Row ROW = SHEET.getRow(0);
//	Cell CELL = ROW.getCell(0);
//	String DATA = CELL.getStringCellValue();
//	System.out.println(DATA);
	// VIRAJ NAIK
}
}
