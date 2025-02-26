package ru.javawebinar.basejava.storage;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage<K> implements Storage {

  //  protected final Logger log = Logger.getLogger(getClass().getName());
  private final static Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

  protected abstract K getSearchKey(String uuid);

  protected abstract void doUpdate(Resume r, K searchKey);

  protected abstract boolean isExist(K searchKey);

  protected abstract void doSave(Resume r, K searchKey);

  protected abstract void doDelete(K searchKey);

  protected abstract Resume doGet(K searchKey);

  protected abstract List<Resume> doCopyAll();

  public void update(Resume r) {
    LOG.info("Update " + r);
    K searchKey = getExistedSearchKey(r.getUuid());
    doUpdate(r, searchKey);
  }

  public void save(Resume r) {
    LOG.info("Save " + r);
    K searchKey = getNotExistedSearchKey(r.getUuid());
    doSave(r, searchKey);
  }

  public void delete(String uuid) {
    LOG.info("Delete " + uuid);
    K searchKey = getExistedSearchKey(uuid);
    doDelete(searchKey);
  }

  public Resume get(String uuid) {
    LOG.info("Get " + uuid);
    K searchKey = getExistedSearchKey(uuid);
    return doGet(searchKey);
  }

  private K getNotExistedSearchKey(String uuid) {
    K searchKey = getSearchKey(uuid);
    if (isExist(searchKey)) {
      LOG.warning("Resume " + uuid + " is already exists!");
      throw new ExistStorageException(uuid);
    }
    return searchKey;
  }

  private K getExistedSearchKey(String uuid) {
    K searchKey = getSearchKey(uuid);
    if (!isExist(searchKey)) {
      LOG.warning("Resume " + uuid + " isn't exists!");
      throw new NotExistStorageException(uuid);
    }
    return searchKey;
  }

  @Override
  public List<Resume> getAllSorted() {
    LOG.info("GetAllSorted");
    List<Resume> list = doCopyAll();
    Collections.sort(list);
    return list;
  }
}
