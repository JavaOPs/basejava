package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

  protected static final int STORAGE_LIMIT = 10000;
  protected Resume[] storage = new Resume[STORAGE_LIMIT];
  protected int storageSize;

  public int size() {
    return storageSize;
  }

  public Resume get(String uuid) {
    int index = getIndex(uuid);
    if (index >= 0) {
      return storage[index];
    } else {
      System.out.println("Resume " + uuid + " isn't exists!");
      return null;
    }
  }

  protected abstract int getIndex(String uuid);
}
