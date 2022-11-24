package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
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
      throw new NotExistStorageException(r.getUuid());
    }
  }

  public int size() {
    return storageSize;
  }

  public void save(Resume r) {
    if (storageSize >= STORAGE_LIMIT) {
      throw new StorageException("Storage is full!", r.getUuid());
    }
    int index = getIndex(r.getUuid());
    if (index < 0) {
      insertElement(r, index);
      storageSize++;
    } else {
      throw new ExistStorageException(r.getUuid());
    }
  }

  public void delete(String uuid) {
    int index = getIndex(uuid);
    if (index >= 0) {
      fillDeletedElement(index);
      storage[storageSize] = null;
      storageSize--;
    } else {
      throw new NotExistStorageException(uuid);
    }
  }

  public final Resume get(String uuid) {
    int index = getIndex(uuid);
    if (index >= 0) {
      return storage[index];
    } else {
      throw new NotExistStorageException(uuid);
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
