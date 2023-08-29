package lab4.db;

import java.sql.Connection;


import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import lab4.db.model.User;
import lab4.db.model.Role;
import lab4.db.model.Task;
import lab4.db.model.Status;

import lab4.db.DbConnector;
public class DbConnector {

	// DB URL with HOST IP, PORT and DB NAME
	private static final String DB_URL = "jdbc:mysql://localhost:3306/exam0623db";
	// DB credentials
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "";
	
	// DB Connection - Used by the other methods of this class
	private Connection conn;
	
	// Singleton Design Pattern
	private static DbConnector instance = null;
	/** Ensure that we will create only one instance of this class */
	public static DbConnector getInstance() {
		synchronized (DbConnector.class) {
			if (instance == null) {
				instance  = new DbConnector();
			}
			return instance;
		}
	}
	private DbConnector() {
		
	}
	
	/** Open DB Connection*/
	public void openDbConnection() throws SQLException, ClassNotFoundException {
		// DB Connection Properties
		final Properties DB_PROP = new Properties();
		DB_PROP.setProperty("user"	, DB_USERNAME);
		DB_PROP.setProperty("password", DB_PASSWORD);
		DB_PROP.setProperty("charSet", "UTF-8");
		
		// Ensure that the DB Connector (i.e., Java Class) is available in your CLASSPATH
		Class.forName("com.mysql.cj.jdbc.Driver");
				
		// Get DB Connection
		this.conn = DriverManager.getConnection(DB_URL, DB_PROP);
	}
	
	private static final String INSERT_USER_SQL_QUERY = 
		"INSERT INTO USERS VALUES (null, ?, ?, now(), 1)";
	
	public int insertUser(User user) throws SQLException {
		final PreparedStatement ps = conn.prepareStatement(INSERT_USER_SQL_QUERY);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPasswordHash());
		final int response = ps.executeUpdate();
		ps.close();
		return response;
	}
	
	private static final String SELECT_USER_SQL_QUERY = 
		"SELECT * FROM USER WHERE username = ? and userpass = ?";
	
	public Role getRole(final String username, final String passwordHash) throws SQLException {
		Role user = null;
		final PreparedStatement ps = conn.prepareStatement(SELECT_USER_SQL_QUERY);
		ps.setString(1, username);
		ps.setString(2, passwordHash);
		final ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			final int id = rs.getInt("ROLE_ID");
			final String type = rs.getString("NAME");
			user = new Role(id, type);
			break;
		}
		rs.close();
		ps.close();
		return user;
	}
	
	private static final String SELECT_ROLE_SQL_QUERY = 
			"SELECT * FROM USER , ROLE WHERE USER.ROLE_ID=ROLE.ID AND USERNAME=? AND\r\n"
			+ "USERPASS=?";
		
		public User getUser(final String username, final String passwordHash) throws SQLException {
			User user = null;
			final PreparedStatement ps = conn.prepareStatement(SELECT_ROLE_SQL_QUERY);
			ps.setString(1, username);
			ps.setString(2, passwordHash);
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				final int id = rs.getInt("ID");
				final int type = rs.getInt("ROLE_ID");
				final String tname = rs.getString("NAME");
				user = new User(id, username, passwordHash, type,tname);
				break;
			}
			rs.close();
			ps.close();
			return user;
		}
	
	private static final String SELECT_ALL_TASKS_SQL_QUERY = 
			" SELECT * FROM TASK , STATUS WHERE TASK.STATUS_ID = STATUS.ID AND USER_ID = ?";

	public List<Task> getTasks(final int useridd) throws SQLException {
		final List<Task> ResList = new ArrayList<>();
		final PreparedStatement ps = conn.prepareStatement(SELECT_ALL_TASKS_SQL_QUERY);
		ps.setInt(1, useridd);
		final ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			final Integer userid = rs.getInt(2);
			final Integer taskid = rs.getInt(1);
			final String title = rs.getString(3);
			final String comments = rs.getString(4);
			final Date startdate = rs.getDate(6);
			final Integer s=rs.getInt(5);
			ResList.add(new Task(taskid,userid, startdate, title, comments,s));
		}
		rs.close();
		ps.close();
		return ResList;
	}
	
	private static final String SELECT_ALL_STATUS_SQL_QUERY = 
			" SELECT * FROM STATUS";

	public List<Status> getStatus() throws SQLException {
		final List<Status> SList = new ArrayList<>();
		final PreparedStatement ps = conn.prepareStatement(SELECT_ALL_STATUS_SQL_QUERY);
		final ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			final Integer id = rs.getInt(1);
			final String name = rs.getString(2);
			SList.add(new Status(id, name));
		}
		rs.close();
		ps.close();
		return SList;
	}
	
	private static final String UPDATE_TASK_STATUS_SQL_QUERY = 
			"UPDATE TASK SET STATUS_ID = ? , DATE_UPDATED = now() WHERE ID = ?";
		
	public int updateTaskStatus(final int sid,final int id) throws SQLException {
		final PreparedStatement ps = conn.prepareStatement(UPDATE_TASK_STATUS_SQL_QUERY);
		ps.setInt(1, sid);
		ps.setInt(2, id);
		final int response = ps.executeUpdate();
		ps.close();
		return response;
	}
	/** Close DB Connection */
	public void closeDbConnection() throws SQLException {
		if (conn != null && !conn.isClosed()) {
			this.conn.close();
		}
	}

	/**
	 * For Testing Purposes ...
	 */
	public static void main(String[] args) throws Exception {
	
		System.out.println(" >> ProgramDB - Testing Place - START");
		System.out.println();
		
		final DbConnector db = DbConnector.getInstance();
		db.openDbConnection();
		
		// View Data
		/*
		final List<Task> vehicleList = db.getTasks();
		System.out.println("View-Response: vehicleList.size(): " + vehicleList.size());
		for (Task vehicle : vehicleList) {
			System.out.println(" - " + vehicle);
		}
		*/
		final User exuser = db.getUser("u2", "p2");
		System.out.println(exuser);
		
		db.closeDbConnection();
		
		System.out.println();
		System.out.println(" >> ProgramDB - Testing Place - END");
	}


}
