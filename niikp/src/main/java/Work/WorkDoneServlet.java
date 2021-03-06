package Work;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.IncomingMailDB;
import DAO.WorkDB;
import DocumentPathTemplate.*;
import ExcelApachePOI.IncomingMailExcel;
import IncomingMail.IncomingMail;
import Property.Property;
import Translit.Translit;

@WebServlet(urlPatterns = { "/workDone" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 20, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 20MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class WorkDoneServlet extends HttpServlet {
	String action;
	String workId;
	String Co_executor;
	Work workForComment;
	public static final String SAVE_DIRECTORY = Property.getProperty("saveDirectory");
	public static final String SAVE_DIR = Property.getProperty("saveDir");

	// public static final String SAVE_DIR = "C:/niikp/"; //server
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		action = request.getParameter("action");
		workId = request.getParameter("workId");
		Co_executor = request.getParameter("Co_executor");
		if (action.equals("accept")) {
			try {
				WorkDB.acceptWorkToUser(Integer.parseInt(workId));
			} catch (NumberFormatException | InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("/niikp/workList?parameter=fromMe");
			return;
		} else if (action.equals("refuse")) {
			try {
				WorkDB.refuseWorkToUser(Integer.parseInt(workId));
			} catch (NumberFormatException | InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("/niikp/workList?parameter=fromMe");
			return;
		} else if (action.equals("comment")) {
			try {
				workForComment = WorkDB.getWorkToWorkId(Integer.parseInt(workId));
				request.setAttribute("workForComment", workForComment);
			} catch (NumberFormatException | InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/workEditAndAddComment.jsp").forward(request, response);
			return;
		} 
		request.getRequestDispatcher("/workDone.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if (action.equals("done")) {
			String reportFilePathAndNameToWork = null;
			String report = request.getParameter("report");

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
					String fileName = extractFileName(part, Integer.parseInt(workId));
					if (fileName != null && fileName.length() > 0) {
						String filePath = fullSavePath + File.separator + fileName;
						if (filePath != null) {
							reportFilePathAndNameToWork = filePath;
							System.out.println("Write attachment to file: " + filePath);
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
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/workDone.jsp");
				dispatcher.forward(request, response);
			}
			if (Co_executor.equals("false")) {
				try {
					WorkDB.doneWorkToObserver(report, Integer.parseInt(workId), reportFilePathAndNameToWork);
					
					Work work = WorkDB.getWorkToWorkId(Integer.parseInt(workId));
					ArrayList<Integer> workForCo_executorIdArray = new ArrayList<>();
					for (Work w : WorkDB.getWorkForCo_executorToAssigment(work.getAssignment())) {
						workForCo_executorIdArray.add(w.getWorkId());
					}
					for (int id : workForCo_executorIdArray) {
						WorkDB.doneWorkToCo_executorFromObserver(id);
					}
					
					
				} catch (NumberFormatException | InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}
			} else if (Co_executor.equals("true")) {
				try {
					WorkDB.doneWorkToObserver(report, Integer.parseInt(workId), reportFilePathAndNameToWork);
				} catch (NumberFormatException | InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}
			}
			

			// ���� �������� 
			/*
			 * 
			 * ������ ���� ��������.
			 * 
			 */
//			try {
//				// ���������, ��� �������� ���������, ���������� �� ������ �����������
//				// ���������, � ���� ��, �� ��������� ���������� ��������������
//				if (DocumentPathTemplate.addTemplate()
//						.contains(WorkDB.getWorkToWorkId(Integer.parseInt(workId)).getToUserId())) {
//					int indexOfIdUserWhoDoneWork = DocumentPathTemplate.addTemplate()
//							.indexOf(WorkDB.getWorkToWorkId(Integer.parseInt(workId)).getToUserId());
//					int nextIndexOfNextUserId = indexOfIdUserWhoDoneWork + 1;
//					if (nextIndexOfNextUserId > DocumentPathTemplate.addTemplate().size()) {
//						System.out.println("������� �������!");
//					} else {
//						// �������� ��������� id
//						int nextUserId = DocumentPathTemplate.addTemplate().get(nextIndexOfNextUserId);
//						Work workForNextUser = new Work();
//						workForNextUser = WorkDB.getWorkToWorkId(Integer.parseInt(workId));
//						workForNextUser.setReport(null);
//						workForNextUser.setComplete(false);
//						workForNextUser.setToUserId(nextUserId);
//						WorkDB.addWork(workForNextUser);
//					}
//				}
//			} catch (NumberFormatException | InstantiationException | IllegalAccessException | SQLException e) {
//				e.printStackTrace();
//			}
		} else if (action.equals("accept")) {
			System.out.println("� ������ ������ (WorkDoneServlet-doPost-if (accept) ����� ��� ����"
					+ "����� ����� ���� ��������� ����� ���� �����������, � �� ������"
					+ "������ ����. � ���� ���� ������ ����� �����������"
					+ "�� � ����� ��� ����� ����� ������ �������� �� accept.");
			//response.sendRedirect("/niikp/workList");
		} else if (action.equals("refuse")) {

		} else if (action.equals("comment")) {
			String comment = request.getParameter("comment");
			String assignment = request.getParameter("assignment");
			String assigmentSource = request.getParameter("assigmentSource");
			try {
				WorkDB.addComment(Integer.parseInt(workId), comment, assignment, assigmentSource);
				WorkDB.addCommentForCo_executor(workForComment.getAssignment(), comment, assignment, assigmentSource);
			} catch (NumberFormatException | InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
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
				String clientFileName = "reportFile_NPK-1_" + prefix + "_"
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
