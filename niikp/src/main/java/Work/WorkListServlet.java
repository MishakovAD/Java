package Work;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.WorkDB;

@WebServlet(urlPatterns = { "/workList" })
public class WorkListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("parameter");
		ArrayList<Work> workList = null;
		ArrayList<Work> workListForCo_executor = null;
		try {
			workList = WorkDB.getWorkList();
			workListForCo_executor = WorkDB.getWorkListForCo_executor();
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		} 
		
		if (parameter.equalsIgnoreCase("toMe")) {
			request.setAttribute("workList", workList);
			request.setAttribute("workListForCo_executor", workListForCo_executor);
			request.getRequestDispatcher("/workListToMe.jsp").forward(request, response);
		} else if (parameter.equalsIgnoreCase("fromMe")) {
			request.setAttribute("workList", workList);
			request.getRequestDispatcher("/workListFromMe.jsp").forward(request, response);
		} else if (parameter.equalsIgnoreCase("Arhiv")) {
			request.setAttribute("workList", workList);
			request.getRequestDispatcher("/workListArhiv.jsp").forward(request, response);
		} else {
			response.sendRedirect("/niikp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
}
