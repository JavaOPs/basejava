package ru.javawebinar.basejava.storage;

import java.util.ArrayList;
import java.util.List;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public class ListStorage implements Storage {

  List<Resume> storage = new ArrayList<>();

  @Override
  public void clear() {
    storage.clear();
  }

  @Override
  public void update(Resume r) {
    int index = getIndex(r.getUuid());
    if(index >= 0) {
      storage.add(index, r);
    } else {
      throw new NotExistStorageException(r.getUuid());
    }
  }

  private int getIndex(String uuid) {
    for (int i = 0; i < storage.size(); i++) {
      if(storage.get(i).getUuid().equals(uuid)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public void save(Resume r) {
    int index = getIndex(r.getUuid());
    if(index >= 0) {
      throw new ExistStorageException(r.getUuid());
    } else {
      storage.add(r);
    }
  }

  @Override
  public Resume get(String uuid) {
    int index = getIndex(uuid);
    if(index >= 0) {
      return storage.get(index);
    } else {
      throw new NotExistStorageException(uuid);
    }
  }

  @Override
  public void delete(String uuid) {
    boolean isDeleted = storage.remove(new Resume(uuid));
    if(!isDeleted) {
      throw new NotExistStorageException(uuid);
    }
  }

  @Override
  public Resume[] getAll() {
    return storage.toArray(new Resume[storage.size()]);
  }

  @Override
  public int size() {
    return storage.size();
  }
}
