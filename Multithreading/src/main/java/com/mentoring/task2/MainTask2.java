package com.mentoring.task2;

import java.util.ArrayList;
import java.util.List;

public class MainTask2 {

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();

    InfiniteAdd infiniteAdd = new InfiniteAdd(list);
    PrintSum printSum = new PrintSum(list);
    PrintSquireRoot printSquireRoot = new PrintSquireRoot(list);

    Runnable writingThread = infiniteAdd::infiniteAddToList;
    Runnable printingSumThread = printSum::printSumList;
    Runnable printingSquareRootThread = printSquireRoot::printSquireRoot;

    new Thread(writingThread).start();
    new Thread(printingSumThread).start();
    new Thread(printingSquareRootThread).start();
  }
}