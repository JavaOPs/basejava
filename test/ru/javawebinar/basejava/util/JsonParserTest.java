package ru.javawebinar.basejava.util;

import static org.junit.jupiter.api.Assertions.*;
import static ru.javawebinar.basejava.TestData.R1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.Section;
import ru.javawebinar.basejava.model.SectionLine;

class JsonParserTest {

  @Test
  void testResume() {
    String json = JsonParser.write(R1);
    System.out.println(json);
    Resume resume = JsonParser.read(json, Resume.class);
    Assertions.assertEquals(R1, resume);
  }

  @Test
  void testSection() {
    Section section = new SectionLine("Personal1");
    String json = JsonParser.write(section, Section.class);
    System.out.println(json);
    Section section2 = JsonParser.read(json, Section.class);
    Assertions.assertEquals(section, section2);
  }
}