package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import IncomingMail.IncomingMail;
import Work.Work;

public class WorkDB {
	private static String url = "jdbc:mysql://localhost:3306/niikp_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String username = "root";
	private static String password = "hedghog";
	
	public static void addWork(Work work) throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);			

		int toUserId = work.getToUserId();
		int observerId = work.getObserverId();
		int fromUserId = work.getFromUserId();	
		String startDate = work.getStartDate();
		String endDate = work.getEndDate();
		String assignment = work.getAssignment();
		String mailId = work.getMailId();
		String filePathAndNameToWork = work.getFilePathAndNameToWork();
		boolean isComplete = work.isComplete();
		String report = work.getReport();
		
		Statement statement = null;
		statement = con.createStatement();

		String SQL_insert_work = "insert into work (toUserId, observerId, fromUserId, startDate, "
				+ "endDate, assignment, mailId, filePathAndNameToWork, isComplete, report)"
				+ " values (" + toUserId + ", " + observerId + ", " + fromUserId
				+ ", '" + startDate + "', '" + endDate + "', '" + assignment
				+ "', '" + mailId + "', '" + filePathAndNameToWork + "', " + isComplete + ", '" + report + "');";
				
		statement.execute(SQL_insert_work);
		
		toUserId = 0;
		observerId = 0;
		fromUserId = 0;
		startDate = null;
		endDate = null;
		assignment = null;
		mailId = null;
		filePathAndNameToWork = null;
		isComplete = false;
		report = null;
	}
	
	public static ArrayList<Work> getWorkList() throws SQLException, InstantiationException, IllegalAccessException {
		Work work = new Work();
		IncomingMail incMail = new IncomingMail();
		ArrayList<Work> listWorkFromMethodGet = new ArrayList<>();
		
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();
		
		String SQL_get_incomingMail = "SELECT * FROM work;";
				
		statement.execute(SQL_get_incomingMail);
		ResultSet rs = statement.executeQuery(SQL_get_incomingMail);
		while (rs.next()) {
			String mailId = rs.getString("mailId");
			int indexOf = mailId.indexOf("_");
			String typeMail = mailId.substring(0, indexOf);
			String idMail = mailId.substring(indexOf+1);
			
			if (typeMail.equalsIgnoreCase("incomingMail")) {
				incMail = IncomingMailDB.getIncomingMailToId(Integer.parseInt(idMail));
				work.setFilePathAndNameToWork(incMail.getFilePathAndName());
			} else {
				work.setFilePathAndNameToWork(rs.getString("filePathAndNameToWork"));				
			}//доделать остальные типы проверок на тип письма
			
			work.setWorkId(rs.getInt("workId"));
			work.setToUserId(rs.getInt("toUserId"));
			work.setObserverId(rs.getInt("observerId"));
			work.setFromUserId(rs.getInt("fromUserId"));
			work.setStartDate(rs.getString("startDate"));
			work.setEndDate(rs.getString("endDate"));
			work.setAssignment(rs.getString("assignment"));
			work.setMailId(rs.getString("mailId"));
			
			work.setComplete(rs.getBoolean("isComplete"));
			work.setReport(rs.getString("report"));
			listWorkFromMethodGet.add(work);
			work = new Work();
		}
		return listWorkFromMethodGet;
	}
	
	public static IncomingMail getIncomingMailToId(int id) throws SQLException, InstantiationException, IllegalAccessException { //Change to Work
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
	
	public static void doneWorkToUser(String report, int workId) throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);	
		Statement statement = null;
		statement = con.createStatement();

		String SQL_update_work = "UPDATE work SET isComplete=true, report='" + report + "' WHERE workId=" + workId + ";";		
		
		statement.executeUpdate(SQL_update_work);		
		

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
