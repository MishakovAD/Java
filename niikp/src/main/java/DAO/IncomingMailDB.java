package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import IncomingMail.IncomingMail;
import IncomingMail.IncomingMailServlet;
import UserProfile.UserProfile;

public class IncomingMailDB {
	private static String url = "jdbc:mysql://localhost:3306/niikp_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String username = "root";
	private static String password = "hedghog";
	
	public static void addIncomingMail(IncomingMail incMail) throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);			

		//String regDate = incMail.getRegDate();
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
				+ "mailNum, mailTheme, secondFloorDate, secondFloorNum, filePathAndName)"
				+ " values (now(), '" + typeMail + "', '" 
				+ sender + "', '" + sendDate + "', '" + mailNum + "', '" 
				+ mailTheme + "', '" + secondFloorDate + "', '" + secondFloorNum + "', '" + filePathAndName + "');";
				
		statement.execute(SQL_insert_incomingMail);
		//regDate = null;
		typeMail = null;
		sender = null;
		sendDate = null;
		mailNum = null;
		mailTheme = null;
		secondFloorDate = null;
		secondFloorNum = null;
		filePathAndName = null;
	}
	
	public static ArrayList<IncomingMail> getIncomingMail() throws SQLException, InstantiationException, IllegalAccessException {
		IncomingMail incMail = new IncomingMail();
		ArrayList<IncomingMail> listIncomingMailFromMethodGet = new ArrayList<>();
		
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_incomingMail = "SELECT * FROM incomingMail;";
				
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
		return listIncomingMailFromMethodGet;
		//����� ����������, ��� �����:
		/* 
		 * ���������� ������, ��������� � ���� ������
		 * ��� �� ������ ��������� ��� ��������� ������ � ������ ������
		 * */

	}
	
	public static IncomingMail getIncomingMailToId(int id) throws SQLException, InstantiationException, IllegalAccessException {
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
		
		return incMail;

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
		return lastId+1;

	}
	
	
	public static void deleteIncomingMail(int id) throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);	
		Statement statement = null;
		statement = con.createStatement();

		String SQL_delete_incomingMail_to_id = "DELETE FROM incomingMail WHERE idMail=" + id + ";";		
		
		statement.executeUpdate(SQL_delete_incomingMail_to_id);		
	}
	
	public static void main(String[] args) {
		for (int i=1; i<11; i++) {
			try {
				deleteIncomingMail(i);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
		
}