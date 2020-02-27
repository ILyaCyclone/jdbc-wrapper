package ru.unisuite.jdbc.driver.retry;

import java.sql.SQLException;

class RetryClauseStateDiscarded implements RetryClause {
    private static final int ERROR_CODE = 4068;
    private static final String SQL_STATE = "72000";

    public boolean retryRequired(SQLException e) {
        return e.getErrorCode() == ERROR_CODE && SQL_STATE.equals(e.getSQLState());
    }
}
