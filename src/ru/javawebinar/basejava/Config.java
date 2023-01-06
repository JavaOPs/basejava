package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

  private static Config INSTANCE;
  private static File storageDir;

  public static Config get() {
    if (INSTANCE == null) {
      Properties properties = new Properties();
      File fileWithProperties = new File("config/resumes.properties");

      try (InputStream is = new FileInputStream(fileWithProperties)) {
        properties.load(is);
        storageDir = new File(properties.getProperty("storage.dir"));
      } catch (IOException e) {
        throw new IllegalStateException(
            "Invalid config file " + fileWithProperties.getAbsolutePath());
      }
      INSTANCE = new Config();
    }
    return INSTANCE;
  }

  private Config() {
  }

  public File getStorageDir() {
    return storageDir;
  }
}
