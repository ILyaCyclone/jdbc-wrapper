package ru.unisuite.jdbc.wrapper;

import java.sql.SQLException;

@FunctionalInterface
public interface SqlSupplier<T> {
    T execute() throws SQLException;
}
