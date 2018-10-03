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
		String Co_executor = work.getCo_executor();
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
		String assigmentSourse = work.getAssigmentSourse();

		Statement statement = null;
		statement = con.createStatement();

		String SQL_insert_work = "insert into work (assigmentSource, toUserId, Co_executor, observerId, fromUserId, startDate, "
				+ "endDate, assignment, mailId, filePathAndNameToWork, isComplete, isAccept, report, reportFilePathAndNameToWork)"
				+ " values ('" + assigmentSourse + "', " + toUserId + ", '" + Co_executor + "', " + observerId + ", " + fromUserId + ", '" + startDate + "', '" + endDate
				+ "', '" + assignment + "', '" + mailId + "', '" + filePathAndNameToWork + "', " + isComplete + ", '"
				+ isAccept + "', '" + report + "', '" + reportFilePathAndNameToWork + "');";

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
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
	}
	
	public static void addWorkForCo_executor(Work work) throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);

		int toUserId = work.getToUserId();
		int observerId = work.getObserverId();
		String Co_executor = work.getCo_executor();
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
		String assigmentSourse = work.getAssigmentSourse();

		Statement statement = null;
		statement = con.createStatement();

		String SQL_insert_work = "insert into workForCo_executor (assigmentSource, toUserId, Co_executor, observerId, fromUserId, startDate, "
				+ "endDate, assignment, mailId, filePathAndNameToWork, isComplete, isAccept, report, reportFilePathAndNameToWork)"
				+ " values ('" + assigmentSourse + "', " + toUserId + ", '" + Co_executor + "', " + observerId + ", " + fromUserId + ", '" + startDate + "', '" + endDate
				+ "', '" + assignment + "', '" + mailId + "', '" + filePathAndNameToWork + "', " + isComplete + ", '"
				+ isAccept + "', '" + report + "', '" + reportFilePathAndNameToWork + "');";

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
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
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
			String idMail = mailId.substring(indexOf + 1);

			if (typeMail.equalsIgnoreCase("incomingMail")) {
				incMail = IncomingMailDB.getIncomingMailToId(Integer.parseInt(idMail));
				work.setFilePathAndNameToWork(incMail.getFilePathAndName());
			} else {
				work.setFilePathAndNameToWork(rs.getString("filePathAndNameToWork"));
			} // доделать остальные типы проверок на тип письма

			work.setWorkId(rs.getInt("workId"));
			work.setAssigmentSourse(rs.getString("assigmentSource"));
			work.setToUserId(rs.getInt("toUserId"));
			work.setCo_executor(rs.getString("Co_executor"));
			work.setObserverId(rs.getInt("observerId"));
			work.setFromUserId(rs.getInt("fromUserId"));
//			work.setStartDate(doConvert(rs.getString("startDate").substring(0, 10)));
//			work.setEndDate(doConvert(rs.getString("endDate").substring(0, 10)));
			work.setStartDate(rs.getString("startDate"));
			work.setEndDate(rs.getString("endDate"));
			work.setFinishDate(rs.getString("finishDate"));
			work.setAssignment(rs.getString("assignment"));
			work.setMailId(rs.getString("mailId"));

			work.setComplete(rs.getBoolean("isComplete"));
			work.setIsAccept(rs.getString("isAccept"));
			work.setReport(rs.getString("report"));
			work.setReportFilePathAndNameToWork(rs.getString("reportFilePathAndNameToWork"));
			work.setComment(rs.getString("comment"));
			listWorkFromMethodGet.add(work);
			work = new Work();
		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return listWorkFromMethodGet;
	}
	
	public static ArrayList<Work> getWorkListForCo_executor() throws SQLException, InstantiationException, IllegalAccessException {
		Work work = new Work();
		IncomingMail incMail = new IncomingMail();
		ArrayList<Work> listWorkFromMethodGet = new ArrayList<>();

		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_work = "SELECT * FROM workForCo_executor;";

		statement.execute(SQL_get_work);
		ResultSet rs = statement.executeQuery(SQL_get_work);
		while (rs.next()) {
			String mailId = rs.getString("mailId");
			int indexOf = mailId.indexOf("_");
			String typeMail = mailId.substring(0, indexOf);
			String idMail = mailId.substring(indexOf + 1);

			if (typeMail.equalsIgnoreCase("incomingMail")) {
				incMail = IncomingMailDB.getIncomingMailToId(Integer.parseInt(idMail));
				work.setFilePathAndNameToWork(incMail.getFilePathAndName());
			} else {
				work.setFilePathAndNameToWork(rs.getString("filePathAndNameToWork"));
			} // доделать остальные типы проверок на тип письма

			work.setWorkId(rs.getInt("workForCo_executorId"));
			work.setAssigmentSourse(rs.getString("assigmentSource"));
			work.setToUserId(rs.getInt("toUserId"));
			work.setCo_executor(rs.getString("Co_executor"));
			work.setObserverId(rs.getInt("observerId"));
			work.setFromUserId(rs.getInt("fromUserId"));
//			work.setStartDate(doConvert(rs.getString("startDate").substring(0, 10)));
//			work.setEndDate(doConvert(rs.getString("endDate").substring(0, 10)));
			work.setStartDate(rs.getString("startDate"));
			work.setEndDate(rs.getString("endDate"));
			work.setFinishDate(rs.getString("finishDate"));
			work.setAssignment(rs.getString("assignment"));
			work.setMailId(rs.getString("mailId"));

			work.setComplete(rs.getBoolean("isComplete"));
			work.setIsAccept(rs.getString("isAccept"));
			work.setReport(rs.getString("report"));
			work.setReportFilePathAndNameToWork(rs.getString("reportFilePathAndNameToWork"));
			work.setComment(rs.getString("comment"));
			listWorkFromMethodGet.add(work);
			work = new Work();
		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return listWorkFromMethodGet;
	}

//	public static String doConvert(String d) {
//		String dateRev = "";
//		String[] dateArr = d.split("-");
//		for (int i = dateArr.length - 1; i >= 0; i--) {
//			if (i != dateArr.length - 1)
//				dateRev += "-";
//			dateRev += dateArr[i];
//		}
//		return dateRev;
//	}

	public static ArrayList<Work> getWorkListToId(int observerId)
			throws SQLException, InstantiationException, IllegalAccessException {
		// ƒобавл€ютс€ только невыполненные дела! ƒл€ этого проверка переменной
		Work work = new Work();
		ArrayList<Work> workListToId = new ArrayList<>();
		ArrayList<Work> workListDoneToId = new ArrayList<>();

		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_incomingMail_to_id = "SELECT * FROM work WHERE observerId=" + observerId + ";";

		ResultSet rs = statement.executeQuery(SQL_get_incomingMail_to_id);
		while (rs.next()) {
			work.setWorkId(rs.getInt("workId"));
			work.setAssigmentSourse(rs.getString("assigmentSource"));
			work.setToUserId(rs.getInt("toUserId"));
			work.setObserverId(rs.getInt("observerId"));
			work.setFromUserId(rs.getInt("fromUserId"));
			work.setStartDate(rs.getString("startDate"));
			work.setEndDate(rs.getString("endDate"));
			work.setAssignment(rs.getString("assignment"));
			work.setMailId(rs.getString("mailId"));
			work.setComplete(rs.getBoolean("isComplete"));
			work.setIsAccept(rs.getString("isAccept"));
			work.setReport(rs.getString("report"));
			work.setReportFilePathAndNameToWork(rs.getString("reportFilePathAndNameToWork"));
			work.setComment(rs.getString("comment"));
			if (work.isComplete() == false && !work.getIsAccept().equals("accept")) {
				workListToId.add(work);
				work = new Work();
			} else {
				workListDoneToId.add(work); // не используетс€, но это список уже выполненных поручений
				work = new Work();
			}

		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return workListToId;

	}
	
	public static ArrayList<Work> getWorkListForCo_executorToId(int userId)
			throws SQLException, InstantiationException, IllegalAccessException {
		// ƒобавл€ютс€ только невыполненные дела! ƒл€ этого проверка переменной
		Work work = new Work();
		ArrayList<Work> workListToId = new ArrayList<>();
		ArrayList<Work> workListDoneToId = new ArrayList<>();

		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_incomingMail_to_id = "SELECT * FROM workForCo_executor WHERE toUserId=" + userId + ";";

		ResultSet rs = statement.executeQuery(SQL_get_incomingMail_to_id);
		while (rs.next()) {
			work.setWorkId(rs.getInt("workForCo_executorId"));
			work.setAssigmentSourse(rs.getString("assigmentSource"));
			work.setToUserId(rs.getInt("toUserId"));
			work.setObserverId(rs.getInt("observerId"));
			work.setFromUserId(rs.getInt("fromUserId"));
			work.setStartDate(rs.getString("startDate"));
			work.setEndDate(rs.getString("endDate"));
			work.setAssignment(rs.getString("assignment"));
			work.setMailId(rs.getString("mailId"));
			work.setComplete(rs.getBoolean("isComplete"));
			work.setIsAccept(rs.getString("isAccept"));
			work.setReport(rs.getString("report"));
			work.setReportFilePathAndNameToWork(rs.getString("reportFilePathAndNameToWork"));
			work.setComment(rs.getString("comment"));
			if (work.isComplete() == false && !work.getIsAccept().equals("accept")) {
				workListToId.add(work);
				work = new Work();
			} else {
				workListDoneToId.add(work); // не используетс€, но это список уже выполненных поручений
				work = new Work();
			}

		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return workListToId;

	}

	public static ArrayList<Work> getWorkToMailId(String mailId)
			throws SQLException, InstantiationException, IllegalAccessException {
		// ƒобавл€ютс€ только невыполненные дела! ƒл€ этого проверка переменной
		Work work = new Work();
		ArrayList<Work> workListToMailId = new ArrayList<>();

		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_workList_to_mailId = "SELECT * FROM work WHERE mailId='" + mailId + "';";

		ResultSet rs = statement.executeQuery(SQL_get_workList_to_mailId);
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
			work.setIsAccept(rs.getString("isAccept"));
			work.setReport(rs.getString("report"));
			work.setReportFilePathAndNameToWork(rs.getString("reportFilePathAndNameToWork"));
			work.setComment(rs.getString("comment"));
			workListToMailId.add(work);

			work = new Work();

		}

		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return workListToMailId;

	}

	public static void doneWorkToObserver(String report, int workId, String reportFile)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_update_work = "UPDATE work SET isComplete=true, finishDate=now(), isAccept='done', report='" + report
				+ "', reportFilePathAndNameToWork='" + reportFile + "' WHERE workId=" + workId + ";";
		//System.out.println(SQL_update_work);
		statement.executeUpdate(SQL_update_work);
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();

	}
	
	public static void addComment(int workId, String comment, String assignment, String assigmentSource)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_update_work = "UPDATE work SET comment='" + comment +"', assignment='" + assignment 
				+ "', assigmentSource='" + assigmentSource + "'  WHERE workId=" + workId + ";";
		//System.out.println(SQL_update_work);
		statement.executeUpdate(SQL_update_work);
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();

	}
	
	public static void addCommentForCo_executor(String oldAssignment, String comment, String assignment, String assigmentSource)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_update_work = "UPDATE work SET comment='" + comment +"', assignment='" + assignment 
				+ "', assigmentSource='" + assigmentSource + "'  WHERE assignment='" + oldAssignment + "';";
		//System.out.println(SQL_update_work);
		statement.executeUpdate(SQL_update_work);
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();

	}
	
	public static void doneWorkToCo_executor(String report, int workId, String reportFile)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_update_work = "UPDATE workForCo_executor SET isComplete=true, finishDate=now(), isAccept='done', report='" + report
				+ "', reportFilePathAndNameToWork='" + reportFile + "' WHERE workForCo_executorId=" + workId + ";";
		//System.out.println(SQL_update_work);
		statement.executeUpdate(SQL_update_work);
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();

	}
	
	public static void doneWorkToCo_executorFromObserver(int workId)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_update_work = "UPDATE workForCo_executor SET isComplete=true, finishDate=now(), isAccept='done', "
				+ "comment='¬ыполнил ответсвенный' WHERE workForCo_executorId=" + workId + ";";
		//System.out.println(SQL_update_work);
		statement.executeUpdate(SQL_update_work);
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();

	}

	public static void acceptWorkToUser(int workId)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_update_work = "UPDATE work SET isAccept='" + "accept" + "' WHERE workId=" + workId + ";";

		statement.executeUpdate(SQL_update_work);
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();

	}

	public static void refuseWorkToUser(int workId)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_update_work = "UPDATE work SET isComplete=false, isAccept='" + "refuse" + "' WHERE workId=" + workId
				+ ";";

		statement.executeUpdate(SQL_update_work);
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();

	}

	// ѕолучение ѕоручени€ по айди поручени€ дл€ того, чтобы можно было реализовать
	// маршруты (см. notes)
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
			workToWorkID.setAssigmentSourse(rs.getString("assigmentSource"));
			workToWorkID.setMailId(rs.getString("mailId"));
			workToWorkID.setComplete(rs.getBoolean("isComplete"));
			workToWorkID.setIsAccept(rs.getString("isAccept"));
			workToWorkID.setReport(rs.getString("report"));
			workToWorkID.setReportFilePathAndNameToWork(rs.getString("reportFilePathAndNameToWork"));
			workToWorkID.setComment(rs.getString("comment"));
		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return workToWorkID;

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
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return lastId + 1;

	}
	
	public static ArrayList<Work> getWorkForCo_executorToAssigment(String assignment) throws SQLException, InstantiationException, IllegalAccessException {
		Work workForCo_executor = new Work();
		ArrayList<Work> workList = new ArrayList<>();
		IncomingMail incMail = new IncomingMail();
		
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_incomingMail_to_id = "SELECT * FROM workForCo_executor WHERE assignment='" + assignment + "';";

		ResultSet rs = statement.executeQuery(SQL_get_incomingMail_to_id);
		while (rs.next()) {
			String mailId = rs.getString("mailId");
			int indexOf = mailId.indexOf("_");
			String typeMail = mailId.substring(0, indexOf);
			String idMail = mailId.substring(indexOf + 1);

			if (typeMail.equalsIgnoreCase("incomingMail")) {
				incMail = IncomingMailDB.getIncomingMailToId(Integer.parseInt(idMail));
				workForCo_executor.setFilePathAndNameToWork(incMail.getFilePathAndName());
			} else {
				workForCo_executor.setFilePathAndNameToWork(rs.getString("filePathAndNameToWork"));
			} // доделать остальные типы проверок на тип письма    акие??

			workForCo_executor.setWorkId(rs.getInt("workForCo_executorId"));
			workForCo_executor.setAssigmentSourse(rs.getString("assigmentSource"));
			workForCo_executor.setToUserId(rs.getInt("toUserId"));
			workForCo_executor.setCo_executor(rs.getString("Co_executor"));
			workForCo_executor.setObserverId(rs.getInt("observerId"));
			workForCo_executor.setFromUserId(rs.getInt("fromUserId"));
			workForCo_executor.setStartDate(rs.getString("startDate"));
			workForCo_executor.setEndDate(rs.getString("endDate"));
			workForCo_executor.setFinishDate(rs.getString("finishDate"));
			workForCo_executor.setAssignment(rs.getString("assignment"));
			workForCo_executor.setMailId(rs.getString("mailId"));

			workForCo_executor.setComplete(rs.getBoolean("isComplete"));
			workForCo_executor.setIsAccept(rs.getString("isAccept"));
			workForCo_executor.setReport(rs.getString("report"));
			workForCo_executor.setReportFilePathAndNameToWork(rs.getString("reportFilePathAndNameToWork"));
			workForCo_executor.setComment(rs.getString("comment"));
			
			workList.add(workForCo_executor);			
			workForCo_executor = new Work();
		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return workList;

	}

	public static void main(String[] args) {
		for (int i = 1; i < 11; i++) {
			try {
				deleteIncomingMail(i);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
