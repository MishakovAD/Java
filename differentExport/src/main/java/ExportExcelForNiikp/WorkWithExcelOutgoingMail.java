package ExportExcelForNiikp;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DAO.OutgoingMailDB;
import Entity.OutgoingMail;

public class WorkWithExcelOutgoingMail {
	public static void readFromExcel(String file) throws IOException, InterruptedException {
		XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
		// XSSFSheet myExcelSheet = myExcelBook.getSheet("outgoingMail");
		System.out.println("Start Reading Excel File");
		XSSFSheet myExcelSheet = myExcelBook.getSheet("mail");
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
		//String filePathAndName;


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
//				filePathAndName = "Не заполнено";
//				System.out.println("filePathAndName : " + filePathAndName);
//			}

			String stringRegDate;
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				stringRegDate = formatter.format(regDate);
			} catch (Exception e) {
				stringRegDate = "2000-01-01";
			}
			
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

			outMail = new Entity.OutgoingMail();

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

}
