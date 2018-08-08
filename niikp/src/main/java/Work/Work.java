package Work;

import java.util.HashMap;
import java.util.Map;


public class Work {
	private int workId;
	private int toUserId;
	private int observerId; //otvetstvennbIy
	private int fromUserId;	
	private String startDate;
	private String endDate;
	private String assignment; //поручение poruchenie	ограничение в Ѕƒ 500
	private String mailId;
	private String filePathAndNameToWork; //дл€ данного пол€ конструктор не задан
	private boolean isComplete = false; //данные два пол€ предназначены дл€ отчетности, конструктора дл€ них нет
	private String report; //будут заноситьс€ в бд путем апдейта по workId
	
	public static Map<Integer, Work> workList = new HashMap<>(); //userId -> Work
	//не продумано, что если на одного человека несколько резолюций
	//тогда стара€ просто перезапишетс€

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
	public int getToUserId() {
		return toUserId;
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
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public static Map<Integer, Work> getWorkList() {
		return workList;
	}
	public static void setWorkList(Map<Integer, Work> workList) {
		Work.workList = workList;
	}
	
}
