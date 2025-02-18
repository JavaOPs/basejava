package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public abstract void clear();

    @Override
    public void update(Resume r) {
        if (r == null) {
            throw new IllegalArgumentException("Resume must not be null");
        }
        String uuid = r.getUuid();
        if (!exist(uuid)) {
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
        if (!exist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        doSave(r);
    }

    @Override
    public Resume get(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Resume must not be null");
        }
        if (!exist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(uuid);
    }

    @Override
    public abstract void delete(String uuid);

    @Override
    public abstract Resume[] getAll();

    @Override
    public abstract int size();

    protected abstract boolean exist(String uuid);

    protected abstract void doUpdate(Resume r);

    protected abstract void doSave(Resume r);

    protected abstract Resume doGet(String uuid);
}