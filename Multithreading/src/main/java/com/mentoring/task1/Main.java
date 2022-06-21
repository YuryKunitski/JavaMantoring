package com.mentoring.task1;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    long startTime = System.currentTimeMillis();
    workWithThreads();
    long stopTime = System.currentTimeMillis();
    System.out.printf("Working time: %d millisecond%n", stopTime - startTime);
  }

  static void workWithThreads() throws InterruptedException {
//    Map<Integer, Integer> simpleMap = new HashMap<>(); // --throw ConcurrentModificationException
//    Map<Integer, Integer> map = Collections.synchronizedMap(simpleMap); // --throw ConcurrentModificationException
//    Map<Integer, Integer> map = new ConcurrentHashMap<>(); // --don't throw ConcurrentModificationException
    Map<Integer, Integer> map = new ThreadSafeMapSync<>(); // --don't throw ConcurrentModificationException
//    Map<Integer, Integer> map = new ThreadSafeMapLock<>(); // --don't throw ConcurrentModificationException

    Runnable putThread = () -> {
      System.out.println("Starting putThread");
      for (int i = 0; i < 1000; i++) {
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

    t1.join();
    t2.join();
  }

}
