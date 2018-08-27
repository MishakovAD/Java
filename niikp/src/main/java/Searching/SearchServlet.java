package Searching;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.IncomingMailDB;
import DAO.WorkDB;
import IncomingMail.IncomingMail;
import MainPage.MainPageServlet;
import UserProfile.UserProfile;
import Work.Work;



@WebServlet("/search")
public class SearchServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserProfile user = (UserProfile) request.getSession().getAttribute("userSignIn");
		
		String referURL = request.getRequestURI();
		//System.out.println("referURL " + referURL);
		

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		ArrayList<IncomingMail> searchList = new ArrayList<>();
		
		String refererURL = request.getHeader("Referer"); //������, � ������� ������������ ������ ������.
		int indexOfSplit = refererURL.indexOf("niikp/");
		refererURL = refererURL.substring(indexOfSplit+6);
		
		if (refererURL.contains("?")) {
			int indexOfChar = refererURL.indexOf("?");
			refererURL = refererURL.substring(0, indexOfChar);
		}
		
		if (refererURL.equalsIgnoreCase("incomingMailList")) {	
			
			String searchParameterForOnceSearcing = request.getParameter("search");
			if (!(searchParameterForOnceSearcing == null)) {
				System.out.println("searchParameterForOnceSearcing = " + searchParameterForOnceSearcing);
//				System.out.println("request.getParameter(\"searchParameterForOnceSearcing\") =" + request.getParameter(searchParameterForOnceSearcing));
				String findWord = request.getParameter(searchParameterForOnceSearcing);
//				searchList = SearchRobot.searchIntoIncomingMailForOneField(findWord, MainPageServlet.listIncomingMail);
//				Collections.reverse(searchList);
				try {
					searchList = SearchRobot.searchIntoIncomingMailForOneField(searchParameterForOnceSearcing, findWord, IncomingMailDB.getIncomingMail());
				} catch (InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}
			} else {
				String searchParameter = request.getParameter("searchAll");
				searchList = SearchRobot.searchIntoIncomingMail(searchParameter, MainPageServlet.listIncomingMail);
			}
						
			HashMap<Integer, ArrayList<Work>> resolutionMapForSearchIncomingMail = new HashMap<>();
			ArrayList<Work> resolutionListForSearchIncomingMail = new ArrayList<>();
			
			
			for (IncomingMail incMail : searchList) {
				int idFromMail = incMail.getIdMail();
				String prefix = "incomingMail_";
				String idMail = prefix + idFromMail;
				
				try {
					resolutionListForSearchIncomingMail = WorkDB.getWorkToMailId(idMail);						
				} catch (InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}
			
				
				resolutionMapForSearchIncomingMail.put(idFromMail, resolutionListForSearchIncomingMail);
				idMail = null;
			}

					
			
			request.setAttribute("resolutionMapForSearchIncomingMail", resolutionMapForSearchIncomingMail);
			
			request.setAttribute("searchList", searchList);
			request.getRequestDispatcher("/incomingMailSearchList.jsp").forward(request, response);

		}

		
	}
}
