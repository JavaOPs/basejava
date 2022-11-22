package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

  public void clear() {
    Arrays.fill(storage,0, storageSize, null);
    storageSize = 0;
  }

  public void update(Resume r) {
    int index = getIndex(r.getUuid());
    if (index >= 0) {
      storage[index] = r;
    } else {
      System.out.println("Resume " + r.getUuid() + " isn't exists!");
    }
  }

  public void save(Resume r) {
    if (storageSize >= STORAGE_LIMIT) {
      System.out.println("Storage is full!");
      return;
    }
    int index = getIndex(r.getUuid());
    if (index == -1) {
      storage[storageSize] = r;
      storageSize++;
    } else {
      System.out.println("Resume " + r.getUuid() + " is already exists!");
    }
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

  public void delete(String uuid) {
    int index = getIndex(uuid);
    if (index >= 0) {
      storageSize--;
      storage[index] = storage[storageSize];
      storage[storageSize] = null;
    } else {
      System.out.println("Resume " + uuid + " isn't exists!");
    }
  }

  /**
   * @return array, contains only Resumes in storage (without null)
   */
  public Resume[] getAll() {
    return Arrays.copyOf(storage, storageSize);
  }

  protected int getIndex(String uuid) {
    for (int i = 0; i < storageSize; i++) {
      if (uuid.equals(storage[i].getUuid())) {
        return i;
      }
    }
    return -1;
  }
}
