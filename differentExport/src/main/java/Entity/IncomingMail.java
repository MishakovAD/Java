package Entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;


public class IncomingMail {
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

	public static ArrayList<String> typeMailList = new ArrayList<>(); // ��������� ������������ ������ ������
	public static ArrayList<String> senderList = new ArrayList<>(); // ��������� ������������ �������������
	
	public static ArrayList<String> getTypeMailList() {
		typeMailList.add("���������");
		typeMailList.add("��������");
		typeMailList.add("������");
		typeMailList.add("������� �� ������������");
		typeMailList.add("��������� �������");
		typeMailList.add("������������");
		typeMailList.add("�������������� ���������");
		typeMailList.add("������");
		return typeMailList;
	}
	
	public static ArrayList<String> getSenderList() {
		senderList.add("��� �������λ");
		senderList.add("�����");
		senderList.add("��� ��� �����-��������");
		senderList.add("��� ���� \"����� �������\"");
		senderList.add("��� \"���\"");
		senderList.add("��� �������");
		senderList.add("�������� ����������� ������������ ���");
		senderList.add("��������������� ���� \"�����\" ��. �.�. ���������");
		senderList.add("��� �� - ������ ���� \"����� ��. �.�. ���������\"");
		senderList.add("����� ���");
		senderList.add("����� ���");
		senderList.add("��� \"������\"");
		senderList.add("��� \"��������\"");
		senderList.add("��� \"�������� ����������\"");
		senderList.add("��� \"��������\"");
		senderList.add("��� \"��������\"");
		senderList.add("��� \"������������������ ����� \"�������\"");
		senderList.add("��� \"������-����������������� �������� ������������ ���������������\"");
		senderList.add("��� \"������-���������������� ����������� \"�����\"");
		senderList.add("��� \"��� \" ���������\"");
		senderList.add("��� \"��� ��\"");
		senderList.add("��� \"�����\"");
		senderList.add("��� \"��� ���\"");
		senderList.add("��� \"��� ��\"");
		senderList.add("��� \"��� \"�����\"");
		senderList.add("��� \"��� ���\"");
		senderList.add("��� \"�������-����������� ���������� \"�������\" ����� �.�. ��������\"");
		senderList.add("��� \"���������� ����������� �������\"");
		senderList.add("��� \"������\"");
		senderList.add("��� �����ѻ");
		senderList.add("��� ������������ ����� ����������������� ��������-������ ");
		senderList.add("��� ���� ���� ���������������");
		senderList.add("��� ���֖��� �̻");
		senderList.add("��� �����̻");
		senderList.add("��� ���� �����һ");
		senderList.add("��� ����� ����������������һ");
		senderList.add("��� ����� ������ͻ");
		senderList.add("��� ����� ����ѻ");
		senderList.add("��� ��� ����������");
		senderList.add("��� ��� ��. ��������� �.�. ���������");
		senderList.add("����");
		senderList.add("��� �����˻");
		senderList.add("��� ���� ���һ");
		senderList.add("�� ������ � ������ ���� ������ ��. �.�.��������� ");
		senderList.add("�������");
		senderList.add("���������");
		senderList.add("���� \"������-����������������� �������� ��������� ��������\"");
		senderList.add("���� \"��� ������ � ������� ��. �.�.���������\" ");
		senderList.add("���� \"������� ��������������� ���� \"�����\"");
		senderList.add("���� \"����������� \"����\"");
		senderList.add("���� ������������ ������������ ����� (���) ������ ���� ������ ��. �.�. ���������");
		senderList.add("���� ������  ��. �.�. ���������");
		senderList.add("���� �����������");
		senderList.add("���� �������");
		senderList.add("���� ���������������� ���� �������� ��. �.�. ������");
		senderList.add("���� ����������� ������-��������������� ���� �����");
		senderList.add("���� ������� ���������������� ����������� ��. �. �. ���������");
		senderList.add("���� �������-���������������� ����������� ����������");
		senderList.add("���� ��������");
		senderList.add("���� ����ϻ");
		senderList.add("���� �����Ȼ");
		senderList.add("���� �����-�����Ի");
		senderList.add("���� �����-�����Ի");
		senderList.add("���� ����� �������");
		senderList.add("���� ������� ��. ��������� �.�. �����");
		senderList.add("���� ����");
		senderList.add("���� ��� �������");
		senderList.add("���� ��� ���������� � ��������������� ����� ��������� �.�.��������");
		senderList.add("������ ��� �46 ���� ������������ ������� ������");
		senderList.add("��� \"������-������������� ����� �������-����������� ��������������\"");
		senderList.add("�� ���� ������  ��. �.�. ��������� - ���� �� ��. �.�. ���������");
		senderList.add("�������");
		senderList.add("�����");
		return senderList;
	}
	
	public IncomingMail() {
		super();
	}

	
	public static Comparator<IncomingMail> COMPARE_BY_IDMAIL = new Comparator<IncomingMail>() {
        public int compare(IncomingMail one, IncomingMail other) {
            //return one.idMail.compareTo(other.idMail);
            return Integer.compare(one.idMail, other.idMail);
        }
    };

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



	@Override
	public String toString() {
		return "IncomingMail [regDate=" + regDate + ", idMail=" + idMail + ", typeMail=" + typeMail + ", sender="
				+ sender + ", sendDate=" + sendDate + ", mailNum=" + mailNum + ", mailTheme=" + mailTheme
				+ ", secondFloorDate=" + secondFloorDate + ", filePathAndName=" + filePathAndName + "]";
	}
	
	

}
