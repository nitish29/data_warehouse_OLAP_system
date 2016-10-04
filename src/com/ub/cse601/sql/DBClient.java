package com.ub.cse601.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBClient {

	//private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String URL = "jdbc:oracle:thin:@aos.acsu.buffalo.edu:1521/aos.buffalo.edu";
	//private static final String USER_NAME = "BIOMED_DATA";
	private static final String USER_NAME = "nitishgo";
	private static final String PWD = "cse601";
	private Connection conn = null;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public DBClient() {
		try {
			conn = initConnection();
		} catch (Exception ex) {
			System.out.println("Exception in connection: "+ex.getMessage());
		}

	}

	private static Connection initConnection() throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(URL, USER_NAME, PWD);
		conn.createStatement();
		return conn;

	}
	
	public static void main(String[] args){
		DBClient client = new DBClient();
	}

}
