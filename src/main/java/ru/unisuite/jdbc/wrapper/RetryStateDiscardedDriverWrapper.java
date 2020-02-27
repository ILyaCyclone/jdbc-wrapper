package ru.unisuite.jdbc.wrapper;

import oracle.jdbc.OracleDriver;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class RetryStateDiscardedDriverWrapper implements Driver {
	private static final DriverPropertyInfo[] DRIVER_PROPERTY_INFO = new DriverPropertyInfo[0];

	private static final String WRAPPER_ACCEPTABLE_URL_PREFIX = "jdbc:unisuite-wrapper:thin:";
	private static final String ORACLE_JDBC_URL_PREFIX = "jdbc:oracle:thin:";

	private static Logger logger = Logger.getLogger(RetryStateDiscardedDriverWrapper.class.getName());

	private static Driver driver;

	static {
		try {
			DriverManager.registerDriver(new RetryStateDiscardedDriverWrapper());
			driver = new OracleDriver();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Can't register RetryStateDiscardedDriverWrapper", e);
			throw new RuntimeException("Can't register RetryStateDiscardedDriverWrapper", e);
		}
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		String jdbcUrl = url.replaceFirst(WRAPPER_ACCEPTABLE_URL_PREFIX, ORACLE_JDBC_URL_PREFIX);
		return new RetryStateDiscardedConnectionWrapper(driver.connect(jdbcUrl, info));
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		return DRIVER_PROPERTY_INFO;
	}

	@Override
	public boolean jdbcCompliant() {
		return true;
	}

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		return url != null && url.startsWith(WRAPPER_ACCEPTABLE_URL_PREFIX);
	}

	@Override
	public int getMinorVersion() {
		return 0;
	}

	@Override
	public int getMajorVersion() {
		return 1;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return driver.getParentLogger();
	}
}
