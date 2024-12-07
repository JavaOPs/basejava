package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("\nThe sorted array was successfully cleared");
    }

    @Override
    public void update(Resume r) {
        if (findIndex(r.getUuid()) >= 0) {
            storage[findIndex(r.getUuid())].setUuid(r.getUuid());
            System.out.println("\nElement " + r + " successfully update");
        } else {
            System.out.println("\nERROR: Element " + r + " not found");
        }
    }

    @Override
    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (index < 0 && size < storage.length) {
            int insertIndex = -(Arrays.binarySearch(storage, 0, size, r) + 1);
            System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
            storage[insertIndex] = r;
            size++;
            System.out.println("Element " + r + " successfully saved to sorted array");
        } else {
            System.out.println("ERROR: array overflow! Element " + r + " not saved to sorted array.");
        }
    }

    @Override
    public void delete(String uuid) {
        if (findIndex(uuid) >= 0) {
            storage[findIndex(uuid)] = storage[size - 1];
            size--;
            System.out.println("\nElement " + uuid + " successfully deleted");
            return;
        }
        System.out.println("\nElement " + uuid + " not found");
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
