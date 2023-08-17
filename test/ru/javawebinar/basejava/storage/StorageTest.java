package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public class StorageTest extends AbstractStorageTest {

  public StorageTest() {
    super(new ArrayStorage());
  }

  @Test
  public void saveOverflow() {
    try {
      for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
        storage.save(new Resume("name" + i));
      }
    } catch (StorageException e) {
      Assertions.fail("Overflow was too early");
    }
    Assertions.assertThrows(StorageException.class,
        () -> storage.save(new Resume("Overflow")));
  }
}