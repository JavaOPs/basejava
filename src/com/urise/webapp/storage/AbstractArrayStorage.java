package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final void templateMethod() {
        Resume r1 = new Resume();
        r1.setUuid("uuid4");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid1");
        save(r1);
        save(r2);
        save(r3);
        printAll();

        System.out.println("Get r1: " + get(r1.getUuid()));
        System.out.println("Size: " + size());

        System.out.println("Get dummy: " + get("dummy"));

        Resume r = new Resume();
        r.setUuid("uuid2");
        update(r);
        save(r);

        printAll();
        delete(r1.getUuid());
        printAll();
        clear();
        printAll();

        System.out.println("Size: " + size());
        size();
    }

    protected void printAll() {
        System.out.println("\nGet All");
        for (Resume r : getAll()) {
            System.out.println(r);
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

    public int size() {
        return size;
    }

    protected abstract int findIndex(String uuid);
}

