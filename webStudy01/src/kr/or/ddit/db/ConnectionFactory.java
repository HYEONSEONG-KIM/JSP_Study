package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

/**
 * 	Factory Object[Method] Pattern
 * 		: 객체의 생성을 전담하는 factory 객체 운영
 *
 */
public class ConnectionFactory {
	
	private static String url;
	private static String user;
	private static String password;
	private static DataSource dataSource;
	
	static {
		
		Properties dbProps = new Properties();
		InputStream is = ConnectionFactory.class.getResourceAsStream("/kr/or/ddit/db/dbInfo.properties");
		
		try {
			dbProps.load(is);
//			Class.forName(dbProps.getProperty("driverClassName"));
			url = dbProps.getProperty("url");
			user = dbProps.getProperty("user");
			password = dbProps.getProperty("password");
			
//			OracleConnectionPoolDataSource ds = new OracleConnectionPoolDataSource();
//			ds.setURL(url);
//			ds.setUser(user);
//			ds.setPassword(password);
			BasicDataSource ds = new BasicDataSource();
			
			ds.setDriverClassName(dbProps.getProperty("driverClassName"));
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(password);
			
			int initialSize = Integer.parseInt(dbProps.getProperty("initialSize"));
			long maxWait = Long.parseLong(dbProps.getProperty("maxWait"));
			int maxTotal =Integer.parseInt(dbProps.getProperty("maxTotal"));
			int maxIdle = Integer.parseInt(dbProps.getProperty("maxIdle"));
			
			// 처음 풀링 갯수
			ds.setInitialSize(initialSize);
			// 자원이 없을시 최대 기다리는 시간
			ds.setMaxWaitMillis(maxWait);
			// 최대 풀링 갯수, 이후에는 exception발생
			ds.setMaxTotal(maxTotal);
			ds.setMaxIdle(maxIdle);
			dataSource = ds;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	
	public static Connection getConnection() throws SQLException {


//		Connection conn = DriverManager.getConnection(url, user, password);	
		Connection conn = dataSource.getConnection();
		return conn;
	}
}
