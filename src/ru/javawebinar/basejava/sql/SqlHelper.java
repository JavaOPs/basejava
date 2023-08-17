package ru.javawebinar.basejava.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ru.javawebinar.basejava.exception.StorageException;

public class SqlHelper {

  private final ConnectionFactory connectionFactory;

  public SqlHelper(ConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  public void execute(String query) {
    execute(query, PreparedStatement::execute);
  }

  public <T> T execute(String query, SqlExecutor<T> sqlExecutor) {
    try (Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)) {
      return sqlExecutor.execute(ps);
    } catch (SQLException e) {
      throw new StorageException(e);
    }
  }

  public <T> T transactionalExecute(SqlTransaction<T> executor) {
    try (Connection conn = connectionFactory.getConnection()) {
      try {
        conn.setAutoCommit(false);
        T res = executor.execute(conn);
        conn.commit();
        return res;
      } catch (SQLException e) {
        conn.rollback();
        throw ExceptionUtil.convertException(e);
      }
    } catch (SQLException e) {
      throw new StorageException(e);
    }
  }
}
