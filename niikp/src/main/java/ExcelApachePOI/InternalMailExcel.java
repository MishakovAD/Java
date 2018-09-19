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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DAO.InternalMailDB;
import InternalMail.InternalMail;
import Property.Property;

public class InternalMailExcel {
	public static void writeIntoExcel(InternalMail internalMail) throws FileNotFoundException, IOException {
		String filePath = Property.getProperty("fileInternalMail");
		XSSFWorkbook book;
		File f = new File(filePath);
		if (f.exists() && !f.isDirectory()) {
			book = new XSSFWorkbook(new FileInputStream(filePath)); // дозапись в уже существующий
		} else {
			book = new XSSFWorkbook(); // создание нового файла
		}

		Sheet sheet;
		if (book.getSheet("internalMail") != null) {
			sheet = book.getSheet("internalMail");
			// System.out.println("get");
		} else {
			sheet = book.createSheet("internalMail");
			// System.out.println("create");
		}

		Row row;
		for (int i = 2;; i++) {
			row = sheet.getRow(i);
			String check = row.getCell(0).getStringCellValue();
			if (check.isEmpty()) {
				break;
			}
		}

		Cell regDate = row.createCell(0);
		Cell docType = row.createCell(1);
		Cell numNPK = row.createCell(2);
		Cell destination = row.createCell(3); // ispolnitel'
		Cell additionalDestination = row.createCell(4);
		Cell docTheme = row.createCell(5);
		Cell executor = row.createCell(6);
		Cell note = row.createCell(7);

		docType.setCellValue(internalMail.getDocType());
		numNPK.setCellValue(internalMail.getNumNPK());
		destination.setCellValue(internalMail.getDestination());
		additionalDestination.setCellValue(internalMail.getAdditionalDestination());
		docTheme.setCellValue(internalMail.getDocTheme());
		executor.setCellValue(internalMail.getExecutor());
		note.setCellValue(internalMail.getNote());

		DataFormat format = book.createDataFormat();
		CellStyle dateStyle = book.createCellStyle();
		dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
		regDate.setCellStyle(dateStyle);
		regDate.setAsActiveCell();

		regDate.setCellValue(internalMail.getRegDate());

		internalMail = new InternalMail();

		// Записываем всё в файл
		book.write(new FileOutputStream(filePath));
		book.close();
	}

	public static void writeSearchListIntoExcel(ArrayList<InternalMail> searchList)
			throws FileNotFoundException, IOException {
		String filePath = Property.getProperty("fileInternalMailForSearchList");
		// String filePath = "C:/niikp/excel/internalMail.xlsx"; //server
		XSSFWorkbook book;
		File f = new File(filePath);
		if (f.exists() && !f.isDirectory()) {
			book = new XSSFWorkbook(new FileInputStream(filePath)); // дозапись в уже существующий
		} else {
			book = new XSSFWorkbook(); // создание нового файла
		}

		Sheet sheet;
		if (book.getSheet("internalMail") != null) {
			sheet = book.getSheet("internalMail");
			// System.out.println("get");
		} else {
			sheet = book.createSheet("internalMail");
			// System.out.println("create");
		}

		int lastRowNum = sheet.getLastRowNum();
		for (int i = 3; i <= lastRowNum; i++) {
			Row row = sheet.getRow(i);
			sheet.removeRow(row);
		}

		for (InternalMail internalMail : searchList) {
			// Нумерация начинается с нуля
			// int lastRowNum = sheet.getLastRowNum();
			lastRowNum = sheet.getLastRowNum();
			Row row = sheet.createRow(lastRowNum + 1);

			Cell regDate = row.createCell(0);
			Cell docType = row.createCell(1);
			Cell numNPK = row.createCell(2);
			Cell destination = row.createCell(3); // ispolnitel'
			Cell additionalDestination = row.createCell(4);
			Cell docTheme = row.createCell(5);
			Cell executor = row.createCell(6);
			Cell note = row.createCell(7);

			docType.setCellValue(internalMail.getDocType());
			numNPK.setCellValue(internalMail.getNumNPK());
			destination.setCellValue(internalMail.getDestination());
			additionalDestination.setCellValue(internalMail.getAdditionalDestination());
			docTheme.setCellValue(internalMail.getDocTheme());
			executor.setCellValue(internalMail.getExecutor());
			note.setCellValue(internalMail.getNote());

			DataFormat format = book.createDataFormat();
			CellStyle dateStyle = book.createCellStyle();
			dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
			regDate.setCellStyle(dateStyle);
			regDate.setAsActiveCell();

			regDate.setCellValue(internalMail.getRegDate());

			internalMail = new InternalMail();

		}

		// Записываем всё в файл
		book.write(new FileOutputStream(filePath));
		book.close();
	}

	public static void readFromExcel(String file) throws IOException, InterruptedException {
		XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
		// XSSFSheet myExcelSheet = myExcelBook.getSheet("internalMail");
		System.out.println("Start Reading Excel File");
		XSSFSheet myExcelSheet = myExcelBook.getSheet("mail");
		int lastRowNum = myExcelSheet.getLastRowNum();
		InternalMail internalMail = new InternalMail();
		ArrayList<InternalMail> listMail = new ArrayList<>();

		String docType;
		Date regDate;
		String numNPK;
		String destination;
		String additionalDestination;
		String docTheme;
		String executor;
		String note;
		
		String stringRegDate;

		for (int i = 4; i <= 1037; i++) {
			XSSFRow row = myExcelSheet.getRow(i);
			if ( i != 301 && row.getCell(2).getCellType() != XSSFCell.CELL_TYPE_STRING) {
				if (row.getCell(0).getCellType() == XSSFCell.CELL_TYPE_STRING) {
					docType = row.getCell(0).getStringCellValue();
					System.out.println("docType : " + docType);
				} else {
					docType = "null";
					System.out.println("docType : " + docType);
				}
				try {
					if (!row.getCell(1).equals("null")) {
						regDate = row.getCell(1).getDateCellValue();
						System.out.println("regDate : " + regDate);
					} else {
						regDate = null;
						System.out.println("regDate : " + regDate);
					}
				} catch (IllegalStateException e) {
					regDate = null;
				}
				
				if (row.getCell(2).equals("298-1")) {
					numNPK = "298-1";
				} else if (!row.getCell(2).equals("null") && !row.getCell(2).equals("резерв")) {
					numNPK = String.valueOf(row.getCell(2).getNumericCellValue());
					System.out.println("numNPK : " + numNPK);
				} else {
					numNPK = "null";
					System.out.println("numNPK : " + numNPK);
				}
				
				if (row.getCell(3).getCellType() == XSSFCell.CELL_TYPE_STRING) {
					destination = row.getCell(3).getStringCellValue();
					System.out.println("destination : " + destination);
				} else {
					destination = "null";
					System.out.println("destination : " + destination);
				}
				
				if (!row.getCell(4).equals("null") && row.getCell(4).getCellType() == XSSFCell.CELL_TYPE_STRING) {
					additionalDestination = row.getCell(4).getStringCellValue();
					System.out.println("additionalDestination : " + additionalDestination);
				} else {
					additionalDestination = "null";
					System.out.println("additionalDestination : " + additionalDestination);
				}
				
				if (row.getCell(5).getCellType() == XSSFCell.CELL_TYPE_STRING) {
					docTheme = row.getCell(5).getStringCellValue();
					System.out.println("docTheme : " + docTheme);
				} else {
					docTheme = "null";
					System.out.println("docTheme : " + docTheme);
				}

				if (row.getCell(6).getCellType() == XSSFCell.CELL_TYPE_STRING) {
					executor = row.getCell(6).getStringCellValue();
					System.out.println("executor : " + executor);
				} else {
					executor = "null";
					System.out.println("executor : " + executor);
				}
				if (row.getCell(7).getCellType() == XSSFCell.CELL_TYPE_STRING) {
					note = row.getCell(7).getStringCellValue();
					System.out.println("note : " + note);
				} else {
					note = "null";
					System.out.println("note : " + note);
				}
				
				
				Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					stringRegDate = formatter.format(regDate);
				} catch (IllegalArgumentException e) {
					stringRegDate = "2000-01-01";
				}
				
				internalMail.setDocType(docType);
				internalMail.setRegDate(stringRegDate);
				internalMail.setNumNPK(numNPK);
				internalMail.setDestination(destination);
				internalMail.setAdditionalDestination(additionalDestination);
				internalMail.setDocTheme(docTheme);
				internalMail.setExecutor(executor);
				internalMail.setNote(note);
			}
			
			
			
			
			listMail.add(internalMail);

			internalMail = new InternalMail();

		}
		for (int i = 0; i < listMail.size(); i++) {
			try {
				InternalMailDB.addInternalMailFromExcel(listMail.get(i));
				Thread.sleep(100);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("End of Job!");
		myExcelBook.close();

	}

	public static void main(String[] args) throws InterruptedException {
		String filename = Property.getProperty("fileForExportInternalMail"); // server
		try {
			// writeIntoExcel("1",1,"1","1","1","1","1","1","1");
			readFromExcel(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
