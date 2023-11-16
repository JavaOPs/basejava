import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    private static int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (r == null) throw new IllegalArgumentException("resume not null");
        if (size == 0) storage[0] = r;
        else storage[size] = r;
        size++;
    }


    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (this.storage[i].uuid == uuid)
                return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                // storage[i] = null;
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
            }
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        return size;
    }

    @Override
    public String toString() {
        return "ArrayStorage{" +
                "storage=" + Arrays.toString(storage);
    }
}
