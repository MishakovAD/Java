package MainPage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DataBase;

@WebServlet(urlPatterns = { "" }, loadOnStartup=1)
public class MainPageServlet extends HttpServlet {
	
	@Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        try {
			DataBase.connect();
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MainPage servlet");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		//super.doGet(request, response); //из-за этой строчки была ошибка sendError()
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}
	
}
