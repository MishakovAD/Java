package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entity.IncomingMail;
import Property.Property;

public class IncomingMailDB {
	public static String url = Property.getProperty("db.url");
	public static String username = Property.getProperty("db.login");
	public static String password = Property.getProperty("db.password");

	public static void addIncomingMail(IncomingMail incMail)
			throws SQLException, InstantiationException, IllegalAccessException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loading success!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// Открываем соединение
		Connection con = DriverManager.getConnection(url, username, password);
		System.out.println("Connected.");
		
		// String regDate = incMail.getRegDate();
		String typeMail = incMail.getTypeMail();
		String sender = incMail.getSender();
		String sendDate = incMail.getSendDate();
		String mailNum = incMail.getMailNum();
		String mailTheme = incMail.getMailTheme();
		String secondFloorDate = incMail.getSecondFloorDate();
		String secondFloorNum = incMail.getSecondFloorNum();
		String filePathAndName = incMail.getFilePathAndName();
		boolean onControl = incMail.isOnControl();

		Statement statement = null;
		statement = con.createStatement();

		String SQL_insert_incomingMail = "insert into incomingMail (regDate, typeMail, sender, sendDate, "
				+ "mailNum, mailTheme, secondFloorDate, secondFloorNum, filePathAndName, onControl)" + " values (now(), '"
				+ typeMail + "', '" + sender + "', '" + sendDate + "', '" + mailNum + "', '" + mailTheme + "', '"
				+ secondFloorDate + "', '" + secondFloorNum + "', '" + filePathAndName + "', " + onControl + ");";


		statement.execute(SQL_insert_incomingMail);
		// regDate = null;
		typeMail = null;
		sender = null;
		sendDate = null;
		mailNum = null;
		mailTheme = null;
		secondFloorDate = null;
		secondFloorNum = null;
		filePathAndName = null;
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();

	}
	
	public static void addIncomingMailFromExcel(IncomingMail incMail)
			throws SQLException, InstantiationException, IllegalAccessException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loading success!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// Открываем соединение
		Connection con = DriverManager.getConnection(url, username, password);
		System.out.println("Connected.");

		String regDate = incMail.getRegDate();
		String typeMail = incMail.getTypeMail();
		String sender = incMail.getSender();
		String sendDate = incMail.getSendDate();
		String mailNum = incMail.getMailNum();
		String mailTheme = incMail.getMailTheme();
		String secondFloorDate = incMail.getSecondFloorDate();
		String secondFloorNum = incMail.getSecondFloorNum();
		String filePathAndName = incMail.getFilePathAndName();

		Statement statement = null;
		statement = con.createStatement();
		

		String SQL_insert_incomingMail = "insert into incomingMail (regDate, typeMail, sender, sendDate, "
				+ "mailNum, mailTheme, secondFloorDate, secondFloorNum, filePathAndName)" + " values ('" + regDate + "', '"
				+ typeMail + "', '" + sender + "', '" + sendDate + "', '" + mailNum + "', '" + mailTheme + "', '"
				+ secondFloorDate + "', '" + secondFloorNum + "', '" + filePathAndName + "');";

		statement.execute(SQL_insert_incomingMail);
		// regDate = null;
		typeMail = null;
		sender = null;
		sendDate = null;
		mailNum = null;
		mailTheme = null;
		secondFloorDate = null;
		secondFloorNum = null;
		filePathAndName = null;
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();

	}




	public static void deleteIncomingMail(int id) throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_delete_incomingMail_to_id = "DELETE FROM incomingMail WHERE idMail=" + id + ";";

		statement.executeUpdate(SQL_delete_incomingMail_to_id);
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
	}

	public static void updateIncomingMail(IncomingMail incMail)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String typeMail = incMail.getTypeMail();
		String sender = incMail.getSender();
		String sendDate = incMail.getSendDate();
		String mailNum = incMail.getMailNum();
		String mailTheme = incMail.getMailTheme();
		String secondFloorDate = incMail.getSecondFloorDate();
		String secondFloorNum = incMail.getSecondFloorNum();
		String filePathAndName = incMail.getFilePathAndName();

		String SQL_update_incomingMail = "UPDATE incomingMail SET typeMail='" + typeMail + "', sender='" + sender
				+ "', sendDate='" + sendDate + "', mailNum='" + mailNum + "', mailTheme='" + mailTheme + "', secondFloorDate='" + secondFloorDate
				+ "', secondFloorNum='" + secondFloorNum + "' WHERE idMail=" + incMail.getIdMail() + ";";

		statement.executeUpdate(SQL_update_incomingMail);
		System.out.println(incMail);
		System.out.println("UPDATE!");
		typeMail = null;
		sender = null;
		sendDate = null;
		mailNum = null;
		mailTheme = null;
		secondFloorDate = null;
		secondFloorNum = null;
		filePathAndName = null;
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();

	}
	


	public static void main(String[] args) {
		for (int i = 1; i < 11; i++) {
			try {
				deleteIncomingMail(i);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

