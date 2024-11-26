package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("\nThe array was successfully cleared");
    }

    public void update(Resume r) {
        int index = desiredIndex(r.getUuid());
        if (index >= 0) {
            storage[index].setUuid(r.getUuid());
            System.out.println("\nElement " + r + " successfully update");
        } else {
            System.out.println("\nERROR: Element " + r + " not found");
        }
    }

    public void save(Resume r) {
        int index = desiredIndex(r.getUuid());
        if (index < 0 && size < storage.length) {
            storage[size] = r;
            size++;
            System.out.println("Element " + r + " successfully saved to array");
        } else {
            System.out.println("ERROR: array overflow! Element " + r + " not saved to array.");
        }
    }

    public Resume get(String uuid) {
        int index = desiredIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = desiredIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
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
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public int desiredIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
