package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public interface Storage {

  void clear();

  void update(Resume r);

  void save(Resume r);

  Resume get(String uuid);

  void delete(String uuid);

  /**
   * @return array, contains only Resumes in storage (without null)
   */
  Resume[] getAll();

  int size();
}
