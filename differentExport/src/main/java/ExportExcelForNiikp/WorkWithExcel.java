package ExportExcelForNiikp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import DAO.IncomingMailDB;
import Property.Property;

public class WorkWithExcel {
	public static void writeIntoExcel(String regDateValue, int idMailValue, String typeMailValue, String senderValue,
			String sendDateValue, String mailNumValue, String mailThemeValue, String secondFloorDateValue,
			String filePathAndNameValue) throws FileNotFoundException, IOException {
		String filePath = Property.getProperty("fileIncomingMail");
		// String filePath = "C:/niikp/excel/incomingMail.xlsx"; //server
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
			// System.out.println("get");
		} else {
			sheet = book.createSheet("incomingMail");
			// System.out.println("create");
		}

		// Нумерация начинается с нуля
		int lastRowNum = sheet.getLastRowNum();
		Row row = sheet.createRow(lastRowNum + 1);

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
		// sheet.autoSizeColumn(1);
//		MergedCellsTable m = new MergedCellsTable();
//		m.addArea(sheet.getFirstRowNum()+1, 0, sheet.getLastRowNum(), 8);
		// EmpNo
		XSSFTable table = new XSSFTable();

		// Записываем всё в файл
		book.write(new FileOutputStream(filePath));
		book.close();
	}

	public static void readFromExcel(String file) throws IOException, InterruptedException {
		XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
		// XSSFSheet myExcelSheet = myExcelBook.getSheet("incomingMail");
		System.out.println("Start Reading Excel File");
		XSSFSheet myExcelSheet = myExcelBook.getSheet("mail");
		int lastRowNum = myExcelSheet.getLastRowNum();
		Entity.IncomingMail incMail = new Entity.IncomingMail();
		ArrayList<Entity.IncomingMail> listMail = new ArrayList<>();

		Date regDate;
		int idMail; // regNum
		String typeMail;
		String sender;
		String sendDate;
		String mailNum;
		String mailTheme;
		String secondFloorDate;
		String secondFloorNum;
		String filePathAndName;

		for (int i = 4; i <= 1148; i++) {
			XSSFRow row = myExcelSheet.getRow(i);
			if (!row.getCell(0).equals("null")) {
				regDate = row.getCell(0).getDateCellValue();
				System.out.println("regDate : " + regDate);
			} else {
				regDate = null;
				System.out.println("regDate : " + regDate);
			}
			if (row.getCell(1).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				idMail = (int) row.getCell(1).getNumericCellValue();
				System.out.println("idMail : " + idMail);
			} else {
				idMail = 0;
				System.out.println("idMail : " + idMail);
			}
			if (row.getCell(2).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				typeMail = row.getCell(2).getStringCellValue();
				System.out.println("typeMail : " + typeMail);
			} else {
				typeMail = "null";
				System.out.println("typeMail : " + typeMail);
			}
			if (row.getCell(3).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				sender = row.getCell(3).getStringCellValue();
				System.out.println("sender : " + sender);
			} else {
				sender = "null";
				System.out.println("sender : " + sender);
			}
			if (row.getCell(4).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				sendDate = row.getCell(4).getStringCellValue();
				System.out.println("sendDate : " + sendDate);
			} else {
				sendDate = "null";
				System.out.println("sendDate : " + sendDate);
			}
			if (row.getCell(5).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				mailNum = row.getCell(5).getStringCellValue();
				System.out.println("mailNum : " + mailNum);
			} else {
				mailNum = "null";
				System.out.println("mailNum : " + mailNum);
			}
			if (row.getCell(6).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				mailTheme = row.getCell(6).getStringCellValue();
				System.out.println("mailTheme : " + mailTheme);
			} else {
				mailTheme = "null";
				System.out.println("mailTheme : " + mailTheme);
			}
			if (row.getCell(7).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				secondFloorDate = row.getCell(7).getStringCellValue();
				System.out.println("secondFloorDate : " + secondFloorDate);
			} else {
				secondFloorDate = "null";
				System.out.println("secondFloorDate : " + secondFloorDate);
			}
			if (!row.getCell(8).getStringCellValue().isEmpty()) {
				filePathAndName = row.getCell(8).getStringCellValue();
				System.out.println("filePathAndName : " + filePathAndName);
			} else {
				filePathAndName = "Не заполнено";
				System.out.println("filePathAndName : " + filePathAndName);
			}

			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String stringRegDate = formatter.format(regDate);
//			
			incMail.setRegDate(stringRegDate);
			incMail.setIdMail(idMail);
			incMail.setTypeMail(typeMail);
			incMail.setSender(sender);
			incMail.setSendDate(sendDate);
			incMail.setMailNum(mailNum);
			incMail.setMailTheme(mailTheme);
			incMail.setSecondFloorDate(secondFloorDate);
//			incMail.setSecondFloorNum(secondFloorNum);
			incMail.setFilePathAndName(filePathAndName);
			listMail.add(incMail);

			incMail = new Entity.IncomingMail();

		}
		for (int i = 0; i < listMail.size(); i++) {
			try {
				IncomingMailDB.addIncomingMailFromExcel(listMail.get(i));
				Thread.sleep(100);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("End of Job!");
		myExcelBook.close();

	}

}
