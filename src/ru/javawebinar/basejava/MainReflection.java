package ru.javawebinar.basejava;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import ru.javawebinar.basejava.model.Resume;

public class MainReflection {

  public static void main(String[] args)
      throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    Resume r = new Resume("Name1", sections);
    Class<? extends Resume> resumeClass = r.getClass();
    Field field = resumeClass.getDeclaredFields()[0];
    field.setAccessible(true);
    System.out.println(field.getName());
    System.out.println(field.get(r));
    field.set(r, "plumber");

    Method method = resumeClass.getMethod("toString");
    Object result = method.invoke(r);

    System.out.println(result);
  }
}
