package com.mentoring.task3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class MessageBus {

  private Queue<Integer> queue= new LinkedList<>();

  public Queue<Integer> getQueue() {
    return queue;
  }

  public synchronized void addMessage(Integer message) {
    if (queue.size() != 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        //ignore
      }
    }
    try {
      TimeUnit.MILLISECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    queue. add(message);
    notifyAll();
  }

  public synchronized Integer getMessage() {
    if (queue.size() == 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        //ignore
      }
    }
    try {
      TimeUnit.MILLISECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Integer m = queue.poll();
    notifyAll();
    return m;
  }
}
