package Searching;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.IncomingMailDB;
import DAO.OutgoingMailDB;
import DAO.WorkDB;
import IncomingMail.IncomingMail;
import MainPage.MainPageServlet;
import OutgoingMail.OutgoingMail;
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
		
		ArrayList<IncomingMail> searchListIncomingMail = new ArrayList<>();
		ArrayList<OutgoingMail> searchListOutgoingMail = new ArrayList<>();
		
		String refererURL = request.getHeader("Referer"); //ссылка, с которой производился запрос поиска.
		int indexOfSplit = refererURL.indexOf("niikp/");
		refererURL = refererURL.substring(indexOfSplit+6);
		
		if (refererURL.contains("?")) {
			int indexOfChar = refererURL.indexOf("?");
			refererURL = refererURL.substring(0, indexOfChar);
		}
		
		if (refererURL.equalsIgnoreCase("incomingMailList") || refererURL.equalsIgnoreCase("outgoingMailList")) {	
			
			String searchParameterForOnceSearcing = request.getParameter("search");
			if (!(searchParameterForOnceSearcing == null) && searchParameterForOnceSearcing.equals("searchIncomingMail")) {
//				System.out.println("searchParameterForOnceSearcing = " + searchParameterForOnceSearcing);
//				System.out.println("request.getParameter(\"searchParameterForOnceSearcing\") =" + request.getParameter(searchParameterForOnceSearcing));
//				String findWord = request.getParameter(searchParameterForOnceSearcing);
//				searchList = SearchRobot.searchIntoIncomingMailForOneField(findWord, MainPageServlet.listIncomingMail);
//				Collections.reverse(searchList);
				
				Map<String, String> searchParameterMapIncomingMail = new HashMap<>();
				
				String searchRegDate = request.getParameter("searchRegDate");
				String searchIdMail = request.getParameter("searchIdMail");
				String searchMailType = request.getParameter("searchMailType");
				String searchSender = request.getParameter("searchSender");
				String searchSendDate = request.getParameter("searchSendDate");
				String searchMailNum = request.getParameter("searchMailNum");
				String searchMailTheme = request.getParameter("searchMailTheme");
				String searchSecondFloorDate = request.getParameter("searchSecondFloorDate");
				String searchSecondFloorNum = request.getParameter("searchSecondFloorNum");
				
				if (!searchRegDate.isEmpty()) {
					searchParameterMapIncomingMail.put("searchRegDate", searchRegDate);
				}
				
				if (!searchIdMail.isEmpty()) {
					searchParameterMapIncomingMail.put("searchIdMail", searchIdMail);
				}
				
				if (!searchMailType.isEmpty()) {
					searchParameterMapIncomingMail.put("searchMailType", searchMailType);
				}
				
				if (!searchSender.isEmpty()) {
					searchParameterMapIncomingMail.put("searchSender", searchSender);
				}
				
				if (!searchSendDate.isEmpty()) {
					searchParameterMapIncomingMail.put("searchSendDate", searchSendDate);
				}
				
				if (!searchMailNum.isEmpty()) {
					searchParameterMapIncomingMail.put("searchMailNum", searchMailNum);
				}
				
				if (!searchMailTheme.isEmpty()) {
					searchParameterMapIncomingMail.put("searchMailTheme", searchMailTheme);
				}
				
				if (!searchSecondFloorDate.isEmpty()) {
					searchParameterMapIncomingMail.put("searchSecondFloorDate", searchSecondFloorDate);
				}
				
				if (!searchSecondFloorNum.isEmpty()) {
					searchParameterMapIncomingMail.put("searchSecondFloorNum", searchSecondFloorNum);
				}
				
				
				
				try {
					searchListIncomingMail = SearchRobot.searchIntoIncomingMailForOneField(searchParameterMapIncomingMail, IncomingMailDB.getIncomingMail());
				} catch (InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}
				HashMap<Integer, ArrayList<Work>> resolutionMapForSearchIncomingMail = new HashMap<>();
				ArrayList<Work> resolutionListForSearchIncomingMail = new ArrayList<>();
				
				
				for (IncomingMail incMail : searchListIncomingMail) {
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
				request.getSession().setAttribute("searchListIncomingMail", searchListIncomingMail);
				request.getRequestDispatcher("/incomingMailSearchList.jsp").forward(request, response);
				
			} else if (!(searchParameterForOnceSearcing == null) && searchParameterForOnceSearcing.equals("searchOutgoingMail")) {
				Map<String, String> searchParameterMapOutgoingMail = new HashMap<>();
				
				String searchRegDate = request.getParameter("searchRegDate");
				String searchMailNum = request.getParameter("searchMailNum");
				String searchDestination = request.getParameter("searchDestination");
				String searchForWhom = request.getParameter("searchForWhom");
				String searchSendDate = request.getParameter("searchSendDate");
				String searchMailTheme = request.getParameter("searchMailTheme");
				String searchExecutor = request.getParameter("searchExecutor");
				String searchRealExecutor = request.getParameter("searchRealExecutor");
				String searchIncomingMailNum = request.getParameter("searchIncomingMailNum");
				String searchToWhomItIsPainted = request.getParameter("searchToWhomItIsPainted");
				String searchIncomingMailId = request.getParameter("searchIncomingMailId");
				
				if (!searchRegDate.isEmpty()) {
					searchParameterMapOutgoingMail.put("searchRegDate", searchRegDate);
				}

				if (!searchMailNum.isEmpty()) {
					searchParameterMapOutgoingMail.put("searchMailNum", searchMailNum);
				}

				if (!searchDestination.isEmpty()) {
					searchParameterMapOutgoingMail.put("searchDestination", searchDestination);
				}

				if (!searchForWhom.isEmpty()) {
					searchParameterMapOutgoingMail.put("searchForWhom", searchForWhom);
				}

				if (!searchSendDate.isEmpty()) {
					searchParameterMapOutgoingMail.put("searchSendDate", searchSendDate);
				}

				if (!searchMailTheme.isEmpty()) {
					searchParameterMapOutgoingMail.put("searchMailTheme", searchMailTheme);
				}

				if (!searchExecutor.isEmpty()) {
					searchParameterMapOutgoingMail.put("searchExecutor", searchExecutor);
				}

				if (!searchRealExecutor.isEmpty()) {
					searchParameterMapOutgoingMail.put("searchRealExecutor", searchRealExecutor);
				}

				if (!searchIncomingMailNum.isEmpty()) {
					searchParameterMapOutgoingMail.put("searchIncomingMailNum", searchIncomingMailNum);
				}

				if (!searchToWhomItIsPainted.isEmpty()) {
					searchParameterMapOutgoingMail.put("searchToWhomItIsPainted", searchToWhomItIsPainted);
				}

				if (!searchIncomingMailId.isEmpty()) {
					searchParameterMapOutgoingMail.put("searchIncomingMailId", searchIncomingMailId);
				}
				
				try {
					searchListOutgoingMail = SearchRobot.searchIntoOutgoingMailForOneField(searchParameterMapOutgoingMail, OutgoingMailDB.getOutgoingMail());
				} catch (InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}
					
				request.getSession().setAttribute("searchListOutgoingMail", searchListOutgoingMail);
				request.getRequestDispatcher("/outgoingMailSearchList.jsp").forward(request, response);
			} 
//			else {
//				String searchParameter = request.getParameter("searchAll");
//				searchList = SearchRobot.searchIntoIncomingMail(searchParameter, MainPageServlet.listIncomingMail);
//			}

		} 

		
	}
}
