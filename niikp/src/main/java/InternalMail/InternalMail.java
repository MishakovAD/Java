package InternalMail;

import java.util.ArrayList;

public class InternalMail {
	private int idMail;
	private String docType;
	private String regDate;
	private String numNPK;
	private String destination;
	private String additionalDestination;
	private String docTheme;
	private String executor;
	private String note;
	
	public static ArrayList<String> docTypeList = new ArrayList<>();
	
	public static ArrayList<String> getDocTypeList() {
		docTypeList.add("Служебная записка");
		docTypeList.add("Заявка на пропуск");
		docTypeList.add("Сохранная расписка");
		return docTypeList;
		
	}
	
	public InternalMail() {
		super();
	}

	public int getIdMail() {
		return idMail;
	}

	public void setIdMail(int idMail) {
		this.idMail = idMail;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getNumNPK() {
		return numNPK;
	}

	public void setNumNPK(String numNPK) {
		this.numNPK = numNPK;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getAdditionalDestination() {
		return additionalDestination;
	}

	public void setAdditionalDestination(String additionalDestination) {
		this.additionalDestination = additionalDestination;
	}

	public String getDocTheme() {
		return docTheme;
	}

	public void setDocTheme(String docTheme) {
		this.docTheme = docTheme;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	

}
