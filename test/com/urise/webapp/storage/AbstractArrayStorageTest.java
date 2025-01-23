package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

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
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume r = new Resume("uuid2");
        storage.update(r);
        Assert.assertEquals(r, storage.get("uuid2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void save() {
        Resume r = new Resume("uuid4");
        storage.save(r);
        Assert.assertEquals(r, storage.get("uuid4"));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test
    public void get() {
        Resume r = new Resume("uuid1");
        Assert.assertEquals(storage.get("uuid1"), r);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void delete() {
        Resume r = new Resume("uuid2");
        storage.delete(r.getUuid());
        Assert.assertEquals(2, storage.size());
        Assert.assertThrows(NotExistStorageException.class, () -> storage.get("uuid2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void getAll() {
        Resume[] r = storage.getAll();
        Assert.assertEquals(3, r.length);
        Assert.assertTrue(Arrays.asList(r).contains(storage.get("uuid1")));
        Assert.assertTrue(Arrays.asList(r).contains(storage.get("uuid2")));
        Assert.assertTrue(Arrays.asList(r).contains(storage.get("uuid3")));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
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