package ru.unisuite.jdbc.wrapper;

import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

class RetrySqlExecutor implements SqlExecutor {
    private static Logger logger = Logger.getLogger(RetrySqlExecutor.class.getName());

    private final RetryClause retryClause;

    RetrySqlExecutor(RetryClause retryClause) {
        this.retryClause = retryClause;
    }

    @Override
    public <T> T executeWithRetry(SqlSupplier<T> sqlSupplier) throws SQLException {
        try {
            return sqlSupplier.execute();
        } catch (SQLException e) {
            if (retryClause.retryRequired(e)) {
                String packageName = extractPackageFromSqlException(e).orElse("unknown");
                logger.log(Level.WARNING, "Package \"" + packageName + "\" state discarded, retrying sql execution");
                return sqlSupplier.execute();
            } else
                throw e;
        }
    }

    /**
     * relies on Oracle Driver sql exception message format:
     * java.sql.SQLException: ORA-04068: существующее состояние пакетов было сброшено
     * ORA-04061: существующее состояния package "PILOT.WP_TIMETABLE" стало неприемлемым
     * ORA-04065: не выполнено, package "PILOT.WP_TIMETABLE" изменено или удалено
     * <p>
     * should extract PILOT.WP_TIMETABLE
     */
    private Optional<String> extractPackageFromSqlException(SQLException e) {
        String message = e.getMessage();
        int indexOfPackageName = message.indexOf("package \"");
        if (indexOfPackageName != -1) {
            String startsWithPackageName = message.substring(indexOfPackageName + "package \"".length());
            int indexOfQuote = startsWithPackageName.indexOf('"');
            if (indexOfQuote != -1) {
                String packageName = startsWithPackageName.substring(0, indexOfQuote);
                return Optional.of(packageName);
            }
        }
        return Optional.empty();
    }
}
