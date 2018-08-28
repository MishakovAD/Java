package OutgoingMail;

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
import Pagination.Pagination;
import Property.Property;
import UserProfile.UserProfile;
import Work.Work;

@WebServlet("/outgoingMailList")
public class OutgoingMailListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserProfile user = (UserProfile) request.getSession().getAttribute("userSignIn");
		String pageNumber = request.getParameter("pageNumber");

		if (pageNumber.equals("num")) {
			String num = request.getParameter("pageNum");
			response.sendRedirect("/niikp/incomingMailList?pageNumber=" + num);
			return;
		}
		
		ArrayList<OutgoingMail> listOutgoingMailForServlet = null;
		try {
			if (user != null && Rules.Rules.getLawToWatchMail(user)) {
				
				if (MainPageServlet.lastIndexOutgoingMail != (OutgoingMailDB.getLastIndexOutgoingMail()-1)) {
					MainPageServlet.listOutgoingMail = OutgoingMailDB.getOutgoingMail(); 
					listOutgoingMailForServlet = MainPageServlet.listOutgoingMail;
					MainPageServlet.lastIndexOutgoingMail++;
				} else {
					listOutgoingMailForServlet = MainPageServlet.listOutgoingMail;
				}
				
				
				
				
				
//				try {
//					list = IncomingMailDB.getIncomingMail();
//				} catch (InstantiationException | IllegalAccessException | SQLException e) {
//					e.printStackTrace();
//				}

				int pageCount = listOutgoingMailForServlet.size(); //размер массива с данными входящей корреспонденции
				
				ArrayList<OutgoingMail> listForPage = new ArrayList<>();
				if (pageNumber.equals("null")) {
					request.setAttribute("paginationPages", Pagination.getPagination(pageCount, 1, 
							Integer.parseInt(Property.getProperty("counterElementsOnPage")), 3));
					for (int i=0; i<=Integer.parseInt(Property.getProperty("counterElementsOnPage")); i++) {	
						if (i==pageCount) break;
						listForPage.add(listOutgoingMailForServlet.get(i));						
					}
				} else {
					request.setAttribute("paginationPages", Pagination.getPagination(pageCount, Integer.parseInt(pageNumber),
							Integer.parseInt(Property.getProperty("counterElementsOnPage")), 3));
					for (int i=0 + Integer.parseInt(Property.getProperty("counterElementsOnPage"))*(Integer.parseInt(pageNumber)-1); i<=Integer.parseInt(Property.getProperty("counterElementsOnPage"))*Integer.parseInt(pageNumber); i++) {
						if (i==pageCount) break;
						listForPage.add(listOutgoingMailForServlet.get(i));					
					}
				}
				
				HashMap<Integer, ArrayList<Work>> resolutionMapForIncomingMail = new HashMap<>();
				ArrayList<Work> resolutionList = new ArrayList<>();
				
				
				for (OutgoingMail incMail : listForPage) {
					int idFromMail = incMail.getIdMail();
					String prefix = "incomingMail_";
					String idMail = prefix + idFromMail;
					
					try {
						resolutionList = WorkDB.getWorkToMailId(idMail);						
					} catch (InstantiationException | IllegalAccessException | SQLException e) {
						e.printStackTrace();
					}
				
					
					resolutionMapForIncomingMail.put(idFromMail, resolutionList);
					idMail = null;
				}

						
				
				request.setAttribute("resolutionListForIncomingMail", resolutionMapForIncomingMail);
				request.setAttribute("incomingMailList", listForPage);
				listForPage = null;
				resolutionMapForIncomingMail = null;
				request.getRequestDispatcher("/outgoingMailList.jsp").forward(request, response);				
			} else {
				// вывести страницу с ошибкой вместо редиректа
				response.sendRedirect("/niikp/");
			}
		} catch (NullPointerException | InstantiationException | IllegalAccessException | SQLException e) {
			// вывести страницу со свойствами юзера, или вообще отправить запрос к
			// администратору сразу
			response.sendRedirect("/niikp/");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String sortDateReg = request.getParameter("sortDateReg");
		String sortNumReg = request.getParameter("sortNumReg");
		String sortSender = request.getParameter("sortSender");
		String sortSendDate = request.getParameter("sortSendDate");

		
		if (sortDateReg.equals("noSort") && sortNumReg.equals("noSort")) {
			try {
				MainPageServlet.listIncomingMail = IncomingMailDB.getIncomingMail();
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
		} else if (sortDateReg.equals("increase")) {
			try {
				MainPageServlet.listIncomingMail = IncomingMailDB.getIncomingMailSortedByRegDate("ASC");
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}			
		} else if (sortDateReg.equals("decrease")) {
			try {
				MainPageServlet.listIncomingMail = IncomingMailDB.getIncomingMailSortedByRegDate("DESC");
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}		
		}
		
		if (sortNumReg.equals("noSort") && sortDateReg.equals("noSort")) {
			try {
				MainPageServlet.listIncomingMail = IncomingMailDB.getIncomingMail();
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
		} else if (sortNumReg.equals("increase")) {
			if (MainPageServlet.listIncomingMail.get(0).getIdMail() > MainPageServlet.listIncomingMail.get(1).getIdMail()) {
				Collections.reverse(MainPageServlet.listIncomingMail);
			}			
		} else if (sortNumReg.equals("decrease")) {
			if (MainPageServlet.listIncomingMail.get(0).getIdMail() < MainPageServlet.listIncomingMail.get(1).getIdMail()) {
				Collections.reverse(MainPageServlet.listIncomingMail);
			}
		}

		
		response.sendRedirect("/niikp/incomingMailList?pageNumber=1");
		
	}
}
