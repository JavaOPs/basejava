package ru.javawebinar.basejava.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ru.javawebinar.basejava.exception.StorageException;

public class SqlHelper {

  private SqlExecutor sqlExecutor;
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
}
