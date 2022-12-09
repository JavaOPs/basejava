package ru.javawebinar.basejava.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

  protected abstract Resume doRead(File file);

  @Override
  protected File getSearchKey(String uuid) {
    return new File(directory, uuid);
  }

  @Override
  protected void doUpdate(Resume r, File file) {
    try {
      doWrite(r, file);
    } catch (IOException e) {
      throw new StorageException("IO error", file.getName(), e);
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
      doWrite(r, file);
    } catch (IOException e) {
      throw new StorageException("IO error", file.getName(), e);
    }
  }

  @Override
  protected void doDelete(File file) {
    file.delete();
  }

  @Override
  protected Resume doGet(File file) {
    return doRead(file);
  }

  @Override
  protected List<Resume> doCopyAll() {
    List<Resume> resumeList = new ArrayList<>();
    for(File file : directory.listFiles()) {
      if(!file.isDirectory()) {
        resumeList.add(doRead(file));
      }
    }
    return resumeList;
  }

  @Override
  public void clear() {
    for (File file : directory.listFiles()) {
      if(!file.isDirectory()) {
        file.delete();
      }
    }
  }

    @Override
  public int size() {
    return directory.listFiles().length;
  }
}
