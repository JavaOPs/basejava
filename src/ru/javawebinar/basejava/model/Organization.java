package ru.javawebinar.basejava.model;

import static ru.javawebinar.basejava.util.DateUtil.NOW;
import static ru.javawebinar.basejava.util.DateUtil.of;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization {

  private final Link homePage;
  private List<Position> positions;

  public Organization(String name, String url, Position... positions) {
    this(new Link(name, url), Arrays.asList(positions));
  }

  public Organization(Link homePage, List<Position> positions) {
    Objects.requireNonNull(positions, "positions mustn't be null");
    this.homePage = homePage;
    this.positions = positions;
  }

  public List<Position> getPositions() {
    return positions;
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
    return homePage.equals(that.homePage) && positions.equals(that.positions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(homePage, positions);
  }

  @Override
  public String toString() {
    return "Organization{" +
        "homePage=" + homePage +
        ", positions=" + positions +
        '}';
  }

  public static class Position {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Position(int year, Month startMonth, String title, String description) {
      this(of(year, startMonth), NOW, title, description);
    }

    public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title,
        String description) {
      this(of(startYear, startMonth), of(endYear, endMonth), title, description);
    }

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
      return startDate.equals(position.startDate) && endDate.equals(position.endDate)
          && title.equals(
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
}
