package ru.javawebinar.basejava.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

public class SqlStorage implements Storage {

  private final SqlHelper sqlHelper;

  public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
    sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
  }

  @Override
  public void clear() {
    sqlHelper.execute("DELETE FROM resume");
  }

  @Override
  public void update(Resume r) {
    sqlHelper.transactionalExecute(conn -> {
      try (PreparedStatement ps = conn.prepareStatement(
          "UPDATE resume SET full_name = ? WHERE uuid = ?")) {
        ps.setString(1, r.getFullName());
        ps.setString(2, r.getUuid());
        if (ps.executeUpdate() == 0) {
          throw new NotExistStorageException(r.getUuid());
        }
        deleteContacts(conn, r);
        insertContacts(conn, r);
        return null;
      }
    });
  }

  @Override
  public void save(Resume r) {
    sqlHelper.transactionalExecute(conn -> {
      try (PreparedStatement ps = conn.prepareStatement(
          "INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
        ps.setString(1, r.getUuid());
        ps.setString(2, r.getFullName());
        ps.execute();
      }
      insertContacts(conn, r);
      return null;
    });
  }

  @Override
  public Resume get(String uuid) {
    return sqlHelper.execute("SELECT * FROM resume r " +
        " LEFT JOIN contact c " +
        " ON r.uuid = c.resume_uuid " +
        " WHERE r.uuid = ?", ps -> {
      ps.setString(1, uuid);
      ResultSet rs = ps.executeQuery();
      if (!rs.next()) {
        throw new NotExistStorageException(uuid);
      }
      Resume resume = new Resume(uuid, rs.getString("full_name"));
      do {
        addContact(rs, resume);
      } while (rs.next());
      return resume;
    });
  }

  @Override
  public void delete(String uuid) {
    sqlHelper.<Void>execute("DELETE FROM resume WHERE uuid = ?", ps -> {
      ps.setString(1, uuid);
      if (ps.executeUpdate() == 0) {
        throw new NotExistStorageException(uuid);
      }
      return null;
    });
  }

  @Override
  public List<Resume> getAllSorted() {
    return sqlHelper.execute("SELECT * FROM resume r " +
        " LEFT JOIN contact c " +
        " ON r.uuid = c.resume_uuid" +
        " ORDER BY full_name, uuid", ps -> {
      ResultSet rs = ps.executeQuery();
      Map<String, Resume> map = new LinkedHashMap<>();
      while (rs.next()) {
        String uuid = rs.getString("uuid");
        Resume r = map.get(uuid);
        if (r == null) {
          r = new Resume(uuid, rs.getString("full_name"));
          map.put(uuid, r);
        }
        addContact(rs, r);
      }
      return new ArrayList<>(map.values());
    });
  }

  @Override
  public int size() {
    return sqlHelper.execute("SELECT count(*) FROM resume", ps -> {
      ResultSet rs = ps.executeQuery();
      return rs.next() ? rs.getInt("count") : 0;
    });
  }

  private void deleteContacts(Connection conn, Resume r) {
    sqlHelper.execute("DELETE FROM contact WHERE resume_uuid = ?", ps -> {
      ps.setString(1, r.getUuid());
      ps.execute();
      return null;
    });
  }

  private void insertContacts(Connection conn, Resume r) throws SQLException {
    try (PreparedStatement ps = conn.prepareStatement
        ("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
      for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
        ps.setString(1, r.getUuid());
        ps.setString(2, e.getKey().name());
        ps.setString(3, e.getValue());
        ps.addBatch();
      }
      ps.executeBatch();
    }
  }

  private void addContact(ResultSet rs, Resume r) throws SQLException {
    String value = rs.getString("value");
    if (value != null) {
      r.addContact(ContactType.valueOf(rs.getString("type")), value);
    }
  }
}
