package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage{
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }
    public abstract void clear();
    public abstract void update(Resume r);
    public abstract void save(Resume r);
    public abstract Resume get(String uuid);
    public abstract void delete(String uuid);
    public abstract Resume[] getAll();
    protected abstract int findIndex(String uuid);
}

