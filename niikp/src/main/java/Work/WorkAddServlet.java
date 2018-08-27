package Work;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import DAO.WorkDB;
import DAO.WorkWithTemplateDB;
import DocumentPathTemplate.DocumentPathTemplate;
import ExcelApachePOI.IncomingMailExcel;
import Property.Property;
import Rules.Groups;
import Translit.Translit;
import UserProfile.UserProfile;
import Users.UsersList;

@WebServlet(urlPatterns = { "/workAdd" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 20, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 20MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class WorkAddServlet extends HttpServlet {
	private String mailId = null;
	private String idMail = null;
	public static final String SAVE_DIRECTORY = Property.getProperty("saveDirectory");
	public static final String SAVE_DIR = Property.getProperty("saveDir");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			UsersList.usersList = GetterDB.getUsersList();

		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}

		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("submit")) {
				response.sendRedirect("/workList?parameter=toMe");
			}
		}
		
		
		request.setAttribute("groupList", Groups.groupList);

		idMail = request.getParameter("id");
		String typeMail = request.getParameter("type");
		mailId = typeMail + "_" + idMail;
		request.setAttribute("usersList", (HashMap<Integer, UserProfile>) UsersList.usersList);
		request.getRequestDispatcher("/work.jsp").forward(request, response);

//		String str = request.getPathInfo();
//		if (str != null) {
//			String id = str.substring(1);
//			UserProfile userProfileInfo = UsersList.usersList.get(Integer.parseInt(id));
//			request.setAttribute("userProfileInfo", userProfileInfo);
//			request.getRequestDispatcher("/userProfileInfo.jsp").forward(request, response);
//		} else {
//			request.setAttribute("usersList", (HashMap<Integer, UserProfile>) UsersList.usersList);
//			request.getRequestDispatcher("/work.jsp").forward(request, response);
//		}
	}

	/*
	 * 
	 * Так как мы можем добавлять дело к корреспонденции, а перенаправление у нас
	 * идет только после успешной загрузкифайла то при проверки на маршрут,
	 * перенаправления не идет, поэтому и с маршрутом и без нужно добавить редирект
	 * иначе у нас не будет переадресации после добавления и придется это делать
	 * ручками
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Work work = new Work();
		UserProfile userSignIn = (UserProfile) request.getSession().getAttribute("userSignIn");
		int fromUserId = 0;
		if (userSignIn != null) {
			fromUserId = userSignIn.getUserId();
		}

		ArrayList<Integer> toUserId = new ArrayList<>();
		int observerId = 0;

		String action = request.getParameter("action");
		if ("submit".equals(action)) {
			StringBuilder users = new StringBuilder();
			for (int i = 1; ; i++) {
				if (request.getParameter("user" + i) == null){
					break;
				} else {
					users.append(request.getParameter("user" + i) + ";");
				}				
			}
			String userName;
			String userSecondName;
			if (users.length() > 0) {
				String[] usersListNameAndSecondname = users.toString().split(";");
				for (int i = 0; i < usersListNameAndSecondname.length; i++) {
					int indexOfSpaseFromUser = usersListNameAndSecondname[i].indexOf(" ");
					userName = usersListNameAndSecondname[i].substring(0, indexOfSpaseFromUser);
					userSecondName = usersListNameAndSecondname[i].substring(indexOfSpaseFromUser + 1);
					
					for (Map.Entry entry : UsersList.usersList.entrySet()) {
						UserProfile user = (UserProfile) entry.getValue();
						if (user.getName().equalsIgnoreCase(userName)
								&& user.getSecondName().equalsIgnoreCase(userSecondName)) {
							toUserId.add((int) entry.getKey());
						}
						
					}
				}
			} else {
				userName = "Не";
				userSecondName = "заполнено";
			}

			String observer = request.getParameter("observer");
			String observerName;
			String observerSecondName;
			if (!observer.isEmpty()) {
				int indexOfSpaseFromObserver = observer.indexOf(" ");
				observerName = observer.substring(0, indexOfSpaseFromObserver);
				observerSecondName = observer.substring(indexOfSpaseFromObserver + 1);
			} else {
				observerName = "Не";
				observerSecondName = "заполнено";
			}

			for (Map.Entry entry : UsersList.usersList.entrySet()) {
				UserProfile user = (UserProfile) entry.getValue();
				if (user.getName().equalsIgnoreCase(observerName)
						&& user.getSecondName().equalsIgnoreCase(observerSecondName)) {
					observerId = (int) entry.getKey();
				}
			}

			String startDate = request.getParameter("startDate");
			System.out.println(startDate);
			if (startDate.isEmpty()) {
				startDate = "2015-01-01";
			}
			String endDate = request.getParameter("endDate");
			if (endDate.isEmpty()) {
				endDate = "2015-01-01";
			}
			String assignment = request.getParameter("assignment");

			work.setObserverId(observerId);
			work.setFromUserId(fromUserId);
			work.setStartDate(startDate);
			work.setEndDate(endDate);
			work.setAssignment(assignment);
			work.setMailId(mailId);

			if (idMail != null) {
				try {
					String filePathAndNameToWork = IncomingMailDB.getFileIncomingMailToId(Integer.parseInt(idMail));
					work.setFilePathAndNameToWork(filePathAndNameToWork);
					
					IncomingMailExcel.writeResolutionIntoExcel(Integer.parseInt(idMail), work.getAssignment(), work.getStartDate(), work.getEndDate(), toUserId, WorkDB.getLastIndexWork()-1);
					
					response.sendRedirect(request.getContextPath() + "/incomingMailList?pageNumber=1");
				} catch (NumberFormatException | InstantiationException | IllegalAccessException | SQLException e1) {
					e1.printStackTrace();
				}
			} else {
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
						String fileName = extractFileName(part, WorkDB.getLastIndexWork());
						if (fileName != null && fileName.length() > 22) { // Почему то он думал, что тут несколько
																			// файлов, а из за того, что мы добавляем
																			// свой префикс, то файлнэйм никогда не
																			// пустой
							// и он занулял его
							String filePath = fullSavePath + File.separator + fileName;
							if (filePath != null) {
								work.setFilePathAndNameToWork(filePath);
								// Write to file
								part.write(filePath);
							}

						}
					}
					// Upload successfully!.
					response.sendRedirect(request.getContextPath() + "/workList?parameter=toMe");
					// response.sendRedirect("/niikp");
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("errorMessage", "Error: " + e.getMessage());
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/workAdd.jsp");
					dispatcher.forward(request, response);
				}
			}

			/*
			 * ТУТ БУДЕТ ЛОГИКА ДОБАВЛЕНИЯ И ОТСЛЕЖИВАНИЯ МАРШРУТА
			 */

			String template = request.getParameter("template");

			if (!(template == null)) {
				System.out.println("start template");
				work.setTemplate(template);
				try {
					work.setWorkTemplateId(WorkWithTemplateDB.getLastIndexWorkTemplateId());
				} catch (InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}
				for (Integer userId : DocumentPathTemplate.getTemplate(template)) {

				}
			} else {
				try {
					for (Integer userId : toUserId) {
						work.setToUserId(userId);
						WorkDB.addWork(work);
					}
				} catch (InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}
			}

		}

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
				String clientFileName = "Assignment_NPK-1_" + prefix + "_"
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
