package Users;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Authorization.SignInServlet;
import UserProfile.UserProfile;

@WebServlet(urlPatterns = { "/userProfile" })
public class UserInfoUpdateServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("Enter doGet UserInfoUpdateServlet");

		String action = request.getParameter("action");
		switch (action == null ? "info" : action) {
		case "update":
			request.setAttribute("userProfileUpdate", (UserProfile) request.getSession().getAttribute("userSignIn"));
			request.getRequestDispatcher("/userInfoUpdate.jsp").forward(request, response);
			break;
		case "info":
		default:
			request.setAttribute("userProfileInfo", (UserProfile) request.getSession().getAttribute("userSignIn"));
			request.getRequestDispatcher("/userProfileInfo.jsp").forward(request, response);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ВАЖНО!!! мы должны не создавать новый объект,а получать и менять его
		//Или создавать,но перезаписывать
		UserProfile userProfile = null;	
		userProfile = (UserProfile) request.getSession().getAttribute("userSignIn");
		int userId = SignInServlet.userLoginMap.get(userProfile.getEmail()).getUserId();		

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("submit".equals(action)) {
			SignInServlet.userLoginMap.remove(request.getParameter("email"));
			request.getSession().removeAttribute("userSignIn");
			userProfile = new UserProfile();
			// //Получаем из БД и автоинкремент,либо в базе само
			userProfile.setUserId(userId);
			userProfile.setName(request.getParameter("name"));
			userProfile.setSecondName(request.getParameter("secondName"));
			userProfile.setBirthday(request.getParameter("birthday"));
			userProfile.setEmail(request.getParameter("email"));
			userProfile.setPassword(request.getParameter("password"));
			SignInServlet.userLoginMap.put(request.getParameter("email"), userProfile);
			//request.setAttribute("userSignIn", userProfile);
			request.getSession().setAttribute("userSignIn", userProfile);
			//Add to dataBase!!!!!!!!!
			//Данные на странице не меняются, так как изменения не вносятся в БД
			//А все данные берутся из БД в классе UsersList методом GetterDB.getUsersList();
			//Все будет работать, если вносить изменения в БД сразу
			response.sendRedirect("/niikp/users/" + userId);
		}

	}

}
