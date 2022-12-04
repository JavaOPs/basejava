import java.util.List;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ArrayStorage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Interactive test for com.urise.webapp.storage.ArrayStorage implementation (just run, no need to
 * understand)
 */
public class MainArray {

  private final static ArrayStorage ARRAY_STORAGE = new ArrayStorage();

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Resume r;
    while (true) {
      System.out.print("Введите одну из команд - "
          + "(list | update uuid fullName | size | save fullName | delete uuid | get uuid | clear | exit): ");
      String[] params = reader.readLine().trim().toLowerCase().split(" ");
      if (params.length < 1 || params.length > 3) {
        System.out.println("Неверная команда.");
        continue;
      }
      String parameter = null;
      if (params.length > 1) {
        parameter = params[1].intern();
      }
      switch (params[0]) {
        case "list":
          printAll();
          break;
        case "update":
          r = new Resume(parameter, params[2]);
          ARRAY_STORAGE.update(r);
          printAll();
          break;
        case "size":
          System.out.println(ARRAY_STORAGE.size());
          break;
        case "save":
          r = new Resume(parameter);
          ARRAY_STORAGE.save(r);
          printAll();
          break;
        case "delete":
          ARRAY_STORAGE.delete(parameter);
          printAll();
          break;
        case "get":
          System.out.println(ARRAY_STORAGE.get(parameter));
          break;
        case "clear":
          ARRAY_STORAGE.clear();
          printAll();
          break;
        case "exit":
          return;
        default:
          System.out.println("Неверная команда.");
          break;
      }
    }
  }

  static void printAll() {
    List<Resume> all = ARRAY_STORAGE.getAllSorted();
    System.out.println("----------------------------");
    if (all.size() == 0) {
      System.out.println("Empty");
    } else {
      for (Resume r : all) {
        System.out.println(r);
      }
    }
    System.out.println("----------------------------");
  }
}
