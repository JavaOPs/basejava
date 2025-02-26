package ru.javawebinar.basejava.storage;

import java.util.Comparator;
import ru.javawebinar.basejava.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

  private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

  @Override
  protected Integer getSearchKey(String uuid) {
    Resume searchKey = new Resume(uuid, "dummy");
    return Arrays.binarySearch(storage, 0, storageSize, searchKey, RESUME_COMPARATOR);
  }

  @Override
  protected void insertElement(Resume r, int index) {
    int insertIndex = -index - 1;
    System.arraycopy(storage, insertIndex, storage, insertIndex + 1, storageSize - insertIndex);
    storage[insertIndex] = r;
  }

  @Override
  protected void fillDeletedElement(int index) {
    int numMoved = storageSize - 1 - index;
    if (numMoved >= 0) {
      System.arraycopy(storage, index + 1, storage, index, numMoved);
    }
  }
}
