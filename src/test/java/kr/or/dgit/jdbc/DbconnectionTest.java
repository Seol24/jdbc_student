package kr.or.dgit.jdbc;

import static org.junit.Assert.fail;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.jdbc.util.ConnectionFactory;

public class DbconnectionTest {
	private static Connection connection;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		connection = ConnectionFactory.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		connection = null;
	}

	@Test
	public void test() {
		Assert.assertNotNull(connection);
	}

}
