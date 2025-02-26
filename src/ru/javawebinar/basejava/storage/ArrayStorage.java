package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

  @Override
  protected Integer getSearchKey(String uuid) {
    for (int i = 0; i < storageSize; i++) {
      if (uuid.equals(storage[i].getUuid())) {
        return i;
      }
    }
    return -1;
  }

  @Override
  protected void insertElement(Resume r, int index) {
    storage[storageSize] = r;
  }

  @Override
  protected void fillDeletedElement(int index) {
    storage[index] = storage[storageSize - 1];
  }
}
