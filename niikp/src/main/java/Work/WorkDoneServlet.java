package Work;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.WorkDB;

@WebServlet(urlPatterns = { "/workDone" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 20, // 2MB
maxFileSize = 1024 * 1024 * 10, // 20MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class WorkDoneServlet extends HttpServlet {
	String action;
	String workId;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action = request.getParameter("action");
		workId = request.getParameter("workId");
		request.getRequestDispatcher("/workDone.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if (action.equals("done")) {
			String report = request.getParameter("report");
			try {
				WorkDB.doneWorkToUser(report, Integer.parseInt(workId));
			} catch (NumberFormatException | InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("/niikp/workList");
		}
		
		

	}
}
