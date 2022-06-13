package com.mentoring.firsthw.main;

import com.mentoring.firsthw.bank.impl.BankImpl;
import com.mentoring.firsthw.dto.BankCardType;
import com.mentoring.firsthw.dto.CreditBankCard;
import com.mentoring.firsthw.dto.Subscription;
import com.mentoring.firsthw.dto.User;
import com.mentoring.firsthw.exception.SubscriptionCardTypeException;
import com.mentoring.firsthw.service.api.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.ServiceLoader;
import java.util.UUID;

public class Application {

  public static void main(String[] args) throws SubscriptionCardTypeException {
    var user1 = new User("John", "Depp", LocalDate.of(1999, 9, 9));
    var user2 = new User("Tom", "Hardi", LocalDate.of(2005, 8, 8));

    var bankImpl = new BankImpl();
    var creditBankCard = bankImpl.createBankCard(UUID.randomUUID().toString(), user1, CreditBankCard::new);
    var debitBankCard = bankImpl.createBankCard(user2, BankCardType.DEBIT);

    ServiceLoader<Service> serviceLoader = ServiceLoader.load(Service.class);
    Service service = serviceLoader.findFirst().orElseThrow(() -> new Error("Cannot get service."));

    System.out.printf("Subscribe user with name %s%n", creditBankCard.getUser().getName());
    service.subscribe(creditBankCard);
    System.out.printf("Subscribe user with name %s%n", debitBankCard.getUser().getName());
    service.subscribe(debitBankCard);

    var subscription = service.getSubscriptionByBankCardNumber(creditBankCard.getNumber())
        .orElseThrow(() -> new SubscriptionCardTypeException(
            String.format("Cannot find subscription by card number %s",
                creditBankCard.getNumber())));

    System.out.printf("Subscription: %s%n%n", subscription);

    List<User> users = service.getAllUsers();
    System.out.println("User list:");
    users.forEach(System.out::println);

    System.out.printf("%nAverage users age: %f%n%n", service.getAverageUsersAge());

    users.forEach(user -> System.out.printf("User with name %s is over 18 years old: %s%n", user.getName(), Service.isPayableUser(user)));

    List<Subscription> anniversarySubscriptions = service.getAllSubscriptionsByCondition(x -> x.getStartDate().getDayOfYear() == LocalDate.now()
        .getDayOfYear());
    System.out.println("\nSubscriptions that have an anniversary today:");
    anniversarySubscriptions.forEach(System.out::println);
  }
}
