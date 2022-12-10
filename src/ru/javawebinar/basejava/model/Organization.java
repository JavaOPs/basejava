package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {

  private final Link homePage;
  private final List<Position> positions;

  public Organization(String name, String url, List<Position> positions) {
    Objects.requireNonNull(positions, "positions mustn't be null");
    this.homePage = new Link(name, url);
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
}
