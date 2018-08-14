package DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import UserProfile.UserProfile;
import Property.Property;

//for registration and create new table and connect to db
public class DataBase {  
	public static String url = Property.getProperty("db.url");
	public static String username = Property.getProperty("db.login");
	public static String password = Property.getProperty("db.password");
	
//	public static String url = "jdbc:mysql://localhost:3307/niikp_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//	public static String username = "root";
//	public static String password = "root"; // for server


	// Для отдельного запуска работы с БД
	public static void connect() throws SQLException, InstantiationException, IllegalAccessException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loading success!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// Открываем соединение
		Connection con = DriverManager.getConnection(url, username, password);
		System.out.println("Connected.");

		Statement statement = null;
		statement = con.createStatement();
		// Создание таблицы в БД
		//CREATE DATABASE `my_db` CHARACTER SET utf8 COLLATE utf8_general_ci;
//		String SQL = "CREATE TABLE users " +
//				 "(userId INTEGER not NULL AUTO_INCREMENT, " +
//				 " dateRegistration DATETIME not NULL, " +
//				 " name VARCHAR (100), " +
//				 " secondName VARCHAR (100), " +
//				 " middleName VARCHAR (100), " +
//				 " email VARCHAR (50) not NULL, " +
//				 " password VARCHAR (100) not NULL, " +
//				 " birthday DATE not NULL, " +
//				 " phoneNumber VARCHAR (15), " +
//				 " roomNumber VARCHAR (10), " +
//				 " position VARCHAR (100), " +
//				 " userGroup VARCHAR (50), " +
//				 " privilege VARCHAR (50), " +
//				 " PRIMARY KEY (userId))";
//		
//		 statement.executeUpdate(SQL);
//		 System.out.println("Table successfully created...");
////
//		String SQL = "CREATE TABLE incomingMail " +
//				 "(regDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
//				 " idMail INTEGER not NULL AUTO_INCREMENT, " +
//				 " typeMail VARCHAR (50), " +
//				 " sender VARCHAR (150), " +
//				 " sendDate VARCHAR (100), " +
//				 " mailNum VARCHAR (50), " +
//				 " mailTheme VARCHAR (200), " +
//				 " secondFloorDate VARCHAR (50), " +
//				 " secondFloorNum VARCHAR (100), " +
//				 " filePathAndName VARCHAR (100), " +
//				 " PRIMARY KEY (idMail))";
//		
//		 statement.executeUpdate(SQL);
//		 System.out.println("Table successfully created...");
////		
//		String SQL = "CREATE TABLE work " +
//		 "(workId INTEGER not NULL AUTO_INCREMENT, " +
//		 " toUserId INTEGER, " +
//		 " observerId INTEGER, " +
//		 " fromUserId INTEGER, " +
//		 " startDate TIMESTAMP, " +	
//		 " endDate TIMESTAMP, " +
//		 " assignment VARCHAR (500), " +
//		 " mailId VARCHAR (50), " +
//		 " filePathAndNameToWork VARCHAR (100), " +
//		 " isComplete BOOLEAN DEFAULT NULL, " +
//		 " report VARCHAR (500), " +
//		 " reportFilePathAndNameToWork VARCHAR (150), " +
//		 " PRIMARY KEY (workId))";
////
//statement.executeUpdate(SQL);
//System.out.println("Table successfully created...");
	}

	public static void regNewUser(UserProfile userProfile) throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		System.out.println("Регистрация нового пользователя!");
		
		String name = userProfile.getName();
		String secondName = userProfile.getSecondName();
		String email = userProfile.getEmail();
		String password = userProfile.getPassword();
		String birthday = userProfile.getBirthday();		

		Statement statement = null;
		statement = con.createStatement();

		String SQL_insert_new_user = "insert into users (dateRegistration, name, secondName, email, "
				+ "password, birthday)"
				+ " values (now(), '" + name + "', '" + secondName + "', '" 
				+ email + "', '" + password + "', '" + birthday + "');";
				
		statement.execute(SQL_insert_new_user);
		name = null;
		secondName = null;
		email = null;
		password = null;
		birthday = null;
	}
		

	public static void main(String[] args) {
		try {
			//addUser();
			connect();
			//checkNewUser("a10");
//			System.out.println("Start JSP");
//			   ArrayList<UserProfile> list = DataBase.getBirthday();
//			   System.out.println("Start before for JSP");
//			   for(UserProfile userProfile : list) {
//				   System.out.println("Start FOR JSP");
//			    userProfile.getEmail();
//			   }
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}
	}

}
