package IncomingMail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.IncomingMailDB;
import UserProfile.UserProfile;

@WebServlet("/incomingMailList")
public class IncomingMailListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserProfile user = (UserProfile) request.getSession().getAttribute("userSignIn");
		try {
			if (user != null && Rules.Rules.getLawToWatchMail(user)) {
				ArrayList<IncomingMail> list = null;
				try {
					list = IncomingMailDB.getIncomingMail();
				} catch (InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("incomingMailList", list);
				request.getRequestDispatcher("/incomingMailList.jsp").forward(request, response);
			} else {
				//вывести страницу с ошибкой вместо редиректа
				response.sendRedirect("/niikp/");
			}
		} catch (NullPointerException e) {
			//вывести страницу со свойствами юзера, или вообще отправить запрос к администратору сразу
			response.sendRedirect("/niikp/");
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}
}
