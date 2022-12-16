package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Link implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;
  private String url;

  public Link() {
  }

  public Link(String name, String url) {
    Objects.requireNonNull(name, "name mustn't be null");
    this.name = name;
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public String toString() {
    return "Link{" +
        "name='" + name + '\'' +
        ", url='" + url + '\'' +
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
    Link link = (Link) o;
    return name.equals(link.name) && Objects.equals(url, link.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, url);
  }
}
