package kr.or.ddit.db;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Test;

public class ConnectionFactoryTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetConnection() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		assertNotNull(conn);
	}

}
