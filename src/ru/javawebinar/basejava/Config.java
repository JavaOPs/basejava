package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

public class Config {

  private static Config INSTANCE;
  private static File storageDir;
  private static Storage storage;

  public static Config get() {
    if (INSTANCE == null) {
      Properties properties = new Properties();
      File fileWithProperties = new File(getHomeDir(), "config/resumes.properties");

      try (InputStream is = new FileInputStream(fileWithProperties)) {
        properties.load(is);
        storageDir = new File(properties.getProperty("storage.dir"));
        storage = new SqlStorage(properties.getProperty("db.url"),
            properties.getProperty("db.user"),
            properties.getProperty("db.password"));
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

  public Storage getStorage() {
    return storage;
  }

  private static File getHomeDir() {
    String prop = System.getProperty("homeDir");
    File homeDir = new File(prop != null ? prop : ".");
    if (!homeDir.isDirectory()) {
      throw new IllegalStateException(homeDir + " isn't directory!");
    }
    return homeDir;
  }
}
