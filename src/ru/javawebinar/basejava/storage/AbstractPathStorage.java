package ru.javawebinar.basejava.storage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {

  private final Path directory;

  protected AbstractPathStorage(String dir) {
    directory = Paths.get(dir);
    Objects.requireNonNull(directory, "directory mustn't be null");
    if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
      throw new IllegalArgumentException(dir + " isn't directory or isn't writable");
    }
  }

  protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

  protected abstract Resume doRead(InputStream is) throws IOException;

  @Override
  protected Path getSearchKey(String uuid) {
    return directory.resolve(uuid);
  }

  @Override
  protected void doUpdate(Resume r, Path path) {
    try {
      doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
    } catch (IOException e) {
      throw new StorageException("Path write error", r.getUuid(), e);
    }
  }

  @Override
  protected boolean isExist(Path path) {
    return Files.exists(path);
  }

  @Override
  protected void doSave(Resume r, Path path) {
    try {
      Files.createFile(path);
    } catch (IOException e) {
      throw new StorageException("Couldn't create file" + path, getFileName(path), e);
    }
    doUpdate(r, path);
  }

  @Override
  protected void doDelete(Path path) {
    try {
      Files.delete(path);
    } catch (IOException e) {
      throw new StorageException("Path delete error", getFileName(path), e);
    }
  }

  @Override
  protected Resume doGet(Path path) {
    try {
      return doRead(new BufferedInputStream(Files.newInputStream(path)));
    } catch (IOException e) {
      throw new StorageException("Path read error", getFileName(path), e);
    }
  }

  @Override
  protected List<Resume> doCopyAll() {
    List<Resume> resumeList = new ArrayList<>();
    try {
      Files.list(directory).forEach(path -> resumeList.add(doGet(path)));
    } catch (IOException e) {
      throw new StorageException("File read error", null);
    }
    return resumeList;
  }

  @Override
  public void clear() {
    try {
      Files.list(directory).forEach(this::doDelete);
    } catch (IOException e) {
      throw new StorageException("File delete error", null);
    }
  }

  @Override
  public int size() {
    List<Path> files;
    try {
      files = Files.list(directory).collect(Collectors.toList());
    } catch (IOException e) {
      throw new StorageException("Directory read error", null);
    }
    return files.size();
  }

  private String getFileName(Path path) {
    return path.getFileName().toString();
  }
}
