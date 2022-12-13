package ru.javawebinar.basejava.storage;

public class ObjectStreamStorageTest extends  AbstractStorageTest {

  protected ObjectStreamStorageTest() {
    super(new ObjectStreamStorage(STORAGE_DIR));
  }
}
