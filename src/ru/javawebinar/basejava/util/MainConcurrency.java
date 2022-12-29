package ru.javawebinar.basejava.util;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {

  private static final Object LOCK = new Object();
  public static final int THREADS_NUMBER = 10000;
  private static int counter = 0;

  public static void main(String[] args) throws InterruptedException {
    System.out.println(Thread.currentThread().getName());
    Thread thread0 = new Thread() {
      @Override
      public void run() {
        System.out.println(getName() + ", " + getState());
      }
    };
    thread0.start();

    new Thread(() -> {
      System.out.println(Thread.currentThread().getName());
      System.out.println(Thread.currentThread().getState());
    }).start();

    System.out.println(thread0.getState());
    MainConcurrency mainConcurrency = new MainConcurrency();
    List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

    for (int i = 0; i < THREADS_NUMBER; i++) {
      Thread thread = new Thread(() -> {
        for (int j = 0; j < 100; j++) {
          mainConcurrency.increment();
        }
      });
      thread.start();
      threads.add(thread);
//    Thread.sleep(500);
      threads.forEach( t -> {
        try {
          t.join();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      });
    }
    System.out.println(counter);
  }

  private synchronized void increment() {
//    double a = Math.cos(13);
//    synchronized (LOCK) {
//    synchronized (this) {
//    synchronized (MainConcurrency.class) {
      counter++;
//    }
//    }
//    }
  }

}
