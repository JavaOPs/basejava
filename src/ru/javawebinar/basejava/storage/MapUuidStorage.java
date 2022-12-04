package ru.javawebinar.basejava.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.javawebinar.basejava.model.Resume;

public class MapUuidStorage extends AbstractStorage {

  private final Map<String, Resume> mapStorage = new HashMap<>();

  @Override
  protected boolean isExist(Object searchKey) {
    return mapStorage.containsKey((String) searchKey);
  }

  @Override
  protected String getSearchKey(String uuid) {
    return uuid;
  }

  @Override
  protected void doUpdate(Resume r, Object searchKey) {
    mapStorage.put((String) searchKey, r);
  }

  @Override
  protected void doSave(Resume r, Object searchKey) {
    mapStorage.put((String) searchKey, r);
  }

  @Override
  protected void doDelete(Object searchKey) {
    mapStorage.remove((String) searchKey);
  }

  @Override
  protected Resume doGet(Object searchKey) {
    return mapStorage.get((String) searchKey);
  }

  @Override
  protected List<Resume> doCopyAll() {
    return new ArrayList<>(mapStorage.values());
  }

  @Override
  public void clear() {
    mapStorage.clear();
  }

  @Override
  public int size() {
    return mapStorage.size();
  }
}
