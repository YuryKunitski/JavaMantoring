package com.mentoring.task4;

public class MainTask4 {

  private static final int AMOUNT = 20;
  public static void main(String[] args) throws InterruptedException {
    BlockingObjectPool objectPool = new BlockingObjectPool(10);

    Thread threadTake = new Thread(() -> {
      for (int i = 0; i < AMOUNT; i++) {
        Object o = "OBJECT#" + i;
        objectPool.take(o);
      }
    });

    Thread threadGet = new Thread(() -> {
      for (int i = 0; i < AMOUNT; i++) {
        objectPool.get();
      }
    });

    threadGet.setName("GETTING-Thread");
    threadGet.start();
    threadTake.setName("TAKING-Thread");
    threadTake.start();


    threadTake.join();
    threadGet.join();

    System.out.println("\n" + "Final pool size = " + objectPool.objectPool.size());
  }

}
