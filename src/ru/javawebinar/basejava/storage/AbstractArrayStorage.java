package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import java.util.List;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

  protected static final int STORAGE_LIMIT = 10000;
  protected Resume[] storage = new Resume[STORAGE_LIMIT];
  protected int storageSize;

  @Override
  protected boolean isExist(Integer index) {
    return index >= 0;
  }

  @Override
  protected abstract Integer getSearchKey(String uuid);

  protected abstract void insertElement(Resume r, int index);

  protected abstract void fillDeletedElement(int index);

  @Override
  protected void doUpdate(Resume r, Integer index) {
    storage[index] = r;
  }

  public int size() {
    return storageSize;
  }

  @Override
  protected void doSave(Resume r, Integer index) {
    if (storageSize == STORAGE_LIMIT) {
      throw new StorageException("Storage is overflow!", r.getUuid());
    }
    insertElement(r, index);
    storageSize++;
  }

  @Override
  public void doDelete(Integer index) {
    fillDeletedElement(index);
    storage[storageSize - 1] = null;
    storageSize--;
  }

  @Override
  public Resume doGet(Integer index) {
    return storage[index];
  }

  /**
   * @return array, contains only Resumes in storage (without null)
   */
  @Override
  public List<Resume> doCopyAll() {
    return Arrays.asList(Arrays.copyOf(storage, storageSize));
  }

  public void clear() {
    Arrays.fill(storage, 0, storageSize, null);
    storageSize = 0;
  }
}
