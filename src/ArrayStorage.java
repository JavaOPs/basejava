import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        System.out.println("\nThe array was successfully cleared");
    }

    void save(Resume r) {
        if (size < storage.length) {
            storage[size] = r;
            size++;
        }
        System.out.println("Element " + r + " successfully saved to array");
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid) && storage[i] != null) {
                for (int j = i; j < size - 1; j++) {
                    storage[j] = storage[j + 1];
                }
                storage[size - 1] = null;
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
    Resume[] getAll() {
        Resume[] storageAll = new Resume[size];
        for (int i = 0; i < size; i++) {
            storageAll[i] = storage[i];
        }
        return storageAll;
    }

    int size() {
        return size;
    }
}
