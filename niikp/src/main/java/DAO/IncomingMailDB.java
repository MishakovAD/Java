package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ExcelApachePOI.IncomingMailExcel;
import IncomingMail.IncomingMail;
import IncomingMail.IncomingMailServlet;
import UserProfile.UserProfile;

public class IncomingMailDB {
	private static String url = DataBase.url;
	private static String username = DataBase.username;
	private static String password = DataBase.password;

	public static void addIncomingMail(IncomingMail incMail)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);

		// String regDate = incMail.getRegDate();
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
				+ "mailNum, mailTheme, secondFloorDate, secondFloorNum, filePathAndName)" + " values (now(), '"
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

	public static ArrayList<IncomingMail> getIncomingMail()
			throws SQLException, InstantiationException, IllegalAccessException {
		IncomingMail incMail = new IncomingMail();
		ArrayList<IncomingMail> listIncomingMailFromMethodGet = new ArrayList<>();

		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_incomingMail = "SELECT * FROM incomingMail ORDER BY idMail DESC;";

		statement.execute(SQL_get_incomingMail);
		ResultSet rs = statement.executeQuery(SQL_get_incomingMail);
		while (rs.next()) {
			incMail.setRegDate(rs.getString("regDate"));
			incMail.setIdMail(rs.getInt("idMail"));
			incMail.setTypeMail(rs.getString("typeMail"));
			incMail.setSender(rs.getString("sender"));
			incMail.setSendDate(rs.getString("sendDate"));
			incMail.setMailNum(rs.getString("mailNum"));
			incMail.setMailTheme(rs.getString("mailTheme"));
			incMail.setSecondFloorDate(rs.getString("secondFloorDate"));
			incMail.setSecondFloorNum(rs.getString("secondFloorNum"));
			incMail.setFilePathAndName(rs.getString("filePathAndName"));
			listIncomingMailFromMethodGet.add(incMail);
			incMail = new IncomingMail();
		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return listIncomingMailFromMethodGet;
		// Нужно определить, что лучше:
		/*
		 * возвращать массив, созданный в этом методе или же просто наполнять уже
		 * созданный массив в другом классе
		 */

	}

	public static IncomingMail getIncomingMailToId(int id)
			throws SQLException, InstantiationException, IllegalAccessException {
		IncomingMail incMail = new IncomingMail();

		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_incomingMail_to_id = "SELECT * FROM incomingMail WHERE idMail=" + id + ";";

		ResultSet rs = statement.executeQuery(SQL_get_incomingMail_to_id);
		while (rs.next()) {
			incMail.setRegDate(rs.getString("regDate"));
			incMail.setIdMail(rs.getInt("idMail"));
			incMail.setTypeMail(rs.getString("typeMail"));
			incMail.setSender(rs.getString("sender"));
			incMail.setSendDate(rs.getString("sendDate"));
			incMail.setMailNum(rs.getString("mailNum"));
			incMail.setMailTheme(rs.getString("mailTheme"));
			incMail.setSecondFloorDate(rs.getString("secondFloorDate"));
			incMail.setSecondFloorNum(rs.getString("secondFloorNum"));
			incMail.setFilePathAndName(rs.getString("filePathAndName"));
		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return incMail;

	}

	public static String getFileIncomingMailToId(int id)
			throws SQLException, InstantiationException, IllegalAccessException {
		String file = null;

		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_incomingMail_to_id = "SELECT filePathAndName FROM incomingMail WHERE idMail=" + id + ";";

		ResultSet rs = statement.executeQuery(SQL_get_incomingMail_to_id);
		while (rs.next()) {
			file = rs.getString("filePathAndName");
		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return file;

	}

	public static int getLastIndexIncomingMail() throws SQLException, InstantiationException, IllegalAccessException {
		int lastId = 0;

		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_incomingMail_to_id = "SELECT idMail FROM incomingMail ORDER BY idMail DESC LIMIT 1;";

		ResultSet rs = statement.executeQuery(SQL_get_incomingMail_to_id);
		while (rs.next()) {
			lastId = rs.getInt("idMail");
		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return lastId + 1;

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
