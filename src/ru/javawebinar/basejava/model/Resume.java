package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>, Serializable {

  private static final long serialVersionUID = 1L;
  
  private final String uuid;
  private final String fullName;
  private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
  private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

  public Resume(String uuid, String fullName) {
    Objects.requireNonNull(uuid, "uuid must not be null");
    Objects.requireNonNull(fullName, "fullName must not be null");
    this.uuid = uuid;
    this.fullName = fullName;
  }

  public Resume(String fullName) {
    this(UUID.randomUUID().toString(), fullName);
  }

  public String getUuid() {
    return uuid;
  }

  public String getFullName() {
    return fullName;
  }

  public String getContact(ContactType type) {
    return contacts.get(type);
  }

  public Section getSection(SectionType type) {
    return sections.get(type);
  }

  public Map<ContactType, String> getContacts() {
    return contacts;
  }

  public Map<SectionType, Section> getSections() {
    return sections;
  }

  public void addContact(ContactType type, String value) {
    contacts.put(type, value);
  }

  public void addSection(SectionType type, Section section) {
    sections.put(type, section);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Resume resume = (Resume) o;
    return uuid.equals(resume.uuid) && fullName.equals(resume.fullName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, fullName);
  }

  @Override
  public String toString() {
    return "Resume{" +
        "uuid='" + uuid + '\'' +
        ", fullName='" + fullName + '\'' +
        '}';
  }

  @Override
  public int compareTo(Resume o) {
    int compareFullName = fullName.compareTo(o.fullName);
    return compareFullName != 0 ? compareFullName : uuid.compareTo(o.uuid);
  }
}
