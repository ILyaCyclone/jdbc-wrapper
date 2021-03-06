package ru.unisuite.jdbc.wrapper;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.OracleDriver;

public final class RetryStateDiscardedDriverWrapper implements Driver {
	private static final DriverPropertyInfo[] DRIVER_PROPERTY_INFO = new DriverPropertyInfo[0];

	public static final String ACCEPTABLE_URL_PREFIX = "jdbc:unisuite-wrapper:thin:";

	private static Logger logger = Logger.getLogger(RetryStateDiscardedDriverWrapper.class.getName());
	
	private static Driver driver;

	static {
		try {
			DriverManager.registerDriver(new RetryStateDiscardedDriverWrapper());
			driver = new OracleDriver();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Can't registrate RetryStateDiscardedDriverWrapper. " + e.toString(), e);
			throw new RuntimeException("Can't registrate RetryStateDiscardedDriverWrapper. " + e.toString(), e);
		} 
	}

	public Connection connect(String url, Properties info) throws SQLException {
		String myUrl = url.replaceFirst(ACCEPTABLE_URL_PREFIX, "jdbc:oracle:thin:");
		return new RetryStateDiscardedConnectionWrapper(driver.connect(myUrl, info));
	}

	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		return DRIVER_PROPERTY_INFO;
	}

	public boolean jdbcCompliant() {
		return true;
	}

	public boolean acceptsURL(String url) throws SQLException {
		return url != null && url.startsWith(ACCEPTABLE_URL_PREFIX);
	}

	public int getMinorVersion() {
		return 0;
	}

	public int getMajorVersion() {
		return 1;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return driver.getParentLogger();
	}
}
