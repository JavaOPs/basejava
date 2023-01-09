package ru.javawebinar.basejava.storage;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ru.javawebinar.basejava.exception.NotExistStorageException;
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
    sqlHelper.execute("UPDATE resume SET full_name = ? WHERE uuid = ?", ps -> {
      ps.setString(1, r.getFullName());
      ps.setString(2, r.getUuid());
      if (ps.executeUpdate() == 0) {
        throw new NotExistStorageException(r.getUuid());
      }
      return null;
    });
  }

  @Override
  public void save(Resume r) {
    sqlHelper.execute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", ps -> {
      ps.setString(1, r.getUuid());
      ps.setString(2, r.getFullName());
      ps.execute();
      return null;
    });
  }

  @Override
  public Resume get(String uuid) {
    return sqlHelper.execute("SELECT * FROM resume r WHERE r.uuid=?", ps -> {
      ps.setString(1, uuid);
      ResultSet rs = ps.executeQuery();
      if (!rs.next()) {
        throw new NotExistStorageException(uuid);
      }
      return new Resume(uuid, rs.getString("full_name"));
    });
  }

  @Override
  public void delete(String uuid) {
    sqlHelper.execute("DELETE FROM resume r WHERE r.uuid=?", ps -> {
      ps.setString(1, uuid);
      if (ps.executeUpdate() == 0) {
        throw new NotExistStorageException(uuid);
      }
      return null;
    });
  }

  @Override
  public List<Resume> getAllSorted() {
    return sqlHelper.execute("SELECT * FROM resume ORDER BY full_name,uuid", ps -> {
      ResultSet rs = ps.executeQuery();
      List<Resume> list = new ArrayList<>();
      while (rs.next()) {
        list.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
      }
      return list;
    });
  }

  @Override
  public int size() {
    return sqlHelper.execute("SELECT count(*) FROM resume", ps -> {
      ResultSet rs = ps.executeQuery();
      return rs.next() ? rs.getInt("count") : 0;
    });
  }
}
