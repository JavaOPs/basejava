package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractStorage;
import com.urise.webapp.storage.ListStorage;

import java.util.*;

public class MainCollections {
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);
//    private static final Resume UUID_NOT_EXIST = new Resume("dummy");
    private static final AbstractStorage ABSTRACT_STORAGE = new ListStorage();

    public static void main(String[] args) {
        ABSTRACT_STORAGE.save(RESUME_1);
        ABSTRACT_STORAGE.save(RESUME_2);
        ABSTRACT_STORAGE.save(RESUME_3);
        ABSTRACT_STORAGE.getAll();
        printAll();

        System.out.println("Get r1: " + ABSTRACT_STORAGE.get(RESUME_1.getUuid()));
        System.out.println("Size: " + ABSTRACT_STORAGE.size());

//        System.out.println("Get dummy: " + ABSTRACT_STORAGE.get(UUID_NOT_EXIST.getUuid()));

        ABSTRACT_STORAGE.update(RESUME_2);
        System.out.println("ok update");
        printAll();
        System.out.println("--------");

        ABSTRACT_STORAGE.save(RESUME_4);
        System.out.println("ok save");
        printAll();
        System.out.println("--------");

        ABSTRACT_STORAGE.delete(RESUME_3.getUuid());
        System.out.println("ok delete");
        printAll();
        System.out.println("--------");

        ABSTRACT_STORAGE.clear();
        System.out.println("ok clear");
        printAll();
        System.out.println("--------");

        System.out.println("Size: " + ABSTRACT_STORAGE.size());
        ABSTRACT_STORAGE.size();

        System.out.println("Collection");
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);

        for (Resume r : collection) {
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)) {
//                collection.remove(r);
            }
        }

        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)) {
                iterator.remove();
            }
        }
        System.out.println(collection.toString());


        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, RESUME_1);
        map.put(UUID_2, RESUME_2);
        map.put(UUID_3, RESUME_3);

        // Bad!
        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ABSTRACT_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}


