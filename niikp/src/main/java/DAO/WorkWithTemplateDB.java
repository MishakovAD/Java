package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Work.Work;

public class WorkWithTemplateDB {
	private static String url = DataBase.url;
	private static String username = DataBase.username;
	private static String password = DataBase.password;
	
	public static void addWorkWithTemplate(Work work) throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);			
		
		int workTemplateId = work.getWorkTemplateId();
		int toUserId = work.getToUserId();
		int observerId = work.getObserverId();
		int fromUserId = work.getFromUserId();	
		String startDate = work.getStartDate();
		String endDate = work.getEndDate();
		String assignment = work.getAssignment();
		String mailId = work.getMailId();
		String filePathAndNameToWork = work.getFilePathAndNameToWork();
		boolean isComplete = work.isComplete();
		String isAccept = "null";
		String report = work.getReport();
		String reportFilePathAndNameToWork = work.getReportFilePathAndNameToWork();
		String template = work.getTemplate();
		
		Statement statement = null;
		statement = con.createStatement();

		String SQL_insert_work = "insert into work (workTemplateId, toUserId, observerId, fromUserId, startDate, "
				+ "endDate, assignment, mailId, filePathAndNameToWork, isComplete, isAssept, report, reportFilePathAndNameToWork, template)"
				+ " values (" + workTemplateId + toUserId + ", " + observerId + ", " + fromUserId
				+ ", '" + startDate + "', '" + endDate + "', '" + assignment
				+ "', '" + mailId + "', '" + filePathAndNameToWork + "', " + isComplete + ", '" + isAccept + "', '" + report + "', '" + reportFilePathAndNameToWork  + "', '" + template + "');";
				
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
	
	public static int getLastIndexWorkTemplateId() throws SQLException, InstantiationException, IllegalAccessException {
		int lastId = 0;
		
		Connection con = DriverManager.getConnection(url, username, password);	
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_incomingMail_to_id = "SELECT workTemplateId FROM workWithTemplate ORDER BY workTemplateId DESC LIMIT 1;";		
		
		ResultSet rs = statement.executeQuery(SQL_get_incomingMail_to_id);		
		while (rs.next()) {
			lastId = rs.getInt("workTemplateId");
		}
		if(statement != null) statement.close(); 
	    if(con != null)  con.close(); 
		return lastId+1;

	}

}
