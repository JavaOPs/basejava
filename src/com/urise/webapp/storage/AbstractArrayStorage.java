package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("\nThe " + storage.getClass().getSimpleName() + " was successfully cleared");
    }


    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index < 0) {
            System.out.println("\nERROR: Element " + r + " not found");
            return;
        }
        storage[index] = r;
        System.out.println("\nElement " + r + " successfully update");
    }


    public final void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (index < 0 && size < storage.length) {
            System.out.println("ERROR: array overflow! Element " + r + " not saved to storage.");
        } else {
            insertResume(r);
            increaseSize();
            System.out.println("Element " + r + " successfully saved to storage.");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("\nElement " + uuid + " not found");
        return null;
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("\nElement " + uuid + " not found");
        } else {
            indexDelete(index);
            reductionSize();
            System.out.println("\nElement " + uuid + " successfully deleted from storage");
        }
    }

    public int size() {
        return size;
    }

    protected abstract int findIndex(String uuid);

    protected abstract void insertResume(Resume r);

    protected void increaseSize() {
        size++;
    }

    protected abstract void indexDelete(int index);

    protected void reductionSize() {
        size--;
    }

}

