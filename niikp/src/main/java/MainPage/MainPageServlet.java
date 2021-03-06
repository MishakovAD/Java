package MainPage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DataBase;
import DAO.IncomingMailDB;
import DAO.InternalMailDB;
import DAO.OutgoingMailDB;
import DAO.WorkDB;
import IncomingMail.IncomingMail;
import InternalMail.InternalMail;
import OutgoingMail.OutgoingMail;
import Rules.Groups;
import UserProfile.UserProfile;
import Work.Work;

@WebServlet(urlPatterns = { "" }, loadOnStartup = 1)
public class MainPageServlet extends HttpServlet {
	public static ArrayList<IncomingMail> listIncomingMail;
	public static int lastIndexIncomingMail;
	public static ArrayList<OutgoingMail> listOutgoingMail;
	public static int lastIndexOutgoingMail;
	public static ArrayList<InternalMail> listInternalMail;
	public static int lastIndexInternalMail;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		Groups groups = new Groups(); //������� ��������� ������, ����� ���������� ����������� �� ���������	
		try {
			DataBase.connect();
			listIncomingMail = IncomingMailDB.getIncomingMail();
			lastIndexIncomingMail = IncomingMailDB.getLastIndexIncomingMail()-1;
			
			listOutgoingMail = OutgoingMailDB.getOutgoingMail();
			lastIndexOutgoingMail = OutgoingMailDB.getLastIndexOutgoingMail()-1;
			
			listInternalMail = InternalMailDB.getInternalMail();
			lastIndexInternalMail = InternalMailDB.getLastIndexInternalMail()-1;
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserProfile userSignIn = (UserProfile) request.getSession().getAttribute("userSignIn");
		ArrayList<Work> workListToUser = null;
		ArrayList<Work> workListToUserFromCo_executor = null;
		if (userSignIn != null) {
			try {
				workListToUser = WorkDB.getWorkListToId(userSignIn.getUserId());
				workListToUserFromCo_executor = WorkDB.getWorkListForCo_executorToId(userSignIn.getUserId());
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
			request.getSession().setAttribute("workListToUser", workListToUser);
			request.getSession().setAttribute("workListToUserFromCo_executor", workListToUserFromCo_executor);
		}

		request.getRequestDispatcher("/index.jsp").forward(request, response);
		// super.doGet(request, response); //��-�� ���� ������� ���� ������ sendError()
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}

}
