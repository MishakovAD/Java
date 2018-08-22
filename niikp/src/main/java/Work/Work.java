package Work;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DAO.GetterDB;


public class Work {
	private int workId;
	private int workTemplateId;
	private int toUserId;
	private int observerId; //otvetstvennbIy
	private int fromUserId;	
	private String startDate;
	private String endDate;
	private String assignment; //поручение poruchenie	ограничение в БД 500
	private String mailId;
	private String filePathAndNameToWork; //для данного поля конструктор не задан
	private boolean isComplete = false; //данные два поля предназначены для отчетности, конструктора для них нет
	private String isAccept; //принято дело или отправлено на переделывание Accept/Refuse/Null
	private String report; //будут заноситься в бд путем апдейта по workId
	private String reportFilePathAndNameToWork;
	private String template; //пусть будет тип маршрута стринг - будем класть номер маршрута и по номеру маршрута смотреть. Хотя нужно ли это поле? Пока пробуем без
	//Нужно, потому что мы должны смотреть, задан маршрут или нет, и если задан, то делаем проверку, если нет, то не делаем
	//А так же нужно сделать, что если поддерживаются маршруты, то нужно сохранять всю предыдущую историю действий
	
	public static Map<Integer, Work> workList = new HashMap<>(); //userId -> Work
	//не продумано, что если на одного человека несколько резолюций
	//тогда старая просто перезапишется

	public Work() {
		super();
	}
	public Work(int workId, int toUserId, int observerId, int fromUserId, String startDate, String endDate,
			String assignment, String mailId) {
		super();
		this.workId = workId;
		this.toUserId = toUserId;
		this.observerId = observerId;
		this.fromUserId = fromUserId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.assignment = assignment;
		this.mailId = mailId;
	}
	public Work(int workId, int toUserId, int observerId, int fromUserId, String startDate, String endDate,
			String assignment) {
		super();
		this.workId = workId;
		this.toUserId = toUserId;
		this.observerId = observerId;
		this.fromUserId = fromUserId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.assignment = assignment;
	}
	public Work(int workId, int toUserId, int fromUserId, String startDate, String endDate, String assignment,
			String mailId) {
		super();
		this.workId = workId;
		this.toUserId = toUserId;
		this.fromUserId = fromUserId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.assignment = assignment;
		this.mailId = mailId;
	}
	public Work(int workId, int toUserId, int fromUserId, String startDate, String endDate, String assignment) {
		super();
		this.workId = workId;
		this.toUserId = toUserId;
		this.fromUserId = fromUserId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.assignment = assignment;
	}
	
	
	public int getWorkId() {
		return workId;
	}
	public void setWorkId(int workId) {
		this.workId = workId;
	}
	public int getWorkTemplateId() {
		return workTemplateId;
	}
	public void setWorkTemplateId(int workTemplateId) {
		this.workTemplateId = workTemplateId;
	}
	public int getToUserId() {
		return toUserId;
	}
	
	public String getUserNameFromId(int userId) {
		String name = " ";
		String secondName = " ";

		try {
			name = GetterDB.getUserFromId(userId).getName();
			secondName = GetterDB.getUserFromId(userId).getSecondName();
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}
		
		return name + " " + secondName;
	}
	
	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
	}
	public int getObserverId() {
		return observerId;
	}
	public void setObserverId(int observerId) {
		this.observerId = observerId;
	}
	public int getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(int fromUserId) {
		this.fromUserId = fromUserId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getFilePathAndNameToWork() {
		return filePathAndNameToWork;
	}
	public void setFilePathAndNameToWork(String filePathAndNameToWork) {
		this.filePathAndNameToWork = filePathAndNameToWork;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	public String getIsAccept() {
		return isAccept;
	}
	public void setIsAccept(String isAccept) {
		this.isAccept = isAccept;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getReportFilePathAndNameToWork() {
		return reportFilePathAndNameToWork;
	}
	public void setReportFilePathAndNameToWork(String reportFilePathAndNameToWork) {
		this.reportFilePathAndNameToWork = reportFilePathAndNameToWork;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public static Map<Integer, Work> getWorkList() {
		return workList;
	}
	public static void setWorkList(Map<Integer, Work> workList) {
		Work.workList = workList;
	}
	@Override
	public String toString() {
		return "Work [workId=" + workId + ", workTemplateId=" + workTemplateId + ", toUserId=" + toUserId
				+ ", observerId=" + observerId + ", fromUserId=" + fromUserId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", assignment=" + assignment + ", mailId=" + mailId
				+ ", filePathAndNameToWork=" + filePathAndNameToWork + ", isComplete=" + isComplete + ", isAccept="
				+ isAccept + ", report=" + report + ", reportFilePathAndNameToWork=" + reportFilePathAndNameToWork
				+ ", template=" + template + "]";
	}
	
}
