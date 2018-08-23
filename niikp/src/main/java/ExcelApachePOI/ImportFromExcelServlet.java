package ExcelApachePOI;

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

@WebServlet("/importExcel")
public class ImportFromExcelServlet  extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			System.out.println("Property   itno ImportFromExcelServlet" + Property.getProperty("fileForExportIncomingMail"));
			IncomingMailExcel.readFromExcel(Property.getProperty("fileForExportIncomingMail"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/niikp");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

}
