package InternalMail;

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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.GetterDB;
import DAO.InternalMailDB;
import ExcelApachePOI.InternalMailExcel;
import InternalMail.InternalMail;
import InternalMail.InternalMail;
import UserProfile.UserProfile;
import Users.UsersList;

@WebServlet("/internalMail")
public class InternalMailServlet extends HttpServlet {
	public static ArrayList<String> docTypeList = InternalMail.getDocTypeList();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
				request.setAttribute("docTypeList", docTypeList);
				request.getRequestDispatcher("/internalMail.jsp").forward(request, response);
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

		InternalMail internalMail = new InternalMail();
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if ("submit".equals(action)) {
			String docType = request.getParameter("docType");
			String numNPK = request.getParameter("numNPK");
			String destination = request.getParameter("destination");
			String additionalDestination = request.getParameter("additionalDestination");
			String docTheme = request.getParameter("docTheme");
			String executor = request.getParameter("executor");
			String note = request.getParameter("note");
			
			internalMail.setDocType(docType);
			internalMail.setNumNPK(numNPK);
			internalMail.setDestination(destination);
			internalMail.setAdditionalDestination(additionalDestination);
			internalMail.setDocTheme(docTheme);
			internalMail.setExecutor(executor);
			internalMail.setNote(note);
			
			try {
				InternalMailDB.addInternalMail(internalMail);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}

			
			try {
				InternalMail internalMailToExcel = InternalMailDB
						.getInternalMailToId(InternalMailDB.getLastIndexInternalMail() - 1);
				InternalMailExcel.writeIntoExcel(internalMailToExcel);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
			
			response.sendRedirect(request.getContextPath() + "/internalMailList?pageNumber=1");
		} 
	}

}
