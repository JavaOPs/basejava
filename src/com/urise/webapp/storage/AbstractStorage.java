package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        Object searchKey = getExistingSearchKey(r.getUuid());
        if (!isExisting(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        }
        doUpdate(r, searchKey);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = getNotExistingSearchKey(r.getUuid());
        doSave(searchKey, r);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public void delete(String uuid) {
        getExistingSearchKey(uuid);
        doDelete(uuid);
    }

    protected Object getExistingSearchKey(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Resume must not be null");
        }
        Object searchKey = getSearchKey(uuid);
        if (!isExisting(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected Object getNotExistingSearchKey(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Resume must not be null");
        }
        Object searchKey = getSearchKey(uuid);
        if (isExisting(searchKey)) {
            if (searchKey.equals(uuid)) {
                throw new IllegalArgumentException("Resume already exists: " + uuid);
            }
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExisting(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract void doSave(Object searchKey, Resume r);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(String uuid);

    protected abstract Object getSearchKey(String uuid);
}

