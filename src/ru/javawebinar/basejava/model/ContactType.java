package ru.javawebinar.basejava.model;

public enum ContactType {
  MOBILE_PHONE("Mobile phone"),
  SKYPE("Skype"),
  EMAIL("Email"),
  LINKEDIN("LinkedIn profile"),
  GITHUB("Github profile"),
  STACKOVERFLOW("Stackoverflow profile"),
  HOME_PAGE("Home page");
  private final String title;

  ContactType(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
