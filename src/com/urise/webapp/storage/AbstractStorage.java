package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        getExistingSearchKey(r.getUuid());
        doUpdate(r);
    }

    @Override
    public void save(Resume r) {
        getNotExistingSearchKey(r.getUuid());
        doSave(r);
    }

    @Override
    public Resume get(String uuid) {
        return getExistingSearchKey(uuid);
    }

    @Override
    public void delete(String uuid) {
        getExistingSearchKey(uuid);
        doDelete(uuid);
    }

    protected Resume getExistingSearchKey(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Resume must not be null");
        }
        Resume r = doGet(uuid);
        if (r == null) {
            throw new NotExistStorageException(uuid);
        }
        return r;
    }

    protected void getNotExistingSearchKey(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Resume must not be null");
        }
        if (isExisting(uuid)) {
            throw new IllegalArgumentException("Resume already exists: " + uuid);
        }
    }

    protected abstract boolean isExisting(Object searchKey);

    protected abstract void doUpdate(Resume r);

    protected abstract void doSave(Resume r);

    protected abstract Resume doGet(String uuid);

    protected abstract void doDelete(String uuid);

    protected abstract Object getSearchKey(String uuid);
}

