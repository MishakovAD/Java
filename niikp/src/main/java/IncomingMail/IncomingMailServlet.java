package IncomingMail;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.IncomingMailDB;
import ExcelApachePOI.IncomingMailExcel;
import Property.Property;
import Translit.Translit;
import UserProfile.UserProfile;

/*
 * DONT FORGOT ABOUT MAILID!!!!
 *  */

@WebServlet("/incomingMail")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 20, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 20MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class IncomingMailServlet extends HttpServlet {
	public static final String SAVE_DIRECTORY = Property.getProperty("saveDirectory");
	public static final String SAVE_DIR = Property.getProperty("saveDir");
	// public static final String SAVE_DIR = "C:/niikp/"; //server

	// для редактирования сделать мапу с ключом - айди письма, чтобы письмо, после
	// изменения, заменяло старое на новое
	// только нужнопродумать, что делать с датами и не измененными полями, как их
	// заново получать. (в особенности даты и файл)
	public static ArrayList<IncomingMail> incomingMailList = new ArrayList<>();
	public static ArrayList<String> typeMailList = IncomingMail.getTypeMailList();
	public static ArrayList<String> senderMailList = IncomingMail.getSenderList();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UserProfile user = (UserProfile) request.getSession().getAttribute("userSignIn");
			if (user != null && Rules.Rules.getLawToAddMail(user)) {
				request.setCharacterEncoding("UTF-8");

				request.setAttribute("typeMailList", typeMailList);
				request.setAttribute("senderMailList", senderMailList);
				request.getRequestDispatcher("/incomingMail.jsp").forward(request, response);
			} else {
				// вывести страницу с ошибкой вместо редиректа
				response.sendRedirect("/niikp/");
			}
		} catch (NullPointerException e) {
			// вывести страницу со свойствами юзера, или вообще отправить запрос к
			// администратору сразу
			response.sendRedirect("/niikp/");
		}

		// super.doGet(request, response); //из-за этой строчки была ошибка sendError()
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IncomingMail incMail = new IncomingMail();
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if ("submit".equals(action)) {
			String typeMail = request.getParameter("typeMail");
			String sender = request.getParameter("sender");
			String sendDateParameter = request.getParameter("sendDate");
			String mailNum = request.getParameter("mailNum");
			String mailTheme = request.getParameter("mailTheme");
			String secondFloorDateParameter = request.getParameter("secondFloorDate");
			String secondFloorNum = request.getParameter("secondFloorNum");
			
			Date resultSendDate = null;
			String sendDate = null;
			
			Date resultSecondFloorDate = null;			
			String secondFloorDate = null;
			
			SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
			try {
				if (sendDateParameter != null) {
					resultSendDate = oldDateFormat.parse(sendDateParameter);
					sendDate = newDateFormat.format(resultSendDate);
				} else {
					sendDate = null;
				}
				
				if (secondFloorDateParameter != null) {
					resultSecondFloorDate = oldDateFormat.parse(secondFloorDateParameter);				
					secondFloorDate = newDateFormat.format(resultSecondFloorDate);
				} else {
					secondFloorDate = null;
				}
				

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// IncomingMail incMail = new IncomingMail(regDate, typeMail, sender, sendDate,
			// mailNum, mailTheme, secondFloorDate);
			incMail.setTypeMail(typeMail);
			incMail.setSender(sender);
			incMail.setSendDate(sendDate);
			incMail.setMailNum(mailNum);
			incMail.setMailTheme(mailTheme);
			incMail.setSecondFloorDate(secondFloorDate);
			incMail.setSecondFloorNum(secondFloorNum);
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
					String fileName = extractFileName(part, IncomingMailDB.getLastIndexIncomingMail());
					if (fileName != null && fileName.length() > 20) {
						String filePath = fullSavePath + File.separator + fileName;
						if (filePath != null) {
							incMail.setFilePathAndName(filePath);
							// System.out.println("Write attachment to file: " + filePath);
							// Write to file
							part.write(filePath);
						}

					}
				}
				// Upload successfully!.
				response.sendRedirect(request.getContextPath() + "/incomingMailList?pageNumber=1");
				// response.sendRedirect("/niikp");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Error: " + e.getMessage());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/incomingMail.jsp");
				dispatcher.forward(request, response);
			}
			incomingMailList.add(incMail);
			try {
				IncomingMailDB.addIncomingMail(incMail);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
			/*
			 * 
			 * ВАЖНО!!!!!!!!!! ПОСТАВИТЬ СЮДА АЙДИ ПИСЬМА ИЗ БД!!!!!! И про дату
			 * регистрации. Это все делается автоматом и получается из БД
			 * 
			 * 
			 */
			try {
				IncomingMail incMailToExcel = IncomingMailDB
						.getIncomingMailToId(IncomingMailDB.getLastIndexIncomingMail() - 1);
				IncomingMailExcel.writeIntoExcel(incMailToExcel.getRegDate(), incMailToExcel.getIdMail(), typeMail,
						sender, sendDate, mailNum, mailTheme, secondFloorDate, incMail.getFilePathAndName());
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}

			// System.out.println(incMail);
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
				String clientFileName = "VhodKorr_NPK-1_" + prefix + "_"
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
