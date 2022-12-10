package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Position {
  private final LocalDate startDate;
  private final LocalDate endDate;
  private final String title;
  private final String description;

  public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
    Objects.requireNonNull(startDate, "startDate mustn't be null");
    Objects.requireNonNull(endDate, "endDate mustn't be null");
    Objects.requireNonNull(title, "title mustn't be null");
    this.startDate = startDate;
    this.endDate = endDate;
    this.title = title;
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Position position = (Position) o;
    return startDate.equals(position.startDate) && endDate.equals(position.endDate) && title.equals(
        position.title) && Objects.equals(description, position.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startDate, endDate, title, description);
  }

  @Override
  public String toString() {
    return "Position{" +
        "startDate=" + startDate +
        ", endDate=" + endDate +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
