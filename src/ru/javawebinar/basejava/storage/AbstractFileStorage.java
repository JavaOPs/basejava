package ru.javawebinar.basejava.storage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

  private final File directory;

  protected AbstractFileStorage(File directory) {
    Objects.requireNonNull(directory, "directory mustn't be null");
    if (!directory.isDirectory()) {
      throw new IllegalArgumentException(directory.getAbsolutePath() + " isn't directory");
    }
    if (!directory.canRead() || !directory.canWrite()) {
      throw new IllegalArgumentException(directory.getAbsolutePath() + " isn't readable/writable!");
    }
    this.directory = directory;
  }

  protected abstract void doWrite(Resume r, File file) throws IOException;

  protected abstract Resume doRead(File file) throws IOException;

  @Override
  protected File getSearchKey(String uuid) {
    return new File(directory, uuid);
  }

  @Override
  protected void doUpdate(Resume r, File file) {
    try {
      doWrite(r, file);
    } catch (IOException e) {
      throw new StorageException("File write error", r.getUuid(), e);
    }
  }

  @Override
  protected boolean isExist(File file) {
    return file.exists();
  }

  @Override
  protected void doSave(Resume r, File file) {
    try {
      file.createNewFile();
    } catch (IOException e) {
      throw new StorageException("Couldn't create file" + file.getAbsolutePath(), file.getName(),
          e);
    }
    doUpdate(r, file);
  }

  @Override
  protected void doDelete(File file) {
    if (!file.delete()) {
      throw new StorageException("File delete error", file.getName());
    }
  }

  @Override
  protected Resume doGet(File file) {
    try {
      return doRead(file);
    } catch (IOException e) {
      throw new StorageException("File read error", file.getName(), e);
    }
  }

  @Override
  protected List<Resume> doCopyAll() {
    File[] files = directory.listFiles();
    if (files == null) {
      throw new StorageException("Directory read error", null);
    }
    List<Resume> resumeList = new ArrayList<>(files.length);
    for (File file : files) {
      resumeList.add(doGet(file));
    }
    return resumeList;
  }

  @Override
  public void clear() {
    File[] files = directory.listFiles();
    if (files != null) {
      for (File file : files) {
        doDelete(file);
      }
    }
  }

  @Override
  public int size() {
    String[] files = directory.list();
    if (files == null) {
      throw new StorageException("Directory read error", directory.getName());
    }
    return files.length;
  }
}
