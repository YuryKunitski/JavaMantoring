package com.mentoring.task3;

public class EvenConsumer extends Thread {

  private final MessageBus messageBus;

  public EvenConsumer(MessageBus messageBus) {
    super("EVEN_CONSUMER");
    this.messageBus = messageBus;
  }

  @Override
  public void run() {
    Integer msg = 0;
    while (msg != Integer.MIN_VALUE) {
      msg = messageBus.getMessage();
      if (msg % 2 == 0 && msg != Integer.MIN_VALUE) {
        System.out.println(getName() + " consumed only even numbers. Message - " + msg);
      }
    }
  }

}
