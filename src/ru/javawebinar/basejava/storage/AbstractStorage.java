package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

  protected abstract Object getSearchKey(String uuid);

  protected abstract void doUpdate(Resume r, Object searchKey);

  protected abstract boolean isExist(Object searchKey);

  protected abstract void doSave(Resume r, Object searchKey);

  protected abstract void doDelete(Object searchKey);

  protected abstract Resume doGet(Object searchKey);

  public void update(Resume r) {
    Object searchKey = getExistedSearchKey(r.getUuid());
    doUpdate(r, searchKey);
  }

  public void save(Resume r) {
    Object searchKey = getNotExistedSearchKey(r.getUuid());
    doSave(r, searchKey);
  }

  public void delete(String uuid) {
    Object searchKey = getExistedSearchKey(uuid);
    doDelete(searchKey);
  }

  public Resume get(String uuid) {
    Object searchKey = getExistedSearchKey(uuid);
    return doGet(searchKey);
  }

  private Object getNotExistedSearchKey(String uuid) {
    Object searchKey = getSearchKey(uuid);
    if (isExist(searchKey)) {
      throw new ExistStorageException(uuid);
    }
    // TODO: Возвращать ключ в рамках которого можно положить следующую запись
    return searchKey;
  }

  private Object getExistedSearchKey(String uuid) {
    Object searchKey = getSearchKey(uuid);
    if (!isExist(searchKey)) {
      throw new NotExistStorageException(uuid);
    }
    return searchKey;
  }
}
