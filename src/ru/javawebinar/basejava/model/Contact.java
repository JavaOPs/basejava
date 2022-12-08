package ru.javawebinar.basejava.model;

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
}
