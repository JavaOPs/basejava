package ru.javawebinar.basejava.util;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

  @Override
  public LocalDate unmarshal(String str) throws Exception {
    return LocalDate.parse(str);
  }

  @Override
  public String marshal(LocalDate ld) throws Exception {
    return ld.toString();
  }
}
