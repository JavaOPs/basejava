package ru.javawebinar.basejava.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.javawebinar.basejava.model.Resume;

public class MapUuidStorage extends AbstractStorage<String> {

  private final Map<String, Resume> mapStorage = new HashMap<>();

  @Override
  protected boolean isExist(String searchKey) {
    return mapStorage.containsKey(searchKey);
  }

  @Override
  protected String getSearchKey(String uuid) {
    return uuid;
  }

  @Override
  protected void doUpdate(Resume r, String searchKey) {
    mapStorage.put(searchKey, r);
  }

  @Override
  protected void doSave(Resume r, String searchKey) {
    mapStorage.put(searchKey, r);
  }

  @Override
  protected void doDelete(String searchKey) {
    mapStorage.remove(searchKey);
  }

  @Override
  protected Resume doGet(String searchKey) {
    return mapStorage.get(searchKey);
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
