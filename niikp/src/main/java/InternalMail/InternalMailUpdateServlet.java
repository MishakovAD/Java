package InternalMail;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.GetterDB;
import DAO.InternalMailDB;
import DAO.OutgoingMailDB;
import ExcelApachePOI.InternalMailExcel;
import InternalMail.InternalMail;
import MainPage.MainPageServlet;
import InternalMail.InternalMailServlet;
import UserProfile.UserProfile;
import Users.UsersList;

@WebServlet("/internalMail/*")
public class InternalMailUpdateServlet extends HttpServlet {
	String id;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("delete")) {
			String url_str = request.getPathInfo();
			if (url_str != null) {
				String id = url_str.substring(1);
				// InternalMailServlet.internalMailList.remove(Integer.parseInt(id));
				try {
					InternalMailDB.deleteInternalMail(Integer.parseInt(id));

					response.sendRedirect("/niikp/internalMailList?pageNumber=1");
				} catch (NumberFormatException | InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}

			}
		} else if (action != null && action.equals("update")) {
			String url_str = request.getPathInfo();
			if (url_str != null) {
				id = url_str.substring(1);
				try {
					request.setAttribute("internalMail", InternalMailDB.getInternalMailToId(Integer.parseInt(id)));
					request.setAttribute("docTypeList", InternalMailServlet.docTypeList);
					try {
						UsersList.usersList = GetterDB.getUsersList();
						request.setAttribute("usersList", (HashMap<Integer, UserProfile>) UsersList.usersList);

					} catch (InstantiationException | IllegalAccessException | SQLException e) {
						e.printStackTrace();
					}
				} catch (NumberFormatException | InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}
				// internalMail = InternalMailServlet.internalMailList.get(Integer.parseInt(id));
				request.getRequestDispatcher("/internalMailUpdate.jsp").forward(request, response);
			}
		} else if (action == null) {
			request.getRequestDispatcher("/internalMail.jsp").forward(request, response);
		}

		// super.doGet(request, response); //из-за этой строчки была ошибка sendError()
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		InternalMail internalMail = new InternalMail();
		String action = request.getParameter("action");
		if ("submit".equals(action)) {
			String docType = request.getParameter("docType");
			String numNPK = request.getParameter("numNPK");
			String destination = request.getParameter("destination");
			String additionalDestination = request.getParameter("additionalDestination");
			String docTheme = request.getParameter("docTheme");
			String executor = request.getParameter("executor");
			String note = request.getParameter("note");

			internalMail.setIdMail(Integer.parseInt(id));
			
			if (docType != null)
				internalMail.setDocType(docType);
			if (numNPK != null)
				internalMail.setNumNPK(numNPK);
			if (destination != null)
				internalMail.setDestination(destination);
			if (additionalDestination != null)
				internalMail.setAdditionalDestination(additionalDestination);
			if (docTheme != null)
				internalMail.setDocTheme(docTheme);
			if (executor != null)
				internalMail.setExecutor(executor);
			if (note != null)
				internalMail.setNote(note);
			
			try {
				InternalMailDB.updateInternalMail(internalMail);
				MainPageServlet.listInternalMail = InternalMailDB.getInternalMail();
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("/niikp/internalMailList?pageNumber=1");
			


		} // end if
	}
}
