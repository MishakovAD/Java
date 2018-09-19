package Entity;

import java.util.ArrayList;

public class OutgoingMail {
	private int idMail; //regNum
	private String regDate;
	private String mailNum;
	private String destination; //adresat
	private String forWhom; //ispolnitel'
	private String sendDate;
	private String mailTheme;
	private String executor; //ispolnitel'
	private String realExecutor; //real ispolnitel'
	private String incomingMailNum;
	private String toWhomItIsPainted;
	private int incomingMailId;
	private String note;
	private String mailingNote; //primechanie rassbIlki
	private String filePathAndName;
	private boolean isDeleted = false;
	
	public static ArrayList<String> destinationList = new ArrayList<>();
	
	public static ArrayList<String> getDestinationList() {
		destinationList.add("��� �������λ");
		destinationList.add("�����");
		destinationList.add("��� ��� �����-��������");
		destinationList.add("��� ���� \"����� �������\"");
		destinationList.add("��� \"���\"");
		destinationList.add("��� �������");
		destinationList.add("�������� ����������� ������������ ���");
		destinationList.add("��������������� ���� \"�����\" ��. �.�. ���������");
		destinationList.add("��� �� - ������ ���� \"����� ��. �.�. ���������\"");
		destinationList.add("����� ���");
		destinationList.add("����� ���");
		destinationList.add("��� \"������\"");
		destinationList.add("��� \"��������\"");
		destinationList.add("��� \"�������� ����������\"");
		destinationList.add("��� \"��������\"");
		destinationList.add("��� \"��������\"");
		destinationList.add("��� \"������������������ ����� \"�������\"");
		destinationList.add("��� \"������-����������������� �������� ������������ ���������������\"");
		destinationList.add("��� \"������-���������������� ����������� \"�����\"");
		destinationList.add("��� \"��� \" ���������\"");
		destinationList.add("��� \"��� ��\"");
		destinationList.add("��� \"�����\"");
		destinationList.add("��� \"��� ���\"");
		destinationList.add("��� \"��� ��\"");
		destinationList.add("��� \"��� \"�����\"");
		destinationList.add("��� \"��� ���\"");
		destinationList.add("��� \"�������-����������� ���������� \"�������\" ����� �.�. ��������\"");
		destinationList.add("��� \"���������� ����������� �������\"");
		destinationList.add("��� \"������\"");
		destinationList.add("��� �����ѻ");
		destinationList.add("��� ������������ ����� ����������������� ��������-������ ");
		destinationList.add("��� ���� ���� ���������������");
		destinationList.add("��� ���֖��� �̻");
		destinationList.add("��� �����̻");
		destinationList.add("��� ���� �����һ");
		destinationList.add("��� ����� ����������������һ");
		destinationList.add("��� ����� ������ͻ");
		destinationList.add("��� ����� ����ѻ");
		destinationList.add("��� ��� ����������");
		destinationList.add("��� ��� ��. ��������� �.�. ���������");
		destinationList.add("����");
		destinationList.add("��� �����˻");
		destinationList.add("��� ���� ���һ");
		destinationList.add("�� ������ � ������ ���� ������ ��. �.�.��������� ");
		destinationList.add("�������");
		destinationList.add("���������");
		destinationList.add("���� \"������-����������������� �������� ��������� ��������\"");
		destinationList.add("���� \"��� ������ � ������� ��. �.�.���������\" ");
		destinationList.add("���� \"������� ��������������� ���� \"�����\"");
		destinationList.add("���� \"����������� \"����\"");
		destinationList.add("���� ������������ ������������ ����� (���) ������ ���� ������ ��. �.�. ���������");
		destinationList.add("���� ������  ��. �.�. ���������");
		destinationList.add("���� �����������");
		destinationList.add("���� �������");
		destinationList.add("���� ���������������� ���� �������� ��. �.�. ������");
		destinationList.add("���� ����������� ������-��������������� ���� �����");
		destinationList.add("���� ������� ���������������� ����������� ��. �. �. ���������");
		destinationList.add("���� �������-���������������� ����������� ����������");
		destinationList.add("���� ��������");
		destinationList.add("���� ����ϻ");
		destinationList.add("���� �����Ȼ");
		destinationList.add("���� �����-�����Ի");
		destinationList.add("���� �����-�����Ի");
		destinationList.add("���� ����� �������");
		destinationList.add("���� ������� ��. ��������� �.�. �����");
		destinationList.add("���� ����");
		destinationList.add("���� ��� �������");
		destinationList.add("���� ��� ���������� � ��������������� ����� ��������� �.�.��������");
		destinationList.add("������ ��� �46 ���� ������������ ������� ������");
		destinationList.add("��� \"������-������������� ����� �������-����������� ��������������\"");
		destinationList.add("�� ���� ������  ��. �.�. ��������� - ���� �� ��. �.�. ���������");
		destinationList.add("�������");
		destinationList.add("�����");
		return destinationList;
	}
	
	public OutgoingMail() {
		super();
	}

	public int getIdMail() {
		return idMail;
	}

	public void setIdMail(int idMail) {
		this.idMail = idMail;
	}

	public String getForWhom() {
		return forWhom;
	}

	public void setForWhom(String forWhom) {
		this.forWhom = forWhom;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getMailNum() {
		return mailNum;
	}

	public void setMailNum(String mailNum) {
		this.mailNum = mailNum;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getMailTheme() {
		return mailTheme;
	}

	public void setMailTheme(String mailTheme) {
		this.mailTheme = mailTheme;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public String getRealExecutor() {
		return realExecutor;
	}

	public void setRealExecutor(String realExecutor) {
		this.realExecutor = realExecutor;
	}

	public String getIncomingMailNum() {
		return incomingMailNum;
	}

	public void setIncomingMailNum(String incomingMailNum) {
		this.incomingMailNum = incomingMailNum;
	}

	public String getToWhomItIsPainted() {
		return toWhomItIsPainted;
	}

	public void setToWhomItIsPainted(String toWhomItIsPainted) {
		this.toWhomItIsPainted = toWhomItIsPainted;
	}

	public int getIncomingMailId() {
		return incomingMailId;
	}

	public void setIncomingMailId(int incomingMailId) {
		this.incomingMailId = incomingMailId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getMailingNote() {
		return mailingNote;
	}

	public void setMailingNote(String mailingNote) {
		this.mailingNote = mailingNote;
	}
	
	public String getFilePathAndName() {
		return filePathAndName;
	}

	public void setFilePathAndName(String filePathAndName) {
		this.filePathAndName = filePathAndName;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "OutgoingMail [idMail=" + idMail + ", regDate=" + regDate + ", mailNum=" + mailNum + ", destination="
				+ destination + ", forWhom=" + forWhom + ", sendDate=" + sendDate + ", mailTheme=" + mailTheme
				+ ", executor=" + executor + ", realExecutor=" + realExecutor + ", incomingMailNum="
				+ incomingMailNum + ", toWhomItIsPainted=" + toWhomItIsPainted + ", note=" + note + ", mailingNote="
				+ mailingNote + ", filePathAndName=" + filePathAndName + "]";
	}


	
	

}
