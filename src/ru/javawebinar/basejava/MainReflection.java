package ru.javawebinar.basejava;

import java.lang.reflect.Field;
import ru.javawebinar.basejava.model.Resume;

public class MainReflection {

  public static void main(String[] args) throws IllegalAccessException {
    Resume r = new Resume();
    Field field = r.getClass().getDeclaredFields()[0];
    field.setAccessible(true);
    System.out.println(field.getName());
    System.out.println(field.get(r));
    field.set(r, "plumber");
    System.out.println(r);
  }
}
