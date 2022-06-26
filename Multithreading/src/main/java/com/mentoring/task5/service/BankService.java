package com.mentoring.task5.service;

import com.mentoring.task5.model.Currency;
import com.mentoring.task5.model.ExchangeRate;
import com.mentoring.task5.model.UserAccount;
import java.math.BigDecimal;
import java.util.Objects;

public class BankService {

  public void withdraw(UserAccount userAccount, Currency currency, BigDecimal amount) {
    BigDecimal balance = userAccount.getWallet().get(currency);
    if (Objects.nonNull(balance)) {
//      System.out.printf("%nUserAccount %s before withdraw %s operation: %s%n", userAccount.getName(), currency.toString(),
//          userAccount);

      BigDecimal subtractedBalance = balance.subtract(amount);
      userAccount.getWallet().put(currency, subtractedBalance);
//      System.out.printf("UserAccount %s after withdraw %s operation: %s%n", userAccount.getName(), currency.toString(),
//          userAccount);
    }
  }

  public void deposit(UserAccount userAccount, Currency currency, BigDecimal amount) {
//    System.out.printf("%nUserAccount %s before deposit %s operation: %s", userAccount.getName(), currency.toString(),
//        userAccount);
    BigDecimal balance = userAccount.getWallet().get(currency);
    BigDecimal depositedBalance = balance.add(amount);
    userAccount.getWallet().put(currency, depositedBalance);
//    System.out.printf("%nUserAccount %s after deposit %s operation: %s%n", userAccount.getName(),currency.toString(),
//        userAccount);
  }

  public BigDecimal exchange(ExchangeRate exchangeRate, BigDecimal amount) {
    return amount.multiply(BigDecimal.valueOf(exchangeRate.getRate()));
  }


}
