package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainFile {

  public static void main(String[] args) {
    String filePath = ".gitignore";
    File file = new File(filePath);
    try {
      System.out.println(file.getCanonicalPath());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    File dir = new File("/Users/vladimirsafronov/Desktop/java/projects/basejava");
    System.out.println(dir.isDirectory());
    String[] listOfFiles = dir.list();
    if(listOfFiles != null) {
      for(String name : listOfFiles) {
        System.out.println(name);
      }
    }

    try(FileInputStream fis = new FileInputStream(filePath)) {
      System.out.println(fis.read());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
