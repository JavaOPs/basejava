package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorage implements Storage {

  protected static final int STORAGE_LIMIT = 10000;
  protected Resume[] storage = new Resume[STORAGE_LIMIT];
  protected int storageSize;

  protected abstract int getIndex(String uuid);

  protected abstract void insertElement(Resume r, int index);

  protected abstract void fillDeletedElement(int index);

  public void update(Resume r) {
    int index = getIndex(r.getUuid());
    if (index >= 0) {
      storage[index] = r;
    } else {
      System.out.println("Resume " + r.getUuid() + " isn't exists!");
    }
  }

  public int size() {
    return storageSize;
  }

  public void save(Resume r) {
    if (storageSize >= STORAGE_LIMIT) {
      System.out.println("Storage is full!");
      return;
    }
    int index = getIndex(r.getUuid());
    if (index < 0) {
      insertElement(r, index);
      storageSize++;
    } else {
      System.out.println("Resume " + r.getUuid() + " is already exists!");
    }
  }

  public void delete(String uuid) {
    int index = getIndex(uuid);
    if (index >= 0) {
      storageSize--;
      fillDeletedElement(index);
      storage[storageSize] = null;
    } else {
      System.out.println("Resume " + uuid + " isn't exists!");
    }
  }

  public final Resume get(String uuid) {
    int index = getIndex(uuid);
    if (index >= 0) {
      return storage[index];
    } else {
      System.out.println("Resume " + uuid + " isn't exists!");
      return null;
    }
  }

  /**
   * @return array, contains only Resumes in storage (without null)
   */
  public Resume[] getAll() {
    return Arrays.copyOf(storage, storageSize);
  }

  public void clear() {
    Arrays.fill(storage, 0, storageSize, null);
    storageSize = 0;
  }
}
