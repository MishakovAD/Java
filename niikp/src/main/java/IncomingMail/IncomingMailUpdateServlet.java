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
import Property.Property;


@WebServlet("/incomingMail/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 20, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 20MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class IncomingMailUpdateServlet  extends HttpServlet {
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
				//IncomingMailServlet.incomingMailList.remove(Integer.parseInt(id));
				try {
					IncomingMailDB.deleteIncomingMail(Integer.parseInt(id));
					response.sendRedirect("/niikp/incomingMailList?pageNumber=1");
				} catch (NumberFormatException | InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}
				
				
			}
		} else if (action != null && action.equals("update")) {
			String url_str = request.getPathInfo();
			if (url_str != null) {
				id = url_str.substring(1);
				try {
					request.setAttribute("incomingMail", IncomingMailDB.getIncomingMailToId(Integer.parseInt(id)));
					request.setAttribute("typeMailList", IncomingMailServlet.typeMailList);
					request.setAttribute("senderMailList", IncomingMailServlet.senderMailList);
				} catch (NumberFormatException | InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}
				//incMail = IncomingMailServlet.incomingMailList.get(Integer.parseInt(id));
				request.getRequestDispatcher("/incomingMailUpdate.jsp").forward(request, response);
			}
		} else if (action == null) {
			request.getRequestDispatcher("/incomingMail.jsp").forward(request, response);
		}
		
		// super.doGet(request, response); //из-за этой строчки была ошибка sendError()
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		IncomingMail incMail = new IncomingMail();
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
			String sendDate = null;
			String secondFloorDate = null;
			SimpleDateFormat oldDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
			SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
			try {
				resultSendDate = oldDateFormat.parse(sendDateParameter);
				resultSecondFloorDate = oldDateFormat.parse(secondFloorDateParameter);
				
				sendDate = newDateFormat.format(resultSendDate);
				secondFloorDate = newDateFormat.format(resultSecondFloorDate);
				System.out.println();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			incMail.setIdMail(Integer.parseInt(id));
			if (typeMail != null) incMail.setTypeMail(typeMail);
			if (sender != null) incMail.setSender(sender);
			if (sendDate != null) incMail.setSendDate(sendDate);
			if (mailNum != null) incMail.setMailNum(mailNum);
			if (mailTheme != null) incMail.setMailTheme(mailTheme);
			if (secondFloorDate != null) incMail.setSecondFloorDate(secondFloorDate);
			if (secondFloorNum != null) incMail.setSecondFloorNum(secondFloorNum);
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
				response.sendRedirect(request.getContextPath() + "/incomingMailList?pageNumber=1");
				// response.sendRedirect("/niikp");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Error: " + e.getMessage());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/incomingMail.jsp");
				dispatcher.forward(request, response);
			}
			try {
				IncomingMailDB.updateIncomingMail(incMail);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
			//System.out.println(incMail);
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
