package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        if (r == null) {
            throw new IllegalArgumentException("Resume must not be null");
        }
        String uuid = r.getUuid();
        if (!isExisting(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        doUpdate(r);
    }

    @Override
    public void save(Resume r) {
        if (r == null) {
            throw new IllegalArgumentException("Resume must not be null");
        }
        String uuid = r.getUuid();
        if (!isExisting(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        doSave(r);
    }

    @Override
    public Resume get(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Resume must not be null");
        }
        if (!isExisting(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(uuid);
    }

    @Override
    public void delete(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Resume must not be null");
        }
        if (!isExisting(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        doDelete(uuid);
    }

    protected abstract boolean  isExisting(Object searchKey);

    protected abstract void doUpdate(Resume r);

    protected abstract void doSave(Resume r);

    protected abstract Resume doGet(String uuid);

    protected abstract void doDelete(String uuid);

    protected abstract Object getSearchKey(String uuid);
}

