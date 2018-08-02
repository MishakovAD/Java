package Authorization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = { "/logOut" })
public class LogOutServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SignInServlet.userLoginMap.remove(request.getSession().getAttribute("userSignIn")); //delete user in map
		request.getSession().removeAttribute("userSignIn"); //delete user in session
		response.sendRedirect("/niikp/");
		//super.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

}
