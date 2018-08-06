package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import IncomingMail.IncomingMail;
import UserProfile.UserProfile;

public class IncomingMailDB {
	private static String url = "jdbc:mysql://localhost:3306/niikp_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String username = "root";
	private static String password = "hedghog";
	public static void addIncomingMail(IncomingMail incMail) throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);			

		String regDate = incMail.getRegDate();
		String typeMail = incMail.getTypeMail();
		String sender = incMail.getSender();
		String sendDate = incMail.getSendDate();
		String mailNum = incMail.getMailNum();
		String mailTheme = incMail.getMailTheme();
		String secondFloorDate = incMail.getSecondFloorDate();
		String filePathAndName = incMail.getFilePathAndName();
		
		Statement statement = null;
		statement = con.createStatement();

		String SQL_insert_incomingMail = "insert into incomingMail (regDate, typeMail, sender, sendDate, "
				+ "mailNum, mailTheme, secondFloorDate, filePathAndName)"
				+ " values (now(), '" + regDate + "', '" + typeMail + "', '" 
				+ sender + "', '" + sendDate + "', '" + mailNum + "', '" 
				+ mailTheme + "', '" + secondFloorDate + "', '" + filePathAndName + "');";
				
		statement.execute(SQL_insert_incomingMail);
		regDate = null;
		typeMail = null;
		sender = null;
		sendDate = null;
		mailNum = null;
		mailTheme = null;
		secondFloorDate = null;
		filePathAndName = null;
	}
}
