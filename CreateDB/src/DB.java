import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//create db on server
public class DB {

	private static String url = "jdbc:mysql://localhost:3307/niikp_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String username = "root";
	private static String password = "root";

	// ��� ���������� ������� ������ � ��
	public static void connect() throws SQLException, InstantiationException, IllegalAccessException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loading success!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// ��������� ����������
		Connection con = DriverManager.getConnection(url, username, password);
		System.out.println("Connected.");

		Statement statement = null;
		statement = con.createStatement();
		// �������� ������� � ��
//			String SQL = "CREATE DATABASE `niikp_db` CHARACTER SET utf8 COLLATE utf8_general_ci;";
//			statement.executeUpdate(SQL);
//			System.out.println("BD successfully created...");
		String SQL = "CREATE TABLE users " + "(userId INTEGER not NULL AUTO_INCREMENT, "
				+ " dateRegistration DATETIME not NULL, " + " name VARCHAR (100), " + " secondName VARCHAR (100), "
				+ " middleName VARCHAR (100), " + " email VARCHAR (50) not NULL, "
				+ " password VARCHAR (100) not NULL, " + " birthday DATE not NULL, " + " phoneNumber VARCHAR (15), "
				+ " roomNumber VARCHAR (10), " + " position VARCHAR (100), " + " userGroup VARCHAR (50), "
				+ " privilege VARCHAR (50), " + " PRIMARY KEY (userId))";

//		statement.executeUpdate(SQL);
//		System.out.println("Table successfully created...");
//
		SQL = "CREATE TABLE incomingMail " + "(regDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
				+ " idMail INTEGER not NULL AUTO_INCREMENT, " + " typeMail VARCHAR (500), " + " sender VARCHAR (150), "
				+ " sendDate VARCHAR (100), " + " mailNum VARCHAR (50), " + " mailTheme VARCHAR (500), "
				+ " secondFloorDate VARCHAR (50), " + " secondFloorNum VARCHAR (100), "
				+ " onControl BOOLEAN DEFAULT false, " + " needTalk BOOLEAN DEFAULT false, "
				+ " filePathAndName VARCHAR (100), " + " PRIMARY KEY (idMail))";

		statement.executeUpdate(SQL);
		System.out.println("Table successfully created...");

		SQL = "CREATE TABLE outgoingMail " + "(regDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
				+ " idMail INTEGER not NULL AUTO_INCREMENT, " + " mailNum VARCHAR (50), "
				+ " destination VARCHAR (50), " + " forWhom VARCHAR (50), " + " sendDate VARCHAR (50), "
				+ " mailTheme VARCHAR (500), " + " executor VARCHAR (50), " + " realExecutor VARCHAR (50), "
				+ " incomingMailNum VARCHAR (50), " + " toWhomItIsPainted VARCHAR (50), " + " incomingMailId INT, "
				+ " note VARCHAR (500), " + " mailingNote VARCHAR (500), " + " filePathAndName VARCHAR (100), "
				+ " isDeleted BOOLEAN DEFAULT false, " + " PRIMARY KEY (idMail))";
		statement.executeUpdate(SQL);
		System.out.println("Table successfully created...");
//	
		SQL = "CREATE TABLE work " + "(workId INTEGER not NULL AUTO_INCREMENT, " + " assigmentSourse VARCHAR (500), "
				+ " toUserId INTEGER, " + " Co_executor VARCHAR (500), " + " observerId INTEGER, "
				+ " fromUserId INTEGER, " + " startDate VARCHAR (50), " + " endDate VARCHAR (50), "
				+ " finishDate TIMESTAMP, " + " assignment VARCHAR (500), " + " mailId VARCHAR (50), "
				+ " filePathAndNameToWork VARCHAR (100), " + " isComplete BOOLEAN DEFAULT NULL, "
				+ " isAccept VARCHAR (10), " + " report VARCHAR (500), "
				+ " reportFilePathAndNameToWork VARCHAR (150), " + " comment VARCHAR (500), " + " template VARCHAR (50), "
				+ " PRIMARY KEY (workId))";
//
		statement.executeUpdate(SQL);
		System.out.println("Table successfully created...");

		SQL = "CREATE TABLE workForCo_executor " + "(workForCo_executorId INTEGER not NULL AUTO_INCREMENT, "
				+ " assigmentSourse VARCHAR (500), " + " toUserId INTEGER, " + " Co_executor VARCHAR (500), "
				+ " observerId INTEGER, " + " fromUserId INTEGER, " + " startDate VARCHAR (50), "
				+ " endDate VARCHAR (50), " + " finishDate TIMESTAMP, " + " assignment VARCHAR (500), "
				+ " mailId VARCHAR (50), " + " filePathAndNameToWork VARCHAR (100), "
				+ " isComplete BOOLEAN DEFAULT NULL, " + " isAccept VARCHAR (10), " + " report VARCHAR (500), "
				+ " reportFilePathAndNameToWork VARCHAR (150), " + " comment VARCHAR (500), " + " template VARCHAR (50), "
				+ " PRIMARY KEY (workForCo_executorId))";
//
		statement.executeUpdate(SQL);
		System.out.println("Table successfully created...");

//	
		SQL = "CREATE TABLE internalMail " + "(regDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
				+ " idMail INTEGER not NULL AUTO_INCREMENT, " + " docType VARCHAR (50), " + " numNPK VARCHAR (50), "
				+ " destination VARCHAR (50), " + " additionalDestination VARCHAR (50), " + " docTheme VARCHAR (500), "
				+ " executor VARCHAR (50), " + " note VARCHAR (500), " + " PRIMARY KEY (idMail))";
		statement.executeUpdate(SQL);
		System.out.println("Table successfully created...");
//
		SQL = "CREATE TABLE workWithTemplate " + "(workId INTEGER not NULL AUTO_INCREMENT, "
				+ " workTemplateId INTEGER not NULL, " + " toUserId INTEGER, " + " observerId INTEGER, "
				+ " fromUserId INTEGER, " + " startDate TIMESTAMP, " + " endDate TIMESTAMP, "
				+ " assignment VARCHAR (500), " + " mailId VARCHAR (50), " + " filePathAndNameToWork VARCHAR (100), "
				+ " isComplete BOOLEAN DEFAULT NULL, " + " isAccept VARCHAR (10), " + " report VARCHAR (500), "
				+ " reportFilePathAndNameToWork VARCHAR (150), " + " template VARCHAR (50), "
				+ " PRIMARY KEY (workId))";
//
		statement.executeUpdate(SQL);
		System.out.println("Table successfully created...");

		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		System.out.println("Table 3 successfully created...");
	}

	public static void main(String[] args) {
		try {
			connect();
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}
	}
}
