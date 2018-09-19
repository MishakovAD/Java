package LinksForOnceChoise;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.IncomingMailDB;
import DAO.InternalMailDB;
import DAO.OutgoingMailDB;
import DAO.WorkDB;
import IncomingMail.IncomingMail;
import InternalMail.InternalMail;
import OutgoingMail.OutgoingMail;
import Searching.SearchRobot;
import UserProfile.UserProfile;
import Work.Work;

@WebServlet("/link")
public class LinksGenerator extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserProfile user = (UserProfile) request.getSession().getAttribute("userSignIn");
		
		IncomingMail incomingMailForLink = new IncomingMail();
		
		String type = request.getParameter("type");
		String mailName = request.getParameter("mailName");
		
		if (type.equals("work")) {
			int indexCharOf_ = mailName.indexOf("_");
			String mailId = mailName.substring(indexCharOf_+1);
			if (mailName.contains("Vhod") || mailName.contains("incoming")) {
				try {
					incomingMailForLink = IncomingMailDB.getIncomingMailToId(Integer.parseInt(mailId));
				} catch (NumberFormatException | InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}
			}
			request.setAttribute("incomingMailForLink", incomingMailForLink);
			request.getRequestDispatcher("/link.jsp").forward(request, response);
		}
		

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		ArrayList<IncomingMail> searchListIncomingMail = new ArrayList<>();
		ArrayList<OutgoingMail> searchListOutgoingMail = new ArrayList<>(); 
		ArrayList<InternalMail> searchListInternalMail = new ArrayList<>(); 
		
		String refererURL = request.getHeader("Referer"); //ссылка, с которой производился запрос поиска.
		System.out.println(refererURL);
		int indexOfSplit = refererURL.indexOf("niikp/");
		refererURL = refererURL.substring(indexOfSplit+6);
		
		if (refererURL.contains("?")) {
			int indexOfChar = refererURL.indexOf("?");
			refererURL = refererURL.substring(0, indexOfChar);
		}
		
		

		} 

}
