package ru.javawebinar.basejava.exception;

public class ExistStorageException extends StorageException {

  public ExistStorageException(String uuid) {
    super("Resume " + uuid + " is already exists!", uuid);
  }
}
