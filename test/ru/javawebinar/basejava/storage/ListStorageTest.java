package ru.javawebinar.basejava.storage;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.model.Resume;

public class ListStorageTest extends AbstractArrayStorageTest {

  protected ListStorageTest() {
    super(new ListStorage());
  }
}
