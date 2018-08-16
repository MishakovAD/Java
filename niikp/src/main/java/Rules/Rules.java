package Rules;

import Rules.*;
import UserProfile.UserProfile;

//�� ������ � CheckerDB ��� �������� ������ � ������ ���������� ��������� ����, ������� ��������� �����, ��� ��, ��� � setRole
public class Rules {
	private static boolean watchMail = false;
	private static boolean addMail = false;
	private static boolean watchWork = false;
	private static boolean addWork = false;
	
	public boolean isWatchMail() {
		return watchMail;
	}
	public boolean isAddMail() {
		return addMail;
	}
	public boolean isWatchWork() {
		return watchWork;
	}
	public boolean isAddWork() {
		return addWork;
	}
	
	public static boolean getLawToWatchMail (UserProfile user) {
		if (user.getRole().equalsIgnoreCase(Privileges.ADMINISTRATOR)) {
			return true;
		} else if (user.getRole().equalsIgnoreCase(Privileges.USER) && user.getUserGroup().equalsIgnoreCase(Groups.SECRETARY)) {
			return true;
		} else if (user.getRole().equalsIgnoreCase(Privileges.USER) && user.getUserGroup().equalsIgnoreCase(Groups.TESTER)) {
			return false;
		} else if (user.getRole().equalsIgnoreCase(Privileges.USER)) {
			return false;
		} 
		
		return false;
	}
	
	public static boolean getLawToAddMail (UserProfile user) {
		if (user.getRole().equalsIgnoreCase(Privileges.ADMINISTRATOR)) {
			return true;
		} else if (user.getRole().equalsIgnoreCase(Privileges.USER) && user.getUserGroup().equalsIgnoreCase(Groups.SECRETARY)) {
			return true;
		} else if (user.getRole().equalsIgnoreCase(Privileges.USER) && user.getUserGroup().equalsIgnoreCase(Groups.TESTER)) {
			return false;
		} else if (user.getRole().equalsIgnoreCase(Privileges.USER)) {
			return false;
		} 
		
		return false;
	}
	
	public static boolean getLawToWatchWork (UserProfile user) {
		if (user.getRole().equalsIgnoreCase(Privileges.ADMINISTRATOR)) {
			return true;
		} else if (user.getRole().equalsIgnoreCase(Privileges.USER) && user.getUserGroup().equalsIgnoreCase(Groups.SECRETARY)) {
			return true;
		} else if (user.getRole().equalsIgnoreCase(Privileges.USER) && user.getUserGroup().equalsIgnoreCase(Groups.TESTER)) {
			return false;
		} else if (user.getRole().equalsIgnoreCase(Privileges.USER)) {
			return false;
		} 
		
		return false;
	}
	
	public static boolean getLawToAddWork (UserProfile user) {
		//����� ��� �� ������� ������������� �� ����������, ����� ������ �����
		if (user.getRole().equalsIgnoreCase(Privileges.ADMINISTRATOR)) {
			return true;
		} else if (user.getRole().equalsIgnoreCase(Privileges.USER) && user.getUserGroup().equalsIgnoreCase(Groups.SECRETARY)) {
			return true;
		} else if (user.getRole().equalsIgnoreCase(Privileges.USER) && user.getUserGroup().equalsIgnoreCase(Groups.TESTER)) {
			return false;
		} else if (user.getRole().equalsIgnoreCase(Privileges.USER)) {
			return false;
		} 
		
		return false;
	}
	

}
