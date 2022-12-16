package ru.javawebinar.basejava.model;

import static ru.javawebinar.basejava.util.DateUtil.NOW;
import static ru.javawebinar.basejava.util.DateUtil.of;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ru.javawebinar.basejava.util.LocalDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {

  private static final long serialVersionUID = 1L;

  private Link homePage;
  private List<Position> positions = new ArrayList<>();

  public Organization() {
  }

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
    return Objects.equals(homePage, that.homePage) && Objects.equals(positions,
        that.positions);
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

  @XmlAccessorType(XmlAccessType.FIELD)
  public static class Position implements Serializable {

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    private String title;
    private String description;

    public Position() {
    }

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

    public LocalDate getStartDate() {
      return startDate;
    }

    public LocalDate getEndDate() {
      return endDate;
    }

    public String getTitle() {
      return title;
    }

    public String getDescription() {
      return description;
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
