package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorage extends AbstractStorage {

  protected static final int STORAGE_LIMIT = 10000;
  protected Resume[] storage = new Resume[STORAGE_LIMIT];
  protected int storageSize;

  @Override
  protected boolean isExist(Object index) {
    return (Integer) index >= 0;
  }

  @Override
  protected abstract Integer getSearchKey(String uuid);

  protected abstract void insertElement(Resume r, int index);

  protected abstract void fillDeletedElement(int index);

  @Override
  protected void doUpdate(Resume r, Object index) {
    storage[(Integer) index] = r;
  }

  public int size() {
    return storageSize;
  }

  @Override
  protected void doSave(Resume r, Object index) {
    if (storageSize == STORAGE_LIMIT) {
      throw new StorageException("Storage is overflow!", r.getUuid());
    }
    insertElement(r, (Integer) index);
    storageSize++;
  }

  @Override
  public void doDelete(Object index) {
    fillDeletedElement((Integer) index);
    storage[storageSize - 1] = null;
    storageSize--;
  }

  @Override
  public Resume doGet(Object index) {
    return storage[(Integer) index];
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
