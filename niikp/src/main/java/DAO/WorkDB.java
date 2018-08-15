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
	private static String url = DataBase.url;
	private static String username = DataBase.username;
	private static String password = DataBase.password;
	
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
		String reportFilePathAndNameToWork = work.getReportFilePathAndNameToWork();
		
		Statement statement = null;
		statement = con.createStatement();

		String SQL_insert_work = "insert into work (toUserId, observerId, fromUserId, startDate, "
				+ "endDate, assignment, mailId, filePathAndNameToWork, isComplete, report, reportFilePathAndNameToWork)"
				+ " values (" + toUserId + ", " + observerId + ", " + fromUserId
				+ ", '" + startDate + "', '" + endDate + "', '" + assignment
				+ "', '" + mailId + "', '" + filePathAndNameToWork + "', " + isComplete + ", '" + report + "', '" + reportFilePathAndNameToWork + "');";
				
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
		reportFilePathAndNameToWork = null;
		if(statement != null) statement.close(); 
	    if(con != null)  con.close(); 
	}
	
	public static ArrayList<Work> getWorkList() throws SQLException, InstantiationException, IllegalAccessException {
		Work work = new Work();
		IncomingMail incMail = new IncomingMail();
		ArrayList<Work> listWorkFromMethodGet = new ArrayList<>();
		
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();
		
		String SQL_get_work = "SELECT * FROM work;";
				
		statement.execute(SQL_get_work);
		ResultSet rs = statement.executeQuery(SQL_get_work);
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
			work.setReportFilePathAndNameToWork(rs.getString("reportFilePathAndNameToWork"));
			listWorkFromMethodGet.add(work);
			work = new Work();
		}
	      if(statement != null) statement.close(); 
	      if(con != null)  con.close(); 
		return listWorkFromMethodGet;
	}
	
	public static ArrayList<Work> getWorkListToId(int userId) throws SQLException, InstantiationException, IllegalAccessException { 
		//ƒобавл€ютс€ только невыполненные дела! ƒл€ этого проверка переменной
		Work work = new Work();
		ArrayList<Work> workListToId = new ArrayList<>();
		ArrayList<Work> workListDoneToId = new ArrayList<>();
		
		Connection con = DriverManager.getConnection(url, username, password);	
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_incomingMail_to_id = "SELECT * FROM work WHERE toUserId=" + userId + ";";		
		
		ResultSet rs = statement.executeQuery(SQL_get_incomingMail_to_id);		
		while (rs.next()) {
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
			work.setReportFilePathAndNameToWork(rs.getString("reportFilePathAndNameToWork"));
			if (work.isComplete() == false) {
				workListToId.add(work);
				work = new Work();
			} else {
				workListDoneToId.add(work); //не используетс€, но это список уже выполненных поручений
				work = new Work();
			}
			
		}
		if(statement != null) statement.close(); 
	    if(con != null)  con.close(); 
		return workListToId;

	}
	
	public static void doneWorkToUser(String report, int workId, String reportFile) throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);	
		Statement statement = null;
		statement = con.createStatement();

		String SQL_update_work = "UPDATE work SET isComplete=true, report='" + report + "', reportFilePathAndNameToWork='" + reportFile + "' WHERE workId=" + workId + ";";		
		
		statement.executeUpdate(SQL_update_work);		
		if(statement != null) statement.close(); 
	    if(con != null)  con.close(); 

	}
	
	//ѕолучение ѕоручени€ по айди поручени€ дл€ того, чтобы можно было реализовать маршруты (см. notes)
	public static Work getWorkToWorkId(int workId) throws SQLException, InstantiationException, IllegalAccessException {
		Work workToWorkID = new Work();
		Connection con = DriverManager.getConnection(url, username, password);	
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_work = "SELECT * FROM work WHERE workId=" + workId + ";";		
		
		ResultSet rs = statement.executeQuery(SQL_get_work); 
		while (rs.next()) {
			workToWorkID.setWorkId(rs.getInt("workId"));
			workToWorkID.setToUserId(rs.getInt("toUserId"));
			workToWorkID.setObserverId(rs.getInt("observerId"));
			workToWorkID.setFromUserId(rs.getInt("fromUserId"));
			workToWorkID.setStartDate(rs.getString("startDate"));
			workToWorkID.setEndDate(rs.getString("endDate"));
			workToWorkID.setAssignment(rs.getString("assignment"));
			workToWorkID.setMailId(rs.getString("mailId"));			
			workToWorkID.setComplete(rs.getBoolean("isComplete"));
			workToWorkID.setReport(rs.getString("report"));
			workToWorkID.setReportFilePathAndNameToWork(rs.getString("reportFilePathAndNameToWork"));
		}
		if(statement != null) statement.close(); 
	    if(con != null)  con.close(); 
	    return workToWorkID;

	}
	
	
	public static void deleteIncomingMail(int id) throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);	
		Statement statement = null;
		statement = con.createStatement();

		String SQL_delete_incomingMail_to_id = "DELETE FROM incomingMail WHERE idMail=" + id + ";";		
		
		statement.executeUpdate(SQL_delete_incomingMail_to_id);	
		if(statement != null) statement.close(); 
	    if(con != null)  con.close(); 
	}
	
	public static int getLastIndexWork() throws SQLException, InstantiationException, IllegalAccessException {
		int lastId = 0;
		
		Connection con = DriverManager.getConnection(url, username, password);	
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_incomingMail_to_id = "SELECT workId FROM work ORDER BY workId DESC LIMIT 1;";		
		
		ResultSet rs = statement.executeQuery(SQL_get_incomingMail_to_id);		
		while (rs.next()) {
			lastId = rs.getInt("workId");
		}
		if(statement != null) statement.close(); 
	    if(con != null)  con.close(); 
		return lastId+1;

	}
	
	public static void main(String[] args) {
		for (int i=1; i<11; i++) {
			try {
				deleteIncomingMail(i);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
