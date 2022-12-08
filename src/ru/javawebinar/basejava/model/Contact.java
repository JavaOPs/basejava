package ru.javawebinar.basejava.model;

import java.util.Objects;

public class Contact {
  private final ContactType type;
  private final String content;

  public Contact(ContactType type, String content) {
    this.type = type;
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public ContactType getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contact contact = (Contact) o;
    return type == contact.type && Objects.equals(content, contact.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, content);
  }

  @Override
  public String toString() {
    return "Contact{" +
        "type=" + type +
        ", content='" + content + '\'' +
        '}';
  }
}
