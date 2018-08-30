package OutgoingMail;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
//import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.GetterDB;
import DAO.IncomingMailDB;
import DAO.OutgoingMailDB;
import ExcelApachePOI.IncomingMailExcel;
import ExcelApachePOI.OutgoingMailExcel;
import IncomingMail.IncomingMail;
import Property.Property;
import Translit.Translit;
import UserProfile.UserProfile;
import Users.UsersList;

/*
 * DONT FORGOT ABOUT MAILID!!!!
 *  */

@WebServlet("/outgoingMail")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 20, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 20MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class OutgoingMailServlet extends HttpServlet {
	public static final String SAVE_DIRECTORY = Property.getProperty("saveDirectory");
	public static final String SAVE_DIR = Property.getProperty("saveDir");

//	public static ArrayList<String> typeMailList = OutgoingMail.getTypeMailList();
	public static ArrayList<String> destinationMailList = OutgoingMail.getDestinationList();
	String incomingMailId;
	String typeMail;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		incomingMailId = request.getParameter("id");
		typeMail = request.getParameter("type");
		try {
			UserProfile user = (UserProfile) request.getSession().getAttribute("userSignIn");
			if (user != null && Rules.Rules.getLawToAddMail(user)) {
				request.setCharacterEncoding("UTF-8");
				try {
					UsersList.usersList = GetterDB.getUsersList();
					request.setAttribute("usersList", (HashMap<Integer, UserProfile>) UsersList.usersList);

				} catch (InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("destinationMailList", destinationMailList);
				request.getRequestDispatcher("/outgoingMail.jsp").forward(request, response);
			} else {
				// вывести страницу с ошибкой вместо редиректа
				response.sendRedirect("/niikp/");
			}
		} catch (NullPointerException e) {
			// вывести страницу со свойствами юзера, или вообще отправить запрос к
			// администратору сразу
			response.sendRedirect("/niikp/");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		OutgoingMail outMail = new OutgoingMail();
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if ("submit".equals(action)) {
			String mailNum = request.getParameter("mailNum");
			String destination = request.getParameter("destination");
			String forWhomNameAdnSecondName = request.getParameter("forWhom");
			String sendDateParameter = request.getParameter("sendDate");
			String mailTheme = request.getParameter("mailTheme");
			String executorNameAdnSecondName = request.getParameter("executor");
			String realExecutorNameAdnSecondName = request.getParameter("realExecutor");
			String incomingMailNum = request.getParameter("incomingMailNum");
			String note = request.getParameter("note");
			String mailingNote = request.getParameter("mailingNote");

			Date resultSendDate = null;
			String sendDate = null;

			SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
			try {
				if (sendDateParameter != null) {
					resultSendDate = oldDateFormat.parse(sendDateParameter);
					sendDate = newDateFormat.format(resultSendDate);
				} else {
					sendDate = null;
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
/**
 * 
 * It is the logic for get Id user from his name and secind name
 * 			
 */
//			String userNameforWhom = "Не";
//			String userSecondNameforWhom = "Заполнено";
//			if (forWhomNameAdnSecondName.length() > 0) {
//				int indexOfSpaceForWhomNameAdnSecondName = forWhomNameAdnSecondName.indexOf(" ");
//				userNameforWhom = forWhomNameAdnSecondName.substring(0, indexOfSpaceForWhomNameAdnSecondName);
//				userSecondNameforWhom = forWhomNameAdnSecondName.substring(indexOfSpaceForWhomNameAdnSecondName+1);
//			}
//			
//			String userNameexecutor = "Не";
//			String userSecondNameexecutor = "Заполнено";
//			if (executorNameAdnSecondName.length() > 0) {
//				int indexOfSpaceExecutorNameAdnSecondName = executorNameAdnSecondName.indexOf(" ");
//				userNameexecutor = executorNameAdnSecondName.substring(0, indexOfSpaceExecutorNameAdnSecondName);
//				userSecondNameexecutor = executorNameAdnSecondName.substring(indexOfSpaceExecutorNameAdnSecondName+1);
//			}
//			
//			String userNameRealExecutor = "Не";
//			String userSecondNameRealExecutor = "Заполнено";
//			if (realExecutorNameAdnSecondName.length() > 0) {
//				int indexOfSpaceRealExecutorNameAdnSecondName = realExecutorNameAdnSecondName.indexOf(" ");
//				userNameRealExecutor = realExecutorNameAdnSecondName.substring(0, indexOfSpaceRealExecutorNameAdnSecondName);
//				userSecondNameRealExecutor = realExecutorNameAdnSecondName.substring(indexOfSpaceRealExecutorNameAdnSecondName+1);
//			}
//			
//			
//			HashMap<Integer, UserProfile> usersList = new HashMap<>();
//			try {
//				usersList = GetterDB.getUsersList();
//			} catch (InstantiationException | IllegalAccessException | SQLException e1) {
//				e1.printStackTrace();
//			}
//			for (Map.Entry user : usersList.entrySet()) {
//				UserProfile userProfile = (UserProfile) user.getValue();
//				if (userProfile.getName().equalsIgnoreCase(userNameforWhom)
//						&& userProfile.getSecondName().equalsIgnoreCase(userSecondNameforWhom)) {
//					forWhomId = (int) userProfile.getUserId();
//				} 
//				if (userProfile.getName().equalsIgnoreCase(userNameexecutor)
//						&& userProfile.getSecondName().equalsIgnoreCase(userSecondNameexecutor)) {
//					executorId = (int) userProfile.getUserId();
//				} 
//				if (userProfile.getName().equalsIgnoreCase(userNameRealExecutor)
//						&& userProfile.getSecondName().equalsIgnoreCase(userSecondNameRealExecutor)) {
//					realExecutorId = (int) userProfile.getUserId();
//				}
//			}
			outMail.setForWhom(forWhomNameAdnSecondName);
			outMail.setExecutor(executorNameAdnSecondName);
			outMail.setRealExecutor(realExecutorNameAdnSecondName);
			
			outMail.setMailNum(mailNum);
			outMail.setDestination(destination);
			outMail.setSendDate(sendDate);
			outMail.setMailTheme(mailTheme);
			outMail.setIncomingMailNum(incomingMailNum);
			outMail.setNote(note);
			outMail.setMailingNote(mailingNote);
			
			
			StringBuilder usersToWhomItIsPainted = new StringBuilder();
//			StringBuilder usersToWhomItIsPaintedId = new StringBuilder();
			for (int i = 1; ; i++) {
				if (request.getParameter("toWhomItIsPainted" + i) == null){
					break;
				} else {
					usersToWhomItIsPainted.append(request.getParameter("toWhomItIsPainted" + i) + ";");
				}				
			}

//			String userName;
//			String userSecondName;
//			
//			if (usersToWhomItIsPainted.length() > 0) {
//				String[] usersListNameAndSecondname = usersToWhomItIsPainted.toString().split(";");
//				for (int i = 0; i < usersListNameAndSecondname.length; i++) {
//					int indexOfSpaseFromUser = usersListNameAndSecondname[i].indexOf(" ");
//					userName = usersListNameAndSecondname[i].substring(0, indexOfSpaseFromUser);
//					userSecondName = usersListNameAndSecondname[i].substring(indexOfSpaseFromUser + 1);
//					
//					for (Map.Entry entry : usersList.entrySet()) {
//						UserProfile user = (UserProfile) entry.getValue();
//						if (user.getName().equalsIgnoreCase(userName)
//								&& user.getSecondName().equalsIgnoreCase(userSecondName)) {
//							usersToWhomItIsPaintedId.append(user.getUserId() + ";");
//						}
//						
//					}
//				}
//
//			}
			
			outMail.setToWhomItIsPainted(usersToWhomItIsPainted.toString());
			
			
			//raspisat'
			if (incomingMailId != null && typeMail != null) {
				outMail.setIncomingMailId(Integer.parseInt(incomingMailId));
			}

			try {
				String appPath = request.getServletContext().getRealPath("");
				appPath = appPath.replace('\\', '/');
				String fullSavePath = null;
				fullSavePath = SAVE_DIR + SAVE_DIRECTORY;

				// Creates the save directory if it does not exists
				File fileSaveDir = new File(fullSavePath);
				if (!fileSaveDir.exists()) {
					fileSaveDir.mkdir();
				}

				// Part list (multi files).
				for (Part part : request.getParts()) {
					String fileName = extractFileName(part, OutgoingMailDB.getLastIndexOutgoingMail());
					if (fileName != null && fileName.length() > 20) {
						String filePath = fullSavePath + File.separator + fileName;
						if (filePath != null) {
							outMail.setFilePathAndName(filePath);
							// System.out.println("Write attachment to file: " + filePath);
							// Write to file
							part.write(filePath);
						}

					}
				}
				// Upload successfully!.
				response.sendRedirect(request.getContextPath() + "/outgoingMailList?pageNumber=1");
				// response.sendRedirect("/niikp");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Error: " + e.getMessage());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/incomingMail.jsp");
				dispatcher.forward(request, response);
			}
			try {
				OutgoingMailDB.addOutgoingMail(outMail);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}

			
			try {
				OutgoingMail outMailToExcel = OutgoingMailDB
						.getOutgoingMailToId(OutgoingMailDB.getLastIndexOutgoingMail() - 1);
				OutgoingMailExcel.writeIntoExcel(outMailToExcel);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}

			//System.out.println(outMail);
		} // end if
	}

	private String extractFileName(Part part, int prefix) {
		// form-data; name="file"; filename="C:\file1.zip"
		// form-data; name="file"; filename="C:\Note\file2.zip"
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				// C:\file1.zip
				// C:\Note\file2.zip
				String clientFileName = "IshodKorr_NPK-1_" + prefix + "_"
						+ s.substring(s.indexOf("=") + 2, s.length() - 1);
				clientFileName = Translit.cyr2lat(clientFileName);
				clientFileName = clientFileName.replace("\\", "/");
				int i = clientFileName.lastIndexOf('/');
				// file1.zip
				// file2.zip
				return clientFileName.substring(i + 1);
			}
		}
		return null;
	}

}
