package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

  private Resume[] storage = new Resume[10000];
  private int storageSize;

  public void clear() {
    Arrays.fill(storage,0, storageSize, null);
    storageSize = 0;
  }

  public void update(Resume r) {
    int index = getPresentResumeIndex(r.getUuid());
    if (index >= 0) {
      storage[index] = r;
    } else {
      System.out.println("There are not resume with this uuid!");
    }
  }

  public void save(Resume r) {
    if (storageSize == storage.length) {
      System.out.println("Storage is full!");
      return;
    }
    int index = getPresentResumeIndex(r.getUuid());
    if (index == -1) {
      storage[storageSize++] = r;
    } else {
      System.out.println("This resume already exists!");
    }
  }

  public Resume get(String uuid) {
    int index = getPresentResumeIndex(uuid);
    if (index >= 0) {
      return storage[index];
    }
    return null;
  }

  public void delete(String uuid) {
    int index = getPresentResumeIndex(uuid);
    if (index >= 0) {
      storageSize--;
      storage[index] = storage[storageSize];
      storage[storageSize] = null;
    } else {
      System.out.println("There are not resume with this uuid!");
    }
  }

  /**
   * @return array, contains only Resumes in storage (without null)
   */
  public Resume[] getAll() {
    return Arrays.copyOf(storage, storageSize);
  }

  public int size() {
    return storageSize;
  }

  private int getPresentResumeIndex(String uuid) {
    for (int i = 0; i < storageSize; i++) {
      if (uuid.equals(storage[i].getUuid())) {
        return i;
      }
    }
    return -1;
  }
}
