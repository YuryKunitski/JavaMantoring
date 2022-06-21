package com.mentoring.task3;

public class MainTask3 {

  public static void main(String[] args) {
    MessageBus messageBus = new MessageBus();

    Producer producer = new Producer(messageBus);
    EvenConsumer evenConsumer = new EvenConsumer(messageBus);

    producer.start();
    evenConsumer.start();

  }

}
