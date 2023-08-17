package ru.javawebinar.basejava.storage;


import static ru.javawebinar.basejava.TestData.*;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.Config;

public abstract class AbstractStorageTest {

  protected static final File STORAGE_DIR = Config.get().getStorageDir();

  protected Storage storage;

  protected AbstractStorageTest(Storage storage) {
    this.storage = storage;
  }

  @BeforeEach
  public void setUp() {
    storage.clear();
    storage.save(R1);
    storage.save(R2);
    storage.save(R3);
  }

  @Test
  public void update() {
    Resume newResume = new Resume(UUID_1, "New Name");
    R1.setContact(ContactType.EMAIL, "m1@google.com");
    R1.setContact(ContactType.SKYPE, "newSkype");
    R1.setContact(ContactType.MOBILE_PHONE, "+79877654321");
    storage.update(newResume);
    Assertions.assertEquals(newResume, storage.get(UUID_1));
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
    storage.save(R4);
    assertSize(4);
    assertGet(R4);
  }

  @Test
  public void saveExist() {
    Assertions.assertThrows(ExistStorageException.class,
        () -> storage.save(R1));
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
    assertGet(R1);
    assertGet(R2);
    assertGet(R3);
  }

  @Test
  public void getNotExist() throws Exception {
    Assertions.assertThrows(NotExistStorageException.class,
        () -> storage.get("dummy"));
  }

  @Test
  public void getAllSorted() throws Exception {
    List<Resume> testStorage = storage.getAllSorted();
    Assertions.assertEquals(3, testStorage.size());
    List<Resume> sortedList = Arrays.asList(R1, R2, R3);
    Collections.sort(sortedList);
    Assertions.assertEquals(sortedList, testStorage);

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