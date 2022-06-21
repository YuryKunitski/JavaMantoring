package com.mentoring.task4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingObjectPool {

  public BlockingQueue<Object> objectPool;

  /**
   * Creates filled pool of passed size
   *
   * @param size of pool
   */
  public BlockingObjectPool(int size) {
    objectPool = new ArrayBlockingQueue<>(size);
  }

  /**
   * Gets object from pool or blocks if pool is empty
   *
   * @return object from pool
   */
  public Object get() {
    Object obj = null;
    try {
      obj = objectPool.take(); //waiting if necessary until an element becomes available.
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Get object " + obj + " from pool. Pool size = " + objectPool.size());
    return obj;
  }

  /**
   * Puts object to pool or blocks if pool is full
   *
   * @param object to be taken back to pool
   */
  public void take(Object object) {
    try {
      objectPool.put(object); //waiting if necessary for space to become available.
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Sent object " + object + " to pool. Pool size = " + objectPool.size());
  }

}
