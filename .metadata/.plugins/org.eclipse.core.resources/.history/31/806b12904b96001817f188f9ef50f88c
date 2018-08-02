package Work;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.GetterDB;
import UserProfile.UserProfile;
import Users.UsersList;

@WebServlet(urlPatterns = { "/workAdd" })
@MultipartConfig
public class WorkAddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			UsersList.usersList = GetterDB.getUsersList();

		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}

		String str = request.getPathInfo();
		//System.out.println(str);
		if (str != null) {
			String id = str.substring(1);
			UserProfile userProfileInfo = UsersList.usersList.get(Integer.parseInt(id));
			request.setAttribute("userProfileInfo", userProfileInfo);
			request.getRequestDispatcher("/userProfileInfo.jsp").forward(request, response);
		} else {
			request.setAttribute("usersList", (HashMap<Integer, UserProfile>) UsersList.usersList);
			request.getRequestDispatcher("/work.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userNameSecondName = request.getParameter("user"); 
		String date = request.getParameter("endDate");
		String assignment = request.getParameter("assignment");
		
		System.out.println(userNameSecondName + " date: " + date + "; assigment: " + assignment);
		
//		Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
//		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
//		InputStream fileContent = filePart.getInputStream();
//		// ... (do your job here)
//		//просто читаем содерживое и все
//		//нужно сделать сохранение самого файла
//		BufferedReader br = new BufferedReader(new InputStreamReader(fileContent));
//	    String text;
//	    while (br.ready()) {
//	    	text = br.readLine();
//		    System.out.println(text);
//	    }
	    

	}
}
