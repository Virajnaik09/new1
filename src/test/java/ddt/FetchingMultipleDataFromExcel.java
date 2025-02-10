package ddt;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchingMultipleDataFromExcel {
public static void main(String[] args) throws Throwable 
{
	FileInputStream fis = new FileInputStream("C:\\Users\\viraj\\Downloads\\Book1.xlsx");
	Workbook book = WorkbookFactory.create(fis);
	int row = 0 ;
	int num = 0;
	Sheet sheet = book.getSheet("sheet1");
    int rowNum = sheet.getLastRowNum();	
    //System.out.println(rowNum);
    for(int i=0 ; i <=rowNum ; i++)
    {
    	Cell allLinks = sheet.getRow(row++).getCell(0);
    	System.out.println(allLinks);
    	System.out.println(num++);
    }
	}
	
}

