package Searching;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ExcelApachePOI.IncomingMailExcel;
import ExcelApachePOI.InternalMailExcel;
import IncomingMail.*;
import InternalMail.InternalMail;

@WebServlet("/exportInternalMailSearchListIntoExcel")
public class ExportInternalMailSearchListIntoExcelServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<InternalMail> searchListInternalMail = (ArrayList<InternalMail>) request.getSession().getAttribute("searchListInternalMail");
		InternalMailExcel.writeSearchListIntoExcel(searchListInternalMail);
		
		response.sendRedirect("/niikp/downloadInternalMailSearchListExcel");

		// request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
	}
}
