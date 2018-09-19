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
		
		
		String mailNum = outMail.getMailNum();
		String destination = outMail.getDestination() ; //adresat
		String forWhom = outMail.getForWhom(); //ispolnitel'
		String sendDate = outMail.getSendDate();
		String mailTheme = outMail.getMailTheme();
		String executor = outMail.getExecutor(); //ispolnitel'
		String realExecutor = outMail.getRealExecutor(); //real ispolnitel'
		String incomingMailNum = outMail.getIncomingMailNum();
		String toWhomItIsPainted = outMail.getToWhomItIsPainted();
		int incomingMailId = outMail.getIncomingMailId();
		String note = outMail.getNote();
		String mailingNote = outMail.getMailingNote(); //primechanie rassbIlki
		String filePathAndName = outMail.getFilePathAndName();


		Statement statement = null;
		statement = con.createStatement();

		String SQL_insert_outgoingMail = "insert into outgoingMail (regDate, mailNum, destination, forWhom, "
				+ "sendDate, mailTheme, executor, realExecutor, incomingMailNum, toWhomItIsPainted, "
				+ "incomingMailId, note, mailingNote, filePathAndName)" + " values (now(), '"
				+ mailNum + "', '" + destination + "', '" + forWhom + "', '" + sendDate + "', '" + mailTheme + "', '"
				+ executor + "', '" + realExecutor + "', '"  + incomingMailNum + "', '" + toWhomItIsPainted + "', " + incomingMailId + ", '" 
				+ note + "', '" + mailingNote + "', '" + filePathAndName + "');";


		statement.execute(SQL_insert_outgoingMail);

		outMail = new OutgoingMail();
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();

	}
	
	public static void addOutgoingMailFromExcel(OutgoingMail outMail)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);

		String regDate = outMail.getRegDate();
		String mailNum = outMail.getMailNum();
		String destination = outMail.getDestination() ; //adresat
		String forWhom = outMail.getForWhom(); //ispolnitel'
		String sendDate = outMail.getSendDate();
		String mailTheme = outMail.getMailTheme();
		String executor = outMail.getExecutor(); //ispolnitel'
		String realExecutor = outMail.getRealExecutor(); //real ispolnitel'
		String incomingMailNum = outMail.getIncomingMailNum();
		String toWhomItIsPainted = outMail.getToWhomItIsPainted();
		int incomingMailId = outMail.getIncomingMailId();
		String note = outMail.getNote();
		String mailingNote = outMail.getMailingNote(); //primechanie rassbIlki
		String filePathAndName = outMail.getFilePathAndName();

		Statement statement = null;
		statement = con.createStatement();
		

		String SQL_insert_outgoingMail = "insert into outgoingMail (regDate, mailNum, destination, forWhom, "
				+ "sendDate, mailTheme, executor, realExecutor, incomingMailNum, toWhomItIsPainted, "
				+ "incomingMailId, note, mailingNote, filePathAndName)" + " values ('" + regDate + "', '"
				+ mailNum + "', '" + destination + "', '" + forWhom + "', '" + sendDate + "', '" + mailTheme + "', '"
				+ executor + "', '" + realExecutor + "', '"  + incomingMailNum + "', '" + toWhomItIsPainted + "', " + incomingMailId + ", '" 
				+ note + "', '" + mailingNote + "', '" + filePathAndName + "');";

		statement.execute(SQL_insert_outgoingMail);
		outMail = new OutgoingMail();
			statement.close();
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
			outMail.setIdMail(rs.getInt("idMail"));
			outMail.setRegDate(rs.getString("regDate"));
			outMail.setMailNum(rs.getString("mailNum"));
			outMail.setDestination(rs.getString("destination")) ; //adresat
			outMail.setForWhom(rs.getString("forWhom")); //ispolnitel'
			outMail.setSendDate(rs.getString("sendDate"));
			outMail.setMailTheme(rs.getString("mailTheme"));
			outMail.setExecutor(rs.getString("executor")); //ispolnitel'
			outMail.setRealExecutor(rs.getString("realExecutor")); //real ispolnitel'
			outMail.setIncomingMailNum(rs.getString("incomingMailNum"));
			outMail.setToWhomItIsPainted(rs.getString("toWhomItIsPainted"));
			outMail.setIncomingMailId(rs.getInt("incomingMailId"));
			outMail.setNote(rs.getString("note"));
			outMail.setMailingNote(rs.getString("mailingNote")); //primechanie rassbIlki
			outMail.setFilePathAndName(rs.getString("filePathAndName"));
			outMail.setDeleted(rs.getBoolean("isDeleted"));
			if (!outMail.isDeleted()) {
				listOutgoingMailFromMethodGet.add(outMail);
			}			
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
			outMail.setMailNum(rs.getString("mailNum"));
			outMail.setDestination(rs.getString("destination")) ; //adresat
			outMail.setForWhom(rs.getString("forWhom")); //ispolnitel'
			outMail.setSendDate(rs.getString("sendDate"));
			outMail.setMailTheme(rs.getString("mailTheme"));
			outMail.setExecutor(rs.getString("executor")); //ispolnitel'
			outMail.setRealExecutor(rs.getString("realExecutor")); //real ispolnitel'
			outMail.setIncomingMailNum(rs.getString("incomingMailNum"));
			outMail.setToWhomItIsPainted(rs.getString("toWhomItIsPainted"));
			outMail.setIncomingMailId(rs.getInt("incomingMailId"));
			outMail.setNote(rs.getString("note"));
			outMail.setMailingNote(rs.getString("mailingNote")); //primechanie rassbIlki
			outMail.setFilePathAndName(rs.getString("filePathAndName"));
			outMail.setDeleted(rs.getBoolean("isDeleted"));
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

		String SQL_get_file_outgoingMail_to_id = "SELECT filePathAndName FROM outgoingMail WHERE idMail=" + id + ";";

		ResultSet rs = statement.executeQuery(SQL_get_file_outgoingMail_to_id);
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

		String SQL_get_lastIndex_outgoingMail = "SELECT idMail FROM outgoingMail ORDER BY idMail DESC LIMIT 1;";

		ResultSet rs = statement.executeQuery(SQL_get_lastIndex_outgoingMail);
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

		//String SQL_delete_outgoingMail_to_id = "UPDATE outgoingMail SET isDeleted=" + true + " WHERE idMail=" + id + ";";
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

		String mailNum = outMail.getMailNum();
		String destination = outMail.getDestination() ; //adresat
		String forWhom = outMail.getForWhom(); //ispolnitel'
		String sendDate = outMail.getSendDate();
		String mailTheme = outMail.getMailTheme();
		String executor = outMail.getExecutor(); //ispolnitel'
		String realExecutor = outMail.getRealExecutor(); //real ispolnitel'
		String incomingMailNum = outMail.getIncomingMailNum();
		String toWhomItIsPainted = outMail.getToWhomItIsPainted();
		int incomingMailId = outMail.getIncomingMailId();
		String note = outMail.getNote();
		String mailingNote = outMail.getMailingNote(); //primechanie rassbIlki
		String filePathAndName = outMail.getFilePathAndName();
		//редактирование документа убираю, т.к. если не внесут, он удалится.

		String SQL_update_outgoingMail = "UPDATE outgoingMail SET mailNum='" + mailNum + "', destination='" + destination
				+ "', forWhom='" + forWhom + "', sendDate='" + sendDate + "', mailTheme='" + mailTheme + "', executor='" + executor
				+ "', realExecutor='" + realExecutor + "', incomingMailNum='" + incomingMailNum
				+ "', toWhomItIsPainted='" + toWhomItIsPainted + "', incomingMailId=" + incomingMailId 
				+ ", note='" + note + "', mailingNote='" + mailingNote +  "' WHERE idMail=" + outMail.getIdMail() + ";";
		System.out.println(SQL_update_outgoingMail);

		statement.executeUpdate(SQL_update_outgoingMail);
		
		outMail = new OutgoingMail();

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
			outMail.setMailNum(rs.getString("mailNum"));
			outMail.setDestination(rs.getString("destination")) ; //adresat
			outMail.setForWhom(rs.getString("forWhom")); //ispolnitel'
			outMail.setSendDate(rs.getString("sendDate"));
			outMail.setMailTheme(rs.getString("mailTheme"));
			outMail.setExecutor(rs.getString("executor")); //ispolnitel'
			outMail.setRealExecutor(rs.getString("realExecutor")); //real ispolnitel'
			outMail.setIncomingMailNum(rs.getString("incomingMailNum"));
			outMail.setToWhomItIsPainted(rs.getString("toWhomItIsPainted"));
			outMail.setIncomingMailId(rs.getInt("incomingMailId"));
			outMail.setNote(rs.getString("note"));
			outMail.setMailingNote(rs.getString("mailingNote")); //primechanie rassbIlki
			outMail.setFilePathAndName(rs.getString("filePathAndName"));
			outMail.setDeleted(rs.getBoolean("isDeleted"));
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

