package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import UserProfile.UserProfile;

public class GetterDB {
	private static String url = DataBase.url;
	private static String username = DataBase.username;
	private static String password = DataBase.password;
	
	private static UserProfile userProfileBirthday;
	
	//получение текущей даты и составление sql запроса
			public static String getDate() {
				LocalDateTime ldt = LocalDateTime.now();
				String today = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH).format(ldt);
				int year = Integer.parseInt(today.substring(0, 4));
				int month = Integer.parseInt(today.substring(4, 6));
				int day = Integer.parseInt(today.substring(6, 8));
				String SQL_select_birthday;

//				if (day == 1) {
//					//Сделать проверку на предыдущий и следующий день,с учетом 28,30,31 дней в месяце
//				}
				if (month<10) {
					SQL_select_birthday = "SELECT * FROM users WHERE birthday BETWEEN "
							+ "STR_TO_DATE('"+ year + "-0" + month + "-" + day + " 00:00:00', '%Y-%m-%d %H:%i:%s') "
							+ "AND STR_TO_DATE('"+ year + "-0" + month + "-" + day + " 23:59:59', '%Y-%m-%d %H:%i:%s');";
					System.out.println(SQL_select_birthday);
				}else {
					SQL_select_birthday = "SELECT * FROM users" + 
							"  WHERE birthday BETWEEN STR_TO_DATE('" + year + "-" + month + "-" 
							+ day + " 00:00:00', '%Y-%m-%d %H:%i:%s')" + 
							"  AND STR_TO_DATE('" + year + "-" + month + "-" 
							+ day + " 23:59:59', '%Y-%m-%d %H:%i:%s');";

					System.out.println(SQL_select_birthday);
				}
				return SQL_select_birthday;
			}
		
		// Проверка: у каких пользователей СЕГОДНЯ День Рождения.
			public static ArrayList<UserProfile> getBirthday() throws SQLException, InstantiationException, IllegalAccessException {
				ArrayList<UserProfile> userBirthdayArray = new ArrayList<>();
				String name;
				String secondName;
				String email;
				String birthday;
				
				 // Текущие дата и время
				String SQL_select_birthday = getDate();
				
				Connection con = DriverManager.getConnection(url, username, password);
				Statement statement = null;
				statement = con.createStatement();
				ResultSet rs = statement.executeQuery(SQL_select_birthday);
				System.out.println("Start while");
				while (rs.next()) {
					UserProfile userProfileBirthday = new UserProfile();
					name = rs.getString("name");
					secondName = rs.getString("secondName");
					email = rs.getString("email");
					birthday = rs.getString("birthday");
					userProfileBirthday.setName(name);
					userProfileBirthday.setSecondName(secondName);
					userProfileBirthday.setEmail(email);
					userProfileBirthday.setBirthday(birthday);				
					userBirthdayArray.add(userProfileBirthday);
				}
				if(statement != null) statement.close(); 
			    if(con != null)  con.close();
				return userBirthdayArray;		
			}
			
			//Получение списка всех пользователей
					public static HashMap<Integer, UserProfile> getUsersList() throws SQLException, InstantiationException, IllegalAccessException {
						HashMap<Integer, UserProfile> usersList = new HashMap<>();
						int userId;
						String name;
						String secondName;
						String email;
						String birthday;
						
						String SQL_select_users_list = "SELECT * FROM users;";
						
						Connection con = DriverManager.getConnection(url, username, password);
						Statement statement = null;
						statement = con.createStatement();
						ResultSet rs = statement.executeQuery(SQL_select_users_list);
						while (rs.next()) {
							UserProfile user = new UserProfile();
							userId = rs.getInt("userId");
							name = rs.getString("name");
							secondName = rs.getString("secondName");
							email = rs.getString("email");
							birthday = rs.getString("birthday");
							user.setUserId(userId);
							user.setName(name);
							user.setSecondName(secondName);
							user.setEmail(email);
							user.setBirthday(birthday);				
							usersList.put(userId, user);
						}
						if(statement != null) statement.close(); 
					    if(con != null)  con.close();
						return usersList;		
					}
					
					public static UserProfile getUserFromId(int id) throws SQLException, InstantiationException, IllegalAccessException {
						UserProfile user = new UserProfile();
						int userId;
						String name;
						String secondName;
						String email;
						String birthday;
						
						String SQL_select_users_list = "SELECT * FROM users WHERE userId=" + id + ";";
						
						Connection con = DriverManager.getConnection(url, username, password);
						Statement statement = null;
						statement = con.createStatement();
						ResultSet rs = statement.executeQuery(SQL_select_users_list);
						while (rs.next()) {
							user = new UserProfile();
							userId = rs.getInt("userId");
							name = rs.getString("name");
							secondName = rs.getString("secondName");
							email = rs.getString("email");
							birthday = rs.getString("birthday");
							user.setUserId(userId);
							user.setName(name);
							user.setSecondName(secondName);
							user.setEmail(email);
							user.setBirthday(birthday);				
						}
						if(statement != null) statement.close(); 
					    if(con != null)  con.close();
						return user;		
					}
}
