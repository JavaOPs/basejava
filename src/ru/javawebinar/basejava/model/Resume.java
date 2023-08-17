package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {

  private static final long serialVersionUID = 1L;
  public static final Resume EMPTY = new Resume();

  static {
    EMPTY.setSection(SectionType.OBJECTIVE, SectionLine.EMPTY);
    EMPTY.setSection(SectionType.PERSONAL, SectionLine.EMPTY);
    EMPTY.setSection(SectionType.ACHIEVEMENT, SectionList.EMPTY);
    EMPTY.setSection(SectionType.QUALIFICATION, SectionList.EMPTY);
    EMPTY.setSection(SectionType.EXPERIENCE, new SectionOrganization(Organization.EMPTY));
    EMPTY.setSection(SectionType.EDUCATION, new SectionOrganization(Organization.EMPTY));
  }

  private String uuid;
  private String fullName;
  private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
  private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

  public Resume() {
  }

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

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Map<ContactType, String> getContacts() {
    return contacts;
  }

  public Map<SectionType, Section> getSections() {
    return sections;
  }

  public String getContact(ContactType type) {
    return contacts.get(type);
  }

  public Section getSection(SectionType type) {
    return sections.get(type);
  }

  public void setContact(ContactType type, String value) {
    contacts.put(type, value);
  }

  public void setSection(SectionType type, Section section) {
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
    return Objects.equals(uuid, resume.uuid) &&
        Objects.equals(fullName, resume.fullName) &&
        Objects.equals(contacts, resume.contacts) &&
        Objects.equals(sections, resume.sections);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, fullName, contacts, sections);
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
