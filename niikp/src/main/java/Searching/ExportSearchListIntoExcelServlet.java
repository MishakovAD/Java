package Searching;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ExcelApachePOI.IncomingMailExcel;
import IncomingMail.*;

@WebServlet("/exportSearchListIntoExcel")
public class ExportSearchListIntoExcelServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<IncomingMail> searchList = (ArrayList<IncomingMail>) request.getSession().getAttribute("searchList");
		IncomingMailExcel.writeSearchListIntoExcel(searchList);
		
		response.sendRedirect("/niikp/downloadSearchListExcel");

		// request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
	}
}
