package ru.javawebinar.basejava.model;

public enum ContactType {
  MOBILE_PHONE("Mobile phone"),
  SKYPE("Skype") {
    @Override
    public String toHtml0(String value) {
      return "<a href='skype:" + value + "'>" + value + "</a>";
    }
  },
  EMAIL("Email") {
    @Override
    public String toHtml0(String value) {
      return "<a href='mailto:" + value + "'>" + value + "</a>";
    }
  },
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

  protected String toHtml0(String value) {
    return title + ": " + value;
  }

  public String toHtml(String value) {
    return value == null ? "" : toHtml0(value);
  }
}
