package Work;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import UserProfile.UserProfile;

public class Work {
	private int workId;
	private String assignment; //поручение poruchenie
	private Date startDate;
	private Date endDate;
	private int toUserId;
	private int mailId;
	
	
	public static Map<Integer, Work> workList = new HashMap<>(); //userId -> Work
	//не продумано, что если на одного человека несколько резолюций
	//тогда старая просто перезапишется

	public Work() {
		super();
	}

	public Work(int workId, String assignment) {
		super();
		this.workId = workId;
		this.assignment = assignment;
	}

	public Work(int workId, String assignment, int toUserId) {
		super();
		this.workId = workId;
		this.assignment = assignment;
		this.toUserId = toUserId;
	}
	
	
	public Work(int workId, String assignment, Date startDate, Date endDate, int toUserId, int mailId) {
		super();
		this.workId = workId;
		this.assignment = assignment;
		this.startDate = startDate;
		this.endDate = endDate;
		this.toUserId = toUserId;
		this.mailId = mailId;
	}

	public int getWorkId() {
		return workId;
	}

	public void setWorkId(int workId) {
		this.workId = workId;
	}

	public String getAssignment() {
		return assignment;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getToUserId() {
		return toUserId;
	}

	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
	}
	

	public int getMailId() {
		return mailId;
	}

	public void setMailId(int mailId) {
		this.mailId = mailId;
	}

	public static Map<Integer, Work> getWorkList() {
		return workList;
	}

	public static void setWorkList(Map<Integer, Work> workList) {
		Work.workList = workList;
	}
	
	
	
	
}
