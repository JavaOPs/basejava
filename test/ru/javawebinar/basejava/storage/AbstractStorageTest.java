package ru.javawebinar.basejava.storage;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorageTest {

  private final Storage storage;

  protected AbstractStorageTest(Storage storage) {
    this.storage = storage;
  }

  private static final String UUID_1 = "uuid1";
  private static final Resume RESUME_1 = new Resume(UUID_1);
  private static final String UUID_2 = "uuid2";
  private static final Resume RESUME_2 = new Resume(UUID_2);
  private static final String UUID_3 = "uuid3";
  private static final Resume RESUME_3 = new Resume(UUID_3);
  private static final String UUID_4 = "uuid4";
  private static final Resume RESUME_4 = new Resume(UUID_4);

  @BeforeEach
  public void setUp() {
    storage.clear();
    storage.save(RESUME_1);
    storage.save(RESUME_2);
    storage.save(RESUME_3);
  }

  @Test
  public void update() {
    Resume newResume = new Resume(UUID_1);
    storage.update(newResume);
    Assertions.assertSame(newResume, storage.get(UUID_1));
  }

  @Test
  public void resumeNotExist() {
    Assertions.assertThrows(NotExistStorageException.class,
        () -> storage.get("dummy"));
  }

  @Test
  public void size() {
    assertSize(3);
  }

  @Test
  public void save() {
    storage.save(RESUME_4);
    assertSize(4);
    assertGet(RESUME_4);
  }

  @Test
  public void saveExist() {
    Assertions.assertThrows(ExistStorageException.class,
        () -> storage.save(RESUME_1));
  }

  @Test
  public void saveOverflow() {
    try {
      for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
        storage.save(new Resume());
      }
    } catch (StorageException e) {
      Assertions.fail("Overflow was too early");
    }
    Assertions.assertThrows(StorageException.class,
        () -> storage.save(new Resume()));
  }

  @Test
  public void delete() {
    storage.delete(UUID_1);
    assertSize(2);
    Assertions.assertThrows(NotExistStorageException.class,
        () -> storage.delete(UUID_1));
  }

  @Test
  public void deleteNotExist() {
    Assertions.assertThrows(NotExistStorageException.class,
        () -> storage.delete("dummy"));
  }

  @Test
  public void get() {
    assertGet(RESUME_1);
    assertGet(RESUME_2);
    assertGet(RESUME_3);
  }

  @Test
  public void getNotExist() throws Exception {
    Assertions.assertThrows(NotExistStorageException.class,
        () -> storage.get("dummy"));
  }

  @Test
  public void getAll() throws Exception {
    Resume[] testStorage = storage.getAll();
    Assertions.assertEquals(3, testStorage.length);
    Assertions.assertEquals(RESUME_1, testStorage[0]);
    Assertions.assertEquals(RESUME_2, testStorage[1]);
    Assertions.assertEquals(RESUME_3, testStorage[2]);
  }

  @Test
  public void clear() throws Exception {
    storage.clear();
    assertSize(0);
  }

  private void assertSize(int expectedSize) {
    Assertions.assertEquals(expectedSize, storage.size());
  }

  private void assertGet(Resume r) {
    Assertions.assertEquals(r, storage.get(r.getUuid()));
  }
}