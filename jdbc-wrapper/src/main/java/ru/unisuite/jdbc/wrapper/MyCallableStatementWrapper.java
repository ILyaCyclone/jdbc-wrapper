package ru.unisuite.jdbc.wrapper;

import java.io.InputStream;
import java.io.Reader;
import java.util.Map;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Array;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.sql.NClob;
import java.util.Calendar;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.Time;
import java.sql.Timestamp;

public class MyCallableStatementWrapper extends MyPreparedStatementWrapper implements CallableStatement {

	private CallableStatement callableStatement;

	public MyCallableStatementWrapper(CallableStatement statement) {
		super(statement);
		this.callableStatement = (CallableStatement) statement;
	}

	public boolean execute() throws SQLException {
		boolean result = true;
		try {
			result = callableStatement.execute();
		} catch (SQLException e) {
			System.out.println("code:" + e.getErrorCode() + ", sql state: " + e.getSQLState());
			if (reExecutionRequired(e)) {
				System.out.println("re-executing package ");
				result = callableStatement.execute();
			} else
				throw e;
		}
		return result;
	}

	public int executeUpdate() throws SQLException {
		int result = 0;
		try {
			result = callableStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("code:" + e.getErrorCode() + ", sql state: " + e.getSQLState());
			if (reExecutionRequired(e)) {
				System.out.println("re-executing package ");
				result = callableStatement.executeUpdate();
			} else
				throw e;
		}
		return result;
	}

	private boolean reExecutionRequired(SQLException e) {
		return e.getErrorCode() == 4068;// && "72000".equals(e.getSQLState());
	}

	public URL getURL(int parameterIndex) throws SQLException {
		return callableStatement.getURL(parameterIndex);
	}

	@Override
	public ResultSet executeQuery() throws SQLException {
		return callableStatement.executeQuery();
	}

	@Override
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		callableStatement.setNull(parameterIndex, sqlType);
	}

	@Override
	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		callableStatement.setBoolean(parameterIndex, x);
	}

	@Override
	public void setByte(int parameterIndex, byte x) throws SQLException {
		callableStatement.setByte(parameterIndex, x);
	}

	@Override
	public void setShort(int parameterIndex, short x) throws SQLException {
		callableStatement.setShort(parameterIndex, x);
	}

	@Override
	public void setInt(int parameterIndex, int x) throws SQLException {
		callableStatement.setInt(parameterIndex, x);
	}

	@Override
	public void setLong(int parameterIndex, long x) throws SQLException {
		callableStatement.setLong(parameterIndex, x);
	}

	@Override
	public void setFloat(int parameterIndex, float x) throws SQLException {
		callableStatement.setFloat(parameterIndex, x);
	}

	@Override
	public void setDouble(int parameterIndex, double x) throws SQLException {
		callableStatement.setDouble(parameterIndex, x);
	}

	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
		callableStatement.setBigDecimal(parameterIndex, x);
	}

	@Override
	public void setString(int parameterIndex, String x) throws SQLException {
		callableStatement.setString(parameterIndex, x);
	}

	@Override
	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		callableStatement.setBytes(parameterIndex, x);
	}

	@Override
	public void setDate(int parameterIndex, Date x) throws SQLException {
		callableStatement.setDate(parameterIndex, x);
	}

	@Override
	public void setTime(int parameterIndex, Time x) throws SQLException {
		callableStatement.setTime(parameterIndex, x);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
		callableStatement.setTimestamp(parameterIndex, x);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
		callableStatement.setAsciiStream(parameterIndex, x, length);
	}

	@Override
	public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
		callableStatement.setUnicodeStream(parameterIndex, x, length);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
		callableStatement.setBinaryStream(parameterIndex, x, length);
	}

	@Override
	public void clearParameters() throws SQLException {
		callableStatement.clearParameters();
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
		callableStatement.setObject(parameterIndex, x, targetSqlType);
	}

	@Override
	public void setObject(int parameterIndex, Object x) throws SQLException {
		callableStatement.setObject(parameterIndex, x);
	}

	@Override
	public void addBatch() throws SQLException {
		callableStatement.addBatch();
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
		callableStatement.setCharacterStream(parameterIndex, reader, length);
	}

	@Override
	public void setRef(int parameterIndex, Ref x) throws SQLException {
		callableStatement.setRef(parameterIndex, x);
	}

	@Override
	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		callableStatement.setBlob(parameterIndex, x);
	}

	@Override
	public void setClob(int parameterIndex, Clob x) throws SQLException {
		callableStatement.setClob(parameterIndex, x);
	}

	@Override
	public void setArray(int parameterIndex, Array x) throws SQLException {
		callableStatement.setArray(parameterIndex, x);
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		return callableStatement.getMetaData();
	}

	@Override
	public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
		callableStatement.setDate(parameterIndex, x, cal);
	}

	@Override
	public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
		callableStatement.setTime(parameterIndex, x, cal);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
		callableStatement.setTimestamp(parameterIndex, x, cal);
	}

	@Override
	public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
		callableStatement.setNull(parameterIndex, sqlType, typeName);
	}

	@Override
	public void setURL(int parameterIndex, URL x) throws SQLException {
		callableStatement.setURL(parameterIndex, x);
	}

	@Override
	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		callableStatement.setRowId(parameterIndex, x);
	}

	@Override
	public void setNString(int parameterIndex, String value) throws SQLException {
		callableStatement.setNString(parameterIndex, value);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
		callableStatement.setNCharacterStream(parameterIndex, value, length);
	}

	@Override
	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		callableStatement.setNClob(parameterIndex, value);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
		callableStatement.setClob(parameterIndex, reader, length);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
		callableStatement.setBlob(parameterIndex, inputStream, length);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
		callableStatement.setNClob(parameterIndex, reader, length);
	}

	@Override
	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
		callableStatement.setSQLXML(parameterIndex, xmlObject);
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
		callableStatement.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
		callableStatement.setAsciiStream(parameterIndex, x, length);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
		callableStatement.setBinaryStream(parameterIndex, x, length);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		callableStatement.setCharacterStream(parameterIndex, reader, length);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
		callableStatement.setAsciiStream(parameterIndex, x);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
		callableStatement.setBinaryStream(parameterIndex, x);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		callableStatement.setCharacterStream(parameterIndex, reader);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
		callableStatement.setNCharacterStream(parameterIndex, value);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		callableStatement.setClob(parameterIndex, reader);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
		callableStatement.setBlob(parameterIndex, inputStream);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		callableStatement.setNClob(parameterIndex, reader);
	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		return callableStatement.executeQuery(sql);
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		return callableStatement.executeUpdate(sql);
	}

	@Override
	public void close() throws SQLException {
		callableStatement.close();
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		return callableStatement.getMaxFieldSize();
	}

	@Override
	public void setMaxFieldSize(int max) throws SQLException {
		callableStatement.setMaxFieldSize(max);
	}

	@Override
	public int getMaxRows() throws SQLException {
		return callableStatement.getMaxRows();
	}

	@Override
	public void setMaxRows(int max) throws SQLException {
		callableStatement.setMaxRows(max);
	}

	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
		callableStatement.setEscapeProcessing(enable);
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		return callableStatement.getQueryTimeout();
	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		callableStatement.setQueryTimeout(seconds);
	}

	@Override
	public void cancel() throws SQLException {
		callableStatement.cancel();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return callableStatement.getWarnings();
	}

	@Override
	public void clearWarnings() throws SQLException {
		callableStatement.clearWarnings();
	}

	@Override
	public void setCursorName(String name) throws SQLException {
		callableStatement.setCursorName(name);
	}

	@Override
	public boolean execute(String sql) throws SQLException {
		return callableStatement.execute(sql);
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		return callableStatement.getResultSet();
	}

	@Override
	public int getUpdateCount() throws SQLException {
		return callableStatement.getUpdateCount();
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		return callableStatement.getMoreResults();
	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		callableStatement.setFetchDirection(direction);
	}

	@Override
	public int getFetchDirection() throws SQLException {
		return callableStatement.getFetchDirection();
	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		callableStatement.setFetchSize(rows);
	}

	@Override
	public int getFetchSize() throws SQLException {
		return callableStatement.getFetchSize();
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		return callableStatement.getResultSetConcurrency();
	}

	@Override
	public int getResultSetType() throws SQLException {
		return callableStatement.getResultSetType();
	}

	@Override
	public void clearBatch() throws SQLException {
		callableStatement.clearBatch();
	}

	@Override
	public int[] executeBatch() throws SQLException {
		return callableStatement.executeBatch();
	}

	@Override
	public Connection getConnection() throws SQLException {
		return callableStatement.getConnection();
	}

	@Override
	public boolean getMoreResults(int current) throws SQLException {
		return callableStatement.getMoreResults(current);
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		return callableStatement.getGeneratedKeys();
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		return callableStatement.executeUpdate(sql, autoGeneratedKeys);
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		return callableStatement.executeUpdate(sql, columnIndexes);
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		return callableStatement.executeUpdate(sql, columnNames);
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		return callableStatement.execute(sql, autoGeneratedKeys);
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		return callableStatement.execute(sql, columnIndexes);
	}

	@Override
	public boolean execute(String sql, String[] columnNames) throws SQLException {
		return callableStatement.execute(sql, columnNames);
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		return callableStatement.getResultSetHoldability();
	}

	@Override
	public boolean isClosed() throws SQLException {
		return callableStatement.isClosed();
	}

	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		callableStatement.setPoolable(poolable);
	}

	@Override
	public boolean isPoolable() throws SQLException {
		return callableStatement.isPoolable();
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		callableStatement.closeOnCompletion();
	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		return callableStatement.isCloseOnCompletion();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return callableStatement.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return callableStatement.isWrapperFor(iface);
	}

	public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
		callableStatement.registerOutParameter(parameterIndex, sqlType);
	}

	public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
		callableStatement.registerOutParameter(parameterIndex, sqlType, scale);
	}

	public boolean wasNull() throws SQLException {
		return callableStatement.wasNull();
	}

	public String getString(int parameterIndex) throws SQLException {
		return callableStatement.getString(parameterIndex);
	}

	public boolean getBoolean(int parameterIndex) throws SQLException {
		return callableStatement.getBoolean(parameterIndex);
	}

	public byte getByte(int parameterIndex) throws SQLException {
		return callableStatement.getByte(parameterIndex);
	}

	public short getShort(int parameterIndex) throws SQLException {
		return callableStatement.getShort(parameterIndex);
	}

	public int getInt(int parameterIndex) throws SQLException {
		return callableStatement.getInt(parameterIndex);
	}

	public long getLong(int parameterIndex) throws SQLException {
		return callableStatement.getLong(parameterIndex);
	}

	public float getFloat(int parameterIndex) throws SQLException {
		return callableStatement.getFloat(parameterIndex);
	}

	public double getDouble(int parameterIndex) throws SQLException {
		return callableStatement.getDouble(parameterIndex);
	}

	public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
		return callableStatement.getBigDecimal(parameterIndex, scale);
	}

	public byte[] getBytes(int parameterIndex) throws SQLException {
		return callableStatement.getBytes(parameterIndex);
	}

	public Date getDate(int parameterIndex) throws SQLException {
		return callableStatement.getDate(parameterIndex);
	}

	public Time getTime(int parameterIndex) throws SQLException {
		return callableStatement.getTime(parameterIndex);
	}

	public Timestamp getTimestamp(int parameterIndex) throws SQLException {
		return callableStatement.getTimestamp(parameterIndex);
	}

	public Object getObject(int parameterIndex) throws SQLException {
		return callableStatement.getObject(parameterIndex);
	}

	public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
		return callableStatement.getBigDecimal(parameterIndex);
	}

	public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
		return callableStatement.getObject(parameterIndex, map);
	}

	public Ref getRef(int parameterIndex) throws SQLException {
		return callableStatement.getRef(parameterIndex);
	}

	public Blob getBlob(int parameterIndex) throws SQLException {
		return callableStatement.getBlob(parameterIndex);
	}

	public Clob getClob(int parameterIndex) throws SQLException {
		return callableStatement.getClob(parameterIndex);
	}

	
	public Array getArray(int parameterIndex) throws SQLException {
		return callableStatement.getArray(parameterIndex);
	}

	
	public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
		return callableStatement.getDate(parameterIndex, cal);
	}

	
	public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
		return callableStatement.getTime(parameterIndex, cal);
	}

	
	public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
		return callableStatement.getTimestamp(parameterIndex, cal);
	}

	
	public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
		callableStatement.registerOutParameter(parameterIndex, sqlType, typeName);
	}

	
	public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
		callableStatement.registerOutParameter(parameterName, sqlType);
	}

	
	public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
		callableStatement.registerOutParameter(parameterName, sqlType, scale);
	}

	
	public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
		callableStatement.registerOutParameter(parameterName, sqlType, typeName);
	}

	
	public void setURL(String parameterName, URL val) throws SQLException {
		callableStatement.setURL(parameterName, val);
	}

	
	public void setNull(String parameterName, int sqlType) throws SQLException {
		callableStatement.setNull(parameterName, sqlType);
	}

	
	public void setBoolean(String parameterName, boolean x) throws SQLException {
		callableStatement.setBoolean(parameterName, x);
	}

	
	public void setByte(String parameterName, byte x) throws SQLException {
		callableStatement.setByte(parameterName, x);
	}

	
	public void setShort(String parameterName, short x) throws SQLException {
		callableStatement.setShort(parameterName, x);
	}

	
	public void setInt(String parameterName, int x) throws SQLException {
		callableStatement.setInt(parameterName, x);
	}

	
	public void setLong(String parameterName, long x) throws SQLException {
		callableStatement.setLong(parameterName, x);
	}

	
	public void setFloat(String parameterName, float x) throws SQLException {
		callableStatement.setFloat(parameterName, x);
	}

	
	public void setDouble(String parameterName, double x) throws SQLException {
		callableStatement.setDouble(parameterName, x);
	}

	
	public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
		callableStatement.setBigDecimal(parameterName, x);
	}

	
	public void setString(String parameterName, String x) throws SQLException {
		callableStatement.setString(parameterName, x);
	}

	
	public void setBytes(String parameterName, byte[] x) throws SQLException {
		callableStatement.setBytes(parameterName, x);
	}

	
	public void setDate(String parameterName, Date x) throws SQLException {
		callableStatement.setDate(parameterName, x);
	}

	
	public void setTime(String parameterName, Time x) throws SQLException {
		callableStatement.setTime(parameterName, x);
	}

	
	public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
		callableStatement.setTimestamp(parameterName, x);
	}

	
	public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
		callableStatement.setAsciiStream(parameterName, x, length);
	}

	
	public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
		callableStatement.setBinaryStream(parameterName, x, length);
	}

	
	public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
		callableStatement.setObject(parameterName, x, targetSqlType, scale);
	}

	
	public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
		callableStatement.setObject(parameterName, x, targetSqlType);
	}

	
	public void setObject(String parameterName, Object x) throws SQLException {
		callableStatement.setObject(parameterName, x);
	}

	
	public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
		callableStatement.setCharacterStream(parameterName, reader, length);
	}

	
	public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
		callableStatement.setDate(parameterName, x, cal);
	}

	
	public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
		callableStatement.setTime(parameterName, x, cal);
	}

	
	public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
		callableStatement.setTimestamp(parameterName, x, cal);
	}

	
	public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
		callableStatement.setNull(parameterName, sqlType, typeName);
	}

	
	public String getString(String parameterName) throws SQLException {
		return callableStatement.getString(parameterName);
	}

	
	public boolean getBoolean(String parameterName) throws SQLException {
		return callableStatement.getBoolean(parameterName);
	}

	
	public byte getByte(String parameterName) throws SQLException {
		return callableStatement.getByte(parameterName);
	}

	
	public short getShort(String parameterName) throws SQLException {
		return callableStatement.getShort(parameterName);
	}

	
	public int getInt(String parameterName) throws SQLException {
		return callableStatement.getInt(parameterName);
	}

	
	public long getLong(String parameterName) throws SQLException {
		return callableStatement.getLong(parameterName);
	}

	
	public float getFloat(String parameterName) throws SQLException {
		return callableStatement.getFloat(parameterName);
	}

	
	public double getDouble(String parameterName) throws SQLException {
		return callableStatement.getDouble(parameterName);
	}

	
	public byte[] getBytes(String parameterName) throws SQLException {
		return callableStatement.getBytes(parameterName);
	}

	
	public Date getDate(String parameterName) throws SQLException {
		return callableStatement.getDate(parameterName);
	}

	
	public Time getTime(String parameterName) throws SQLException {
		return callableStatement.getTime(parameterName);
	}

	
	public Timestamp getTimestamp(String parameterName) throws SQLException {
		return callableStatement.getTimestamp(parameterName);
	}

	
	public Object getObject(String parameterName) throws SQLException {
		return callableStatement.getObject(parameterName);
	}

	
	public BigDecimal getBigDecimal(String parameterName) throws SQLException {
		return callableStatement.getBigDecimal(parameterName);
	}

	
	public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
		return callableStatement.getObject(parameterName, map);
	}

	
	public Ref getRef(String parameterName) throws SQLException {
		return callableStatement.getRef(parameterName);
	}

	
	public Blob getBlob(String parameterName) throws SQLException {
		return callableStatement.getBlob(parameterName);
	}

	
	public Clob getClob(String parameterName) throws SQLException {
		return callableStatement.getClob(parameterName);
	}

	
	public Array getArray(String parameterName) throws SQLException {
		return callableStatement.getArray(parameterName);
	}

	
	public Date getDate(String parameterName, Calendar cal) throws SQLException {
		return callableStatement.getDate(parameterName, cal);
	}

	
	public Time getTime(String parameterName, Calendar cal) throws SQLException {
		return callableStatement.getTime(parameterName, cal);
	}

	
	public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
		return callableStatement.getTimestamp(parameterName, cal);
	}

	
	public URL getURL(String parameterName) throws SQLException {
		return callableStatement.getURL(parameterName);
	}

	
	public RowId getRowId(int parameterIndex) throws SQLException {
		return callableStatement.getRowId(parameterIndex);
	}

	
	public RowId getRowId(String parameterName) throws SQLException {
		return callableStatement.getRowId(parameterName);
	}

	
	public void setRowId(String parameterName, RowId x) throws SQLException {
		callableStatement.setRowId(parameterName, x);
	}

	
	public void setNString(String parameterName, String value) throws SQLException {
		callableStatement.setNString(parameterName, value);
	}

	
	public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
		callableStatement.setNCharacterStream(parameterName, value, length);
	}

	
	public void setNClob(String parameterName, NClob value) throws SQLException {
		callableStatement.setNClob(parameterName, value);
	}

	
	public void setClob(String parameterName, Reader reader, long length) throws SQLException {
		callableStatement.setClob(parameterName, reader, length);
	}

	
	public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
		callableStatement.setBlob(parameterName, inputStream, length);
	}

	
	public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
		callableStatement.setNClob(parameterName, reader, length);
	}

	
	public NClob getNClob(int parameterIndex) throws SQLException {
		return callableStatement.getNClob(parameterIndex);
	}

	
	public NClob getNClob(String parameterName) throws SQLException {
		return callableStatement.getNClob(parameterName);
	}

	
	public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
		callableStatement.setSQLXML(parameterName, xmlObject);
	}

	
	public SQLXML getSQLXML(int parameterIndex) throws SQLException {
		return callableStatement.getSQLXML(parameterIndex);
	}

	
	public SQLXML getSQLXML(String parameterName) throws SQLException {
		return callableStatement.getSQLXML(parameterName);
	}

	
	public String getNString(int parameterIndex) throws SQLException {
		return callableStatement.getNString(parameterIndex);
	}

	
	public String getNString(String parameterName) throws SQLException {
		return callableStatement.getNString(parameterName);
	}

	
	public Reader getNCharacterStream(int parameterIndex) throws SQLException {
		return callableStatement.getNCharacterStream(parameterIndex);
	}

	
	public Reader getNCharacterStream(String parameterName) throws SQLException {
		return callableStatement.getNCharacterStream(parameterName);
	}

	
	public Reader getCharacterStream(int parameterIndex) throws SQLException {
		return callableStatement.getCharacterStream(parameterIndex);
	}

	
	public Reader getCharacterStream(String parameterName) throws SQLException {
		return callableStatement.getCharacterStream(parameterName);
	}

	
	public void setBlob(String parameterName, Blob x) throws SQLException {
		callableStatement.setBlob(parameterName, x);
	}

	
	public void setClob(String parameterName, Clob x) throws SQLException {
		callableStatement.setClob(parameterName, x);
	}

	
	public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
		callableStatement.setAsciiStream(parameterName, x, length);
	}

	
	public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
		callableStatement.setBinaryStream(parameterName, x, length);
	}

	
	public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
		callableStatement.setCharacterStream(parameterName, reader, length);
	}

	
	public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
		callableStatement.setAsciiStream(parameterName, x);
	}

	
	public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
		callableStatement.setBinaryStream(parameterName, x);
	}

	
	public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
		callableStatement.setCharacterStream(parameterName, reader);
	}

	
	public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
		callableStatement.setNCharacterStream(parameterName, value);
	}

	
	public void setClob(String parameterName, Reader reader) throws SQLException {
		callableStatement.setClob(parameterName, reader);
	}

	
	public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
		callableStatement.setBlob(parameterName, inputStream);
	}

	
	public void setNClob(String parameterName, Reader reader) throws SQLException {
		callableStatement.setNClob(parameterName, reader);
	}

	
	public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
		return callableStatement.getObject(parameterIndex, type);
	}

	
	public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
		return callableStatement.getObject(parameterName, type);
	}

}
