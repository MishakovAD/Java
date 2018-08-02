package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import UserProfile.UserProfile;
import Authorization.SignInServlet;


public class CheckerDB {
	private static String url = "jdbc:mysql://localhost:3306/niikp_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String username = "root";
	private static String password = "hedghog";
	
	
	// ѕроверка: верны ли данные логин пароль.
	public static boolean checkUser(String email, String passwwordUser) throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_check_correct_email_and_password = "SELECT userId, name, secondName,"
				+ " email, password, birthday FROM users WHERE email='" + email + "';";
		
		ResultSet rs = statement.executeQuery(SQL_check_correct_email_and_password);
		while (rs.next()) {
			if (email.equals(rs.getString("email")) && passwwordUser.equals(rs.getString("password"))) { 
				UserProfile userProfileSignIn = new UserProfile(rs.getInt("userId"), rs.getString("name"),
						rs.getString("secondName"),rs.getString("email"),rs.getString("password"),rs.getString("birthday"));
				SignInServlet.userLoginMap.put(rs.getString("email"), userProfileSignIn);
				return true;
			}				
			else {
				return false;
			}
			
		}
		return false;
	}
	
	// ѕроверка: зарегестрирован ли уже такой пользователь.
		public static boolean checkNewUser(String email) throws SQLException, InstantiationException, IllegalAccessException {
			Connection con = DriverManager.getConnection(url, username, password);

			Statement statement = null;
			statement = con.createStatement();

			String SQL_select_email = "SELECT * FROM users WHERE email='" + email + "';";
			//String SQL_select_last_id = "SELECT max(userId) FROM users;";
			ResultSet rs = statement.executeQuery(SQL_select_email);
			if (!rs.next()) {
				return false;
			} else {
				String nameUser = rs.getString("name");
				System.out.println(nameUser + ": Registred yzhe");
				return true;				
			}
						
		}
}
