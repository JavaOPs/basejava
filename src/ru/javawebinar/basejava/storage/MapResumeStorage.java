package ru.javawebinar.basejava.storage;

import java.util.List;
import ru.javawebinar.basejava.model.Resume;

public class MapResumeStorage extends AbstractStorage {

  @Override
  protected Object getSearchKey(String uuid) {
    return null;
  }

  @Override
  protected void doUpdate(Resume r, Object searchKey) {

  }

  @Override
  protected boolean isExist(Object searchKey) {
    return false;
  }

  @Override
  protected void doSave(Resume r, Object searchKey) {

  }

  @Override
  protected void doDelete(Object searchKey) {

  }

  @Override
  protected Resume doGet(Object searchKey) {
    return null;
  }

  @Override
  protected List<Resume> doCopyAll() {
    return null;
  }

  @Override
  public void clear() {

  }

  @Override
  public int size() {
    return 0;
  }
}
