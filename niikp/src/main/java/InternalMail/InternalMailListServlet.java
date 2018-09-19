package InternalMail;

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
import DAO.InternalMailDB;
import DAO.WorkDB;
import MainPage.MainPageServlet;
import InternalMail.InternalMail;
import Pagination.Pagination;
import Property.Property;
import UserProfile.UserProfile;
import Work.Work;

@WebServlet("/internalMailList")
public class InternalMailListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserProfile user = (UserProfile) request.getSession().getAttribute("userSignIn");
		String pageNumber = request.getParameter("pageNumber");

		if (pageNumber.equals("num")) {
			String num = request.getParameter("pageNum");
			response.sendRedirect("/niikp/internalMailList?pageNumber=" + num);
			return;
		}
		
		ArrayList<InternalMail> listInternalMailForServlet = null;
		try {
			if (user != null && Rules.Rules.getLawToWatchMail(user)) {
				
				if (MainPageServlet.lastIndexInternalMail != (InternalMailDB.getLastIndexInternalMail()-1)) {
					MainPageServlet.listInternalMail = InternalMailDB.getInternalMail(); 
					listInternalMailForServlet = MainPageServlet.listInternalMail;
					MainPageServlet.lastIndexInternalMail++;
				} else {
					listInternalMailForServlet = MainPageServlet.listInternalMail;
				}
				


				int pageCount = listInternalMailForServlet.size(); //размер массива с данными входящей корреспонденции
				
				ArrayList<InternalMail> listForPage = new ArrayList<>();
				if (pageNumber.equals("null")) {
					request.setAttribute("paginationPages", Pagination.getPagination(pageCount, 1, 
							Integer.parseInt(Property.getProperty("counterElementsOnPage")), 3));
					for (int i=0; i<=Integer.parseInt(Property.getProperty("counterElementsOnPage")); i++) {	
						if (i==pageCount) break;
						listForPage.add(listInternalMailForServlet.get(i));						
					}
				} else {
					request.setAttribute("paginationPages", Pagination.getPagination(pageCount, Integer.parseInt(pageNumber),
							Integer.parseInt(Property.getProperty("counterElementsOnPage")), 3));
					for (int i=0 + Integer.parseInt(Property.getProperty("counterElementsOnPage"))*(Integer.parseInt(pageNumber)-1); i<=Integer.parseInt(Property.getProperty("counterElementsOnPage"))*Integer.parseInt(pageNumber); i++) {
						if (i==pageCount) break;
						listForPage.add(listInternalMailForServlet.get(i));					
					}
				}
				
				
				request.setAttribute("internalMailList", listForPage);
				listForPage = null;
				request.getRequestDispatcher("/internalMailList.jsp").forward(request, response);				
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
		
		response.sendRedirect("/niikp/internalMailList?pageNumber=1");
		
	}

}
