package ru.unisuite.jdbc.wrapper;

import java.sql.SQLException;

interface SqlExecutor {

    <T> T executeWithRetry(SqlSupplier<T> sqlSupplier) throws SQLException;

}
