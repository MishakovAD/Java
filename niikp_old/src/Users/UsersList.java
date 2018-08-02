package Users;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DataBase;
import DAO.GetterDB;
import UserProfile.UserProfile;

@WebServlet(urlPatterns = { "/users/*" })
public class UsersList extends HttpServlet {

	public static HashMap<Integer, UserProfile> usersList = new HashMap<>();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			usersList = GetterDB.getUsersList();

		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}

		String str = request.getPathInfo();
		System.out.println(str);
		if (str != null) {
			String id = str.substring(1); 
			//Âíèìàòåëüíåå ñ òåì,÷òî id òèïà String, à êëş÷ òèïà Integer
			//Be careful with types String and Integer!
			//System.out.println(id);
			//ÑÄÅËÀÒÜ ÏĞÂÎÅĞÊÓ ÍÀ ÑÓÙÅÑÒÂÎÂÀÍÈÅ ÒÀÊÎÃÎ id
			//ÅÑËÈ ÁÎËÜØÅ,ÅÌ ÂÎÇÌÎÆÍÎ, ÎØÈÁÊÓ ÍÎĞÌÀËÜÍÓŞ
			UserProfile userProfileInfo = usersList.get(Integer.parseInt(id));
			request.setAttribute("userProfileInfo", userProfileInfo);
			request.getRequestDispatcher("/userProfileInfo.jsp").forward(request, response);
		} 
//		else if (str == "/") {
//			request.setAttribute("usersList", (HashMap<Integer, UserProfile>) usersList);
//			request.getRequestDispatcher("/usersList.jsp").forward(request, response);
//		}  // Íå îáğàáàòûâàåò çàïğîñ íà users/ íóæíî ğåøèòü ïğîáëåìó
		else {
			request.setAttribute("usersList", (HashMap<Integer, UserProfile>) usersList);
			request.getRequestDispatcher("/usersList.jsp").forward(request, response);
		}

		// request.getRequestDispatcher("/usersList.jsp").forward(request, response);

	}

//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//				usersList = DataBase.getUsersList();
//				request.getSession().setAttribute("usersList", (ArrayList<UserProfile>) usersList);
//		} catch (InstantiationException | IllegalAccessException | SQLException e) {
//			e.printStackTrace();
//		}
//		if (request.getPathInfo().contains("1")) {
//			request.getRequestDispatcher("/userInfo.jsp").forward(request, response);
//		} else {
//			request.getRequestDispatcher("/usersList.jsp").forward(request, response);
//		}
//	}

}
