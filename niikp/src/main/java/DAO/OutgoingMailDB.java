package DAO; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

//import ExcelApachePOI.OutgoingMailExcel;
import OutgoingMail.OutgoingMail;
import OutgoingMail.OutgoingMailServlet;
import UserProfile.UserProfile;

public class OutgoingMailDB {
	private static String url = DataBase.url;
	private static String username = DataBase.username;
	private static String password = DataBase.password;

	public static void addOutgoingMail(OutgoingMail outMail)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);

		// String regDate = outMail.getRegDate();
		String typeMail = outMail.getTypeMail();
		String sender = outMail.getSender();
		String sendDate = outMail.getSendDate();
		String mailNum = outMail.getMailNum();
		String mailTheme = outMail.getMailTheme();
		String secondFloorDate = outMail.getSecondFloorDate();
		String secondFloorNum = outMail.getSecondFloorNum();
		String filePathAndName = outMail.getFilePathAndName();
		boolean onControl = outMail.isOnControl();

		Statement statement = null;
		statement = con.createStatement();

		String SQL_insert_outgoingMail = "insert into outgoingMail (regDate, typeMail, sender, sendDate, "
				+ "mailNum, mailTheme, secondFloorDate, secondFloorNum, filePathAndName, onControl)" + " values (now(), '"
				+ typeMail + "', '" + sender + "', '" + sendDate + "', '" + mailNum + "', '" + mailTheme + "', '"
				+ secondFloorDate + "', '" + secondFloorNum + "', '" + filePathAndName + "', " + onControl + ");";

		statement.execute(SQL_insert_outgoingMail);
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
	
	public static void addOutgoingMailFromExcel(OutgoingMail outMail)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);

		String regDate = outMail.getRegDate();
		String typeMail = outMail.getTypeMail();
		String sender = outMail.getSender();
		String sendDate = outMail.getSendDate();
		String mailNum = outMail.getMailNum();
		String mailTheme = outMail.getMailTheme();
		String secondFloorDate = outMail.getSecondFloorDate();
		String secondFloorNum = outMail.getSecondFloorNum();
		String filePathAndName = outMail.getFilePathAndName();

		Statement statement = null;
		statement = con.createStatement();
		

		String SQL_insert_outgoingMail = "insert into outgoingMail (regDate, typeMail, sender, sendDate, "
				+ "mailNum, mailTheme, secondFloorDate, secondFloorNum, filePathAndName)" + " values ('" + regDate + "', '"
				+ typeMail + "', '" + sender + "', '" + sendDate + "', '" + mailNum + "', '" + mailTheme + "', '"
				+ secondFloorDate + "', '" + secondFloorNum + "', '" + filePathAndName + "');";

		statement.execute(SQL_insert_outgoingMail);
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

	public static ArrayList<OutgoingMail> getOutgoingMail()
			throws SQLException, InstantiationException, IllegalAccessException {
		OutgoingMail outMail = new OutgoingMail();
		ArrayList<OutgoingMail> listOutgoingMailFromMethodGet = new ArrayList<>();

		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_outgoingMail = "SELECT * FROM outgoingMail ORDER BY idMail DESC;";

		statement.execute(SQL_get_outgoingMail);
		ResultSet rs = statement.executeQuery(SQL_get_outgoingMail);
		while (rs.next()) {
			outMail.setRegDate(rs.getString("regDate"));
			outMail.setIdMail(rs.getInt("idMail"));
			outMail.setTypeMail(rs.getString("typeMail"));
			outMail.setSender(rs.getString("sender"));
			outMail.setSendDate(rs.getString("sendDate"));
			outMail.setMailNum(rs.getString("mailNum"));
			outMail.setMailTheme(rs.getString("mailTheme"));
			outMail.setSecondFloorDate(rs.getString("secondFloorDate"));
			outMail.setSecondFloorNum(rs.getString("secondFloorNum"));
			outMail.setFilePathAndName(rs.getString("filePathAndName"));
			outMail.setOnControl(rs.getBoolean("onControl"));
			listOutgoingMailFromMethodGet.add(outMail);
			outMail = new OutgoingMail();
		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return listOutgoingMailFromMethodGet;
		// Нужно определить, что лучше:
		/*
		 * возвращать массив, созданный в этом методе или же просто наполнять уже
		 * созданный массив в другом классе
		 */

	}

	public static OutgoingMail getOutgoingMailToId(int id)
			throws SQLException, InstantiationException, IllegalAccessException {
		OutgoingMail outMail = new OutgoingMail();

		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_outgoingMail_to_id = "SELECT * FROM outgoingMail WHERE idMail=" + id + ";";

		ResultSet rs = statement.executeQuery(SQL_get_outgoingMail_to_id);
		while (rs.next()) {
			outMail.setRegDate(rs.getString("regDate"));
			outMail.setIdMail(rs.getInt("idMail"));
			outMail.setTypeMail(rs.getString("typeMail"));
			outMail.setSender(rs.getString("sender"));
			outMail.setSendDate(rs.getString("sendDate"));
			outMail.setMailNum(rs.getString("mailNum"));
			outMail.setMailTheme(rs.getString("mailTheme"));
			outMail.setSecondFloorDate(rs.getString("secondFloorDate"));
			outMail.setSecondFloorNum(rs.getString("secondFloorNum"));
			outMail.setFilePathAndName(rs.getString("filePathAndName"));
			outMail.setOnControl(rs.getBoolean("onControl"));
		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return outMail;

	}

	public static String getFileOutgoingMailToId(int id)
			throws SQLException, InstantiationException, IllegalAccessException {
		String file = null;

		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_outgoingMail_to_id = "SELECT filePathAndName FROM outgoingMail WHERE idMail=" + id + ";";

		ResultSet rs = statement.executeQuery(SQL_get_outgoingMail_to_id);
		while (rs.next()) {
			file = rs.getString("filePathAndName");
		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return file;

	}

	public static int getLastIndexOutgoingMail() throws SQLException, InstantiationException, IllegalAccessException {
		int lastId = 0;

		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_outgoingMail_to_id = "SELECT idMail FROM outgoingMail ORDER BY idMail DESC LIMIT 1;";

		ResultSet rs = statement.executeQuery(SQL_get_outgoingMail_to_id);
		while (rs.next()) {
			lastId = rs.getInt("idMail");
		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return lastId + 1;

	}

	public static void deleteOutgoingMail(int id) throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_delete_outgoingMail_to_id = "DELETE FROM outgoingMail WHERE idMail=" + id + ";";

		statement.executeUpdate(SQL_delete_outgoingMail_to_id);
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
	}

	public static void updateOutgoingMail(OutgoingMail outMail)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String typeMail = outMail.getTypeMail();
		String sender = outMail.getSender();
		String sendDate = outMail.getSendDate();
		String mailNum = outMail.getMailNum();
		String mailTheme = outMail.getMailTheme();
		String secondFloorDate = outMail.getSecondFloorDate();
		String secondFloorNum = outMail.getSecondFloorNum();
		String filePathAndName = outMail.getFilePathAndName();

		String SQL_update_outgoingMail = "UPDATE outgoingMail SET typeMail='" + typeMail + "', sender='" + sender
				+ "', sendDate='" + sendDate + "', mailNum='" + mailNum + "', mailTheme='" + mailTheme + "', secondFloorDate='" + secondFloorDate
				+ "', secondFloorNum='" + secondFloorNum + "' WHERE idMail=" + outMail.getIdMail() + ";";

		statement.executeUpdate(SQL_update_outgoingMail);
		System.out.println(outMail);
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
	
	public static ArrayList<OutgoingMail> getOutgoingMailSortedByRegDate(String sort)
			throws SQLException, InstantiationException, IllegalAccessException {
		OutgoingMail outMail = new OutgoingMail();
		ArrayList<OutgoingMail> listOutgoingMailSirtByRegDate = new ArrayList<>();

		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_outgoingMail = "SELECT * FROM outgoingMail ORDER BY regDate " + sort + ";";

		statement.execute(SQL_get_outgoingMail);
		ResultSet rs = statement.executeQuery(SQL_get_outgoingMail);
		while (rs.next()) {
			outMail.setRegDate(rs.getString("regDate"));
			outMail.setIdMail(rs.getInt("idMail"));
			outMail.setTypeMail(rs.getString("typeMail"));
			outMail.setSender(rs.getString("sender"));
			outMail.setSendDate(rs.getString("sendDate"));
			outMail.setMailNum(rs.getString("mailNum"));
			outMail.setMailTheme(rs.getString("mailTheme"));
			outMail.setSecondFloorDate(rs.getString("secondFloorDate"));
			outMail.setSecondFloorNum(rs.getString("secondFloorNum"));
			outMail.setFilePathAndName(rs.getString("filePathAndName"));
			outMail.setOnControl(rs.getBoolean("onControl"));
			listOutgoingMailSirtByRegDate.add(outMail);
			outMail = new OutgoingMail();
		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return listOutgoingMailSirtByRegDate;
		// Нужно определить, что лучше:
		/*
		 * возвращать массив, созданный в этом методе или же просто наполнять уже
		 * созданный массив в другом классе
		 */

	}

	public static void main(String[] args) {
		for (int i = 1; i < 11; i++) {
			try {
				deleteOutgoingMail(i);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

