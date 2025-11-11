package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		//Create a java representation Object of physical file
		FileInputStream fis=new FileInputStream("C:\\Users\\QSP\\Documents\\NINZA_CRM_E34.xlsx");
		
		//Open the excel in read mode
		Workbook wb = WorkbookFactory.create(fis);
		
		//Get the control of sheet
		Sheet sh = wb.getSheet("Campaigns");
		
		//get the control of the row
		Row r = sh.getRow(1);
		
		//get the control of the cell
		Cell c = r.getCell(2);
		
		//read the data
		String campaignName = c.getStringCellValue();
		System.out.println(campaignName);
		
		String targetSize = wb.getSheet("Campaigns").
				                           getRow(1).getCell(3).getStringCellValue();
		System.out.println(targetSize);
		
		//Close the workbook
		wb.close();
		
		
	}

}
