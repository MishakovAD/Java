package IncomingMail;

import java.io.File;
import java.io.IOException;
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

@WebServlet("/incomingMail/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 20, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 20MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class IncomingMailServlet extends HttpServlet {	
	public static final String SAVE_DIRECTORY = "uploadDir";
	public static ArrayList<IncomingMail> incomingMailList = new ArrayList<>();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("delete")) {
			String url_str = request.getPathInfo();
			if (url_str != null) {
				String id = url_str.substring(1);
				incomingMailList.remove(id);
			}
		} else if (action != null && action.equals("update")) {
			String url_str = request.getPathInfo();
			if (url_str != null) {
				String id = url_str.substring(1);
				request.setAttribute("incomingMail", incomingMailList.get(Integer.parseInt(id)));
			}
		}
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
			String regDateParameter = request.getParameter("regDate");			
			int idMail; //regNum
			String typeMail = request.getParameter("typeMail");
			String sender = request.getParameter("sender");
			String sendDateParameter = request.getParameter("sendDate");			
			String mailNum = request.getParameter("mailNum");
			String mailTheme = request.getParameter("mailTheme");
			String secondFloorDateParameter = request.getParameter("secondFloorDate");
			
			System.out.println(regDateParameter);
			
			Date resultRegDate = null;
			Date resultSendDate = null;
			Date resultSecondFloorDate = null;
			String regDate = null;
			String sendDate = null;
			String secondFloorDate = null;
			SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
			try {
				resultRegDate = oldDateFormat.parse(regDateParameter);
				resultSendDate = oldDateFormat.parse(sendDateParameter);
				resultSecondFloorDate = oldDateFormat.parse(secondFloorDateParameter);
				
				regDate = newDateFormat.format(resultRegDate);
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
					String fileName = extractFileName(part);
					if (fileName != null && fileName.length() > 0) {
						String filePath = fullSavePath + File.separator + fileName;
						incMail.setFilePathAndName(filePath);
						System.out.println("Write attachment to file: " + filePath);
						// Write to file
						part.write(filePath);
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
			System.out.println(incMail);
		} //end if
	}

	private String extractFileName(Part part) {
		// form-data; name="file"; filename="C:\file1.zip"
		// form-data; name="file"; filename="C:\Note\file2.zip"
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				// C:\file1.zip
				// C:\Note\file2.zip
				String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
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
