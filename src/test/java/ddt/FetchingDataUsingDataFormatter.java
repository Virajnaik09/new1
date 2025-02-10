package ddt;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchingDataUsingDataFormatter {
public static void main(String[] args) throws Throwable {
	FileInputStream fis = new FileInputStream("C:\\Users\\viraj\\Downloads\\Book1.xlsx");
	Workbook book = WorkbookFactory.create(fis);
	Sheet sheet = book.getSheet("sheet2");
	Row row = sheet.getRow(0);
	Cell cell = row.getCell(0);
	DataFormatter formate = new DataFormatter();
	String data = formate.formatCellValue(cell);
	System.out.println(data);
}
}
