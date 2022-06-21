package com.mentoring.task3;

import java.util.Random;

public class Producer extends Thread {

  private MessageBus messageBus;
  private static final int RANDOM_BOUND = 100;
  private static final int MESSAGES_COUNT = 200;

  public Producer(MessageBus messageBus) {
    super("PRODUCER");
    this.messageBus = messageBus;
  }

  @Override
  public void run() {
    for (int i = 0; i < MESSAGES_COUNT; i++) {
      int msg = new Random().nextInt(RANDOM_BOUND);
      messageBus.addMessage(msg);
      System.out.println(getName() + " produced message - " + msg);
    }
    messageBus.addMessage(Integer.MIN_VALUE);
  }


}
