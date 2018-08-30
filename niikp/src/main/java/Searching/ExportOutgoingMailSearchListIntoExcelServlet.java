package Searching;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ExcelApachePOI.OutgoingMailExcel;
import OutgoingMail.*;

@WebServlet("/exportOutgoingMailSearchListIntoExcel")
public class ExportOutgoingMailSearchListIntoExcelServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<OutgoingMail> searchListOutgoingMail = (ArrayList<OutgoingMail>) request.getSession().getAttribute("searchListOutgoingMail");
		OutgoingMailExcel.writeSearchListIntoExcel(searchListOutgoingMail);
		
		response.sendRedirect("/niikp/downloadOutgoingMailSearchListExcel");

		// request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
	}
}
