package com.mentoring.task2;

import java.util.List;

public class PrintSquireRoot {

  private List<Integer> list;

  public PrintSquireRoot(List<Integer> list) {
    this.list = list;
  }

  public void printSquireRoot() {
    synchronized (list) {
    System.out.println("Start Square root of sum of squares");
        for (int i = 0; i < list.size(); i++) {
          int multiple = list.get(i) * list.get(i);
          list.set(i, multiple);
        }
        int sum = 0;
        for (Integer integer : list) {
          sum += integer;
        }
        System.out.println("Square root of sum of squares = " + Math.sqrt(sum));
    }
  }
}
