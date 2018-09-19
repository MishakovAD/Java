package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entity.InternalMail;
import Property.Property;

public class InternalMailDB {
	public static String url = Property.getProperty("db.url");
	public static String username = Property.getProperty("db.login");
	public static String password = Property.getProperty("db.password");

	public static void addInternalMail(InternalMail internalMail)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);

		String docType = internalMail.getDocType();
		String numNPK = internalMail.getNumNPK();
		String destination = internalMail.getDestination();
		String additionalDestination = internalMail.getAdditionalDestination();
		String docTheme = internalMail.getDocTheme();
		String executor = internalMail.getExecutor();
		String note = internalMail.getNote();


		Statement statement = null;
		statement = con.createStatement();

		String SQL_insert_internalMail = "insert into internalMail (regDate, docType, numNPK, destination, additionalDestination, "
				+ "docTheme, executor, note)" + " values (now(), '"
				+ docType + "', '" + numNPK + "', '" + destination + "', '" + additionalDestination + "', '" + docTheme + "', '"
				+ executor + "', '" + note + "');";

		statement.execute(SQL_insert_internalMail);

		if (statement != null)
			statement.close();
		if (con != null)
			con.close();

	}
	
	public static void addInternalMailFromExcel(InternalMail internalMail)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);

		String regDate = internalMail.getRegDate();
		String docType = internalMail.getDocType();
		String numNPK = internalMail.getNumNPK();
		String destination = internalMail.getDestination();
		String additionalDestination = internalMail.getAdditionalDestination();
		String docTheme = internalMail.getDocTheme();
		String executor = internalMail.getExecutor();
		String note = internalMail.getNote();

		Statement statement = null;
		statement = con.createStatement();
		

		String SQL_insert_internalMail = "insert into internalMail (regDate, docType, numNPK, destination, additionalDestination, "
				+ "docTheme, executor, note)" + " values ('" + regDate + "', '"
				+ docType + "', '" + numNPK + "', '" + destination + "', '" + additionalDestination + "', '" + docTheme + "', '"
				+ executor + "', '" + note + "');";

		statement.execute(SQL_insert_internalMail);

		if (statement != null)
			statement.close();
		if (con != null)
			con.close();

	}

	public static ArrayList<InternalMail> getInternalMail()
			throws SQLException, InstantiationException, IllegalAccessException {
		InternalMail internalMail = new InternalMail();
		ArrayList<InternalMail> listInternalMailFromMethodGet = new ArrayList<>();

		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_internalMail = "SELECT * FROM internalMail ORDER BY idMail DESC;";

		statement.execute(SQL_get_internalMail);
		ResultSet rs = statement.executeQuery(SQL_get_internalMail);
		while (rs.next()) {
			internalMail.setRegDate(rs.getString("regDate"));
			internalMail.setIdMail(rs.getInt("idMail"));
			internalMail.setDocType(rs.getString("docType"));
			internalMail.setNumNPK(rs.getString("numNPK"));
			internalMail.setDestination(rs.getString("destination"));
			internalMail.setAdditionalDestination(rs.getString("additionalDestination"));
			internalMail.setDocTheme(rs.getString("docTheme"));
			internalMail.setExecutor(rs.getString("executor"));
			internalMail.setNote(rs.getString("note"));

			listInternalMailFromMethodGet.add(internalMail);
			internalMail = new InternalMail();
		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return listInternalMailFromMethodGet;
		// Нужно определить, что лучше:
		/*
		 * возвращать массив, созданный в этом методе или же просто наполнять уже
		 * созданный массив в другом классе
		 */

	}

	public static InternalMail getInternalMailToId(int id)
			throws SQLException, InstantiationException, IllegalAccessException {
		InternalMail internalMail = new InternalMail();

		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_internalMail_to_id = "SELECT * FROM internalMail WHERE idMail=" + id + ";";

		ResultSet rs = statement.executeQuery(SQL_get_internalMail_to_id);
		while (rs.next()) {
			internalMail.setRegDate(rs.getString("regDate"));
			internalMail.setIdMail(rs.getInt("idMail"));
			internalMail.setDocType(rs.getString("docType"));
			internalMail.setNumNPK(rs.getString("numNPK"));
			internalMail.setDestination(rs.getString("destination"));
			internalMail.setAdditionalDestination(rs.getString("additionalDestination"));
			internalMail.setDocTheme(rs.getString("docTheme"));
			internalMail.setExecutor(rs.getString("executor"));
			internalMail.setNote(rs.getString("note"));
		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return internalMail;

	}


	public static int getLastIndexInternalMail() throws SQLException, InstantiationException, IllegalAccessException {
		int lastId = 0;

		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_get_internalMail_to_id = "SELECT idMail FROM internalMail ORDER BY idMail DESC LIMIT 1;";

		ResultSet rs = statement.executeQuery(SQL_get_internalMail_to_id);
		while (rs.next()) {
			lastId = rs.getInt("idMail");
		}
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
		return lastId + 1;

	}

	public static void deleteInternalMail(int id) throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String SQL_delete_internalMail_to_id = "DELETE FROM internalMail WHERE idMail=" + id + ";";

		statement.executeUpdate(SQL_delete_internalMail_to_id);
		if (statement != null)
			statement.close();
		if (con != null)
			con.close();
	}

	public static void updateInternalMail(InternalMail internalMail)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = DriverManager.getConnection(url, username, password);
		Statement statement = null;
		statement = con.createStatement();

		String docType = internalMail.getDocType();
		String numNPK = internalMail.getNumNPK();
		String destination = internalMail.getDestination();
		String additionalDestination = internalMail.getAdditionalDestination();
		String docTheme = internalMail.getDocTheme();
		String executor = internalMail.getExecutor();
		String note = internalMail.getNote();

		String SQL_update_internalMail = "UPDATE internalMail SET docType='" + docType + "', numNPK='" + numNPK
				+ "', destination='" + destination + "', additionalDestination='" + additionalDestination
				+ "', docTheme='" + docTheme + "', executor='" + executor
				+ "', note='" + note + "' WHERE idMail=" + internalMail.getIdMail() + ";";

		statement.executeUpdate(SQL_update_internalMail);

		if (statement != null)
			statement.close();
		if (con != null)
			con.close();

	}

	public static void main(String[] args) {
		for (int i = 1; i < 11; i++) {
			try {
				deleteInternalMail(i);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
