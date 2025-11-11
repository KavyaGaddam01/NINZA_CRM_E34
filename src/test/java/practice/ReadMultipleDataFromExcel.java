package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import genericutilities.ExcelFileUtility;

public class ReadMultipleDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		ExcelFileUtility  eLib=new ExcelFileUtility();
		int rowCount = eLib.getRowCount("Practice");
		
		for(int row=1;row<=rowCount;row++) {
//			String data = sh.getRow(row).getCell(0).getStringCellValue();	
			String data =eLib.readDataFromExcelFile("Practice", row, 0);
			System.out.println(data);
		}
	
	}

}
