package ru.javawebinar.basejava.util;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainConcurrency {

//  private static final Object LOCK = new Object();
//  private static final Lock lock = new ReentrantLock();
  private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
  private static final Lock READ_LOCK = reentrantReadWriteLock.readLock();
  private static final Lock WRITE_LOCK = reentrantReadWriteLock.writeLock();

  private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>() {
    @Override
    protected SimpleDateFormat initialValue() {
      return new SimpleDateFormat();
    }
  };
  private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

  public static final int THREADS_NUMBER = 10000;
  private static int counter = 0;
  private static final AtomicInteger atomicCounter = new AtomicInteger();

  final String resource1 = "1";
  final String resource2 = "2";

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
//    List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
//
//    for (int i = 0; i < THREADS_NUMBER; i++) {
//      Thread thread = new Thread(() -> {
//        for (int j = 0; j < 100; j++) {
//          mainConcurrency.increment();
//        }
//      });
//      thread.start();
//      threads.add(thread);
////    Thread.sleep(500);
//      threads.forEach(t -> {
//        try {
//          t.join();
//        } catch (InterruptedException e) {
//          throw new RuntimeException(e);
//        }
//      });
//    }
    CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
    ExecutorService executor = Executors.newCachedThreadPool();
    for (int i = 0; i < THREADS_NUMBER; i++) {
      executor.submit(() -> {
        for (int j = 0; j < 100; j++) {
          mainConcurrency.increment();
          System.out.println(threadLocal.get().format(new Date()));
//          System.out.println(dtf.format((TemporalAccessor) new Date()));
        }
        latch.countDown();
      });
    }
    latch.await(10, TimeUnit.SECONDS);
    executor.shutdown();
//    System.out.println(counter);
    System.out.println(atomicCounter.get());

//    Thread deadLock1 = new Thread(() -> {
//      synchronized (mainConcurrency.resource1) {
//        System.out.println(Thread.currentThread().getName() + " caught resource1");
//        try {
//          Thread.sleep(500);
//        } catch (InterruptedException e) {
//          throw new RuntimeException(e);
//        }
//        synchronized (mainConcurrency.resource2) {
//          System.out.println(Thread.currentThread().getName() + " caught resource2");
//        }
//      }
//    });
//
//    Thread deadLock2 = new Thread(() -> {
//      synchronized (mainConcurrency.resource2) {
//        System.out.println(Thread.currentThread().getName() + " caught resource2");
//        try {
//          Thread.sleep(500);
//        } catch (InterruptedException e) {
//          throw new RuntimeException(e);
//        }
//        synchronized (mainConcurrency.resource1) {
//          System.out.println(Thread.currentThread().getName() + " caught resource1");
//        }
//      }
//    });
//
//    deadLock1.start();
//    deadLock2.start();
  }

  private void increment() {
//    double a = Math.cos(13);
//    synchronized (LOCK) {
//    synchronized (this) {
//    synchronized (MainConcurrency.class) {
//    WRITE_LOCK.lock();
//    try {
    atomicCounter.incrementAndGet();
//      counter++;
//    } finally {
//      WRITE_LOCK.unlock();
//    }
//    }
//    }
//    }
  }
}
