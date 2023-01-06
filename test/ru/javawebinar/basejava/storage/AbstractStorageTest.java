package ru.javawebinar.basejava.storage;


import java.io.File;
import java.time.Month;
import java.util.Arrays;
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

  private static final String UUID_1 = "uuid1";
  private static final String UUID_2 = "uuid2";
  private static final String UUID_3 = "uuid3";
  private static final String UUID_4 = "uuid4";
  private static final Resume R1;
  private static final Resume R2;
  private static final Resume R3;
  private static final Resume R4;

  static {
    R1 = new Resume(UUID_1, "Name1");
    R2 = new Resume(UUID_2, "Name2");
    R3 = new Resume(UUID_3, "Name3");
    R4 = new Resume(UUID_4, "Name4");

    R1.addContact(ContactType.MOBILE_PHONE, "+79001234567");
    R1.addContact(ContactType.EMAIL, "abc@mail.ru");
    R1.addSection(SectionType.OBJECTIVE, new SectionLine("Objective"));
    R1.addSection(SectionType.PERSONAL, new SectionLine("Personal data"));
    R1.addSection(SectionType.ACHIEVEMENT,
        new SectionList("Achievement11", "Achievement12", "Achievement13"));
    R1.addSection(SectionType.QUALIFICATION, new SectionList("Java", "git", "SQL"));
    R1.addSection(SectionType.EXPERIENCE,
        new SectionOrganization(
            new Organization("Organization11", "http://organization11.ru",
                new Organization.Position(2015, Month.JANUARY, "position11", "content11"),
                new Organization.Position(2010, Month.JULY, 2014, Month.DECEMBER, "position12",
                    "content12"))));
    R1.addSection(SectionType.EDUCATION,
        new SectionOrganization(
            new Organization("university", null,
                new Organization.Position(2005, Month.SEPTEMBER, 2010, Month.JUNE, "aspirant",
                    null),
                new Organization.Position(2001, Month.SEPTEMBER, 2005, Month.APRIL, "student",
                    "IT")),
            new Organization("Organization12", "http://organization12.ru")));

    R2.addContact(ContactType.MOBILE_PHONE, "1234567");
    R2.addContact(ContactType.SKYPE, "@SecondMember");
    R2.addSection(SectionType.EXPERIENCE,
        new SectionOrganization(
            new Organization("Organization21", "http://organization21.ru",
            new Organization.Position(2018, Month.JULY, "position21", "content21"))));
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
    Assertions.assertEquals(testStorage, Arrays.asList(R1, R2, R3));
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