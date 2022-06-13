package com.mentoring.firsthw.service.api;

import com.mentoring.firsthw.dto.BankCard;
import com.mentoring.firsthw.dto.Subscription;
import com.mentoring.firsthw.dto.User;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

public interface Service {

  long AGE_OF_MAJORITY = 18;

  void subscribe(BankCard bankCard);

  Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

  List<User> getAllUsers();

  List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate);

  default double getAverageUsersAge() {
    List<User> users = getAllUsers();
    return users.stream()
        .mapToLong(user -> ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()))
        .average().orElseThrow();
  }

  static boolean isPayableUser(User user) {
    return ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()) > AGE_OF_MAJORITY;
  }

}
