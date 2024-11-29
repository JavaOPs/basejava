package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] STORAGE_LIMIT = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(STORAGE_LIMIT, 0, size, null);
        size = 0;
        System.out.println("\nThe array was successfully cleared");
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            STORAGE_LIMIT[index].setUuid(r.getUuid());
            System.out.println("\nElement " + r + " successfully update");
        } else {
            System.out.println("\nERROR: Element " + r + " not found");
        }
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (index < 0 && size < STORAGE_LIMIT.length) {
            STORAGE_LIMIT[size] = r;
            size++;
            System.out.println("Element " + r + " successfully saved to array");
        } else {
            System.out.println("ERROR: array overflow! Element " + r + " not saved to array.");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return STORAGE_LIMIT[index];
        }
        System.out.println("\nElement " + uuid + " not found");
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            STORAGE_LIMIT[index] = STORAGE_LIMIT[size - 1];
            size--;
            System.out.println("\nElement " + uuid + " successfully deleted");
            return;
        }
        System.out.println("\nElement " + uuid + " not found");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(STORAGE_LIMIT, size);
    }

    public int size() {
        return size;
    }

    public int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (STORAGE_LIMIT[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

