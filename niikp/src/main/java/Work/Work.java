package Work;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DAO.GetterDB;


public class Work {
	private int workId;
	private int workTemplateId;
	private String assigmentSourse;
	private int toUserId;
	private String Co_executor;
	private int observerId; //otvetstvennbIy
	private int fromUserId;	
	private String startDate;
	private String endDate;
	private String finishDate;
	private String assignment; //��������� poruchenie	����������� � �� 500
	private String mailId;
	private String filePathAndNameToWork; //��� ������� ���� ����������� �� �����
	private boolean isComplete = false; //������ ��� ���� ������������� ��� ����������, ������������ ��� ��� ���
	private String isAccept; //������� ���� ��� ���������� �� ������������� Accept/Refuse/Null
	private String report; //����� ���������� � �� ����� ������� �� workId
	private String reportFilePathAndNameToWork;
	private String template; //����� ����� ��� �������� ������ - ����� ������ ����� �������� � �� ������ �������� ��������. ���� ����� �� ��� ����? ���� ������� ���
	//�����, ������ ��� �� ������ ��������, ����� ������� ��� ���, � ���� �����, �� ������ ��������, ���� ���, �� �� ������
	//� ��� �� ����� �������, ��� ���� �������������� ��������, �� ����� ��������� ��� ���������� ������� ��������
	
	public static Map<Integer, Work> workList = new HashMap<>(); //userId -> Work
	//�� ���������, ��� ���� �� ������ �������� ��������� ���������
	//����� ������ ������ �������������

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
	
	public String getAssigmentSourse() {
		return assigmentSourse;
	}
	public void setAssigmentSourse(String assigmentSourse) {
		this.assigmentSourse = assigmentSourse;
	}
	public String getCo_executor() {
		return Co_executor;
	}
	public void setCo_executor(String co_executor) {
		Co_executor = co_executor;
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
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
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
