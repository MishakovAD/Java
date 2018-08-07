package IncomingMail;

import java.util.ArrayList;
import java.util.Date;
import Work.Work;

public class IncomingMail {
	/*
	 * add user id, who add mail if it need */
	private String regDate;
	private int idMail; //regNum
	private String typeMail;
	private String sender;
	private String sendDate;
	private String mailNum;
	private String mailTheme;
	private String secondFloorDate;
	private String secondFloorNum;
	private String filePathAndName;
	private Work resolution;
	
	public static ArrayList<String> typeMailList = new ArrayList<>(); // заполнить необходимыми типами письма
	public static ArrayList<String> senderList = new ArrayList<>(); // заполнить необходимыми отправителями
	
	public IncomingMail() {
		super();
	}

	public IncomingMail(String regDate, String typeMail, String sender, String sendDate, String mailNum, String mailTheme,
			String secondFloorDate) {
		super();
		this.regDate = regDate;
		this.typeMail = typeMail;
		this.sender = sender;
		this.sendDate = sendDate;
		this.mailNum = mailNum;
		this.mailTheme = mailTheme;
		this.secondFloorDate = secondFloorDate;
	}

	public IncomingMail(String regDate, String typeMail, String sender, String sendDate, String mailNum, String mailTheme,
			String secondFloorDate, Work resolution) {
		super();
		this.regDate = regDate;
		this.typeMail = typeMail;
		this.sender = sender;
		this.sendDate = sendDate;
		this.mailNum = mailNum;
		this.mailTheme = mailTheme;
		this.secondFloorDate = secondFloorDate;
		this.resolution = resolution;
	}

	public IncomingMail(String regDate, int idMail, String typeMail, String sender, String sendDate, String mailNum,
			String mailTheme, String secondFloorDate, Work resolution) {
		super();
		this.regDate = regDate;
		this.idMail = idMail;
		this.typeMail = typeMail;
		this.sender = sender;
		this.sendDate = sendDate;
		this.mailNum = mailNum;
		this.mailTheme = mailTheme;
		this.secondFloorDate = secondFloorDate;
		this.resolution = resolution;
	}
	

	public IncomingMail(String regDate, String typeMail, String sender, String sendDate, String mailNum, String mailTheme,
			String secondFloorDate, String filePathAndName, Work resolution) {
		super();
		this.regDate = regDate;
		this.typeMail = typeMail;
		this.sender = sender;
		this.sendDate = sendDate;
		this.mailNum = mailNum;
		this.mailTheme = mailTheme;
		this.secondFloorDate = secondFloorDate;
		this.filePathAndName = filePathAndName;
		this.resolution = resolution;
	}

	public IncomingMail(String regDate, String typeMail, String sender, String sendDate, String mailNum, String mailTheme,
			String secondFloorDate, String filePathAndName) {
		super();
		this.regDate = regDate;
		this.typeMail = typeMail;
		this.sender = sender;
		this.sendDate = sendDate;
		this.mailNum = mailNum;
		this.mailTheme = mailTheme;
		this.secondFloorDate = secondFloorDate;
		this.filePathAndName = filePathAndName;
	}
	
	

	public IncomingMail(String regDate, String typeMail, String sender, String sendDate, String mailNum,
			String mailTheme, String secondFloorDate, String secondFloorNum, String filePathAndName) {
		super();
		this.regDate = regDate;
		this.typeMail = typeMail;
		this.sender = sender;
		this.sendDate = sendDate;
		this.mailNum = mailNum;
		this.mailTheme = mailTheme;
		this.secondFloorDate = secondFloorDate;
		this.secondFloorNum = secondFloorNum;
		this.filePathAndName = filePathAndName;
	}

	

	public IncomingMail(String regDate, String typeMail, String sender, String sendDate, String mailNum,
			String mailTheme, String secondFloorDate, String secondFloorNum, String filePathAndName, Work resolution) {
		super();
		this.regDate = regDate;
		this.typeMail = typeMail;
		this.sender = sender;
		this.sendDate = sendDate;
		this.mailNum = mailNum;
		this.mailTheme = mailTheme;
		this.secondFloorDate = secondFloorDate;
		this.secondFloorNum = secondFloorNum;
		this.filePathAndName = filePathAndName;
		this.resolution = resolution;
	}

	public IncomingMail(String regDate, int idMail, String typeMail, String sender, String sendDate, String mailNum,
			String mailTheme, String secondFloorDate, String secondFloorNum, String filePathAndName, Work resolution) {
		super();
		this.regDate = regDate;
		this.idMail = idMail;
		this.typeMail = typeMail;
		this.sender = sender;
		this.sendDate = sendDate;
		this.mailNum = mailNum;
		this.mailTheme = mailTheme;
		this.secondFloorDate = secondFloorDate;
		this.secondFloorNum = secondFloorNum;
		this.filePathAndName = filePathAndName;
		this.resolution = resolution;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate2) {
		this.regDate = regDate2;
	}

	public int getIdMail() {
		return idMail;
	}

	public void setIdMail(int idMail) {
		this.idMail = idMail;
	}

	public String getTypeMail() {
		return typeMail;
	}

	public void setTypeMail(String typeMail) {
		this.typeMail = typeMail;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate2) {
		this.sendDate = sendDate2;
	}

	public String getMailNum() {
		return mailNum;
	}

	public void setMailNum(String mailNum) {
		this.mailNum = mailNum;
	}

	public String getMailTheme() {
		return mailTheme;
	}

	public void setMailTheme(String mailTheme) {
		this.mailTheme = mailTheme;
	}

	public String getSecondFloorDate() {
		return secondFloorDate;
	}

	public void setSecondFloorDate(String secondFloorDate2) {
		this.secondFloorDate = secondFloorDate2;
	}
	
	public String getSecondFloorNum() {
		return secondFloorNum;
	}

	public void setSecondFloorNum(String secondFloorNum) {
		this.secondFloorNum = secondFloorNum;
	}

	public String getFilePathAndName() {
		return filePathAndName;
	}

	public void setFilePathAndName(String filePathAndName) {
		this.filePathAndName = filePathAndName;
	}

	public Work getResolution() {
		return resolution;
	}

	public void setResolution(Work resolution) {
		this.resolution = resolution;
	}

	@Override
	public String toString() {
		return "IncomingMail [regDate=" + regDate + ", idMail=" + idMail + ", typeMail=" + typeMail + ", sender="
				+ sender + ", sendDate=" + sendDate + ", mailNum=" + mailNum + ", mailTheme=" + mailTheme
				+ ", secondFloorDate=" + secondFloorDate + ", filePathAndName=" + filePathAndName + ", resolution="
				+ resolution + "]";
	}
	
	

}
