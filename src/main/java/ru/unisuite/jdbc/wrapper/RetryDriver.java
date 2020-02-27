package ru.unisuite.jdbc.wrapper;

import oracle.jdbc.OracleDriver;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This JDBC driver wraps Oracle JDBC driver 12.2.0.1
 * with following additional behavior:
 * when "ORA-04068: existing state of packages has been discarded" ("существующее состояние было сброшено") happens
 * method will be retried once.
 */
public final class RetryDriver implements Driver {
    private static Logger logger = Logger.getLogger(RetryDriver.class.getName());

    // customizable jdbc url prefix
    private static final String CUSTOM_URL_PREFIX = "jdbc:unisuite-retry:thin:";
    // standard oracle jdbc url prefix, DO NOT TOUCH
    private static final String ORACLE_JDBC_URL_PREFIX = "jdbc:oracle:thin:";

    private static final DriverPropertyInfo[] DRIVER_PROPERTY_INFO = new DriverPropertyInfo[0];

    private static Driver driver;

    private final SqlExecutor sqlExecutor = new RetrySqlExecutor(new RetryClauseStateDiscarded());

    static {
        try {
            DriverManager.registerDriver(new RetryDriver());
            driver = new OracleDriver();
        } catch (Exception e) {
            String errorMessage = "Could not register JDBC driver '" + RetryDriver.class + "'";
            logger.log(Level.SEVERE, errorMessage, e);
            throw new DriverRegistrationException(errorMessage, e);
        }
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        String jdbcUrl = url.replaceFirst(CUSTOM_URL_PREFIX, ORACLE_JDBC_URL_PREFIX);
        return new RetryConnectionWrapper(driver.connect(jdbcUrl, info), sqlExecutor);
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
        return url != null && url.startsWith(CUSTOM_URL_PREFIX);
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
