package ru.javawebinar.basejava.util;

public class LazySingleton {

//  private static volatile LazySingleton INSTANCE;

  private LazySingleton() {
  }

//  public static LazySingleton getInstance() {
//    if (INSTANCE == null) {
//      synchronized (LazySingleton.class) {
//        if (INSTANCE == null) {
//          INSTANCE = new LazySingleton();
//        }
//      }
//    }
//    return INSTANCE;
//  }

  private static class LazySingletonHolder{
    static final LazySingleton INSTANCE = new LazySingleton();
  }

  public static LazySingleton getInstance() {
    return LazySingletonHolder.INSTANCE;
  }

}
