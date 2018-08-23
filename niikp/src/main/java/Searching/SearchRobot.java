package Searching;

import java.util.ArrayList;

import IncomingMail.IncomingMail;

public class SearchRobot {
	public static ArrayList<IncomingMail> searchIntoIncomingMail(String searchParameter,
			ArrayList<IncomingMail> listWhereNeedSearch) {
		ArrayList<IncomingMail> listOfSearch = new ArrayList<>();

		for (IncomingMail iterList : listWhereNeedSearch) {
			String regDate = iterList.getRegDate();
			int idMail = iterList.getIdMail();
			String typeMail = iterList.getTypeMail();
			String sender = iterList.getSender();
			String sendDate = iterList.getSendDate();
			String mailNum = iterList.getMailNum();
			String mailTheme = iterList.getMailTheme();
			String secondFloorDate = iterList.getSecondFloorDate();
			String secondFloorNum = iterList.getSecondFloorNum();
			String filePathAndName = iterList.getFilePathAndName();

			if (regDate.contains(searchParameter)) {
				listOfSearch.add(iterList);
				continue;
			}
			if (String.valueOf(idMail).contains(searchParameter)) {
				listOfSearch.add(iterList);
				continue;
			}
			if (typeMail.contains(searchParameter)) {
				listOfSearch.add(iterList);
				continue;
			}
			if (sender.contains(searchParameter)) {
				listOfSearch.add(iterList);
				continue;
			}
			if (sendDate.contains(searchParameter)) {
				listOfSearch.add(iterList);
				continue;
			}
			if (mailNum.contains(searchParameter)) {
				listOfSearch.add(iterList);
				continue;
			}
			if (mailTheme.contains(searchParameter)) {
				listOfSearch.add(iterList);
				continue;
			}
			if (secondFloorDate.contains(searchParameter)) {
				listOfSearch.add(iterList);
				continue;
			}
			if (secondFloorNum.contains(searchParameter)) {
				listOfSearch.add(iterList);
				continue;
			}
			if (filePathAndName.contains(searchParameter)) {
				listOfSearch.add(iterList);
				continue;
			}

			regDate = null;
			idMail = 0;
			typeMail = null;
			sender = null;
			sendDate = null;
			mailNum = null;
			mailTheme = null;
			secondFloorDate = null;
			secondFloorNum = null;
			filePathAndName = null;
		}

		return listOfSearch;
	}

	public static ArrayList<IncomingMail> searchIntoIncomingMailForOneField(String searchParameterForOnceSearcing,
			ArrayList<IncomingMail> listWhereNeedSearch) {
		ArrayList<IncomingMail> listOfSearch = new ArrayList<>();

		for (IncomingMail iterList : listWhereNeedSearch) {
			String regDate = iterList.getRegDate();
			int idMail = iterList.getIdMail();
			String typeMail = iterList.getTypeMail();
			String sender = iterList.getSender();
			String sendDate = iterList.getSendDate();
			String mailNum = iterList.getMailNum();
			String mailTheme = iterList.getMailTheme();
			String secondFloorDate = iterList.getSecondFloorDate();
			String secondFloorNum = iterList.getSecondFloorNum();
			String filePathAndName = iterList.getFilePathAndName();

			if (regDate.contains(searchParameterForOnceSearcing)) {
				listOfSearch.add(iterList);
				continue;
			}
			if (String.valueOf(idMail).equalsIgnoreCase(searchParameterForOnceSearcing)) {
				listOfSearch.add(iterList);
				//continue;
				break;
			}
			if (typeMail.equalsIgnoreCase(searchParameterForOnceSearcing)) {
				listOfSearch.add(iterList);
				continue;
			}
			if (sender.contains(searchParameterForOnceSearcing)) {
				listOfSearch.add(iterList);
				continue;
			}
			if (sendDate.contains(searchParameterForOnceSearcing)) {
				listOfSearch.add(iterList);
				continue;
			}
			if (mailNum.equalsIgnoreCase(searchParameterForOnceSearcing)) {
				listOfSearch.add(iterList);
				continue;
			}
			if (mailTheme.contains(searchParameterForOnceSearcing)) {
				listOfSearch.add(iterList);
				continue;
			}
			if (secondFloorDate.contains(searchParameterForOnceSearcing)) {
				listOfSearch.add(iterList);
				continue;
			}
			if (secondFloorNum.contains(searchParameterForOnceSearcing)) {
				listOfSearch.add(iterList);
				continue;
			}
			if (filePathAndName.contains(searchParameterForOnceSearcing)) {
				listOfSearch.add(iterList);
				continue;
			}

			regDate = null;
			idMail = 0;
			typeMail = null;
			sender = null;
			sendDate = null;
			mailNum = null;
			mailTheme = null;
			secondFloorDate = null;
			secondFloorNum = null;
			filePathAndName = null;
		}
		
		return listOfSearch;
	}
}
