package com.mentoring.task2;

import java.util.List;
import java.util.Random;

public class InfiniteAdd {

  private List<Integer> list;

  public InfiniteAdd(List<Integer> list) {
    this.list = list;
  }

  public void infiniteAddToList() {
    while (true) {
      synchronized (list) {
        list.add(new Random().nextInt());
//      System.out.println("Finish writingThread");
      }
    }
  }
}
