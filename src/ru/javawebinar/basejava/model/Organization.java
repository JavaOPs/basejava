package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {

  private final Link homePage;
  private final LocalDate startDate;
  private final LocalDate endDate;
  private final String title;
  private final String description;

  public Organization(String name, String url, LocalDate startDate, LocalDate endDate, String title,
      String description) {
    Objects.requireNonNull(startDate, "startDate mustn't be null");
    Objects.requireNonNull(endDate, "endDate mustn't be null");
    Objects.requireNonNull(title, "title mustn't be null");
    this.homePage = new Link(name, url);
    this.startDate = startDate;
    this.endDate = endDate;
    this.title = title;
    this.description = description;
  }

  @Override
  public String toString() {
    return "Organization{" +
        "homePage=" + homePage +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Organization that = (Organization) o;
    return homePage.equals(that.homePage) && startDate.equals(that.startDate) && endDate.equals(
        that.endDate) && title.equals(that.title) && Objects.equals(description,
        that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(homePage, startDate, endDate, title, description);
  }
}
