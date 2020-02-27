package ru.unisuite.jdbc.wrapper;

import java.sql.SQLException;

class RetryClauseStateDiscarded implements RetryClause {
    // "ORA-04068: existing state of packages has been discarded" ("существующее состояние было сброшено")
    private static final int ERROR_CODE = 4068;
    private static final String SQL_STATE = "72000";

    @Override
    public boolean retryRequired(SQLException e) {
        return e.getErrorCode() == ERROR_CODE && SQL_STATE.equals(e.getSQLState());
    }
}
