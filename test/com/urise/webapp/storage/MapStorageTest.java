package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class MapStorageTest {

    private MapStorage mapStorage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume SAVE_RESUME;

    static {
        RESUME_1 = new Resume(UUID_1);
        RESUME_2 = new Resume(UUID_2);
        RESUME_3 = new Resume(UUID_3);
        SAVE_RESUME = new Resume(UUID_4);
    }

    private final Resume UUID_NOT_EXIST = new Resume("dummy");

    private final int size = 3;

    @Before
    public void setUp() {
        mapStorage = new MapStorage();
        mapStorage.clear();
        mapStorage.save(RESUME_1);
        mapStorage.save(RESUME_2);
        mapStorage.save(RESUME_3);
    }

    @Test
    public void clear() {
        mapStorage.clear();
        assertSize(0);
        Assert.assertArrayEquals(new Resume[0], mapStorage.getAll());
    }

    @Test
    public void update() {
        mapStorage.update(RESUME_2);
        Assert.assertSame(RESUME_2, mapStorage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        mapStorage.update(UUID_NOT_EXIST);
    }

    @Test
    public void save() {
        mapStorage.save(SAVE_RESUME);
        assertGet(SAVE_RESUME);
        assertSize(size + 1);
    }

    @Test
    public void saveExist() {
        assertThrows(IllegalArgumentException.class, () -> mapStorage.save(RESUME_1));
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, mapStorage.get(resume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        mapStorage.get(UUID_NOT_EXIST.getUuid());
    }

    @Test
    public void delete() {
        mapStorage.delete(UUID_2);
        assertSize(size - 1);
        assertThrows(NotExistStorageException.class, () -> mapStorage.get(UUID_2));
    }

    @Test
    public void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> mapStorage.delete(UUID_NOT_EXIST.getUuid()));
    }

    @Test
    public void getAll() {
        Resume[] array = mapStorage.getAll();
        assertEquals(size, array.length);
        Assert.assertArrayEquals(new Resume[]{RESUME_1, RESUME_2, RESUME_3}, array);
    }

    @Test
    public void size() {
        assertSize(size);
    }

    private void assertSize(int expectedSize) {
        assertEquals(expectedSize, mapStorage.size());
    }

}
