package ru.unisuite.jdbc.driver.retry;

import java.sql.SQLException;

interface RetryClause {

	boolean retryRequired(SQLException e);

}
