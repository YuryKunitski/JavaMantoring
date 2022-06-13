package com.mentoring.firsthw.service.impl;

import com.mentoring.firsthw.dto.BankCard;
import com.mentoring.firsthw.dto.Subscription;
import com.mentoring.firsthw.dto.User;
import com.mentoring.firsthw.service.api.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServiceImpl implements Service {

  private Map<User, List<BankCard>> userStorage = new ConcurrentHashMap<>();
  private List<Subscription> subscriptions = new ArrayList<>();

  @Override
  public void subscribe(BankCard bankCard) {
    var user = bankCard.getUser();
    userStorage.computeIfAbsent(user, card -> new ArrayList<>()).add(bankCard);
    subscriptions.add(new Subscription(bankCard.getNumber(), LocalDate.now()));
  }

  @Override
  public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
    return subscriptions.stream().filter(subs -> subs.getBankcard().equals(cardNumber)).findFirst();
  }

  @Override
  public List<User> getAllUsers() {
    return List.copyOf(userStorage.keySet());
  }

  @Override
  public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {
    return subscriptions.stream().filter(predicate).collect(Collectors.toUnmodifiableList());
  }
}
