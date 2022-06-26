package com.mentoring.task5.util;

import com.mentoring.task5.model.ExchangeRate;
import com.mentoring.task5.model.UserAccount;
import com.mentoring.task5.service.BankService;
import java.math.BigDecimal;

public class OwnTransfer extends Thread {

  private final UserAccount acc;
  private BigDecimal amount;
  private final BankService bankService;
  private final ExchangeRate exchangeRate;

  public OwnTransfer(UserAccount acc, BigDecimal amount,
      BankService bankService, ExchangeRate exchangeRate) {
    super(String.format("Thread-from-%s-To-%s", exchangeRate.getFrom().toString(),
        exchangeRate.getTo().toString()));
    this.acc = acc;
    this.amount = amount;
    this.bankService = bankService;
    this.exchangeRate = exchangeRate;
  }

  @Override
  public void run() {
    synchronized (acc) {
      System.out.printf("%nUserAccount %s before transfer %s %s to %s: %s", acc.getName(), amount,
          exchangeRate.getFrom(), exchangeRate.getTo(), acc);
      bankService.withdraw(acc, exchangeRate.getFrom(), amount);
      amount = bankService.exchange(exchangeRate, amount);
      bankService.deposit(acc, exchangeRate.getTo(), amount);
      System.out.printf("%nUserAccount %s after transfer from %s got %s %s: %s%n", acc.getName(),
          exchangeRate.getFrom(), amount, exchangeRate.getTo(), acc);
    }
  }
}
