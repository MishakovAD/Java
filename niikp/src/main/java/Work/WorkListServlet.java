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
		ArrayList<Work> workList = null;
		try {
			workList = WorkDB.getWorkList();
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("workList", workList);
		request.getRequestDispatcher("/workList.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
}
