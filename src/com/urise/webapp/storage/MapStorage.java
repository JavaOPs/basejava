package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    protected boolean isExisting(Object searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected void doUpdate(Resume r) {
        if (!isExisting(r.getUuid())) {
            throw new NotExistStorageException(r.getUuid());
        }
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doSave(Resume r) {
        if (isExisting(r.getUuid())) {
            throw new IllegalArgumentException(r.getUuid());
        }
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(String uuid) {
        if (!isExisting(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return storage.get(uuid);
    }

    @Override
    protected void doDelete(String uuid) {
        if (!isExisting(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        storage.remove(uuid);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
 return storage.values().toArray(new Resume[0]);
//        List<Resume> resumeList = new ArrayList<>(storage.values());
//        return resumeList.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
