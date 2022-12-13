package ru.javawebinar.basejava.model;

import java.util.Objects;

public class SectionLine extends Section {

  private static final long serialVersionUID = 1L;

  private final String content;

  public SectionLine(String content) {
    Objects.requireNonNull(content, "content mustn't be null");
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  @Override
  public String toString() {
    return "SectionLine{" +
        "content='" + content + '\'' +
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
    SectionLine that = (SectionLine) o;
    return content.equals(that.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content);
  }
}
