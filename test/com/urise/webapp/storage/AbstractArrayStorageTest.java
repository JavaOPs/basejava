package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private final Storage storage;
    private final int size = 3;
   private final Resume SAVE_RESUME = new Resume("uuid4");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Assert.assertArrayEquals(new Resume[0], storage.getAll());
    }

    @Test
    public void update() {
        Resume r = new Resume(UUID_2);
        storage.update(r);
        Assert.assertSame(r, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void save() {
        storage.save(SAVE_RESUME);
        assertGet(SAVE_RESUME);
        assertSize(size+1);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test
    public void get() {
        Resume resume = new Resume("uuid1");
        assertGet(resume);
      }

    public void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void delete() {
        Resume resume = new Resume("uuid2");
        storage.delete(resume.getUuid());
        assertSize(size-1);
        Assert.assertThrows(NotExistStorageException.class, () -> storage.get(resume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void getAll() {
        Resume[] actual = storage.getAll();
        Assert.assertEquals(size, actual.length);
        assertGet(new Resume(UUID_1));
        assertGet(new Resume(UUID_2));
        assertGet(new Resume(UUID_3));
        Resume[] expected = new Resume[] {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void size() {
        assertSize(size);
    }

    public void assertSize(int expectedSize) {
        Assert.assertEquals(expectedSize, storage.size());
    }

    @Test
    public void testStorageOverflow() {
        try {
            Assert.assertEquals(3, storage.size());
        } catch (StorageException e) {
            Assert.fail("Переполнение произошло раньше времени: " + e.getMessage());
        }
        try {
            storage.save(new Resume("uuid4"));
        } catch (StorageException e) {
            Assert.fail("Ожидалось исключение StorageException при добавлении в переполненное хранилище");
        }
    }
}