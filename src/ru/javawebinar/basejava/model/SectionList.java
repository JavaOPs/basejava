package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class SectionList extends Section {
  private final List<String> content;

  public SectionList() {
    this.content = new ArrayList<>();
  }

  public List<String> getContent() {
    return content;
  }
}
