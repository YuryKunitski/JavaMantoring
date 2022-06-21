package com.mentoring.task2;

import java.util.Iterator;
import java.util.List;

public class PrintSum {

  private List<Integer> list;

  public PrintSum(List<Integer> list) {
    this.list = list;
  }

  public void printSumList() {
    synchronized (list) {
      System.out.println("Start printSumList");
      int sum = 0;
      Iterator<Integer> it = list.iterator();
      while (it.hasNext()) {
        sum += it.next();
      }
      System.out.printf("Finish printSumList. Sum is: %d%n", sum);
    }
  }
}