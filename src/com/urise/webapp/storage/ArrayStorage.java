package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
        System.out.println("\nThe array was successfully cleared");
    }

    public void update(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(r)) {
                storage[i].setUuid(r.getUuid());
                System.out.println("\nElement " + r + " successfully update");
            } else {
                System.out.println("\nERROR: Element " + r + " not found");
            }
        }
    }

    public void save(Resume r) {
        if (size < storage.length) {
            storage[size] = r;
            size++;
        }
        System.out.println("Element " + r + " successfully saved to array");
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[size - 1];
                size--;
                System.out.println("\nElement " + uuid + " successfully deleted");
                return;
            }
        }
        System.out.println("\nElement " + uuid + " not found");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    public int size() {
        return size;
    }
}
