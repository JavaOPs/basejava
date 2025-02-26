package ru.javawebinar.basejava.storage;

import java.util.ArrayList;
import java.util.List;
import ru.javawebinar.basejava.model.Resume;

public class ListStorage extends AbstractStorage<Integer> {

  private final List<Resume> storage = new ArrayList<>();

  @Override
  protected boolean isExist(Integer searchKey) {
    return searchKey >= 0;
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
  public void doUpdate(Resume r, Integer index) {
    storage.set(index, r);
  }

  @Override
  public void doSave(Resume r, Integer index) {
    storage.add(r);
  }

  @Override
  public Resume doGet(Integer index) {
    return storage.get(index);
  }

  @Override
  public void doDelete(Integer index) {
    storage.remove(index.intValue());
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
