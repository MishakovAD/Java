package Work;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import UserProfile.UserProfile;
import Users.UsersList;

@WebServlet(urlPatterns = { "/workAdd" })
@MultipartConfig
public class WorkAddServlet extends HttpServlet {
	private String mailId = null;
	private String idMail = null;
	
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
		if (action != null ) {
			if (action.equals("submit")) {
				response.sendRedirect("/workList");
			}
		}
		
		
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
				
		int toUserId = 0;
		int observerId = 0;
		String filePathAndNameToWork = null;
		try {
			filePathAndNameToWork = IncomingMailDB.getFileIncomingMailToId(Integer.parseInt(idMail));
			System.out.println(filePathAndNameToWork);
		} catch (NumberFormatException | InstantiationException | IllegalAccessException | SQLException e1) {
			e1.printStackTrace();
		}
		
		String userNameSecondName = request.getParameter("user");
		String userName;
		String userSecondName;
		if (userNameSecondName != null) {
			int indexOfSpaseFromUser = userNameSecondName.indexOf(" ");
			userName = userNameSecondName.substring(0, indexOfSpaseFromUser);
			userSecondName = userNameSecondName.substring(indexOfSpaseFromUser+1);
		} else {
			userName = "Не";
			userSecondName = "заполнено";
		}
		
		
		String observer = request.getParameter("observer");
		String observerName;
		String observerSecondName; 
		System.out.println("observer" + observer);
		if(!observer.isEmpty()) {
			int indexOfSpaseFromObserver = observer.indexOf(" ");
			observerName = observer.substring(0, indexOfSpaseFromObserver);
			observerSecondName = observer.substring(indexOfSpaseFromObserver+1);
		} else {
			observerName = "Не";
			observerSecondName = "заполнено";
		}

		
		for (Map.Entry entry : UsersList.usersList.entrySet()) { 
			UserProfile user = (UserProfile) entry.getValue();
			if (user.getName().equalsIgnoreCase(userName) && user.getSecondName().equalsIgnoreCase(userSecondName)) {
				toUserId = (int) entry.getKey();
			}
		}
		for (Map.Entry entry : UsersList.usersList.entrySet()) { 
			UserProfile user = (UserProfile) entry.getValue();
			if (user.getName().equalsIgnoreCase(observerName) && user.getSecondName().equalsIgnoreCase(observerSecondName)) {
				observerId = (int) entry.getKey();
			}
		}

		
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String assignment = request.getParameter("assignment");
		
		work.setToUserId(toUserId);
		work.setObserverId(observerId);
		work.setFromUserId(fromUserId);
		work.setStartDate(startDate);
		work.setEndDate(endDate);
		work.setAssignment(assignment);
		work.setMailId(mailId);
		work.setFilePathAndNameToWork(filePathAndNameToWork);
		
		try {
			WorkDB.addWork(work);
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/workList");
	}
}
