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
			book = new XSSFWorkbook(new FileInputStream(filePath)); // �������� � ��� ������������
		} else {
			book = new XSSFWorkbook(); // �������� ������ �����
		}

		Sheet sheet;
		if (book.getSheet("outgoingMail") != null) {
			sheet = book.getSheet("outgoingMail");
			// System.out.println("get");
		} else {
			sheet = book.createSheet("outgoingMail");
			// System.out.println("create");
		}

		// ��������� ���������� � ����
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

		// ���������� �� � ����
		book.write(new FileOutputStream(filePath));
		book.close();
	}
	
	public static void writeSearchListIntoExcel(ArrayList<OutgoingMail> searchList) throws FileNotFoundException, IOException {
		String filePath = Property.getProperty("fileOutgoingMailForSearchList");
		// String filePath = "C:/niikp/excel/outgoingMail.xlsx"; //server
		XSSFWorkbook book;
		File f = new File(filePath);
		if (f.exists() && !f.isDirectory()) {
			book = new XSSFWorkbook(new FileInputStream(filePath)); // �������� � ��� ������������
		} else {
			book = new XSSFWorkbook(); // �������� ������ �����
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
		// ��������� ���������� � ����
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

		// ���������� �� � ����
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
		String mailNum;
		String destination; //adresat
		String forWhom; //ispolnitel'
		String sendDate;
		String mailTheme;
		String executor; //ispolnitel'
		String realExecutor; //real ispolnitel'
		String incomingMailNum;
		String toWhomItIsPainted;
		int incomingMailId;
		String note;
		String mailingNote; //primechanie rassbIlki
		String filePathAndName;


		for (int i = 4; i <= 872; i++) {
			XSSFRow row = myExcelSheet.getRow(i);
			try {
				if (!row.getCell(0).equals("null")) {
					regDate = row.getCell(0).getDateCellValue();
					System.out.println("regDate : " + regDate);
				} else {
					regDate = null;
					System.out.println("regDate : " + regDate);
				}
			} catch (IllegalStateException e) {
				regDate = null;
			}
			if (row.getCell(1).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				mailNum = row.getCell(1).getStringCellValue();
				System.out.println("mailNum : " + mailNum);
			} else {
				mailNum = "null";
				System.out.println("mailNum : " + mailNum);
			}
			if (row.getCell(2).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				destination = row.getCell(2).getStringCellValue();
				System.out.println("destination : " + destination);
			} else {
				destination = "null";
				System.out.println("destination : " + destination);
			}
			if (row.getCell(3).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				forWhom = row.getCell(3).getStringCellValue();
				System.out.println("forWhom : " + forWhom);
			} else {
				forWhom = "null";
				System.out.println("forWhom : " + forWhom);
			}
			if (row.getCell(4).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				sendDate = row.getCell(4).getStringCellValue();
				System.out.println("sendDate : " + sendDate);
			} else {
				sendDate = "null";
				System.out.println("sendDate : " + sendDate);
			}
			if (row.getCell(5).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				mailTheme = row.getCell(5).getStringCellValue();
				System.out.println("mailTheme : " + mailTheme);
			} else {
				mailTheme = "null";
				System.out.println("mailTheme : " + mailTheme);
			}
			if (row.getCell(6).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				executor = row.getCell(6).getStringCellValue();
				System.out.println("executor : " + executor);
			} else {
				executor = "null";
				System.out.println("executor : " + executor);
			}
			if (row.getCell(7).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				realExecutor = row.getCell(7).getStringCellValue();
				System.out.println("realExecutor : " + realExecutor);
			} else {
				realExecutor = "null";
				System.out.println("realExecutor : " + realExecutor);
			}
			try {
				if (row.getCell(8).getCellType() == XSSFCell.CELL_TYPE_STRING) {
					incomingMailNum = row.getCell(8).getStringCellValue();
					System.out.println("incomingMailNum : " + incomingMailNum);
				} else {
					incomingMailNum = "null";
					System.out.println("incomingMailNum : " + incomingMailNum);
				}
			} catch (NullPointerException e) {
				incomingMailNum = "null";
			}
			try {
				if (row.getCell(9).getCellType() == XSSFCell.CELL_TYPE_STRING) {
					toWhomItIsPainted = row.getCell(9).getStringCellValue();
					System.out.println("toWhomItIsPainted : " + toWhomItIsPainted);
				} else {
					toWhomItIsPainted = "null";
					System.out.println("toWhomItIsPainted : " + toWhomItIsPainted);
				}
			} catch (NullPointerException e) {
				toWhomItIsPainted = "null";
			}
			if (row.getCell(10).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				incomingMailId = (int) row.getCell(10).getNumericCellValue();
				System.out.println("incomingMailId : " + incomingMailId);
			} else {
				incomingMailId = 0;
				System.out.println("incomingMailId : " + incomingMailId);
			}
			if (row.getCell(11).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				note = row.getCell(11).getStringCellValue();
				System.out.println("note : " + note);
			} else {
				note = "null";
				System.out.println("note : " + note);
			}
			if (row.getCell(12).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				mailingNote = row.getCell(12).getStringCellValue();
				System.out.println("mailingNote : " + mailingNote);
			} else {
				mailingNote = "null";
				System.out.println("mailingNote : " + mailingNote);
			}
//			if (!row.getCell(13).getStringCellValue().isEmpty()) {
//				filePathAndName = row.getCell(13).getStringCellValue();
//				System.out.println("filePathAndName : " + filePathAndName);
//			} else {
//				filePathAndName = "�� ���������";
//				System.out.println("filePathAndName : " + filePathAndName);
//			}

			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String stringRegDate = formatter.format(regDate);
			
			outMail.setDestination(destination);
			outMail.setForWhom(forWhom);
			outMail.setExecutor(executor);
			outMail.setRealExecutor(realExecutor);
			outMail.setIncomingMailNum(incomingMailNum);
			outMail.setToWhomItIsPainted(toWhomItIsPainted);
			outMail.setIncomingMailId(incomingMailId);
			outMail.setNote(note);
			outMail.setMailingNote(mailingNote);
			outMail.setRegDate(stringRegDate);
			outMail.setSendDate(sendDate);
			outMail.setMailNum(mailNum);
			outMail.setMailTheme(mailTheme);
			//outMail.setFilePathAndName(filePathAndName);
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
