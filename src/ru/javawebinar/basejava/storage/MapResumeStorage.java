package ru.javawebinar.basejava.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.javawebinar.basejava.model.Resume;

public class MapResumeStorage extends AbstractStorage<Resume> {

  Map<String, Resume> mapStorage = new HashMap<>();

  @Override
  protected boolean isExist(Resume resume) {
    return resume != null;
  }

  @Override
  protected Resume getSearchKey(String uuid) {
    return mapStorage.get(uuid);
  }

  @Override
  protected void doUpdate(Resume r, Resume resume) {
    mapStorage.put(r.getUuid(), r);
  }

  @Override
  protected void doSave(Resume r, Resume resume) {
    mapStorage.put(r.getUuid(), r);
  }

  @Override
  protected void doDelete(Resume resume) {
    mapStorage.remove(resume.getUuid());
  }

  @Override
  protected Resume doGet(Resume resume) {
    return resume;
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
