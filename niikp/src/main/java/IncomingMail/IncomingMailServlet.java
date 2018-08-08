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

/*
 * DONT FORGOT ABOUT MAILID!!!!
 *  */

@WebServlet("/incomingMail")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 20, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 20MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class IncomingMailServlet extends HttpServlet {	
	public static final String SAVE_DIRECTORY = "uploadDir";
	//для редактирования сделать мапу с ключом - айди письма, чтобы письмо, после изменения, заменяло старое на новое
	//только нужнопродумать, что делать с датами и не измененными полями, как их заново получать. (в особенности даты и файл)
	public static ArrayList<IncomingMail> incomingMailList = new ArrayList<>();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			request.getRequestDispatcher("/incomingMail.jsp").forward(request, response);		
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
			Date resultSecondFloorDate = null;
			String regDate = null;
			String sendDate = null;
			String secondFloorDate = null;
			SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
			try {
				resultSendDate = oldDateFormat.parse(sendDateParameter);
				resultSecondFloorDate = oldDateFormat.parse(secondFloorDateParameter);
				
				sendDate = newDateFormat.format(resultSendDate);
				secondFloorDate = newDateFormat.format(resultSecondFloorDate);
				System.out.println();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//IncomingMail incMail = new IncomingMail(regDate, typeMail, sender, sendDate, mailNum, mailTheme, secondFloorDate);
			incMail.setRegDate(regDate);
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
	            fullSavePath = "E:/JavaProjectDocs/" + SAVE_DIRECTORY;

				// Creates the save directory if it does not exists
				File fileSaveDir = new File(fullSavePath);
				if (!fileSaveDir.exists()) {
					fileSaveDir.mkdir();
				}

				// Part list (multi files).
				for (Part part : request.getParts()) {
					String fileName = extractFileName(part, IncomingMailDB.getLastIndexIncomingMail());
					if (fileName != null && fileName.length() > 0) {
						String filePath = fullSavePath + File.separator + fileName;
						if (filePath != null) {
							incMail.setFilePathAndName(filePath);
							System.out.println("Write attachment to file: " + filePath);
							// Write to file
							part.write(filePath);
						}
						
					}
				}
				// Upload successfully!.
				response.sendRedirect(request.getContextPath() + "/incomingMailList");
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
			 * ВАЖНО!!!!!!!!!!
			 * ПОСТАВИТЬ СЮДА АЙДИ ПИСЬМА ИЗ БД!!!!!!
			 * И про дату регистрации. Это все делается автоматом и получается из БД
			 * 
			 * 
			 * */
			IncomingMailExcel.writeIntoExcel("0", 0, typeMail, sender, 
					sendDate, mailNum, mailTheme, secondFloorDate, incMail.getFilePathAndName());
			
			System.out.println(incMail);
		} //end if
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
				String clientFileName = "VhodKorr_NPK-1_" + prefix + "_" + s.substring(s.indexOf("=") + 2, s.length() - 1);
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
