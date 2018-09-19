package ExportExcelForNiikp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Property.Property;



@WebServlet("/importExcelOutgoingMail")
public class ExportExcelServletOutgoingMail extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			System.out.println("Property   itno ImportFromExcelServlet" + Property.getProperty("fileForExportOutgoingMail"));
			WorkWithExcelOutgoingMail.readFromExcel(Property.getProperty("fileForExportOutgoingMail"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/niikp");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}
	
}
