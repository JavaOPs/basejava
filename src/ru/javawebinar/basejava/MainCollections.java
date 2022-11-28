package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import ru.javawebinar.basejava.model.Resume;

public class MainCollections {

  private static final String UUID_1 = "uuid1";
  private static final Resume RESUME_1 = new Resume(UUID_1);
  private static final String UUID_2 = "uuid2";
  private static final Resume RESUME_2 = new Resume(UUID_2);
  private static final String UUID_3 = "uuid3";
  private static final Resume RESUME_3 = new Resume(UUID_3);
  private static final String UUID_4 = "uuid4";
  private static final Resume RESUME_4 = new Resume(UUID_4);

  public static void main(String[] args) {
    Collection<Resume> collection = new ArrayList<>();
    collection.add(RESUME_1);
    collection.add(RESUME_2);
    collection.add(RESUME_3);

    collection.removeIf(r -> Objects.equals(r.getUuid(), UUID_1));
//    System.out.println(collection.);
  }
}
