package Authorization;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CheckerDB;
import UserProfile.UserProfile;

@WebServlet(urlPatterns = { "/signIn" })
public class SignInServlet extends HttpServlet {
	public static Map<String, UserProfile> userLoginMap = new HashMap<>();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		//super.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			if (CheckerDB.checkUser(email, password)) {
				request.getSession().setAttribute("userSignIn", userLoginMap.get(email));
				response.sendRedirect("/niikp/");
			} else {
				System.out.println("Incorrect email/password");
			}
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}