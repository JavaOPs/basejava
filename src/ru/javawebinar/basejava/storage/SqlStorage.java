package ru.javawebinar.basejava.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ConnectionFactory;

public class SqlStorage implements Storage {

  public final ConnectionFactory connectionFactory;

  public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
    connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
  }

  @Override
  public void clear() {
    try (Connection connection = connectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM resume")) {
      ps.execute();
    } catch (SQLException e) {
      throw new StorageException(e);
    }
  }

  @Override
  public void update(Resume r) {

  }

  @Override
  public void save(Resume r) {
    try (Connection connection = connectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement(
            "INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
      ps.setString(1, r.getUuid());
      ps.setString(2, r.getFullName());
      ps.execute();
    } catch (SQLException e) {
      throw new StorageException(e);
    }
  }

  @Override
  public Resume get(String uuid) {
    try (Connection connection = connectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement(
            "SELECT * FROM resume r WHERE r.uuid=?")) {
      ps.setString(1, uuid);
      ResultSet rs = ps.executeQuery();
      if (!rs.next()) {
        throw new NotExistStorageException(uuid);
      }
      return new Resume(uuid, rs.getString("full_name"));
    } catch (SQLException e) {
      throw new StorageException(e);
    }
  }

  @Override
  public void delete(String uuid) {

  }

  @Override
  public List<Resume> getAllSorted() {
    return null;
  }

  @Override
  public int size() {
    return 0;
  }
}
