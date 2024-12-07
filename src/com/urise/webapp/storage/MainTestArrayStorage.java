package com.urise.webapp.storage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    //    private static final AbstractArrayStorage ARRAY_STORAGE = new ArrayStorage();
    private static final AbstractArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        ARRAY_STORAGE.templateMethod();
    }
}
