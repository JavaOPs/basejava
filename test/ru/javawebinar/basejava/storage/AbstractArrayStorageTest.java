package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {

  private Storage storage;
  private static final String UUID_1 = "uuid1";
  private static final String UUID_2 = "uuid2";
  private static final String UUID_3 = "uuid3";

  @Before
  public void setUp() throws Exception {
    storage.clear();
    storage.save(new Resume(UUID_1));
    storage.save(new Resume(UUID_2));
    storage.save(new Resume(UUID_3));
  }

  @Test
  public void update() {
  }

  @Test
  public void size() {
    Assert.assertEquals(3, storage.size());
  }

  @Test
  public void save() {
  }

  @Test
  public void delete() {
  }

  @Test
  public void get() {
  }

  @Test(expected = NotExistStorageException.class)
  public void getNotExist() {
    storage.get("dummy");
  }

  @Test
  public void getAll() {
  }

  @Test
  public void clear() {
  }
}