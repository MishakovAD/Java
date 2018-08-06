package ExcelApachePOI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class IncomingMailExcel {
	
	@SuppressWarnings("deprecation")
    public static void writeIntoExcel(String file) throws FileNotFoundException, IOException{
		XSSFWorkbook book;
		File f = new File(file);
		if(f.exists() && !f.isDirectory()) { 
			book = new XSSFWorkbook(new FileInputStream(file)); //дозапись в уже существующий
		} else {
			book = new XSSFWorkbook(); //создание нового файла
		}
        
        Sheet sheet;
        if (book.getSheet("incomingMail") != null) {
        	sheet = book.getSheet("incomingMail");
        	System.out.println("get");
        } else {
        	sheet = book.createSheet("incomingMail");
        	System.out.println("create");
        }
        
        
        
        start: 
        	for (int i = 0; ; i++) {
        	// Нумерация начинается с нуля             
            int lastRowNum = sheet.getLastRowNum();
            Row row = sheet.createRow(lastRowNum+1);
            
            Cell name = row.createCell(0);
            name.setCellValue("John");
            
            Cell birthdate = row.createCell(1);
            
            DataFormat format = book.createDataFormat();
            CellStyle dateStyle = book.createCellStyle();
            dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
            birthdate.setCellStyle(dateStyle);
            
     
            // Нумерация лет начинается с 1900-го
            birthdate.setCellValue(new Date(110, 10, 10));
            
            // Меняем размер столбца
            sheet.autoSizeColumn(1);
            book.write(new FileOutputStream(file));
            break start;
        }
        
        
        // Записываем всё в файл
        
        book.close();
    }
	
	public static void readFromExcel(String file) throws IOException{
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet myExcelSheet = myExcelBook.getSheet("Birthdays");
        XSSFRow row = myExcelSheet.getRow(0);
        
        if(row.getCell(0).getCellType() == XSSFCell.CELL_TYPE_STRING){
            String name = row.getCell(0).getStringCellValue();
            System.out.println("name : " + name);
        }
        
        if(row.getCell(1).getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
            Date birthdate = row.getCell(1).getDateCellValue();
            System.out.println("birthdate :" + birthdate);
        }
        
        myExcelBook.close();
        
    }
	public static void main(String[] args) {
		String filename = "E:/JavaProjectDocs/excel/incomingMail.xlsx";
		try {
			writeIntoExcel(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
