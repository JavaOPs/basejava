package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

  @Override
  protected int getIndex(String uuid) {
    for (int i = 0; i < storageSize; i++) {
      if (uuid.equals(storage[i].getUuid())) {
        return i;
      }
    }
    return -1;
  }
}
