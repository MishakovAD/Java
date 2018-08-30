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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.GetterDB;
import DAO.OutgoingMailDB;
import OutgoingMail.OutgoingMail;
import OutgoingMail.OutgoingMailServlet;
import MainPage.MainPageServlet;
import Property.Property;
import UserProfile.UserProfile;
import Users.UsersList;

@WebServlet("/outgoingMail/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 20, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 20MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class OutgoingMailUpdateServlet extends HttpServlet {

	public static final String SAVE_DIRECTORY = Property.getProperty("saveDirectory");
	public static final String SAVE_DIR = Property.getProperty("saveDir");
	String id;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("delete")) {
			String url_str = request.getPathInfo();
			if (url_str != null) {
				String id = url_str.substring(1);
				// OutgoingMailServlet.outgoingMailList.remove(Integer.parseInt(id));
				try {
					OutgoingMailDB.deleteOutgoingMail(Integer.parseInt(id));
					
					response.sendRedirect("/niikp/outgoingMailList?pageNumber=1");
				} catch (NumberFormatException | InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}

			}
		} else if (action != null && action.equals("update")) {
			String url_str = request.getPathInfo();
			if (url_str != null) {
				id = url_str.substring(1);
				try {
					request.setAttribute("outgoingMail", OutgoingMailDB.getOutgoingMailToId(Integer.parseInt(id)));
					request.setAttribute("destinationMailList", OutgoingMailServlet.destinationMailList);
					try {
						UsersList.usersList = GetterDB.getUsersList();
						request.setAttribute("usersList", (HashMap<Integer, UserProfile>) UsersList.usersList);

					} catch (InstantiationException | IllegalAccessException | SQLException e) {
						e.printStackTrace();
					}
				} catch (NumberFormatException | InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}
				// outMail = OutgoingMailServlet.outgoingMailList.get(Integer.parseInt(id));
				request.getRequestDispatcher("/outgoingMailUpdate.jsp").forward(request, response);
			}
		} else if (action == null) {
			request.getRequestDispatcher("/outgoingMail.jsp").forward(request, response);
		}

		// super.doGet(request, response); //из-за этой строчки была ошибка sendError()
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		OutgoingMail outMail = new OutgoingMail();
		String action = request.getParameter("action");
		if ("submit".equals(action)) {
			String mailNum = request.getParameter("mailNum");
			String destination = request.getParameter("destination");
			String forWhomNameAdnSecondName = request.getParameter("forWhom");
			String sendDateParameter = request.getParameter("sendDate");
			String mailTheme = request.getParameter("mailTheme");
			String executorNameAdnSecondName = request.getParameter("executor");
			String realExecutorNameAdnSecondName = request.getParameter("realExecutor");
			String outomingMailNum = request.getParameter("outomingMailNum");
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
			
			outMail.setIdMail(Integer.parseInt(id));
			if (forWhomNameAdnSecondName !=null) outMail.setForWhom(forWhomNameAdnSecondName);
			if (executorNameAdnSecondName !=null)outMail.setExecutor(executorNameAdnSecondName);
			if (realExecutorNameAdnSecondName !=null)outMail.setRealExecutor(realExecutorNameAdnSecondName);			
			if (mailNum !=null)outMail.setMailNum(mailNum);
			if (destination !=null)outMail.setDestination(destination);
			if (sendDate !=null)outMail.setSendDate(sendDate);
			if (mailTheme !=null)outMail.setMailTheme(mailTheme);
			if (note !=null)outMail.setNote(note);
			if (mailingNote !=null)outMail.setMailingNote(mailingNote);
			
			StringBuilder usersToWhomItIsPainted = new StringBuilder();
//			StringBuilder usersToWhomItIsPaintedId = new StringBuilder();
			for (int i = 1; ; i++) {
				if (request.getParameter("toWhomItIsPainted" + i) == null){
					break;
				} else {
					usersToWhomItIsPainted.append(request.getParameter("toWhomItIsPainted" + i) + ";");
				}				
			}
			if (usersToWhomItIsPainted.length()>2)outMail.setToWhomItIsPainted(usersToWhomItIsPainted.toString());
			
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
					if (fileName != null && fileName.length() > 0) {
						String filePath = fullSavePath + File.separator + fileName;
						if (filePath != null) {
							outMail.setFilePathAndName(filePath);
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
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/outgoingMail.jsp");
				dispatcher.forward(request, response);
			}
			try {
				OutgoingMailDB.updateOutgoingMail(outMail);
				MainPageServlet.listOutgoingMail = OutgoingMailDB.getOutgoingMail();
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
			// System.out.println(outMail);
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
