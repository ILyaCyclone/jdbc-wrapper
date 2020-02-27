package ru.unisuite.jdbc.wrapper;

import java.sql.SQLException;

public class WrapperUtils {
	
	private static final int ERROR_CODE = 4068;
	private static final String SQL_STATE = "72000";

	public static boolean retryRequired(SQLException e) {
		return e.getErrorCode() == ERROR_CODE && SQL_STATE.equals(e.getSQLState());
	}
	
}
