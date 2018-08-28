package ru.unisuite.jdbc.wrapper;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import oracle.jdbc.OracleDriver;

public final class MyDriverWrapper implements Driver {
	private static final DriverPropertyInfo[] DRIVER_PROPERTY_INFO = new DriverPropertyInfo[0];

//	public static final String ACCEPTABLE_URL_PREFIX = "jdbc:dbj2ee:orawrapper:";

	private static Driver driver = new OracleDriver();

	static {
		try {
			DriverManager.registerDriver(new MyDriverWrapper());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public Connection connect(String url, Properties info) throws SQLException {
//		String myUrl = url.replaceFirst(ACCEPTABLE_URL_PREFIX, "jdbc:oracle:thin:");
		return new MyConnectionWrapper(driver.connect(url, info));
	}

	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		return DRIVER_PROPERTY_INFO;
	}

	public boolean jdbcCompliant() {
		return true;
	}

	public boolean acceptsURL(String url) throws SQLException {
		return url != null;// && url.startsWith(ACCEPTABLE_URL_PREFIX);
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
