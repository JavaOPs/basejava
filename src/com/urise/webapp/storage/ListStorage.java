package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }


    @Override
    protected boolean isExisting(Object searchKey) {
        return searchKey != null && (Integer) searchKey >= 0;
    }

    @Override
    public void doUpdate(Resume r, Object searchKey) {
        Resume resume = get(r.getUuid());
        storage.set(storage.indexOf(resume), r);
    }

    @Override
    public void doSave(Object searchKey, Resume r) {
        if (storage.contains(r)) {
            throw new ExistStorageException(r.getUuid());
        }
        storage.add(r);
    }

    @Override
    public Resume doGet(Object searchKey) {
        int index = (Integer) searchKey;
        if (index >= 0 && index < storage.size()) {
            return storage.get(index);
        }
        throw new NotExistStorageException((String) searchKey);
    }

    @Override
    public void doDelete(String uuid) {
        Resume resume = get(uuid);
        storage.remove(resume);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++)
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        return -1;
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
