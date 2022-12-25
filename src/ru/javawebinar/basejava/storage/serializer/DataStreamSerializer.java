package ru.javawebinar.basejava.storage.serializer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;

public class DataStreamSerializer implements StreamSerializer {

  @Override
  public void doWrite(Resume r, OutputStream os) throws IOException {
    try (DataOutputStream dos = new DataOutputStream(os)) {
      dos.writeUTF(r.getUuid());
      dos.writeUTF(r.getFullName());
      Map<ContactType, String> contacts = r.getContacts();
      dos.writeInt(contacts.size());
      for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
        dos.writeUTF(entry.getKey().name());
        dos.writeUTF(entry.getValue());
      }
      // TODO implement sections
    }
  }

  @Override
  public Resume doRead(InputStream is) throws IOException {
    try (DataInputStream dis = new DataInputStream(is)) {
      String uuid = dis.readUTF();
      String fullName = dis.readUTF();
      Resume resume = new Resume(uuid, fullName);
      int size = dis.readInt();
      for (int i = 0; i < size; i++) {
        resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
      }
      // TODO implement sections
      return resume;
    }
  }
}
