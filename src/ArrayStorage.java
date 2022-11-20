import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

  Resume[] storage = new Resume[10000];
  private int storageSize;

  void clear() {
    for (int i = 0; i < storageSize; i++) {
      storage[i] = null;
    }
    storageSize = 0;
  }

  void save(Resume r) {
    storage[storageSize++] = r;
  }

  Resume get(String uuid) {
    for (int i = 0; i < storageSize; i++) {
      if (uuid.equals(storage[i].uuid)) {
        return storage[i];
      }
    }
    return null;
  }

  void delete(String uuid) {
    for (int i = 0; i < storageSize; i++) {
      if (uuid.equals(storage[i].uuid)) {
        storageSize--;
        storage[i] = storage[storageSize];
        storage[storageSize] = null;
        break;
      }
    }
  }

  /**
   * @return array, contains only Resumes in storage (without null)
   */
  Resume[] getAll() {
    return Arrays.copyOf(storage, storageSize);
  }

  int size() {
    return storageSize;
  }
}
