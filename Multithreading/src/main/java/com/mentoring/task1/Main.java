package com.mentoring.task1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    Map<Integer, Integer> hashMap = new HashMap<>(); // --throw ConcurrentModificationException
    Map<Integer, Integer> synchronizedMap = Collections.synchronizedMap(hashMap); // --throw ConcurrentModificationException
    Map<Integer, Integer> concurrentHashmap = new ConcurrentHashMap<>(); // --don't throw ConcurrentModificationException
    Map<Integer, Integer> customSyncMap = new ThreadSafeMapSync<>(); // --don't throw ConcurrentModificationException
    Map<Integer, Integer> customLockMap = new ThreadSafeMapLock<>(); // --don't throw ConcurrentModificationException

    long startTime = System.currentTimeMillis();
//    System.out.println("throw ConcurrentModificationException");
//    workWithThreads(hashMap);
    long stopTime = System.currentTimeMillis();
//    System.out.printf("Working time hashMap: %d millisecond%n", stopTime - startTime);
//    System.out.println("==============================================================");

    startTime = System.currentTimeMillis();
    System.out.println("synchronizedMap throw ConcurrentModificationException");
    workWithThreads(synchronizedMap);
    stopTime = System.currentTimeMillis();
    System.out.printf("Working time synchronizedMap: %d millisecond%n", stopTime - startTime);
    System.out.println("==============================================================");

    startTime = System.currentTimeMillis();
    System.out.println("concurrentHashmap don't throw ConcurrentModificationException");
    workWithThreads(concurrentHashmap);
    stopTime = System.currentTimeMillis();
    System.out.printf("Working time concurrentHashmap: %d millisecond%n", stopTime - startTime);
    System.out.println("==============================================================");

    startTime = System.currentTimeMillis();
    System.out.println("customSyncMap don't throw ConcurrentModificationException");
    workWithThreads(customSyncMap);
    stopTime = System.currentTimeMillis();
    System.out.printf("Working time customSyncMap: %d millisecond%n", stopTime - startTime);
    System.out.println("==============================================================");

    startTime = System.currentTimeMillis();
    System.out.println("customLockMap don't throw ConcurrentModificationException");
    workWithThreads(customLockMap);
    stopTime = System.currentTimeMillis();
    System.out.printf("Working time customLockMap: %d millisecond%n", stopTime - startTime);
  }

  private static void workWithThreads(Map<Integer, Integer> map) throws InterruptedException {
    Runnable putThread = () -> {
      System.out.println("Starting putThread");
      for (int i = 0; i < 100000; i++) {
        map.put(i, i * 10);
      }
      System.out.println("Finish putThread");
    };

    Runnable sumThread = () -> {
      System.out.println("Starting sumThread");
      int sum = 0;
      Iterator<Entry<Integer, Integer>> it = map.entrySet().iterator();
      while (it.hasNext()) {
        Map.Entry<Integer, Integer> pair = it.next();
        sum += pair.getValue();
      }
      System.out.printf("Finish sumThread. Sum of map is: %d%n", sum);
    };

    Thread t1 = new Thread(putThread);
    Thread t2 = new Thread(sumThread);

    t1.start();
    t2.start();

    try{
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
