package OutgoingMail;

import java.util.ArrayList;

import IncomingMail.IncomingMail;
import UserProfile.UserProfile;

public class OutgoingMail {
	private int idMail; //regNum
	private String regDate;
	private String mailNum;
	private String destination; //adresat
	private int forWhomId; //ispolnitel'
	private String sendDate;
	private String mailTheme;
	private int executorId; //ispolnitel'
	private int realExecutorId; //real ispolnitel'
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

	public int getForWhom() {
		return forWhomId;
	}

	public void setForWhom(int forWhom) {
		this.forWhomId = forWhom;
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
		this.mailNum = "� �-��-01/" + mailNum;
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

	public int getExecutor() {
		return executorId;
	}

	public void setExecutor(int executor) {
		this.executorId = executor;
	}

	public int getRealExecutor() {
		return realExecutorId;
	}

	public void setRealExecutor(int realExecutor) {
		this.realExecutorId = realExecutor;
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
				+ destination + ", forWhomId=" + forWhomId + ", sendDate=" + sendDate + ", mailTheme=" + mailTheme
				+ ", executorId=" + executorId + ", realExecutorId=" + realExecutorId + ", incomingMailNum="
				+ incomingMailNum + ", toWhomItIsPainted=" + toWhomItIsPainted + ", note=" + note + ", mailingNote="
				+ mailingNote + ", filePathAndName=" + filePathAndName + "]";
	}


	
	

}
