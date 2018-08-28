package Searching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import IncomingMail.IncomingMail;
import UserProfile.UserProfile;
import Users.UsersList;

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

	public static ArrayList<IncomingMail> searchIntoIncomingMailForOneField(Map<String, String> searchParameterMap,
			ArrayList<IncomingMail> listWhereNeedSearch) {
		ArrayList<IncomingMail> listOfSearch = new ArrayList<>();
		ArrayList<IncomingMail> listForRemove = new ArrayList<>();

		Collections.reverse(listWhereNeedSearch);

		int count = 0;
		for (String searchParameter : searchParameterMap.keySet()) {
			String searchParameterForOnceSearcing = searchParameterMap.get(searchParameter);
			if (count == 0) {
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

					if (searchParameter.equals("searchRegDate")) {
						if (regDate.contains(searchParameterForOnceSearcing)) {
							listOfSearch.add(iterList);
							continue;
						}
					}

					if (searchParameter.equals("searchIdMail")) {
						if (String.valueOf(idMail).equalsIgnoreCase(searchParameterForOnceSearcing)) {
							listOfSearch.add(iterList);
							// continue;
							break;
						}
					}

					if (searchParameter.equals("searchMailType")) {
						if (typeMail.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							listOfSearch.add(iterList);
							continue;
						}
					}

					if (searchParameter.equals("searchSender")) {
						if (sender.contains(searchParameterForOnceSearcing) || sender.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							listOfSearch.add(iterList);
							continue;
						}
					}

					if (searchParameter.equals("searchSendDate")) {
						if (sendDate.contains(searchParameterForOnceSearcing)) {
							listOfSearch.add(iterList);
							continue;
						}
					}

					if (searchParameter.equals("searchMailNum")) {
						if (mailNum.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							listOfSearch.add(iterList);
							continue;
						}
					}

					if (searchParameter.equals("searchMailTheme")) {
						if (mailTheme.contains(searchParameterForOnceSearcing)) {
							listOfSearch.add(iterList);
							continue;
						}
					}

					if (searchParameter.equals("searchSecondFloorNum")) {
						if (secondFloorNum.contains(searchParameterForOnceSearcing) || secondFloorNum.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							listOfSearch.add(iterList);
							continue;
						}
					}

					if (searchParameter.contains("searchSecondFloorDate")) {
						if (secondFloorDate.contains(searchParameterForOnceSearcing)) {
							listOfSearch.add(iterList);
							continue;
						}
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
				count++;
			} else {
				for (IncomingMail sortList : listOfSearch) {
					String regDate = sortList.getRegDate();
					int idMail = sortList.getIdMail();
					String typeMail = sortList.getTypeMail();
					String sender = sortList.getSender();
					String sendDate = sortList.getSendDate();
					String mailNum = sortList.getMailNum();
					String mailTheme = sortList.getMailTheme();
					String secondFloorDate = sortList.getSecondFloorDate();
					String secondFloorNum = sortList.getSecondFloorNum();
					String filePathAndName = sortList.getFilePathAndName();

					if (searchParameter.equals("searchRegDate")) {
						if (regDate.contains(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.equals("searchIdMail")) {
						if (String.valueOf(idMail).equalsIgnoreCase(searchParameterForOnceSearcing)) {
							// continue;
							break;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.equals("searchMailType")) {
						if (typeMail.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.equals("searchSender")) {
						if (sender.contains(searchParameterForOnceSearcing) || sender.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.equals("searchSendDate")) {
						if (sendDate.contains(searchParameterForOnceSearcing) || sendDate.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.equals("searchMailNum")) {
						if (mailNum.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.equals("searchMailTheme")) {
						if (mailTheme.contains(searchParameterForOnceSearcing) || mailTheme.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.equals("searchSecondFloorNum")) {
						if (secondFloorNum.contains(searchParameterForOnceSearcing) || secondFloorNum.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.contains("searchSecondFloorDate")) {
						if (secondFloorDate.contains(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
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
			}
			

		}
		listOfSearch.removeAll(listForRemove);
		count = 0;
		return listOfSearch;
	}
}
