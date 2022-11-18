/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

  Resume[] storage = new Resume[10000];
  private int storageSize = 0;

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
    int deletedIndex = -1;
    for (int i = 0; i < storageSize; i++) {
      if (uuid.equals(storage[i].uuid)) {
        deletedIndex = i;
        break;
      }
    }
    if (deletedIndex >= 0) {
      for (int i = deletedIndex; i < storageSize - 1; i++) {
        storage[i] = storage[i + 1];
      }
      storageSize -= 1;
    }
  }

  /**
   * @return array, contains only Resumes in storage (without null)
   */
  Resume[] getAll() {
    Resume[] onlyWithData = new Resume[storageSize];
    for (int i = 0; i < storageSize; i++) {
      onlyWithData[i] = storage[i];
    }
    return onlyWithData;
  }

  int size() {
    return storageSize;
  }
}
