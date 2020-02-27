package ru.unisuite.jdbc.wrapper;

import java.sql.SQLException;

interface RetryClause {

    boolean retryRequired(SQLException e);

}
