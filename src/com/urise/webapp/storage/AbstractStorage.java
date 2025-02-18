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
    public abstract void save(Resume r);

    @Override
    public abstract Resume get(String uuid);

    @Override
    public abstract void delete(String uuid);

    @Override
    public abstract Resume[] getAll();

    @Override
    public abstract int size();

    protected abstract boolean exist(String uuid);
    public abstract void doUpdate(Resume r);
}