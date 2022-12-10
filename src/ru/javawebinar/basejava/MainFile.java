package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
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

    File dir = new File("/Users/vladimirsafronov/Desktop/java/projects/basejava/src");
    String[] listOfFiles = dir.list();
    if (listOfFiles != null) {
      for (String name : listOfFiles) {
        System.out.println(name);
      }
    }

    try (FileInputStream fis = new FileInputStream(filePath)) {
      System.out.println(fis.read());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    showAllFiles(dir);
  }

  private static void showAllFiles(File dir) {
    StringBuilder sb = new StringBuilder();
    if (dir.isDirectory()) {
      System.out.println("Searching at: " + dir.getAbsolutePath());
      File[] files = dir.listFiles();
      if (files != null) {
        sb.append("\t");
        for (File file : files) {
          if (file.isDirectory()) {
            sb.append("\t");
            showAllFiles(file);
          } else if (file.isFile()) {
            System.out.println(sb + "File: " + file.getName());
          }
        }
      }
    }
  }
}
