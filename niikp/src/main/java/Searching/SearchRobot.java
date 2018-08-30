package Searching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import IncomingMail.IncomingMail;
import OutgoingMail.OutgoingMail;
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
						if (sender.contains(searchParameterForOnceSearcing)
								|| sender.equalsIgnoreCase(searchParameterForOnceSearcing)) {
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
						if (secondFloorNum.contains(searchParameterForOnceSearcing)
								|| secondFloorNum.equalsIgnoreCase(searchParameterForOnceSearcing)) {
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
						if (sender.contains(searchParameterForOnceSearcing)
								|| sender.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.equals("searchSendDate")) {
						if (sendDate.contains(searchParameterForOnceSearcing)
								|| sendDate.equalsIgnoreCase(searchParameterForOnceSearcing)) {
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
						if (mailTheme.contains(searchParameterForOnceSearcing)
								|| mailTheme.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.equals("searchSecondFloorNum")) {
						if (secondFloorNum.contains(searchParameterForOnceSearcing)
								|| secondFloorNum.equalsIgnoreCase(searchParameterForOnceSearcing)) {
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

	public static ArrayList<OutgoingMail> searchIntoOutgoingMailForOneField(Map<String, String> searchParameterMap,
			ArrayList<OutgoingMail> listWhereNeedSearch) {
		ArrayList<OutgoingMail> listOfSearch = new ArrayList<>();
		ArrayList<OutgoingMail> listForRemove = new ArrayList<>();

		Collections.reverse(listWhereNeedSearch);

		int count = 0;
		for (String searchParameter : searchParameterMap.keySet()) {
			String searchParameterForOnceSearcing = searchParameterMap.get(searchParameter);
			if (count == 0) {
				for (OutgoingMail iterList : listWhereNeedSearch) {
					String regDate = iterList.getRegDate();
					int idMail = iterList.getIdMail();
					String mailNum = iterList.getMailNum();
					String destination = iterList.getDestination(); // adresat
					String forWhom = iterList.getForWhom(); // ispolnitel'
					String sendDate = iterList.getSendDate();
					String mailTheme = iterList.getMailTheme();
					String executor = iterList.getExecutor(); // ispolnitel'
					String realExecutor = iterList.getRealExecutor(); // real ispolnitel'
					String incomingMailNum = iterList.getIncomingMailNum();
					String toWhomItIsPainted = iterList.getToWhomItIsPainted();
					int incomingMailId = iterList.getIncomingMailId();
					String note = iterList.getNote();
					String mailingNote = iterList.getMailingNote(); // primechanie rassbIlki
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

					if (searchParameter.equals("searchMailNum")) {
						if (mailNum.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							listOfSearch.add(iterList);
							// continue;
							break;
						}
					}

					if (searchParameter.equals("searchDestination")) {
						if (destination.contains(searchParameterForOnceSearcing)
								|| destination.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							listOfSearch.add(iterList);
							continue;
						}
					}

					if (searchParameter.equals("searchForWhom")) {
						if (forWhom.contains(searchParameterForOnceSearcing)) {
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

					if (searchParameter.equals("searchMailTheme")) {
						if (mailTheme.contains(searchParameterForOnceSearcing)) {
							listOfSearch.add(iterList);
							continue;
						}
					}

					if (searchParameter.equals("searchExecutor")) {
						if (executor.contains(searchParameterForOnceSearcing)
								|| executor.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							listOfSearch.add(iterList);
							continue;
						}
					}

					if (searchParameter.equals("searchRealExecutor")) {
						if (realExecutor.contains(searchParameterForOnceSearcing)
								|| realExecutor.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							listOfSearch.add(iterList);
							continue;
						}
					}

					if (searchParameter.contains("searchIncomingMailNum")) {
						if (incomingMailNum.contains(searchParameterForOnceSearcing)) {
							listOfSearch.add(iterList);
							continue;
						}
					}

					if (searchParameter.contains("searchToWhomItIsPainted")) {
						if (toWhomItIsPainted.contains(searchParameterForOnceSearcing)
								|| toWhomItIsPainted.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							listOfSearch.add(iterList);
							continue;
						}
					}

					if (searchParameter.contains("searchIncomingMailId")) {
						if (incomingMailId == Integer.parseInt(searchParameterForOnceSearcing)) {
							listOfSearch.add(iterList);
							continue;
						}
					}
				}
				count++;
			} else {
				for (OutgoingMail sortList : listOfSearch) {
					String regDate = sortList.getRegDate();
					int idMail = sortList.getIdMail();
					String mailNum = sortList.getMailNum();
					String destination = sortList.getDestination(); // adresat
					String forWhom = sortList.getForWhom(); // ispolnitel'
					String sendDate = sortList.getSendDate();
					String mailTheme = sortList.getMailTheme();
					String executor = sortList.getExecutor(); // ispolnitel'
					String realExecutor = sortList.getRealExecutor(); // real ispolnitel'
					String incomingMailNum = sortList.getIncomingMailNum();
					String toWhomItIsPainted = sortList.getToWhomItIsPainted();
					int incomingMailId = sortList.getIncomingMailId();
					String note = sortList.getNote();
					String mailingNote = sortList.getMailingNote(); // primechanie rassbIlki
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

					if (searchParameter.equals("searchMailNum")) {
						if (mailNum.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.equals("searchDestination")) {
						if (destination.contains(searchParameterForOnceSearcing)
								|| destination.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.equals("searchForWhom")) {
						if (forWhom.contains(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.equals("searchSendDate")) {
						if (sendDate.contains(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.equals("searchMailTheme")) {
						if (mailTheme.contains(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.equals("searchExecutor")) {
						if (executor.contains(searchParameterForOnceSearcing)
								|| executor.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.equals("searchRealExecutor")) {
						if (realExecutor.contains(searchParameterForOnceSearcing)
								|| realExecutor.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.contains("searchIncomingMailNum")) {
						if (incomingMailNum.contains(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.contains("searchToWhomItIsPainted")) {
						if (toWhomItIsPainted.contains(searchParameterForOnceSearcing)
								|| toWhomItIsPainted.equalsIgnoreCase(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}

					if (searchParameter.contains("searchIncomingMailId")) {
						if (incomingMailId == Integer.parseInt(searchParameterForOnceSearcing)) {
							continue;
						} else {
							listForRemove.add(sortList);
						}
					}
				}
			}

		}
		listOfSearch.removeAll(listForRemove);
		count = 0;
		return listOfSearch;
	}
}
