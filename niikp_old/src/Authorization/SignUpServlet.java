package Authorization;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CheckerDB;
import DAO.DataBase;
import UserProfile.UserProfile;

@WebServlet(urlPatterns = { "/registration", "/reg" })
public class SignUpServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Enter doGet SignUpServlet");
		request.getRequestDispatcher("/registration.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Enter doPost SignUpServlet");
		UserProfile userProfile;

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("submit".equals(action)) {
			try {
				if (!CheckerDB.checkNewUser(request.getParameter("email"))) {
					userProfile = new UserProfile();
					// userProfile.setUserId(Integer.parseInt(request.getParameter("userId")));
					// //�������� �� �� � �������������,���� � ���� ����
					userProfile.setName(request.getParameter("name"));
					userProfile.setSecondName(request.getParameter("secondName"));
					userProfile.setBirthday(request.getParameter("birthday"));
					userProfile.setEmail(request.getParameter("email"));
					userProfile.setPassword(request.getParameter("password"));
					DataBase.regNewUser(userProfile);
					//Need do getter for userId from BD. Because userId dont set for userProfile 
					//And in Attribute we dont have this parametr
//					SignInServlet.userLoginMap.put(userProfile.getEmail(), userProfile);
//					request.getSession().setAttribute("userSignIn", userProfile);
//					request.setAttribute("userSignIn", userProfile);
					response.sendRedirect("/niikp/");
				} else {
					System.out.println("������������ � ����� email ��� ���������������!");
					response.sendRedirect("/niikp/");
				}
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
