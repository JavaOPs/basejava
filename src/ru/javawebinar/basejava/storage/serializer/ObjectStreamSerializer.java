package ru.javawebinar.basejava.storage.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public class ObjectStreamSerializer implements StreamSerializer {

  @Override
  public void doWrite(Resume r, OutputStream os) throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
      oos.writeObject(r);
    }
  }

  @Override
  public Resume doRead(InputStream is) throws IOException {
    try (ObjectInputStream ois = new ObjectInputStream(is)) {
      return (Resume) ois.readObject();
    } catch (ClassNotFoundException e) {
      throw new StorageException("Error read resume", null, e);
    }
  }
}
