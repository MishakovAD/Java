package ExcelApachePOI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class IncomingMailExcel {

	public static void writeIntoExcel(String regDateValue, int idMailValue, String typeMailValue, 
			String senderValue, String sendDateValue, String mailNumValue, String mailThemeValue, 
			String secondFloorDateValue, String filePathAndNameValue) throws FileNotFoundException, IOException {
		String filePath = "E:/JavaProjectDocs/excel/incomingMail.xlsx";
		XSSFWorkbook book;
		File f = new File(filePath);
		if (f.exists() && !f.isDirectory()) {
			book = new XSSFWorkbook(new FileInputStream(filePath)); // дозапись в уже существующий
		} else {
			book = new XSSFWorkbook(); // создание нового файла
		}

		Sheet sheet;
		if (book.getSheet("incomingMail") != null) {
			sheet = book.getSheet("incomingMail");
			System.out.println("get");
		} else {
			sheet = book.createSheet("incomingMail");
			System.out.println("create");
		}

		// Нумерация начинается с нуля
		int lastRowNum = sheet.getLastRowNum();
		Row row = sheet.createRow(lastRowNum+1);
		
		Cell regDate = row.createCell(0);
		Cell idMail = row.createCell(1);
		Cell typeMail = row.createCell(2);
		Cell sender = row.createCell(3);
		Cell sendDate = row.createCell(4);
		Cell mailNum = row.createCell(5);
		Cell mailTheme = row.createCell(6);
		Cell secondFloorDate = row.createCell(7);
		Cell filePathAndName = row.createCell(8);
		
		
		idMail.setCellValue(idMailValue);
		typeMail.setCellValue(typeMailValue);
		sender.setCellValue(senderValue);		
		mailNum.setCellValue(mailNumValue);
		mailTheme.setCellValue(mailThemeValue);		
		filePathAndName.setCellValue(filePathAndNameValue);

		DataFormat format = book.createDataFormat();
		CellStyle dateStyle = book.createCellStyle();
		dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
		regDate.setCellStyle(dateStyle);
		regDate.setAsActiveCell();
		sendDate.setCellStyle(dateStyle);
		secondFloorDate.setCellStyle(dateStyle);
		
		regDate.setCellValue(regDateValue);
		sendDate.setCellValue(sendDateValue);
		secondFloorDate.setCellValue(secondFloorDateValue);

		// Меняем размер столбца
		//sheet.autoSizeColumn(1);
//		MergedCellsTable m = new MergedCellsTable();
//		m.addArea(sheet.getFirstRowNum()+1, 0, sheet.getLastRowNum(), 8);
		 // EmpNo
		XSSFTable table = new XSSFTable();

		// Записываем всё в файл
		book.write(new FileOutputStream(filePath));
		book.close();
	}

	public static void readFromExcel(String file) throws IOException {
		XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
		XSSFSheet myExcelSheet = myExcelBook.getSheet("Birthdays");
		XSSFRow row = myExcelSheet.getRow(0);

		if (row.getCell(0).getCellType() == XSSFCell.CELL_TYPE_STRING) {
			String name = row.getCell(0).getStringCellValue();
			System.out.println("name : " + name);
		}

		if (row.getCell(1).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			Date birthdate = row.getCell(1).getDateCellValue();
			System.out.println("birthdate :" + birthdate);
		}

		myExcelBook.close();

	}

	public static void main(String[] args) {
		String filename = "E:/JavaProjectDocs/excel/incomingMail.xlsx";
		try {
			writeIntoExcel("1",1,"1","1","1","1","1","1","1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
