package ru.javawebinar.basejava.storage;

import java.util.Collections;
import java.util.List;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage<K> implements Storage {

  protected abstract K getSearchKey(String uuid);

  protected abstract void doUpdate(Resume r, K searchKey);

  protected abstract boolean isExist(K searchKey);

  protected abstract void doSave(Resume r, K searchKey);

  protected abstract void doDelete(K searchKey);

  protected abstract Resume doGet(K searchKey);

  protected abstract List<Resume> doCopyAll();

  public void update(Resume r) {
    K searchKey = getExistedSearchKey(r.getUuid());
    doUpdate(r, searchKey);
  }

  public void save(Resume r) {
    K searchKey = getNotExistedSearchKey(r.getUuid());
    doSave(r, searchKey);
  }

  public void delete(String uuid) {
    K searchKey = getExistedSearchKey(uuid);
    doDelete(searchKey);
  }

  public Resume get(String uuid) {
    K searchKey = getExistedSearchKey(uuid);
    return doGet(searchKey);
  }

  private K getNotExistedSearchKey(String uuid) {
    K searchKey = getSearchKey(uuid);
    if (isExist(searchKey)) {
      throw new ExistStorageException(uuid);
    }
    return searchKey;
  }

  private K getExistedSearchKey(String uuid) {
    K searchKey = getSearchKey(uuid);
    if (!isExist(searchKey)) {
      throw new NotExistStorageException(uuid);
    }
    return searchKey;
  }

  @Override
  public List<Resume> getAllSorted() {
    List<Resume> list = doCopyAll();
    Collections.sort(list);
    return list;
  }
}
