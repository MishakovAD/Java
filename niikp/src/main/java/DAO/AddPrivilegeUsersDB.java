package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class AddPrivilegeUsersDB {
	private static String url = DataBase.url;
	private static String username = DataBase.username;
	private static String password = DataBase.password;

	//Должность
	public static void addPosition(String position, int userId)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_add_position = "UPDATE users SET position='" + position + "' WHERE userId=" + userId + ";";
		statement.executeUpdate(SQL_add_position);

		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
	}
	
	//Группа (секретари, испытатели, бухгалтерия)
	public static void addUserGroup(String userGroup, int userId)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_add_userGroup = "UPDATE users SET userGroup='" + userGroup + "' WHERE userId=" + userId + ";";
		statement.executeUpdate(SQL_add_userGroup);

		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
	}
	
	//Роль (SuperAdmin, admin, moderator, user)
		public static void addRole(String role, int userId)
				throws SQLException, InstantiationException, IllegalAccessException {
			Connection con = DriverManager.getConnection(url, username, password);
			Statement statement = null;
			statement = con.createStatement();

			String SQL_add_role = "UPDATE users SET privilege='" + role + "' WHERE userId=" + userId + ";";
			statement.executeUpdate(SQL_add_role);

			if (statement != null)
				statement.close();
			if (con != null)
				con.close();
		}
}
