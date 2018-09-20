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
import DAO.IncomingMailDB;
import DAO.WorkDB;
import IncomingMail.IncomingMail;
import Property.Property;
import Work.Work;

public class IncomingMailExcel {

	public static void writeIntoExcel(String regDateValue, int idMailValue, String typeMailValue, String senderValue,
			String sendDateValue, String mailNumValue, String mailThemeValue, String secondFloorDateValue, String secondFloorNumValue,
			String filePathAndNameValue, boolean onControlValue) throws FileNotFoundException, IOException {
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
		Cell secondFloorNum = row.createCell(8);
		Cell filePathAndName = row.createCell(9);
		Cell onControl = row.createCell(10);


		idMail.setCellValue(idMailValue);
		typeMail.setCellValue(typeMailValue);
		sender.setCellValue(senderValue);
		mailNum.setCellValue(mailNumValue);
		mailTheme.setCellValue(mailThemeValue);
		secondFloorNum.setCellValue(secondFloorNumValue);
		filePathAndName.setCellValue(filePathAndNameValue);
		onControl.setCellValue(onControlValue);


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
	
	public static void writeSearchListIntoExcel(ArrayList<IncomingMail> searchList) throws FileNotFoundException, IOException {
		String filePath = Property.getProperty("fileIncomingMailForSearchList");
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
		
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 1; i <= lastRowNum; i++) {
			Row row = sheet.getRow(i);
			sheet.removeRow(row);
		}

		for (IncomingMail incMail : searchList) {
		// Нумерация начинается с нуля
		//int lastRowNum = sheet.getLastRowNum();
		lastRowNum = sheet.getLastRowNum();
		Row row = sheet.createRow(lastRowNum + 1);

		Cell regDate = row.createCell(0);
		Cell idMail = row.createCell(1);
		Cell typeMail = row.createCell(2);
		Cell sender = row.createCell(3);
		Cell sendDate = row.createCell(4);
		Cell mailNum = row.createCell(5);
		Cell mailTheme = row.createCell(6);
		Cell secondFloorDate = row.createCell(7);
		Cell secondFloorNum = row.createCell(8);
		Cell filePathAndName = row.createCell(9);
		Cell onControl = row.createCell(10);
		Cell resolution = row.createCell(12);


		idMail.setCellValue(incMail.getIdMail());
		typeMail.setCellValue(incMail.getTypeMail());
		sender.setCellValue(incMail.getSender());
		mailNum.setCellValue(incMail.getMailNum());
		mailTheme.setCellValue(incMail.getMailTheme());
		secondFloorNum.setCellValue( incMail.getSecondFloorNum());
		filePathAndName.setCellValue(incMail.getFilePathAndName());
		onControl.setCellValue(incMail.isOnControl());


		DataFormat format = book.createDataFormat();
		CellStyle dateStyle = book.createCellStyle();
		dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
		regDate.setCellStyle(dateStyle);
		regDate.setAsActiveCell();
		sendDate.setCellStyle(dateStyle);
		secondFloorDate.setCellStyle(dateStyle);

		regDate.setCellValue(incMail.getRegDate());
		sendDate.setCellValue(incMail.getSendDate());
		secondFloorDate.setCellValue(incMail.getSecondFloorDate());
		
		//Добавляем резолюцию в экспорт экселя, если она есть
		int idFromMail = incMail.getIdMail();
		String prefix = "incomingMail_";
		String idMailForAdd = prefix + idFromMail;
		ArrayList<Work> resolutionForMail = new ArrayList<Work>();
		
		try {
			resolutionForMail = WorkDB.getWorkToMailId(idMailForAdd);						
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}
	
		if (resolutionForMail.size() > 0) {
			String workSet = "";
			for (Work work : resolutionForMail) {
				String user = "Кому: " + work.getUserNameFromId(work.getToUserId()) + System.getProperty("line.separator");
				String assignment = "Поручение: " + work.getAssignment() + System.getProperty("line.separator");
				String date = "Сроки: " + work.getStartDate().substring(0, 10) + " - " + work.getEndDate().substring(0, 10) + System.getProperty("line.separator");
				String report = "Отчет: " + work.getReport() + System.getProperty("line.separator");
				workSet += user + assignment + date + report;
			}
			CellStyle cs = book.createCellStyle();
		    cs.setWrapText(true);
		    resolution.setCellStyle(cs);
			resolution.setCellValue(workSet);
			
		}
		
		resolutionForMail = new ArrayList<Work>();
		idMail = null;
		
		}

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
	
	public static void writeResolutionIntoExcel(int mailId, String resolutionValue, String startDateValue, String endDateValue, ArrayList<Integer> toUserIdValue, int workIdValue) throws FileNotFoundException, IOException {
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
		Row row = sheet.getRow(9999);
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 1; i <= lastRowNum; i++) {
			row = sheet.getRow(i);
			if (row.getCell(1).getNumericCellValue() == mailId) {
				break;
			}
		}



		Cell resolution = row.createCell(12);
		Cell startDate = row.createCell(14);
		Cell endDate = row.createCell(15);
		Cell toUserId = row.createCell(17);
		Cell workId = row.createCell(19);

		resolution.setCellValue(resolutionValue);
		startDate.setCellValue(startDateValue);
		endDate.setCellValue(endDateValue);
		workId.setCellValue(workIdValue);
		
		StringBuilder usersToWork = new StringBuilder();
		for (Integer usersId : toUserIdValue) {
			try {
				usersToWork.append(GetterDB.getUserFromId(usersId).getName() + " " + GetterDB.getUserFromId(usersId).getSecondName() + "; ");
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
		}
		toUserId.setCellValue(usersToWork.toString());

		// Записываем всё в файл
		book.write(new FileOutputStream(filePath));
		book.close();
	}
	
	public static void writeResolutionResultIntoExcel(int mailId, String resolutionResultValue) throws FileNotFoundException, IOException {
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
		Row row = sheet.getRow(9999);
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 0; i < lastRowNum; i++) {
			row = sheet.getRow(i);
			if (row.getCell(1).getStringCellValue().equals(String.valueOf(mailId))) {
				break;
			}
		}


		Cell resolutionResult = row.createCell(16);


//		StringBuilder usersToWork = new StringBuilder();
//		for (Integer usersId : toUserIdValue) {
//			usersToWork.append(usersId + ";");
//		}
//		toUserId.setCellValue(usersToWork.toString());
		resolutionResult.setCellValue(resolutionResultValue);


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
		IncomingMail incMail = new IncomingMail();
		ArrayList<IncomingMail> listMail = new ArrayList<>();

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

			incMail = new IncomingMail();

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

	public static void main(String[] args) throws InterruptedException {
		String filename = Property.getProperty("fileForExportIncomingMail"); //server
		try {
			// writeIntoExcel("1",1,"1","1","1","1","1","1","1");
			readFromExcel(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
