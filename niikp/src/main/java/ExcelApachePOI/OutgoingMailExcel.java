package ExcelApachePOI;

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

import DAO.GetterDB;
import DAO.OutgoingMailDB;
import OutgoingMail.OutgoingMail;
import OutgoingMail.OutgoingMail;
import Property.Property;

public class OutgoingMailExcel {

	public static void writeIntoExcel(OutgoingMail outMail) throws FileNotFoundException, IOException {
		String filePath = Property.getProperty("fileOutgoingMail");
		// String filePath = "C:/niikp/excel/outgoingMail.xlsx"; //server
		XSSFWorkbook book;
		File f = new File(filePath);
		if (f.exists() && !f.isDirectory()) {
			book = new XSSFWorkbook(new FileInputStream(filePath)); // дозапись в уже существующий
		} else {
			book = new XSSFWorkbook(); // создание нового файла
		}

		Sheet sheet;
		if (book.getSheet("outgoingMail") != null) {
			sheet = book.getSheet("outgoingMail");
			// System.out.println("get");
		} else {
			sheet = book.createSheet("outgoingMail");
			// System.out.println("create");
		}

		// Нумерация начинается с нуля
//		int lastRowNum = sheet.getLastRowNum();
//		Row row = sheet.createRow(lastRowNum + 1);
		
		Row row;
		for (int i=1; ;i++) {
			row = sheet.getRow(i);
			String check = row.getCell(0).getStringCellValue();
			if (check.isEmpty()) {
				break;
			}
		}

		Cell regDate = row.createCell(0);
		Cell mailNum = row.createCell(1);
		Cell destination = row.createCell(2); //adresat
		Cell forWhom = row.createCell(3); //ispolnitel'
		Cell sendDate = row.createCell(4);
		Cell mailTheme = row.createCell(5);
		Cell executor = row.createCell(6); //ispolnitel'
		Cell realExecutor = row.createCell(7); //real ispolnitel'
		Cell incomingMailNum = row.createCell(8);
		Cell toWhomItIsPainted = row.createCell(9);
		Cell incomingMailId = row.createCell(10);
		Cell note = row.createCell(11);
		Cell mailingNote = row.createCell(12); //primechanie rassbIlki
		Cell filePathAndName = row.createCell(13);		


		mailNum.setCellValue(outMail.getMailNum());
		destination.setCellValue(outMail.getDestination()); //adresat
		forWhom.setCellValue(outMail.getForWhom()); //ispolnitel'
		mailTheme.setCellValue(outMail.getMailTheme());
		executor.setCellValue(outMail.getExecutor()); //ispolnitel'
		realExecutor.setCellValue(outMail.getRealExecutor()); //real ispolnitel'
		incomingMailNum.setCellValue(outMail.getIncomingMailNum());
		toWhomItIsPainted.setCellValue(outMail.getToWhomItIsPainted());
		incomingMailId.setCellValue(outMail.getIncomingMailId());
		note.setCellValue(outMail.getNote());
		mailingNote.setCellValue(outMail.getMailingNote()); //primechanie rassbIlki
		filePathAndName.setCellValue(outMail.getFilePathAndName());		

		DataFormat format = book.createDataFormat();
		CellStyle dateStyle = book.createCellStyle();
		dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
		regDate.setCellStyle(dateStyle);
		regDate.setAsActiveCell();
		sendDate.setCellStyle(dateStyle);

		regDate.setCellValue(outMail.getRegDate());
		sendDate.setCellValue(outMail.getSendDate());
		
		outMail = new OutgoingMail();

		// Записываем всё в файл
		book.write(new FileOutputStream(filePath));
		book.close();
	}
	
	public static void writeSearchListIntoExcel(ArrayList<OutgoingMail> searchList) throws FileNotFoundException, IOException {
		String filePath = Property.getProperty("fileOutgoingMailForSearchList");
		// String filePath = "C:/niikp/excel/outgoingMail.xlsx"; //server
		XSSFWorkbook book;
		File f = new File(filePath);
		if (f.exists() && !f.isDirectory()) {
			book = new XSSFWorkbook(new FileInputStream(filePath)); // дозапись в уже существующий
		} else {
			book = new XSSFWorkbook(); // создание нового файла
		}

		Sheet sheet;
		if (book.getSheet("outgoingMail") != null) {
			sheet = book.getSheet("outgoingMail");
			// System.out.println("get");
		} else {
			sheet = book.createSheet("outgoingMail");
			// System.out.println("create");
		}
		
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 3; i <= lastRowNum; i++) {
			Row row = sheet.getRow(i);
			sheet.removeRow(row);
		}

		for (OutgoingMail outMail : searchList) {
		// Нумерация начинается с нуля
		//int lastRowNum = sheet.getLastRowNum();
		lastRowNum = sheet.getLastRowNum();
		Row row = sheet.createRow(lastRowNum + 1);
		
		Cell regDate = row.createCell(0);
		Cell mailNum = row.createCell(1);
		Cell destination = row.createCell(2); //adresat
		Cell forWhom = row.createCell(3); //ispolnitel'
		Cell sendDate = row.createCell(4);
		Cell mailTheme = row.createCell(5);
		Cell executor = row.createCell(6); //ispolnitel'
		Cell realExecutor = row.createCell(7); //real ispolnitel'
		Cell incomingMailNum = row.createCell(8);
		Cell toWhomItIsPainted = row.createCell(9);
		Cell incomingMailId = row.createCell(10);
		Cell note = row.createCell(11);
		Cell mailingNote = row.createCell(12); //primechanie rassbIlki
		Cell filePathAndName = row.createCell(13);		


		mailNum.setCellValue(outMail.getMailNum());
		destination.setCellValue(outMail.getDestination()); //adresat
		forWhom.setCellValue(outMail.getForWhom()); //ispolnitel'
		mailTheme.setCellValue(outMail.getMailTheme());
		executor.setCellValue(outMail.getExecutor()); //ispolnitel'
		realExecutor.setCellValue(outMail.getRealExecutor()); //real ispolnitel'
		incomingMailNum.setCellValue(outMail.getIncomingMailNum());
		toWhomItIsPainted.setCellValue(outMail.getToWhomItIsPainted());
		incomingMailId.setCellValue(outMail.getIncomingMailId());
		note.setCellValue(outMail.getNote());
		mailingNote.setCellValue(outMail.getMailingNote()); //primechanie rassbIlki
		filePathAndName.setCellValue(outMail.getFilePathAndName());		

		DataFormat format = book.createDataFormat();
		CellStyle dateStyle = book.createCellStyle();
		dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
		regDate.setCellStyle(dateStyle);
		regDate.setAsActiveCell();
		sendDate.setCellStyle(dateStyle);

		regDate.setCellValue(outMail.getRegDate());
		sendDate.setCellValue(outMail.getSendDate());
		
		}

		// Записываем всё в файл
		book.write(new FileOutputStream(filePath));
		book.close();
	}
	
	public static void readFromExcel(String file) throws IOException, InterruptedException {
		XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
		// XSSFSheet myExcelSheet = myExcelBook.getSheet("outgoingMail");
		System.out.println("Start Reading Excel File");
		XSSFSheet myExcelSheet = myExcelBook.getSheet("mail");
		int lastRowNum = myExcelSheet.getLastRowNum();
		OutgoingMail outMail = new OutgoingMail();
		ArrayList<OutgoingMail> listMail = new ArrayList<>();

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
			outMail.setRegDate(stringRegDate);
			outMail.setIdMail(idMail);
			outMail.setTypeMail(typeMail);
			outMail.setSender(sender);
			outMail.setSendDate(sendDate);
			outMail.setMailNum(mailNum);
			outMail.setMailTheme(mailTheme);
			outMail.setSecondFloorDate(secondFloorDate);
//			outMail.setSecondFloorNum(secondFloorNum);
			outMail.setFilePathAndName(filePathAndName);
			listMail.add(outMail);

			outMail = new OutgoingMail();

		}
		for (int i = 0; i < listMail.size(); i++) {
			try {
				OutgoingMailDB.addOutgoingMailFromExcel(listMail.get(i));
				Thread.sleep(100);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("End of Job!");
		myExcelBook.close();

	}

	public static void main(String[] args) throws InterruptedException {
		String filename = Property.getProperty("fileForExportOutgoingMail"); //server
		try {
			// writeIntoExcel("1",1,"1","1","1","1","1","1","1");
			readFromExcel(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
