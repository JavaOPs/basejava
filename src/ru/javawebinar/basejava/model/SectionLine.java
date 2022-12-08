package ru.javawebinar.basejava.model;

public class SectionLine extends Section {

  private final String content;

  public SectionLine(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }
}
