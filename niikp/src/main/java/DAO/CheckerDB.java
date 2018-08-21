package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import UserProfile.UserProfile;
import Authorization.SignInServlet;


public class CheckerDB {
	private static String url = DataBase.url;
	private static String username = DataBase.username;
	private static String password = DataBase.password;
	
	
	// ѕроверка: верны ли данные логин пароль.
	public static boolean checkUser(String email, String passwwordUser) throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_check_correct_email_and_password = "SELECT * FROM users WHERE email='" + email + "';";
		
		ResultSet rs = statement.executeQuery(SQL_check_correct_email_and_password);
		while (rs.next()) {
			if (email.equals(rs.getString("email")) && passwwordUser.equals(rs.getString("password"))) { 
				UserProfile userProfileSignIn = new UserProfile(rs.getInt("userId"), rs.getString("name"),
						rs.getString("secondName"),rs.getString("email"),rs.getString("password"),rs.getString("birthday"));
				userProfileSignIn.setMiddleName(rs.getString("middleName"));
				userProfileSignIn.setPhoneNumber(rs.getString("phoneNumber"));
				userProfileSignIn.setRoomNumber(rs.getString("roomNumber"));
				userProfileSignIn.setRole(rs.getString("privilege"));
				userProfileSignIn.setPosition(rs.getString("position"));
				userProfileSignIn.setUserGroup(rs.getString("userGroup"));
				System.out.println(rs.getString("userGroup"));
				SignInServlet.userLoginMap.put(rs.getString("email"), userProfileSignIn);
				return true;
			}				
			else {
				return false;
			}
			
		}
		if(statement != null) statement.close(); 
	    if(con != null)  con.close();
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
				if(statement != null) statement.close(); 
			    if(con != null)  con.close();
				return false;
			} else {
				String nameUser = rs.getString("name");
				System.out.println(nameUser + ": Registred yzhe");
				if(statement != null) statement.close(); 
			    if(con != null)  con.close();
				return true;				
			}
						
		}
}
