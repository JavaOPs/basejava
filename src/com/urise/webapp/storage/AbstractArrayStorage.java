package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("\nThe " + storage.getClass().getSimpleName() + " was successfully cleared");
    }


    public final void doUpdate(Resume r) {
        Object searchKey= getSearchKey(r.getUuid());
        if (!isExisting(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        }
        storage[(Integer)searchKey] = r;
        System.out.println("\nElement " + r + " successfully update");
    }

    public final void save(Resume r) {
        doSave(r);
    }

    public void doSave(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (isExisting(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        } else if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        insertResume(r);
        size++;
        System.out.println("Element " + r + " successfully saved to storage.");
    }

    public final Resume doGet(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExisting(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return storage[(Integer) searchKey];
    }

    public final void doDelete(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExisting(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        fillDeletedElement((Integer)searchKey);
        storage[size - 1] = null;
        size--;
        System.out.println("\nElement " + uuid + " successfully deleted from storage");
    }


    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract void insertResume(Resume r);

    protected abstract void fillDeletedElement(int index);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExisting(Object searchKey);
}