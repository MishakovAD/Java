package Ajax;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/ajax")
public class Ajax extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int workCount = Integer.parseInt(request.getParameter("work"));
		int userId = Integer.parseInt(request.getParameter("user"));
		//System.out.println(request.getParameter("work") + " Это GET запрос! " + request.getParameter("user"));

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
