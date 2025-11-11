package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataBackToExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		
		FileInputStream fis=new FileInputStream("C:\\Users\\QSP\\Documents\\NINZA_CRM_E34.xlsx");

		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh = wb.getSheet("Practice");
		
		Row r = sh.getRow(1);
		Cell c = r.createCell(1, CellType.STRING);
		c.setCellValue("IPHONE 15");
		
		FileOutputStream fos=new FileOutputStream("C:\\Users\\QSP\\Documents\\NINZA_CRM_E34.xlsx");
		
		wb.write(fos);
		
		wb.close();
		
	}

}
