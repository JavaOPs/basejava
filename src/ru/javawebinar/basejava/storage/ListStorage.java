package ru.javawebinar.basejava.storage;

import java.util.ArrayList;
import java.util.List;
import ru.javawebinar.basejava.model.Resume;

public class ListStorage extends AbstractStorage {

  private final List<Resume> storage = new ArrayList<>();

  @Override
  protected boolean isExist(Object searchKey) {
    return (Integer) searchKey >= 0;
  }

  @Override
  protected Integer getSearchKey(String uuid) {
    for (int i = 0; i < storage.size(); i++) {
      if (storage.get(i).getUuid().equals(uuid)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public void clear() {
    storage.clear();
  }

  @Override
  public void doUpdate(Resume r, Object index) {
    storage.set((Integer) index, r);
  }

  @Override
  public void doSave(Resume r, Object index) {
    storage.add(r);
  }

  @Override
  public Resume doGet(Object index) {
    return storage.get((Integer) index);
  }

  @Override
  public void doDelete(Object index) {
    storage.remove(((Integer) index).intValue());
  }

  @Override
  public List<Resume> doCopyAll() {
    return new ArrayList<>(storage);
  }

  @Override
  public int size() {
    return storage.size();
  }
}
