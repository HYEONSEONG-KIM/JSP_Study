package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 	Factory Object[Method] Pattern
 * 		: 객체의 생성을 전담하는 factory 객체 운영
 *
 */
public class ConnectionFactory {
	
	static {
		
		Properties dbProps = new Properties();
		InputStream is = ConnectionFactory.class.getResourceAsStream("/kr/or/ddit/db/dbInfo.properties");
		
		try {
			dbProps.load(is);
			Class.forName(dbProps.getProperty("driverClassName"));
			url = dbProps.getProperty("url");
			user = dbProps.getProperty("user");
			password = dbProps.getProperty("password");
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
	}
	private static String url;
	private static String user;
	private static String password;
	
	public static Connection getConnection() throws SQLException {


		Connection conn = DriverManager.getConnection(url, user, password);	
		return conn;
	}
}
