package ru.javawebinar.basejava.model;

public enum ContactType {
  TELEPHONE_NUMBER("Telephone"),
  SKYPE("Skype"),
  EMAIL("Email"),
  OTHER_INFO("");
  private final String title;

  ContactType(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
